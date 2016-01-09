package implementation.parser;

public class ParseException extends Throwable{
	private static final long serialVersionUID = 1L;

	public ParseException(String message) {
		super("Parse exception with message : "+message);
	}

}
