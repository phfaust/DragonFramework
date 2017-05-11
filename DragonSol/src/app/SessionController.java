package app;

public class SessionController {
	private Session session;
	private SessionView view;
	
	public SessionController(Session session, SessionView view){
		this.session = session;
		this.view = view;
	}

	public int getGameState() {
		return session.getGameState();
	}

	public void setGameState(int gameState) {
		session.setGameState(gameState);
	}
	
	public String getRoom() {
		return session.getCurrentRoom();
	}

	public void setRoom(String room) {
		session.setCurrentRoom(room);
	}

	public void updateSessionView() {
		view.printStatus(session.getGameState(), session.getCurrentRoom());
	}	
}
