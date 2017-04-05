package framework;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import io.github.lukehutch.fastclasspathscanner.*;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

public class RoomCommandValidator 
{
	private HashMap<Class<?>, CommandHandler> map = new HashMap<>();
	
	public RoomCommandValidator() throws Exception{
		ScanResult results = new FastClasspathScanner().scan();
		List<String> allResults = results.getNamesOfClassesWithAnnotation(CommandAnnotation.class);
		for (String s : allResults) {
			try {
				Class<?> c = Class.forName(s);
				CommandAnnotation ca = (CommandAnnotation) c.getAnnotation(CommandAnnotation.class);
				map.put(ca.target(), (CommandHandler) c.newInstance());
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
