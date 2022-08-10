package sg.bowling.bowlingapp.security;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.bowling.bowlingapp.model.Chat;
import sg.bowling.bowlingapp.repository.ChatRepository;

@Service
public class ChatService {
	private ChatRepository chatRepository;
	
	@Autowired
	public ChatService(ChatRepository chatRepository) {
		this.chatRepository = chatRepository;
	}
	@Transactional
	public void saveChat(int itemId, String senderEmail, String receiverEmail) {
		// TODO Auto-generated method stub
		Optional<Chat> c = chatRepository.findExistingChat(itemId, senderEmail, receiverEmail);
		if (c.isEmpty()) {
			Chat chat = new Chat(itemId, senderEmail, receiverEmail);
			chatRepository.save(chat);
		}
	}
	public List<Optional<Chat>> getChatsByEmail(String myEmail) {
		// TODO Auto-generated method stub
		return chatRepository.findAllByParty1OrParty2(myEmail, myEmail);
	}
	
	
	
}
