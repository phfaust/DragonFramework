package app;

import java.util.Scanner;
import framework.DragonFramework;

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
	    	System.out.println("Input read strategy: File/Cmd");
	    	//ReadFileStrategy will read from file first and go back to cmd after
	    	//ReadCmdStrategy will take input from command line
	   
	    	String method = sc.next();
	    	
	    	Object o = Class.forName("app.Read"+method+"Strategy").newInstance();
	    	ReadContext rc = new ReadContext((ReadStrategy) o);	    	

	    	System.out.println("WELCOME TO DRAGON GAME!");
			System.out.println("You are trapped in this maze.\nSolve the puzzles and figure a way out before the dragon turns you in to ashes.");	
			
	    	rc.read(this);
	}
	 
	public void run() throws Exception {
		startgame();
	}
	
	public void in(String s) throws Exception{
		df.in(state.getState().in(s));
		
		if((Context.getSession().getGameState() & 256) == 256) {
			isRunning = false;
			System.out.println("Game Over \n");
			IdleState idle = new IdleState();
			idle.doAction(state);
			startgame();
			//or load check point here?
		}
	}
    
}