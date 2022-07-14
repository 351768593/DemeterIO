package firok.demeterio.store;

import java.nio.charset.StandardCharsets;

/**
 * abstract class for script sources
 */
public abstract class AbstractSource
{
	public abstract boolean existsScript() throws Exception;

	public abstract byte[] readBytes() throws Exception;

	public String readString() throws Exception
	{
		var bytes = readBytes();
		return new String(bytes, StandardCharsets.UTF_8);
	}
}
