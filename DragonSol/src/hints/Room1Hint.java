package hints;



@HintAnnotation(room="Room1")
public class Room1Hint implements HintHandler{

	@Override
	public void process(int gameState) {
		
		System.out.println("Hint: You may call 'Go Room<<room number>>' to navigate.");
		
	}
	
}
