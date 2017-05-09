package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.entity.User;
import app.repositories.UserRepository;
import strategy.Main;

import java.util.List;

import javax.annotation.PostConstruct;

@Component
public class EntryPoint {

	@Autowired
	UserRepository rep;
	
	@PostConstruct
	public void run() throws Exception{
		System.out.println("Users:");
		for(User u: getAll()){
			System.out.println(u.getName());	
		}
		
		Main m = new Main();
		m.run();
		
	}
	
	//REPO UPDATES

	public void saveUser(User u, String room, int status){
		u.setRoom(room);
		u.setStatus(status);
		rep.save(u);
	}
	
	public List<User> getAll(){
		return rep.findAll();
	}
	
	public User findUser(String name){
		try {
			return rep.findByName(name).get(0);
		} catch (Exception e) {
			return null;
		}
	}
	
	public void createUser(User u, String in, String room, int status){	
		u.setName(in);
		u.setRoom(room);
		u.setStatus(status);
		rep.save(u);
		System.out.println("Added user to database");
	}
}
