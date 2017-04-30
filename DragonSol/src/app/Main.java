package app;

import java.util.List;
import java.util.Scanner;
import app.entity.User;
import framework.DragonFramework;

public class Main {

	boolean isRunning = false;
	boolean hasUser = false;
	Scanner sc = new Scanner(System.in);
	DragonFramework df;
	EntryPoint entry;
	
	public Main(EntryPoint entry){
		df = new DragonFramework();
		this.entry = entry;
	}
	
	private void startgame() throws Exception {
	    	//INITIALIZE    	
	    	isRunning = true;
	    	System.out.println("Input read strategy:");
	    	//ReadFileStrategy will read from file first and go back to cmd after
	    	//ReadCmdStrategy will take input from command line
	   
	    	String method = sc.next();
	    	ReadContext rc = new ReadContext();
	    	rc.setReadStrategy(method);
	    	rc.read(this);
	    	
	    	//RUN
	    	while(isRunning){
		    	String in = sc.nextLine();
		    	in = in.trim();
		    	Object o = df.in(in);
		    	run(o);
	    	}
	    }
	 
	//RUN
	public void run() throws Exception {
		System.out.println("WELCOME TO DRAGON GAME!");
		System.out.println("You are trap in this maze.\nSolve the puzzles and figure a way out before the dragon turns you in to ashes. + \nUse command 'Register (name)' to load/create a game.\n");	
		showUsers();
		startgame();
	}	
    
    public void run(Object o) {
    	try {
    		if(!(boolean) o){
    			isRunning =  false;
    			hasUser = false;   
    			run();
    		}
    	} catch (Exception e) {
    		if(hasUser){
    			userSave((String) o);
    			System.out.println("Game exited."
    					+ "\nUse command 'file' if you want to load commands from file."
    					+ "\nUse command 'Register (name)' to load/create a game.\n");	
    			showUsers();
    			
    		} else { 
    			loadUser((String) o);
    		}	
    	}
	}

    //USERS
	private void loadUser(String in) {
		User u = entry.findUser(in);
		if(u == null){
			u = new User();
			entry.createUser(u, in, df.currentRoom, df.gameState);
		}
		else {
			df.currentRoom = u.getRoom();
			df.gameState = u.getStatus();
		}
		hasUser = true;
		System.out.println("Loaded user " + in + "\n");
	}
	
	private void userSave(String in) {
		entry.saveUser(entry.findUser(in), df.currentRoom, df.gameState);
		hasUser = false;
		df.user = "";
	}

	public void showUsers(){
    	List<app.entity.User> users =  entry.getAll();
    	if(users.size() == 0){
    		System.out.println("There are no existing users..");
    	}
    	else {
    		System.out.println("Existing Users:");
    		for(app.entity.User u : users){
    			System.out.println("   " + u.getName());
    		}
    	}
    }
			
}
