package sg.bowling.bowlingapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import sg.bowling.bowlingapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
	@Query("SELECT m FROM Message m WHERE itemId = ?1 AND (m.receiverEmail = ?2 OR m.receiverEmail = ?3) AND (m.senderEmail = ?2 OR m.senderEmail = ?3)")
	List<Optional<Message>> findAllByMessageChat(int itemId, String receiverEmail, String myEmail);
}
