package sg.bowling.bowlingapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sg.bowling.bowlingapp.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
	@Query("SELECT c FROM Chat c WHERE c.itemId = ?1 AND (c.party1 = ?2 OR c.party1 = ?3) AND (c.party2 = ?2 OR c.party2 = ?3)")
	Optional<Chat> findExistingChat(int itemId, String senderEmail, String receiverEmail);

	List<Optional<Chat>> findAllByParty1OrParty2(String myEmail1, String myEmail2);
}
