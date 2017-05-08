package commands;

import java.util.HashMap;
import java.util.regex.Matcher;

import app.Context;
import app.Session;
import framework.CommandAnnotation;
import framework.CommandHandler;

@CommandAnnotation(regEx="(?i)(start)")
public class StartCommand implements CommandHandler {

	@Override
	public void process(Matcher matcher) throws Exception {


		Session s = Context.getSession();
		
		try {
			if(s.getUser().equals("")) throw new RuntimeException();
			s.setCurrentRoom("Room1");
			
			HashMap<String, Object> map = Context.getRcm().processRoom("Room1", s.getGameState(), "checkRoom");
			System.out.println(map.get("message"));
			
		}
		catch (Exception e){
			System.out.println("Register user first");
		}
	}

}