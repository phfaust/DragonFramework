package state;

public class IdleState implements State {

	   public void doAction(StateContext context) {
	      context.setState(this);	
	   }

	   public String toString(){
	      return "Idle State";
	   }
	   
	   public String in(String s) {
		   return s;
	   }
	}