package sg.bowling.bowlingapp.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Table;

@Entity
@Table(appliesTo = "message")
public class Message {
	@Id
	@SequenceGenerator(
			name= "message_sequence",
			sequenceName="message_sequence",
			allocationSize=1
	)
	@GeneratedValue(
			strategy= GenerationType.SEQUENCE,
			generator= "message_sequence"
	)
	private int id;
	private int itemId;
	private Status status;
	private String senderEmail;
	private String receiverEmail;
	private String message;
	private LocalDateTime dateTime=LocalDateTime.now();;
	
	public enum Status {
		JOIN,
		MESSAGE,
		LEAVE
	}
	
	public Message() {
		
	}
	
	public Message(int itemId, Status status, String senderEmail, String receiverEmail, String message) {
		this.itemId = itemId;
		this.status = status;
		this.senderEmail = senderEmail;
		this.receiverEmail = receiverEmail;
		this.message = message;
		this.dateTime = LocalDateTime.now();
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Status getStatus() {
		return status;
	}
	
    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }
    
    public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverId(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}
    
    public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	
	@Override
	public String toString() {
		return "Message [id=" + id + ", status=" + status + ", senderName=" + senderEmail + ", receiverName="
				+ receiverEmail + ", message=" + message + ", dateTime=" + dateTime + "]";
	}
}
