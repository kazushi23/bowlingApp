package sg.bowling.bowlingapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.bowling.bowlingapp.model.Usersaved;

public interface UserSavedRepository extends JpaRepository<Usersaved, Long> {

	List<Usersaved> findByUserId(Long userId);


}
