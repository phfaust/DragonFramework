package framework;
import java.lang.reflect.Method;

//CHECKS IF COMMAND IS VALID

@CommandAnnotation(target=Command.class)
public class CommandValidator implements CommandHandler {
	String reply;

	@Override
	public void process(Object o, Object arg, Method m, DragonFramework df) throws Exception {	
		if(!check((String) arg, df)){
			System.out.println(reply + "\n");
			throw new RuntimeException();
		}
		m.setAccessible(true);
	}
	
	public boolean check(String arg, DragonFramework df){
		String[] args = arg.split(" ");
		reply = "";
		switch(args[0].toLowerCase()) {
			case "register":
				if(args.length < 2){
					reply = "Input a username.";
					return false;
				}
				return true;

			case "start":
				if(df.user.equals("")) {
					reply = "Register first.";
					return false;
				} else if(!df.currentRoom.equals("")) {
					reply = "The game already started.";
					return false;
				}
				df.start = true;
				break;
				
			case "go":	
				if (!df.start){
					reply ="Game has not started yet.";
					return false;
				} else if(args.length < 2) {
					reply = "No correct number of arguments";
					return false;
				}
				else if(args[1].equalsIgnoreCase(df.currentRoom)) {
					reply ="You are already in this room!";
					return false;
				}
				break; 
			default:
				if(!df.start){
					reply ="Game has not started yet.";
					return false;
				}
				break;
		}
		return true;
	}

}