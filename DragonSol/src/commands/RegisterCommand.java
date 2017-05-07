package commands;

import java.util.regex.Matcher;

import app.Context;
import app.Session;
import framework.CommandHandler;

public class RegisterCommand implements CommandHandler {

	@Override
	public void process(Matcher matcher) throws Exception {
		
		//check if game running
		
		String name = matcher.group(1);
		System.out.println(name);
		
		Session s = Context.getSession();
		s.setUser(name);
		
		//add user to database
		
		System.out.println("Successfully registered user " + name);
		

	}
}