import java.util.Scanner;

import framework.DragonFramework;

public class Main {
//	This is where the input will be taken and the Game will be run
	
	static boolean isRunning = false;
	static Scanner sc = new Scanner(System.in);
	static DragonFramework df = new DragonFramework();

//	commit tester
	public static void main(String[] args) throws Exception {
		System.out.println("You are trap in this maze.\nSolve the puzzles and figure a way out before the dragon turns you in to ashes. \n");		
		startgame();
		
	}	
	
    public static void startgame() throws Exception {
    	//INITIALIZE
//    	remote repo test
    	
    	isRunning = true;
    	df.in("Go Room1");

    	//RUN
    	while(isRunning){
	    	String in = sc.nextLine();
	    	if(in.equals("Exit")){
	    		isRunning = false;
	    		System.out.println("Game Exited");
	    	}
	    	else {
	    		if(!df.in(in)){
	    			isRunning =  false;
	    			System.out.println("Game Over");
	    		}
	    	}
    	}
    }
			
}
