package sg.bowling.bowlingapp.security;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sg.bowling.bowlingapp.repository.BowlingBallRepository;
import sg.bowling.bowlingapp.repository.UserRepository;
import sg.bowling.bowlingapp.model.Bowlingball;
import sg.bowling.bowlingapp.model.User;
import sg.bowling.bowlingapp.model.Usersaved;
import sg.bowling.bowlingapp.bucket.BucketName;
import sg.bowling.bowlingapp.filestore.FileStore;

@Service
public class BowlingBallService {
    @Autowired
    private UserRepository userRepository;
    
	private final BowlingBallRepository bowlingBallRepository;
	private final FileStore fileStore;
	
	@Autowired
	public BowlingBallService(BowlingBallRepository bowlingBallRepository, FileStore fileStore) {
		this.bowlingBallRepository = bowlingBallRepository;
		this.fileStore = fileStore;
	}
	
	public Bowlingball getBowlingBallById (Long id) {
		Bowlingball bowlingBall = bowlingBallRepository.findById(id).orElseThrow(
				() -> new IllegalStateException(
						"Bowling Ball with id: " + id + " does not exist"
						)
				);
		return bowlingBall;
	}
	
	public void getBowlingBallByFilter(Map<String, Object> query) {
		System.out.println(query);
	}

	public List<Bowlingball> getBowlingBallByUserid(long userid) {
		// TODO Auto-generated method stub
		return bowlingBallRepository.findByUserid(userid);
	}
	
	public Map<String, Object> getBowlingBall(int start, int end) {
		ArrayList<Long> ids = new ArrayList<Long>();
		Map<String, Object> toReturn = new HashMap<>();
		List<Bowlingball> balls = bowlingBallRepository.findByIdGreaterThanAndIdLessThanEqual(start, end);
		
		 for (Bowlingball ball: balls) {
			if (! ids.contains(ball.getUserid())) {
				ids.add(ball.getUserid());
			}
		}
		 System.out.println(ids);
		// TODO Auto-generated method stub
		toReturn.put("users", userRepository.findAllById(ids));
		toReturn.put("balls", balls);
		
		return toReturn;
	}
	
	@Transactional
	public void uploadBallImage(int id, MultipartFile[] file) {
		// TODO Auto-generated method stub
		// 1. check if image is not empty
		Optional<Bowlingball> ball = bowlingBallRepository.findBowlingBallById(id);
		if (! ball.isPresent()) {
			throw new IllegalStateException(String.format("Bowling ball %s not found", id));
		}
		
		for (MultipartFile mf : file) {
			if (mf.isEmpty()) {
				throw new IllegalStateException("Cannot upload empty file");
			}
			// 2. if file is an image
			if (! Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(), ContentType.IMAGE_PNG.getMimeType(), ContentType.IMAGE_GIF.getMimeType()).contains(mf.getContentType())) {
				throw new IllegalStateException("File must be an image - " + mf.getContentType());
			}
			// 3. check whether user exist in our database
		// 4. if so, grab some metadata from file if any
			String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), ball.get().getId());
			String filename = String.format("%s-%s", mf.getOriginalFilename(), LocalDateTime.now().toString());
			try {
				fileStore.save(path, filename, mf.getContentType(), mf.getSize(), mf.getInputStream());
				String p = ball.get().getBallImageLink();

				if (p.equals("null")) {
					String[] filenames = {filename};
					ball.get().setBallImageLink(Arrays.toString(filenames));
				} else {
					p = p.substring(1, p.length()-1);
					List<String> pArray = new ArrayList<> (Arrays.asList(p.split(",")));
					pArray.add(filename);
					ball.get().setBallImageLink(pArray.toString());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new IllegalStateException(e);
			}
		};
		// 5. store the image in s3 and update the database with s3 image link
	}
	
	public String getBallImageLink(int id) {
		Optional<Bowlingball> ball = bowlingBallRepository.findBowlingBallById(id);
		if (! ball.isPresent()) {
			throw new IllegalStateException(String.format("Bowling ball %s not found", id));
		}
		return ball.get().getBallImageLink();
	}
	
	public List<byte[]> downloadBallImage(int id) {
		// TODO Auto-generated method stub
		Optional<Bowlingball> ball = bowlingBallRepository.findBowlingBallById(id);
		if (! ball.isPresent()) {
			throw new IllegalStateException(String.format("Bowling ball %s not found", id));
		}
		
		String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), ball.get().getId());
		
		String links = ball.get().getBallImageLink();
		links = links.substring(1, links.length()-1);
		
		String[] linksArr = links.split(",");
		
		List<byte[]> images = new ArrayList<>();
		for (String s : linksArr) {
			images.add(fileStore.download(path, s.strip()));
			System.out.println(s.strip());
		}
		return images;
	}
	
	@Transactional
	public int uploadNewBall(Map<String, Object> newBall, Long userid) {
		String date = newBall.get("releaseDate").toString();
		
		
		Bowlingball bowlingBall = new Bowlingball(
				newBall.get("ballName").toString(),
				LocalDate.parse(date),
				newBall.get("coverstock").toString(),
				newBall.get("type").toString(),
				Integer.parseInt(newBall.get("grit").toString()),
				Boolean.parseBoolean(newBall.get("symmetrical").toString()),
				Double.parseDouble(newBall.get("weight").toString()),
				Double.parseDouble(newBall.get("rg").toString()),
				Double.parseDouble(newBall.get("diff").toString()),
				"null",
				Double.parseDouble(newBall.get("price").toString()),
				false,
				newBall.get("gamesUsed").toString(),
				newBall.get("description").toString(),
				newBall.get("brand").toString(),
				Integer.parseInt(newBall.get("drilled").toString()),
				newBall.get("drilledHand").toString(),
				Boolean.parseBoolean(newBall.get("cracked").toString()),
				userid
				);
		int id = bowlingBallRepository.save(bowlingBall).getId();
		return id;
	}

	public byte[] downloadBallImageFromLink(int id, String link) {
		Optional<Bowlingball> ball = bowlingBallRepository.findBowlingBallById(id);
		if (! ball.isPresent()) {
			throw new IllegalStateException(String.format("Bowling ball %s not found", id));
		}
		
		String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), ball.get().getId());
		
		return fileStore.download(path, link);
	}
	
	@Transactional
	public Boolean setBowlingballSold(int ballId, Long userId) {
		// TODO Auto-generated method stub
		Optional<Bowlingball> ball = bowlingBallRepository.findBowlingBallById(ballId);
		if (! ball.isPresent()) {
			throw new IllegalStateException(String.format("Bowling ball %s not found", ballId));
		}
		
		if (ball.get().getUserid() != userId) {
			throw new IllegalStateException(String.format("Bowling ball %s dont belong to user %s", ballId, userId));
		}
		
		ball.get().setSold(true);
		return true;
	}

	public List<Bowlingball> getSavedBowlingBalls (List<Usersaved> userSaved) {
		List<Integer> ballIds = new ArrayList<>();
		
		for (Usersaved us : userSaved) {
			ballIds.add(us.getBallId());
		}
		
		return bowlingBallRepository.findByIdIn(ballIds);
	}
	


}
