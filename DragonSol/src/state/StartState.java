package state;

public class StartState implements State {
	   public void doAction(StateContext context) {
	      context.setState(this);	
	   }

	   public String toString(){
	      return "Start State";
	   }
	   
	   public String in(String s){
		   return "rcm " + s;
	   }
}