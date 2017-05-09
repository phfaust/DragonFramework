package commands;

import java.util.HashMap;
import java.util.regex.Matcher;

import app.Context;
import app.Session;
import framework.CommandAnnotation;
import framework.CommandHandler;
import state.StartState;
import state.StateContext;

@CommandAnnotation(regEx="(?i)(start)")
public class StartCommand implements CommandHandler {

	@Override
	public void process(Matcher matcher) throws Exception {

		StateContext state = Context.getState();
		StartState start = new StartState();
		start.doAction(state);
		
		Session s = Context.getSession();
		
		try {
			if(s.getUser().equals("")) throw new RuntimeException();
			
			HashMap<String, Object> map = Context.getRcm().processRoom(s.getCurrentRoom(), s.getGameState(), "checkRoom");
			System.out.println(map.get("message"));
			
		}
		catch (Exception e){
			System.out.println("Register user first");
		}
	}

}