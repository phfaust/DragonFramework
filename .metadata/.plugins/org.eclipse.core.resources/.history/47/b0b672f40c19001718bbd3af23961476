package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import methodvalidator.annotations.StringLength;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Long id;
	
	@Column
	private String name;
		
	@Column	
	private int IN_POOL; 
	
	@Column	
	private int GRAVE_FOUND;
	
	@Column	
	private int TOOK_SWORD;
	
	@Column	
	private int BABY_DEAD;
	
	@Column	
	private int CHEST_FOUND;
	
	@Column	
	private int WORD_FOUND_2;
	
	@Column	
	private int WORD_FOUND_3;
	
	@Column	
	private int WORD_FOUND_4;
	
	@Column	
	private int DEAD;

	
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	@StringLength
	public void setName(String name) {
		this.name = name;
	}
	
	public int getIN_POOL() {
		return IN_POOL;
	}
	public void setIN_POOL(int i) {
		this.IN_POOL = i;
	}
	
	public int getGRAVE_FOUND() {
		return GRAVE_FOUND;
	}
	public void setGRAVE_FOUND(int i) {
		this.GRAVE_FOUND = i;
	}
	
	public int getTOOK_SWORD() {
		return TOOK_SWORD;
	}
	public void setTOOK_SWORD(int i) {
		this.TOOK_SWORD = i;
	}
	
	public int getBABY_DEAD() {
		return BABY_DEAD;
	}
	public void setBABY_DEAD(int i) {
		this.BABY_DEAD = i;
	}
	
	public int getCHEST_FOUND() {
		return CHEST_FOUND;
	}
	public void setCHEST_FOUND(int i) {
		this.CHEST_FOUND = i;
	}
	
	public int getWORD_FOUND_2() {
		return WORD_FOUND_2;
	}
	public void setWORD_FOUND_2(int i) {
		this.WORD_FOUND_2 = i;
	}
	
	public int getWORD_FOUND_3() {
		return WORD_FOUND_3;
	}
	public void setWORD_FOUND_3(int i) {
		this.WORD_FOUND_3 = i;
	}
	
	public int getWORD_FOUND_4() {
		return WORD_FOUND_4;
	}
	public void setWORD_FOUND_4(int i) {
		this.WORD_FOUND_4 = i;
	}
	
	public int getDEAD() {
		return DEAD;
	}
	public void setDEAD(int i) {
		this.DEAD = i;
	}
	
	
	
}
