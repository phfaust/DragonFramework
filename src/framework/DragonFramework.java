package framework;
import room.*;
import java.lang.reflect.*;
import java.util.HashMap;

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
	
	//USER
	public void register(String input) throws Exception{
		RoomCommandValidator v = new RoomCommandValidator();
		Class<?> c = SessionHandler.class;
		try {
			
			//VALIDATION
			String[] ins = input.split(" ");
			String temp = "";
			for(int i = 1; i < ins.length; i++) temp += ins[i] + " ";
			
			s.setUsername(temp);
			Field field = c.getDeclaredField("username");
			field.setAccessible(true);
			v.validate(s, (String) field.get(s), c.getMethod("setUsername", String.class), this);
			
			//SAVING AND LOADING USER
			user = temp;
			System.out.println("Registered user " + user + "\nInput 'Start' to start game.\n");
			
			//implement saving and loading game here
			
		} catch (RuntimeException e){
			System.out.println("ERROR: User not registered.");
		}
	}
	
	//COMMAND
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
			
			//PROCESS COMMANDS
	    	switch(ins[0]){
	    		case "go": 
	    			command = "checkRoom";
	    			ins[1] = ins[1].substring(0,1).toUpperCase() + ins[1].substring(1).toLowerCase();
	    			if(ins[1].startsWith("Room")) currentRoom = ins[1];
	    			process(ins[1], gameState, command);
	    			break;

	    		case "start":
	    			this.currentRoom = "Room1";
					process("Room1", 0, "checkRoom");
	    			break;

	    		case "hint":
	    			hint();
	    			break;
	    			
	    		default:	
	    			command = "";
	    			if(ins[0].equalsIgnoreCase("takeSword")){
	    				command = "takeSword";
	    			} else if (ins[0].equalsIgnoreCase("openChest")){
	    				command = "openChest";
	    			} else {
	    				for (String i : ins) command += i + " "; //command plus multiple parameters
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

	//process room
	public void process(String currentRoom, int gameState, String command) throws Exception{
		this.hm = rcm.processRoom(currentRoom, gameState, command);
    	System.out.println(this.hm.get("message") + "\n");
	  	this.gameState = (Integer) this.hm.get("status");
	}
	
	//check current state
	public boolean isState(int x ){
		return ((this.gameState & x) == x);
	}
	
	//hints
	public void hint(){
		System.out.println("You are in: " + currentRoom);
		System.out.println("Hint:");
		System.out.println("gatestate: " + gameState);
		switch(currentRoom){
			case "Room1":
				System.out.println("You might want to move rooms now");
				break;
			case "Room2":			
				if(isState(8)){
					System.out.println("You might want to move rooms now.");
				} else if(isState(2)){
					System.out.println("Maybe dig?");
				} else {
					System.out.println("You can choose to 'swim' or 'look'");
				}
				break;
			case "Room3":
				if(isState(64)){
					System.out.println("You might want to move rooms now.");
				} else if (isState(32)){
					System.out.println("Open chest?");
				} else {
					System.out.println("Looks like we can get by the dragon without bothering it."
							+ " Sometimes it's wiser to avoid conflict.");
					System.out.println("You can choose to 'attack' or 'look'");
				}
				break;
			case "Room4":
				if(isState(128)){
					System.out.println("You might want to move rooms now.");
				} else {
					System.out.println("2 _ _ , 7 _ _ ");
					System.out.println("You can choose to 'answer' or 'look'");
				}
				break;
			case "Room5":
				System.out.println("You can choose to answer 'passphrase' or 'look' or 'attack'");
				System.out.println("Now what were the words again? ... ");
				break;
		}    			
	}

}
