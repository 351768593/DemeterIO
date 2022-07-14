package firok.demeterio.store;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * store binary script data in memory
 */
public class MemorySource extends AbstractSource
{
	private final byte[] data;
	public MemorySource(byte[] data)
	{
		this.data = Arrays.copyOf(data, data.length);
	}
	public MemorySource(String text)
	{
		this(text.getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public boolean existsScript()
	{
		return true;
	}

	@Override
	public byte[] readBytes()
	{
		return Arrays.copyOf(data, data.length);
	}
}
