package commands;

import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.ApplicationContextHolder;
import app.Context;
import app.Session;
import app.entity.User;
import app.repositories.UserRepository;
import framework.CommandAnnotation;
import framework.CommandHandler;

@Component
@CommandAnnotation(regEx="(?i)register\\s+(\\w+)")
public class RegisterCommand implements CommandHandler {
	
	@Autowired
	UserRepository rep = ApplicationContextHolder.getContext().getBean(UserRepository.class);
	
	@Override
	public void process(Matcher matcher) throws Exception {

		Session s = Context.getSession();	
		String name = matcher.group(1);
		System.out.println(name);
		
		try {
			User u = findUser(name);
			s.setCurrentRoom(u.getRoom());
		} catch (NullPointerException ne){
			User u = new User();
			createUser(u, name, "Room1", 0);
			System.out.println("Successfully created user " + name);	
			
		}
		
		s.setUser(name);
		System.out.println("Successfully loaded user " + name);	

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
