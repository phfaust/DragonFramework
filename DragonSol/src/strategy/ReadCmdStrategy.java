package strategy;

import java.util.Scanner;

public class ReadCmdStrategy implements ReadStrategy{

	@Override
	public void read(Main m){
		System.out.println("\nUse command 'Register (name)' to load/create a game.\n");
		Scanner sc = new Scanner(System.in);
		while(m.isRunning){
	    	String in = sc.nextLine();
	    	in = in.trim();
	    		    	
	    	try{
	    		m.in(in);	
	    	} catch (Exception e){
	    		e.printStackTrace();
	    	}
	    	
    	}
		sc.close();
	}
}
