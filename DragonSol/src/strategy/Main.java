package strategy;

import java.util.Scanner;

import app.Context;
import framework.DragonFramework;
import state.IdleState;
import state.StateContext;

public class Main {

	boolean isRunning = false;	
	Scanner sc = new Scanner(System.in);
	DragonFramework df;
	StateContext state;

	public Main(){
		df = new DragonFramework();
		state = new StateContext();
		IdleState idle = new IdleState();
		idle.doAction(state);
		Context.setState(state);
	}
	
	private void startgame() throws Exception {
	    	//INITIALIZE    	
	    	isRunning = true;
	    	
	    	boolean incorrect = true;
	    		
	    		do{
	    			System.out.println("Input read strategy: File/Cmd");
			    	//ReadFileStrategy will read from file first and go back to cmd after
			    	//ReadCmdStrategy will take input from command line
			   
			    	String method = sc.next();
			    	method = method.substring(0, 1).toUpperCase() + method.substring(1).toLowerCase();
			    	
			    	try{
			    		Object o = Class.forName("strategy.Read"+method+"Strategy").newInstance();
				    	ReadContext rc = new ReadContext((ReadStrategy) o);	
				    	
				    	System.out.println("WELCOME TO DRAGON GAME!");
						System.out.println("You are trapped in this maze.\nSolve the puzzles and figure a way out before the dragon turns you in to ashes.");	
						
				    	rc.read(this);
				    	incorrect = false;
			    	} catch(Exception e){
			    		System.out.println("Input Strategy: Entered Invalid Command");
			    		incorrect = true;
			    	}
	    		} while (incorrect);
	    	
		    
	}
	 
	public void run() throws Exception {
		startgame();
	}
	
	public void in(String s) throws Exception{
//		System.out.println(state.getState().in(s));
		df.in(state.getState().in(s).toLowerCase());
		
		if((Context.getSession().getGameState() & 256) == 256) {
			isRunning = false;
			System.out.println("Game Over \n");
			IdleState idle = new IdleState();
			idle.doAction(state);
			startgame();
		}
	}
    
}