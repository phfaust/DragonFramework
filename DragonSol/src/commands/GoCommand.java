package commands;

import java.util.HashMap;
import java.util.regex.Matcher;

import app.Context;
import app.Session;
import framework.CommandAnnotation;
import framework.CommandHandler;


//(?i) makes it insensitive
//\\s+ 1 or more white space
//(\\w+) 1 or more word


//RoomsWork now but the R in "Room" should be caps
@CommandAnnotation(regEx="(?i)go\\s+(\\w+)")
public class GoCommand implements CommandHandler {

	@Override
	public void process(Matcher matcher) throws Exception {
		String room = matcher.group(1);
		room = "R" + room.substring(1);
		System.out.println(room);
		
		Session s = Context.getSession();
		s.setCurrentRoom(room);
		
		HashMap<String, Object> map = Context.getRcm().processRoom(room, s.getGameState(), "checkRoom");
		System.out.println(map.get("message"));
	}

}