import room.*;
import java.lang.reflect.Method;

public interface CommandHandler 
{
	public void process(Object o, String command, Method m, DragonFramework df) throws Exception;
}
