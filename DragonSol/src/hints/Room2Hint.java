package hints;



@HintAnnotation(room="Room2")
public class Room2Hint implements HintHandler{

	@Override
	public void process(int gameState) {
		
		String hint_message = "Hint: ";
		
		if(gameState >= 15){
			hint_message+="You might want to move rooms now.";
		} else if(gameState == 7){
			hint_message+="Maybe dig?";
		} else {
			hint_message+="You can choose to 'swim' or 'look'";
		}
		
		System.out.println(hint_message);
	}
	
}
