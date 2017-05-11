package hints;



@HintAnnotation(room="Room3")
public class Room3Hint implements HintHandler{

	@Override
	public void process(int gameState) {
		
		String hint_message = "Hint: ";
		
		if(gameState == 111 || gameState == 127){
			hint_message+="You might want to move rooms now.";
		} else if (gameState == 63 || gameState == 57){
			hint_message+="Open chest?";
			
		} else {
			hint_message+="Looks like we can get by the dragon without bothering it."
					+ " Sometimes it's wiser to avoid conflict. \nYou can choose to 'attack' or 'look'";
		}
		
		System.out.println(hint_message);
	}
	
}
