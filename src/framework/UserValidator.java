package framework;
import java.lang.reflect.Method;


@CommandAnnotation(target=User.class)
public class UserValidator implements CommandHandler{

	@Override
	public void process(Object o, Object arg, Method m, DragonFramework df) throws Exception {
		String[] args = ((String) arg).split(" ");
		if(args.length < 1){
			System.out.println(args.length);
			System.out.println("Input a username.");
			throw new RuntimeException();
		}
	}
}
