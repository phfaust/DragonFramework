package commands;

import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Autowired;

import app.ApplicationContextHolder;
import app.Context;
import app.entity.User;
import app.repositories.UserRepository;
import framework.CommandAnnotation;
import framework.CommandHandler;
import mvc.Session;


//(?i) makes it insensitive
//\\s+ 1 or more white space
//(\\w+) 1 or more word


//RoomsWork now but the R in "Room" should be caps
@CommandAnnotation(regEx="(?i)rcm\\s(?i)(save|exit)")
public class SaveOrExitCommand implements CommandHandler {

	@Autowired
	UserRepository rep = ApplicationContextHolder.getContext().getBean(UserRepository.class);
	
	@Override
	public void process(Matcher matcher) throws Exception {	
		String command = matcher.group(1);
	
		Session s = Context.getSession();
		User u = getUser(s.getUser());
		saveUser(u, s.getCurrentRoom(), s.getGameState());
		System.out.println("Saved current state to database.");
		
		if(command.equalsIgnoreCase("exit")){
			System.out.println("Game Ended!");
			System.exit(0);
		}
	}
	
	public User getUser(String name){
		try {
			return rep.findByName(name).get(0);
		} catch (Exception e) {
			return null;
		}
	}
	public void saveUser(User u, String room, int status){
		u.setRoom(room);
		u.setStatus(status);
		rep.save(u);
	}

}