package commands;

import java.util.HashMap;
import java.util.regex.Matcher;

import app.Context;
import framework.CommandAnnotation;
import framework.CommandHandler;
import mvc.Session;


@CommandAnnotation(regEx="(?i)rcm\\s+(\\w+)\\s(\\w+)")
public class CommandParamAction implements CommandHandler {

	@Override
	public void process(Matcher matcher) throws Exception {
		String action = matcher.group(1);
		action = action.toLowerCase();
		
		String param = matcher.group(2);
		System.out.println(action);
		
		Session s = Context.getSession();
		
		HashMap<String, Object> map = Context.getRcm().processRoom(s.getCurrentRoom(), s.getGameState(), action + " " + param);
		System.out.println(map.get("message"));
		s.setGameState((int) map.get("status"));
	}

}