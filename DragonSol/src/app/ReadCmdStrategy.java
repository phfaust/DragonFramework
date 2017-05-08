package app;

import java.util.Scanner;

public class ReadCmdStrategy implements ReadStrategy{

	@Override
	public void read(Main m){
		System.out.println("\nUse command 'Register (name)' to load/create a game.\n");
		Scanner sc = new Scanner(System.in);
		while(m.isRunning){
	    	String in = sc.nextLine();
	    	in = in.trim();
	    	
	    	StateContext state = Context.getState();
			if(state.getState().toString().equals("Start State")){
				in = "rcm " + in;
			}
			System.out.println(state.toString() + " -- " + in);
	    	
	    	try{
	    		m.df.in(in);	
	    	} catch (Exception e){
	    		e.printStackTrace();
	    	}
	    	
    	}
		sc.close();
	}
}
