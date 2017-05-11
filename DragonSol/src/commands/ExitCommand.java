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
@CommandAnnotation(regEx="(?i)(exit)")
public class ExitCommand implements CommandHandler {
	
	@Override
	public void process(Matcher matcher) throws Exception {

		System.out.println("Game Ended!");
		System.exit(0);
		
			
	}
	

}
