package commands;

import java.util.HashMap;
import java.util.regex.Matcher;

import app.Context;
import app.Session;
import framework.CommandAnnotation;
import framework.CommandHandler;


@CommandAnnotation(regEx = "(?i)(hint)")
public class HintCommand implements CommandHandler {

	@Override
	public void process(Matcher matcher) throws Exception {
		// TODO Auto-generated method stub
		
		String hint = matcher.group(1);
		Session s = Context.getSession();
		String room = s.getCurrentRoom();
		String action = "look";
		
		if(room.equals("Room1")){
			action = "checkRoom";
		} 
		
		HashMap<String, Object> map = Context.getRcm().processRoom(room, s.getGameState(), action);
		System.out.println(map.get("message"));
	}
	
	
	
	
}
