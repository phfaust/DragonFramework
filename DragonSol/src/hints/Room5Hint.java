package hints;



@HintAnnotation(room="Room5")
public class Room5Hint implements HintHandler{

	@Override
	public void process(int gameState) {
		
		String hint_message = "Hint: ";
		
		hint_message+="Now what were the words again? ... ";
		
		System.out.println(hint_message);
	}
	
}
