package firok.demeterio.exception;

public class NoSuchScriptException extends Exception
{
	public NoSuchScriptException()
	{
	}

	public NoSuchScriptException(String message)
	{
		super(message);
	}

	public NoSuchScriptException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public NoSuchScriptException(Throwable cause)
	{
		super(cause);
	}
}
