package framework;
import java.lang.reflect.Method;

//CHECKS IF COMMAND IS VALID

@CommandAnnotation(target=Command.class)
public class CommandValidator implements CommandHandler {
	String reply;

	@Override
	public void process(Object o, Object arg, Method m, DragonFramework df) throws Exception {	
		if(!check((String) arg, df)){
			System.out.println(reply);
			throw new RuntimeException();
		}
		m.setAccessible(true);
	}
	
	public boolean check(String arg, DragonFramework df){
		String[] args = arg.split(" ");
		reply = "";
		
		if(df.start){
			switch(args[0].toLowerCase()) {	
				//EDGE CASES
				case "go":	
					if (!df.start){
						reply ="Game has not started yet.";
						return false;
					} else if(args.length < 2) {
						reply = "No correct number of arguments.";
						return false;
					}
					else if(args[1].equalsIgnoreCase(df.currentRoom)) {
						reply ="You are already in this room!";
						return false;
					}
					break; 
					
				case "takesword":	
					if(df.isState(4)) {
						reply ="You already took the sword.";
						return false;
					}
					break;
					
				case "swim":       
					if(df.isState(4)) {
						reply ="You see nothing of interest as you swam.";
						return false;
					}
					break;
					
				case "dig":			
					if(df.isState(2) && df.isState(8)) {
						reply ="You already dug.";
						return false;
					}
					break;
					
				case "attack":		
					if(df.isState(16) && df.currentRoom.equals("Room3")) {
						reply ="You can't attack nothing, silly.";
						return false;
					} 
					break;
					
				case "openchest":	
					if(df.isState(64)) {
						reply ="What chest?";
						return false;
					}
					break;
					
				case "answer": 		
					if(df.isState(128)) {
						reply ="You already answered correctly!";
						return false;
					}
					break;

				case "start":
					reply = "The game already started.";
					return false;
					
			}
			return true;
		}
		
		else if(args[0].toLowerCase().equals("start")){
			if(df.user.equals("")) {
				reply = "Register first.";
				return false;
			}
			df.start = true;
			return true;
		} else {
			reply ="Game has not started yet.";
			return false;
		}
	}

}