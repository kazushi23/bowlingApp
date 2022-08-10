package sg.bowling.bowlingapp.security;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.bowling.bowlingapp.exception.ResourceNotFoundException;
import sg.bowling.bowlingapp.model.Bowlingball;
import sg.bowling.bowlingapp.model.Usersaved;
import sg.bowling.bowlingapp.repository.BowlingBallRepository;
import sg.bowling.bowlingapp.repository.UserRepository;
import sg.bowling.bowlingapp.repository.UserSavedRepository;

@Service
public class UserSavedService {
	@Autowired
	private UserSavedRepository userSavedRepository;
	
	private final UserRepository userRepository;
	private final BowlingBallRepository bowlingBallRepository;
	
	@Autowired
	public UserSavedService(UserRepository userRepository, BowlingBallRepository bowlingBallRepository) {
		this.userRepository = userRepository;
		this.bowlingBallRepository = bowlingBallRepository;
	}
	
	@Transactional
	public void uploadUserSave(Long userId, int ballId ) {
		// TODO Auto-generated method stub
		Optional<Bowlingball> ball = bowlingBallRepository.findBowlingBallById(ballId);
		if (! ball.isPresent()) {
			throw new IllegalStateException(String.format("Bowling ball %s not found", ballId));
		}
		Usersaved usersaved = new Usersaved(userId, ballId);
		userSavedRepository.save(usersaved);
	}
	
	public List<Usersaved> getUserSaved(Long userId) {
		return userSavedRepository.findByUserId(userId);
	}
}