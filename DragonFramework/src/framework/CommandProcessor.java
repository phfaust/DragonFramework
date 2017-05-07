package framework;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

public class CommandProcessor {
	//matcher hashmap
	//the string inside the hashmop is the regex
//	the command handler is the CommandClass itself ex: GoCommand.java
	HashMap<String, CommandHandler> map = new HashMap<>();	
	
	public CommandProcessor(){
		ScanResult results = new FastClasspathScanner().scan();
		List<String> allResults = results.getNamesOfClassesWithAnnotation(CommandAnnotation.class);
		for (String s : allResults) {
			try {
				Class<?> c = Class.forName(s);
				CommandAnnotation ca = (CommandAnnotation) c.getAnnotation(CommandAnnotation.class);
				map.put(ca.regEx(), (CommandHandler) c.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}
	
	//processtext
	public void processText(String sms) throws Exception{

		for(String regEx: map.keySet()){
//			System.out.println(sms + " :" +  sms.matches(regEx));
//			basically checks if the regex is a match throws an error if it does not
			if(sms.matches(regEx)){
				CommandHandler ch = (CommandHandler) map.get(regEx);
				if(ch != null){
					Pattern strMatch = Pattern.compile(regEx);
					Matcher m = strMatch.matcher(sms);
					
					if(m.find()){
						ch.process(m);
					}
			
				}
				return;
			}
		
		}
		throw new RuntimeException("No regEx match");			
		
		
		
	}
	
	

}
