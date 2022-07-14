package firok.demeterio.exception;

public class InitScriptException extends Exception
{
	public InitScriptException()
	{
	}

	public InitScriptException(String message)
	{
		super(message);
	}

	public InitScriptException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public InitScriptException(Throwable cause)
	{
		super(cause);
	}
}
