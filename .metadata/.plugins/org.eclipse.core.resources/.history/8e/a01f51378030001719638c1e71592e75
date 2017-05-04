package commands;

import java.util.HashMap;

import app.Context;
import app.Session;
import framework.DragonFramework;

@CommandAnnotation(target=GoCommand.class)
public class GoCommand implements CommandHandler {

	@Override
	public void process(DragonFramework df) throws Exception {
	//	String room = 
		
		Session s = Context.getSession();
		s.setCurrentRoom(room);
		
		HashMap<String, Object> map = Context.getRcm().processRoom(room, s.getGameState(), "checkRoom");
		System.out.println(map.get("message"));
	}

}
