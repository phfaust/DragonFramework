package hints;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import app.Context;
import app.Session;
import framework.CommandAnnotation;
import framework.CommandHandler;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

public class HintProcessor {
	
	HashMap<String, HintHandler> room_hints = new HashMap<>();
	
	public HintProcessor(){
		ScanResult results = new FastClasspathScanner().scan();
		List<String> allResults = results.getNamesOfClassesWithAnnotation(HintAnnotation.class);
		for (String s : allResults) {
			try {
				Class<?> c = Class.forName(s);
				HintAnnotation ha = ((HintAnnotation) c.getAnnotation(HintAnnotation.class));
				room_hints.put(ha.room(), (HintHandler) c.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}
	
	public void processHints(String current_room, int gameState){
		for(String room: room_hints.keySet()){			
			HintHandler hinthandler = room_hints.get(room);
			if(hinthandler != null){
				if(current_room.equals(room)){
					hinthandler.process(gameState);
				}
				
			}
			
		}
		
		
		
	}
	
}
