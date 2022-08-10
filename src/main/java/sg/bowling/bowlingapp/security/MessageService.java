package sg.bowling.bowlingapp.security;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.bowling.bowlingapp.model.Message;
import sg.bowling.bowlingapp.repository.MessageRepository;

@Service
public class MessageService {
	private MessageRepository messageRepository;
	
	@Autowired
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	public List<Optional<Message>> getMessageChat(int itemId, String receiverEmail, String myEmail) {
		List<Optional<Message>> messages = messageRepository.findAllByMessageChat(itemId, receiverEmail, myEmail);
		System.out.println("MessageService " + messages);
		return messages;
	}
	
	@Transactional
	public void saveMessage(Message message) {
		// TODO Auto-generated method stub
		messageRepository.save(message);
	}
}
