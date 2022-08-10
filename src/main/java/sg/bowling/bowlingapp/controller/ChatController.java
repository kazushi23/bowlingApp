package sg.bowling.bowlingapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import sg.bowling.bowlingapp.model.Chat;
import sg.bowling.bowlingapp.model.Message;
import sg.bowling.bowlingapp.security.ChatService;
import sg.bowling.bowlingapp.security.CurrentUser;
import sg.bowling.bowlingapp.security.MessageService;
import sg.bowling.bowlingapp.security.UserPrincipal;

@Controller
@RestController
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private ChatService chatService;
    
    @Autowired
    public ChatController(MessageService messageService, ChatService chatService) {
    	this.messageService = messageService;
    	this.chatService = chatService;
    }

//    @MessageMapping("/message")
//    @SendTo("/chatroom/public")
//    public Message receiveMessage(@Payload Message message){
//        return message;
//    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverEmail(),"/private",message);
        System.out.println(message.toString());
        chatService.saveChat(message.getItemId(), message.getSenderEmail(), message.getReceiverEmail());
        messageService.saveMessage(message);
        return message;
    }

    @GetMapping(path="/mychats")
    public List<Optional<Chat>> getMyChats(@CurrentUser UserPrincipal userPrincipal) {
    	return chatService.getChatsByEmail(userPrincipal.getEmail());
    }
    
    @GetMapping(path="/mymessage/{receiverEmail}/{itemId}")
    public List<Optional<Message>> getMyMessages(@PathVariable("receiverEmail") String receiverEmail, @PathVariable("itemId") int itemId, @CurrentUser UserPrincipal userPrincipal) {
    	return messageService.getMessageChat(itemId ,receiverEmail, userPrincipal.getEmail());
    }
    
}
