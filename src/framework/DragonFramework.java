package framework;

import room.*;
import java.lang.reflect.*;
import java.util.HashMap;

//This is where the we make our framework

public class DragonFramework {
	RoomCommandManager rcm;
	SessionHandler s;
	String currentRoom = "";
	String command = "";
	String user = "";
	int gameState;
	boolean start = false;
	HashMap<String, Object> hm;
	
	public DragonFramework(){
		rcm = new RoomCommandManager();
		s = new SessionHandler();		
	}
	
	public boolean in(String input) throws Exception{
		RoomCommandValidator v = new RoomCommandValidator();
		Class<?> c = SessionHandler.class;
		try {
			s.setCommand(input);
			String[] ins = input.split(" ");
			ins[0] = ins[0].toLowerCase();
		
			//VALIDATION
			Field field = c.getDeclaredField("command");
			field.setAccessible(true);
			v.validate(s, (String) field.get(s), c.getMethod("setCommand", String.class), this);

	    	switch(ins[0]){
	    		case "go": 
	    			command = "checkRoom";
	    			ins[1] = ins[1].substring(0,1).toUpperCase() + ins[1].substring(1).toLowerCase();
	    			if(ins[1].startsWith("Room")) currentRoom = ins[1];
	    			process(ins[1], gameState, command);
	    			break;

	    		case "start":
					process("Room1", 0, "checkRoom");
	    			break;

	    		case "register":
	    			user = "";
	    			for(int i = 1; i < ins.length; i++) user += ins[i] + " ";
	    			System.out.println("Registered user " + user + "\nInput 'Start' to start game.\n");
	    			break;

	    		case "hint":
	    			System.out.println("Yay hint but this command is not implemented yet. \n");
	    			//hint logic here;
	    			break;

	    		default:	
	    			command = "";
	    			if(ins[0].equalsIgnoreCase("takeSword")){
	    				command = "takeSword";
	    			} else if (ins[0].equalsIgnoreCase("openChest")){
	    				command = "openChest";
	    			} else {
	    				for (String i : ins) command += i + " "; //command plus multiple parrameters
	  				}

	  				process(currentRoom, gameState, command);		
	  		}

		} catch (Exception e){
			if(e instanceof NullPointerException || e instanceof RuntimeException){
				System.out.println("Invalid command. \n");
			} else {
				e.printStackTrace();
			}
		}

		//CHECK IF DEAD TO END GAME
		if((gameState & 256) == 256){
			return false;
		} else {return true;} 

	}

	public void process(String currentRoom, int gameState, String command) throws Exception{
		this.hm = rcm.processRoom(currentRoom, gameState, command);
    	System.out.println(this.hm.get("message") + "\n");
	  	gameState = (Integer) this.hm.get("status");
	}

}
