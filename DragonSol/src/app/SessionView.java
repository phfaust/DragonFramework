package app;

import hints.HintProcessor;

public class SessionView {
	
	HintProcessor hintProcessor = new HintProcessor();
	
	public void printStatus(int gameState, String room){
		System.out.println("You are currently in  " + room);
		System.out.println("Game State: " + gameState);
		
		hintProcessor.processHints(room, gameState);
	}
}
