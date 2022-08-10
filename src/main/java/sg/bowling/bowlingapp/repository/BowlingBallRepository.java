package sg.bowling.bowlingapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import sg.bowling.bowlingapp.model.Bowlingball;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface BowlingBallRepository extends JpaRepository<Bowlingball, Long> {
	Optional<Bowlingball> findBowlingBallById(int id);
	
	List<Bowlingball> findByUserid(Long userid);
	
	List<Bowlingball> findByIdGreaterThanAndIdLessThanEqual(int start, int end);

	List<Bowlingball> findByIdIn(List<Integer> ballIds);
	
	List<Bowlingball> findWithFilter();
}

