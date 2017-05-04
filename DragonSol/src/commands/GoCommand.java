package commands;

import java.util.HashMap;
import java.util.regex.Matcher;

import app.Context;
import app.Session;

@CommandAnnotation(target=GoCommand.class)
public class GoCommand implements CommandHandler {

	@Override
	public void process(Matcher matcher) throws Exception {
		String room = matcher.group(1); 
		
		Session s = Context.getSession();
		s.setCurrentRoom(room);
		
		HashMap<String, Object> map = Context.getRcm().processRoom(room, s.getGameState(), "checkRoom");
		System.out.println(map.get("message"));
	}

}
