package state;

public interface State {
	   public void doAction(StateContext context);
	   public String in(String s);
}