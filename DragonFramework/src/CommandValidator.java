import room.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.lang.reflect.*;

@CommandAnnotation(target=Command.class)
public class CommandValidator implements CommandHandler {
	String reply;
	
	public void process(Object o, String command, Method m, DragonFramework df) throws Exception {	
		
		try {
			if(!check(command, (SessionHandler) o, df)){
				throw new RuntimeException();
			} 
			m.setAccessible(true);
		}
		catch (Exception e) {
			System.out.println(reply + "\n");
			throw new RuntimeException();		
		}
	}
	
	public boolean check(String arg, SessionHandler session, DragonFramework df) throws Exception{
		String[] RoomArr = {"Room1", "Room2", "Room3", "Room4", "Room5"};
		String[] args = arg.split(" ");
		System.out.println(args[0]); 
		
		GameState gs = session.getGs();
		
		if(df.currentRoom.equals("Room1")){
			switch(args[0]) {
				case "look": return false;
			}
		}

		if(df.currentRoom.equals("Room2")){
			switch(args[0]) {
				case "takeSword":	if(gs.is(GameState.TOOK_SWORD)) {
										reply ="You already took the sword.";
										return false;
									} else {
										session.gameProgress += 4;
										return true;
									}
				case "swim":        if(gs.is(GameState.TOOK_SWORD)) {
										reply ="You see nothing of interest as you swam.";
										return false;
									} else {
										session.gameProgress += 1;
										return true;
									}
				case "dig":			if(gs.is(GameState.GRAVE_FOUND) && gs.is(GameState.WORD_FOUND_2)) {
//											df.gs.set(df.gs.getState() - 2);
//											Get it instead from the session
											session.getGs().set(session.getGs().getState()-2);
										return true;
									} else {
										session.gameProgress += 8;
										return true;
									}
				case "look":		if(!gs.is(GameState.GRAVE_FOUND)){
										session.gameProgress += 2;
									}
									break;
			}
		}

		else if(df.currentRoom.equals("Room3")){
			switch(args[0]) {
				case "attack":		if(gs.is(GameState.BABY_DEAD)) {
										reply ="You can't attack nothing, silly.";
										return false;
									} else if(gs.is(GameState.TOOK_SWORD)){
										session.gameProgress += 16;
									} else {
										session.gameProgress += 256;
									}
									return true;

				case "openChest":	if(gs.is(GameState.WORD_FOUND_3)) {
										reply ="What chest?";
										return false;
									} else {
										session.gameProgress += 64;
										return true;
									}
				}
		}

		else if(df.currentRoom.equals("Room4")){
			switch(args[0]) {
				case "answer": 		if(gs.is(GameState.WORD_FOUND_4)) {
										reply ="You already answered correctly!";
										return false;
									} else {
										if(args[1] == "200754"){
											session.gameProgress += 128;
										} else {
											session.gameProgress += 256;
										}
										return true;
									}
			}
		}

		else if(df.currentRoom.equals("Room5")){
			switch(args[0]) {
				case "passphrase": return true;
				case "attack": {
					session.gameProgress += 256;
					return true;
				}
			}
		}

		switch(args[0]) {
			case "look": 		return true;
			case "Go":			if(Arrays.asList(RoomArr).contains(args[1])) {
									if(args[1].equals(df.currentRoom)) {
										reply ="You are already in this room!";
										return false;
									}
									return true;
								} else {
									reply ="You can't go into a non-existent room!";
									return false;
								}
			default:			reply ="Oops, I don't get what you are trying to do.";
								return false;
		}
	}
}