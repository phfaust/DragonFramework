package commands;

import java.util.HashMap;
import java.util.regex.Matcher;

import app.Context;
import framework.CommandAnnotation;
import framework.CommandHandler;
import mvc.Session;


@CommandAnnotation(regEx="(?i)rcm\\s+(\\w+)")
public class CommandAction implements CommandHandler {

	@Override
	public void process(Matcher matcher) throws Exception {
		String action = matcher.group(1);
		action = action.toLowerCase();
		
		if(action.equals("takesword")){
			action = "takeSword";
		}
		
		Session s = Context.getSession();
		
		try{
			HashMap<String, Object> map = Context.getRcm().processRoom(s.getCurrentRoom(), s.getGameState(), action);
			System.out.println(map.get("message"));
			s.setGameState((int) map.get("status"));
			
			
		} catch (Exception e){
			System.out.println("Command Action: Invalid Commmand.");
		}
		
	
	}

}
