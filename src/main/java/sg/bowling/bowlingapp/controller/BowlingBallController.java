package sg.bowling.bowlingapp.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sg.bowling.bowlingapp.security.BowlingBallService;
import sg.bowling.bowlingapp.security.CurrentUser;
import sg.bowling.bowlingapp.security.UserPrincipal;
import sg.bowling.bowlingapp.security.UserSavedService;
import sg.bowling.bowlingapp.exception.ResourceNotFoundException;
import sg.bowling.bowlingapp.model.Bowlingball;
import sg.bowling.bowlingapp.model.User;
import sg.bowling.bowlingapp.model.Usersaved;
import sg.bowling.bowlingapp.repository.UserRepository;

@RestController
@RequestMapping(path="api/v1/bowlingball")

public class BowlingBallController {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserSavedService userSavedService;
    
	private final BowlingBallService bowlingBallService;
	
	@Autowired
	public BowlingBallController(BowlingBallService bowlingBallService) {
		this.bowlingBallService = bowlingBallService;
	}
	
	@GetMapping()
	public Map<String, Object> getBowlingBall(@RequestParam int page, @RequestParam int results) {
		
//		return bowlingBallService.getBowlingBall(page * 10, (page * 10) + results);
		return bowlingBallService.getBowlingBall(page, page + results);
	}
	
	@PostMapping(path="/filt")
	@PreAuthorize("hasRole('USER')")
	public void queryBowlingBall(@RequestBody Map<String, Object> query) {
		bowlingBallService.getBowlingBallByFilter(query);
	}
	
	@GetMapping(path="/useridball/{userid}")
	public List<Bowlingball> getBowlingBallByUserId(@PathVariable("userid") long userid) {
		
		return bowlingBallService.getBowlingBallByUserid(userid);
	}
	
	@GetMapping(path="/{ballId}")
	@PreAuthorize("hasRole('USER')")
	public Boolean bowlingBallSold(@PathVariable("ballId") int ballId, @CurrentUser UserPrincipal userPrincipal) {
		User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
		return bowlingBallService.setBowlingballSold(ballId, user.getId());
	}
	
	@PostMapping(
			path = "{ballId}/image/upload",
			consumes= MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@PreAuthorize("hasRole('USER')")
	public void uploadBallImage(@PathVariable("ballId") int id, @RequestParam("file") MultipartFile[] file) {
		bowlingBallService.uploadBallImage(id, file);
	}
	
	@GetMapping("{ballId}/image")
	public String getBallImageLink(@PathVariable("ballId") int id) {
		return bowlingBallService.getBallImageLink(id);
	}
	
	@GetMapping("{ballId}/image/download/{ballImgLink}")
	public byte[] downloadBallImageFromLink(@PathVariable("ballId") int id, @PathVariable("ballImgLink") String link) {
		return bowlingBallService.downloadBallImageFromLink(id, link.strip());
	}
	
//	@GetMapping("{ballId}/image/download")
//	public List<byte[]> downloadBallImage(@PathVariable("ballId") int id) {
//		return bowlingBallService.downloadBallImage(id);
//	}
	
	@PostMapping("/newBall")
	@PreAuthorize("hasRole('USER')")
	public int addNewBall(@RequestBody Map<String, Object> newBall, @CurrentUser UserPrincipal userPrincipal) {
		User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

		int id = bowlingBallService.uploadNewBall(newBall, user.getId());
		return id;

	}
	
	@GetMapping("/savedBalls")
	@PreAuthorize("hasRole('USER')")
	public List<Bowlingball> getSavedBowlingballs(@CurrentUser UserPrincipal userPrincipal) {
		User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
		
		List<Usersaved> userSaved = userSavedService.getUserSaved(user.getId());
		
		return bowlingBallService.getSavedBowlingBalls(userSaved);

	}
}
