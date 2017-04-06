package framework;
import room.*;
import java.lang.reflect.*;
import java.util.HashMap;

public class DragonFramework {
	RoomCommandManager rcm;
	SessionHandler s;
	HashMap<String, Object> hm;
	
	public String currentRoom = "Room1";
	public String command = "";
	public String user = "";
	public int gameState;
	
	boolean start = false;

	
	//INSTANTIATION
	public DragonFramework(){
		rcm = new RoomCommandManager();
		s = new SessionHandler();	
	}
	
	//COMMAND
	public Object in(String input) throws Exception{
	
		if(input.toLowerCase().startsWith("register")){
			
    		return register(input);
    	}
		
    	else {
    		
			return command(input);
    	}
	}
	
	private Object command(String input) throws Exception {
		try {
			RoomCommandValidator v = new RoomCommandValidator();
			Class<?> c = SessionHandler.class;
			
			//VALIDATION
			String[] ins = regex(input);
			s.setCommand(input);
			Field field = c.getDeclaredField("command");
			field.setAccessible(true);
			v.validate(s, (String) field.get(s), c.getMethod("setCommand", String.class), this);
		
			//PROCESS COMMANDS
			processCommand(ins);

		} catch (Exception e){
			if(e instanceof NullPointerException || e instanceof RuntimeException){
				System.out.println("Invalid command. \n");
				return true;
			} else {
				e.printStackTrace();
			}
		}
		return checkEnd();
	}

	private Object register(String input) throws Exception{
		RoomCommandValidator v = new RoomCommandValidator();
		Class<?> c = SessionHandler.class;
		try {
			//VALIDATION
			String[] ins = regex(input);
			String temp = ins[1];
				
			s.setUsername(temp);
			Field field = c.getDeclaredField("username");
			field.setAccessible(true);
			v.validate(s, (String) field.get(s), c.getMethod("setUsername", String.class), this);
			
			//SAVING AND LOADING USER
			user = temp;
			System.out.println("\nInput 'Start' to start game.\n"
					+ "Use command 'exit' to save and exit game\n");
			
			return user;
			
		} catch (RuntimeException e){
			e.printStackTrace();
			System.out.println("ERROR: User not registered.");
			return null;
		}
	}

	public void process(String currentRoom, int gameState, String command) throws Exception{
		this.hm = rcm.processRoom(currentRoom, gameState, command);
    	System.out.println(this.hm.get("message") + "\n");
	  	this.gameState = (Integer) this.hm.get("status");
	}
	
	private String[] regex(String s){
		String [] a = null;
		 if(s.matches("(\\w+)")){
			a = s.split("\\s\\s*");				
		} else {
			a = s.split("\\s\\s*", 2);
		}		
		return a;
	}

	private Object checkEnd() {
		if((gameState & 256) == 256){
			start = false;
			currentRoom = "Room1";
			gameState = 0;
			user = "";
			return false;
		} 
		if(!start){
			return user;
		} else {
			return true;
		} 
	}
		
	private void processCommand(String[] ins) throws Exception {
		ins[0] = ins[0].toLowerCase();
		
		switch(ins[0]){
			case "go": 
				command = "checkRoom";
				ins[1] = ins[1].substring(0,1).toUpperCase() + ins[1].substring(1).toLowerCase();
				if(ins[1].startsWith("Room")) currentRoom = ins[1];
				process(ins[1], gameState, command);
				break;
	
			case "start":
				start = true;
				process(currentRoom, gameState, "checkRoom");
				break;
	
			case "hint":
				hint();
				break;
				
			case "exit":
				start = false;
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
	}

	private void hint(){
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
				} else if(isState(16)){
					System.out.println("You can 'look'");
				}else if (isState(32)){
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

	boolean isState(int x ){
		return ((this.gameState & x) == x);
	}
	
}
