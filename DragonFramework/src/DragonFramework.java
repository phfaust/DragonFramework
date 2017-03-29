import room.*;
import java.lang.reflect.*;
//This is where the we make our framework

public class DragonFramework {
	RoomCommandManager rcm;
	SessionHandler s;
	static String currentRoom = "";
	static String command = "checkRoom";
	
	public DragonFramework(){
		rcm = new RoomCommandManager();
		s = new SessionHandler();
		
	}
	public boolean in(String input) throws Exception{
		RoomCommandValidator v = new RoomCommandValidator();
		Class<?> session = SessionHandler.class;

		//PARSE INPUT VIA REGEX HERE
		s.setCommand(input);

	  	try {
//	  		gameState = gs.getState();
	  		s.gameProgress = s.getGs().getState();
	  	} catch (NullPointerException e){
//	  		gameState = 0;
	  		s.gameProgress = 0;
	  	}

	  	Field field = session.getDeclaredField("command");
		field.setAccessible(true);

		try {
			//VALIDATION
			
//			login and start should happen here
			
			
			
//			This is where the command annotation check happens
			v.validate(s, (String) field.get(s), session.getMethod("setCommand", String.class), this);

			//COMMAND MANAGER
			String[] ins = input.split(" ");

	    	if(ins[0].equals("Go")) {
	    		command = "checkRoom";
	    		currentRoom = ins[1];
	    	} else {
	    		command = "";
	    		for (String i : ins) command += i + " ";
	  		}

	  		//PROCESSING || INSTANTIATION
	  		try {
	  			System.out.println(rcm.processRoom(currentRoom, s.getGs().getState(), command) + "\n");
	  		} 
	  		catch (NullPointerException ne) {
	  			System.out.println(rcm.processRoom(currentRoom, null, command) + "\n");
	  		}
	    	
		} catch (RuntimeException e){
			//nothing 
		}

		//CHECK IF DAD TO END GAME

//		System.out.println("this is the game progress " + s.getGameProgress());
		
		s.getGs().set(s.getGameProgress());
		if(s.getGs().is(GameState.DEAD)) {
			return false;
		} else {
			return true;
		} 

	}

}
