import room.GameState;

public class SessionHandler {
    private String command;
    private GameState gs;
    
    //This is the part we need to reset everytime we invoke start
    static int gameProgress;
    private String username;
    
   
    public SessionHandler(){
    	gameProgress = 0;
    	gs = new GameState();
    	
    }
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	//The command annotation checks for the specific command to map the appropriate response
	//All the logic is contained in the command validator
    @Command
    public void setCommand(String command) {
        this.command = command;
    }
    
    public GameState getGs() {
		return gs;
	}

	public void setGs(GameState gs) {
		this.gs = gs;
	}

	public int getGameProgress() {
		return gameProgress;
	}

	public void setGameProgress(int gameProgress) {
		this.gameProgress = gameProgress;
	}

	public String getCommand(){ 
    	return command;
    }
}
