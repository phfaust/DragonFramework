package commands;

import java.util.HashMap;
import java.util.regex.Matcher;

import app.Context;
import app.Session;
import framework.CommandAnnotation;
import framework.CommandHandler;


@CommandAnnotation(regEx = "(?i)rcm\\s(?i)(hint)")
public class HintCommand implements CommandHandler {

	@Override
	public void process(Matcher matcher) throws Exception {
		// TODO Auto-generated method stub
		
		String hint = matcher.group(1);
		Session s = Context.getSession();
		String room = s.getCurrentRoom();
		String action = "look";
		System.out.println("You are currently in: " + room);

		int gameState = s.getGameState();
		String hint_message = "Hint: ";
//		HINTS FOR USER
		switch(room){
			case "Room1":
				action = "checkRoom";
				break;
			case "Room2":
			if(gameState == 15){
					hint_message+="You might want to move rooms now.";
				} else if(gameState == 7){
					hint_message+="Maybe dig?";
				} else {
					hint_message+="You can choose to 'swim' or 'look'";
				}
				break;
			case "Room3":
				if(gameState == 111 || gameState == 127){
					hint_message+="You might want to move rooms now.";
				} else if (gameState == 63 || gameState == 57){
					hint_message+="Open chest?";
					
				} else {
					hint_message+="Looks like we can get by the dragon without bothering it."
							+ " Sometimes it's wiser to avoid conflict. \nYou can choose to 'attack' or 'look'";
				}
				break;
			case "Room4":
				if(gameState == 239 || gameState == 255){
					hint_message+="You might want to move rooms now.";
				} else {
					hint_message+="2 _ _ , 7 _ _ \nYou can choose to 'answer' or 'look'";
				}
				 
				break;
			case "Room5":
				hint_message+="Now what were the words again? ... ";
				break;
	}    			
		
		
		System.out.println(hint_message);
		
		
		
//		HashMap<String, Object> map = Context.getRcm().processRoom(room, s.getGameState(), action);
//		System.out.println(map.get("message"));
	}
	
	
	
	
}
