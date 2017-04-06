package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.entity.User;
import app.repositories.UserRepository;

import java.util.List;

import javax.annotation.PostConstruct;

@Component
public class EntryPoint {

	@Autowired
	UserRepository rep;
	
	@PostConstruct
	public void run() throws Exception{
		Main m = new Main(this);
		m.run();	
	}
	
	//REPO UPDATES
	public void createUser(User u, String in, String room, int status){	
		u.setName(in);
		u.setRoom(room);
		u.setStatus(status);
		rep.save(u);
		System.out.println("Added user to database");
	}
	
	public void saveUser(User u, String room, int status){
		u.setRoom(room);
		u.setStatus(status);
		rep.save(u);
	}
	
	public User findUser(String name){
		try {
			return rep.findByName(name).get(0);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<User> getAll(){
		return rep.findAll();
	}	
}
