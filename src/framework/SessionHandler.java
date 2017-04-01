package framework;

public class SessionHandler {
    private String command;
    private String username;
        
    @User
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
    @Command
    public void setCommand(String command) {
        this.command = command;
    }
	public String getCommand(){ 
    	return command;
    }
}
