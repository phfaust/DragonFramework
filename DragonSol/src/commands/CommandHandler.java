package commands;
import java.util.regex.Matcher;

public interface CommandHandler {
	public void process(Matcher matcher) throws Exception;
}
