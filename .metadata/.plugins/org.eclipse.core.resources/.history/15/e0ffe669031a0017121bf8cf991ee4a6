package app;
import java.util.List;
import java.util.Scanner;
import framework.DragonFramework;

public class Main {

	boolean isRunning = false;
	Scanner sc = new Scanner(System.in);
	DragonFramework df;
	EntryPoint entry;
	
	
	public Main(EntryPoint entry){
		df = new DragonFramework(entry);
		this.entry = entry;
	}
	
	public void run() throws Exception {
		System.out.println("WELCOME TO DRAGON GAME!");
		System.out.println("You are trap in this maze.\nSolve the puzzles and figure a way out before the dragon turns you in to ashes. \nUse command 'Register (name)' to load/create a game.\n");	
		showUsers();
		startgame();
	}	
	
    public void startgame() throws Exception {
    	//INITIALIZE    	
    	isRunning = true;
    	//RUN
    	while(isRunning){
	    	String in = sc.nextLine();
	    	in = in.trim();
	    	if(in.toLowerCase().startsWith("register")){
	    		df.register(in);
	    	}
	    	else {
	    		if(!df.in(in)){
	    			isRunning =  false;
	    			System.out.println("Exited current game.\n \n");
	    			run();
	    		}
	    	}
    	}
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
