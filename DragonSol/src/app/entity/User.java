package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Long id;
	
	
	@Column
	private String name;
	@Column	
	private String room; 
	@Column	
	private int status;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getRoom() {
		return room;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int i) {
		this.status = i;
	}
	
}