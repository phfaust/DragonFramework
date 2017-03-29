import room.*;
 
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

public class RoomCommandValidator 
{
	private HashMap<Class<?>, CommandHandler> map = new HashMap<>();
	
	public RoomCommandValidator() throws Exception{
		
//		Modified to class path scanner version so that we can work with more than 1 annotation
		
		ScanResult results = new FastClasspathScanner().scan();
		List<String> allResults = results.getNamesOfClassesWithAnnotation(CommandAnnotation.class);
//		System.out.println(allResults);
		for (String s : allResults) {
			try {
				Class c = Class.forName(s);
				CommandAnnotation va = (CommandAnnotation) c.getAnnotation(CommandAnnotation.class);
//				System.out.println(va);
				map.put(va.target(), (CommandHandler) c.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
	
	
	public void validate(Object o, String command, Method method, DragonFramework df) throws Exception
	{
		Annotation[] alist = method.getAnnotations();
		for (Annotation a : alist)
		{
			CommandHandler ch = map.get(a.annotationType());
			if (ch!=null)
			{
				ch.process(o, command, method, df);
			}
		}
		
	}

	

	

	
	
	
	
}
