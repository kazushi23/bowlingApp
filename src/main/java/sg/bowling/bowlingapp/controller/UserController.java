package sg.bowling.bowlingapp.controller;

import sg.bowling.bowlingapp.exception.ResourceNotFoundException;
import sg.bowling.bowlingapp.model.User;
import sg.bowling.bowlingapp.repository.UserRepository;
import sg.bowling.bowlingapp.security.BowlingBallService;
import sg.bowling.bowlingapp.security.CurrentUser;
import sg.bowling.bowlingapp.security.UserPrincipal;
import sg.bowling.bowlingapp.security.UserSavedService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
	
    @Autowired
    private UserRepository userRepository;
    
    private final UserSavedService userSavedService;
    
	@Autowired
	public UserController(UserSavedService userSavedService) {
		this.userSavedService = userSavedService;
	}

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
    
    @GetMapping("/save/{ballId}")
    @PreAuthorize("hasRole('USER')")
    public void userSaveBall(@PathVariable("ballId") int ballId, @CurrentUser UserPrincipal userPrincipal) {
    	Optional<User> user = userRepository.findById(userPrincipal.getId());
		if (! user.isPresent()) {
			throw new IllegalStateException(String.format("User id %s not found", userPrincipal.getId()));
		}
    	userSavedService.uploadUserSave(userPrincipal.getId(), ballId);
    }
    
//    @GetMapping("/save/get")
//    @PreAuthorize("hasRole('USER')")
//    public void getUserSaveBall(@CurrentUser UserPrincipal userPrincipal) {
//    	Optional<User> user = userRepository.findById(userPrincipal.getId());
//		if (! user.isPresent()) {
//			throw new IllegalStateException(String.format("User id %s not found", userPrincipal.getId()));
//		}
//    	userSavedService.getUserSaved(user.get().getId());
//    }
}
