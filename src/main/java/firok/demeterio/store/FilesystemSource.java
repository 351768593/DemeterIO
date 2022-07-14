package firok.demeterio.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * a filesystem script source
 */
public class FilesystemSource extends AbstractSource
{
	private final File file;
	public FilesystemSource(String path)
	{
		this.file = new File(path);
	}

	@Override
	public boolean existsScript()
	{
		return file.exists() && file.isFile();
	}

	@Override
	public byte[] readBytes() throws IOException
	{
		try(var is = new FileInputStream(file))
		{
			return is.readAllBytes();
		}
	}
}
