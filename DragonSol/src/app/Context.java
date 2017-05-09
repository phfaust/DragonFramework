package app;

import room.RoomCommandManager;
import state.StateContext;

public class Context {
	static StateContext state = new StateContext();
	static Session session = new Session();
	static RoomCommandManager rcm = new RoomCommandManager();
	
	public static Session getSession() {
		return session;
	}
	public static void setSession(Session session) {
		Context.session = session;
	}
	public static RoomCommandManager getRcm() {
		return rcm;
	}
	public static void setRcm(RoomCommandManager rcm) {
		Context.rcm = rcm;
	}
	public static StateContext getState() {
		return state;
	}
	public static void setState(StateContext state) {
		Context.state = state;
	}

}
