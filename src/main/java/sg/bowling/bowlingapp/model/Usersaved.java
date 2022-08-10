package sg.bowling.bowlingapp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="usersaved")
@IdClass(Usersaved.class)
public class Usersaved implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long userId;
	@Id
	private int ballId;
	
	public Usersaved() {
		
	}
	
	public Usersaved(Long userId, int ballId) {
		this.userId = userId;
		this.ballId = ballId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public int getBallId() {
		return ballId;
	}
	
	public void setBallId(int ballId) {
		this.ballId = ballId;
	}
	
	@Override
	public String toString() {
		return "Usersaved [userId=" + userId + ", ballId=" + ballId + "]";
	}
	
}
