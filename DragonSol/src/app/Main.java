package app;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
	
	public void runfile(String path) throws Exception{
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String sCurrentLine;

			br = new BufferedReader(new FileReader(path));

			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine = sCurrentLine.trim();
				System.out.println(sCurrentLine);
				Object o = df.in(sCurrentLine);
				run(o);
			}

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}	
	}
	
	
	public void run() throws Exception {
		System.out.println("WELCOME TO DRAGON GAME!");
		System.out.println("You are trap in this maze.\nSolve the puzzles and figure a way out before the dragon turns you in to ashes. "
				+ "\nUse command 'file' if you want to load from file. \nUse command 'Register (name)' to load/create a game.\n");	
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
	    	
	    	if(in.toLowerCase().startsWith("file")){
	    		System.out.println("Input file name: ");
	    		runfile(sc.nextLine());
	    	}
	    	else {
	    		Object o = df.in(in);
	    		run(o);
	    	}
    	}
    }
    
    private void run(Object o) {
    	try {
    		if(!(boolean) o){
    			isRunning =  false;
    			hasUser = false;
    			System.out.println("Game Over.\n \n");
    			run();
    		}
    	} catch (Exception e) {
    		if(hasUser){
    			userSave((String) o);
    			hasUser = false;
    			df.user = "";
    			System.out.println("Game exited\n");
    			System.out.println("Use command 'Register (name)' to load/create a game.\n");	
    			showUsers();
    			
    		} else { 
    			loadUser((String) o);
    			hasUser = true;
    			System.out.println("Loaded User \n");
    		}	
    	}
	}

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
	}
	
	private void userSave(String in) {
		entry.saveUser(entry.findUser(in), df.currentRoom, df.gameState);
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