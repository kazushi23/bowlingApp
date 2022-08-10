package sg.bowling.bowlingapp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="chat")
@IdClass(Chat.class)
public class Chat implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int itemId;
	@Id
	private String party1;
	@Id
	private String party2;
	
	public Chat() {
		
	}
	
	public Chat(int itemId, String party1, String party2) {
		this.itemId = itemId;
		this.party1 = party1;
		this.party2 = party2;
	}
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getParty1() {
		return party1;
	}
	public void setParty1(String party1) {
		this.party1 = party1;
	}
	public String getParty2() {
		return party2;
	}
	public void setParty2(String party2) {
		this.party2 = party2;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Chat [itemId=" + itemId + ", party1=" + party1 + ", party2=" + party2 + "]";
	}
	
	
}