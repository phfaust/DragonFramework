package commands;

import java.util.HashMap;
import java.util.regex.Matcher;

import app.Context;
import app.Session;
import framework.CommandAnnotation;
import framework.CommandHandler;


@CommandAnnotation(regEx="(?i)rcm\\s+(\\w+)")
public class CommandAction implements CommandHandler {

	@Override
	public void process(Matcher matcher) throws Exception {
		String action = matcher.group(1);
		System.out.println(action);
		
		Session s = Context.getSession();
		
		HashMap<String, Object> map = Context.getRcm().processRoom(s.getCurrentRoom(), s.getGameState(), action);
		System.out.println(map.get("message"));
	}

}
