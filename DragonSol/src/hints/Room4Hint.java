package hints;



@HintAnnotation(room="Room4")
public class Room4Hint implements HintHandler{

	@Override
	public void process(int gameState) {
		
		String hint_message = "Hint: ";
		
		if(gameState == 239 || gameState == 255){
			hint_message+="You might want to move rooms now.";
		} else {
			hint_message+="2 _ _ , 7 _ _ \nYou can choose to 'answer' or 'look'";
		}
		 
		System.out.println(hint_message);
	}
	
}
