package commands;

import java.util.HashMap;
import java.util.regex.Matcher;

import app.Context;
import app.Session;
import framework.CommandHandler;

public class StartCommand implements CommandHandler {

	@Override
	public void process(Matcher matcher) throws Exception {

		//check if there is user

		Session s = Context.getSession();
		s.setCurrentRoom("Room1");
		
		HashMap<String, Object> map = Context.getRcm().processRoom("Room1", s.getGameState(), "checkRoom");
		System.out.println(map.get("message"));
	}

}