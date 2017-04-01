
import room.*;
import java.util.*;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RoomCommandManager rm = new RoomCommandManager();
		int state = 0;
		String currentRoom = "Room2";
		
		while(true){
				
			Scanner sc = new Scanner(System.in);	
				
			HashMap<String,Object> output = rm.processRoom(currentRoom, state, sc.nextLine());
			
			for(String k : output.keySet()){
				
				System.out.println(k + ": " + output.get(k));
				
			}
			
			state = (int) output.get("status");
		}
		
	}

}
