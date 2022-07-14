package firok.demeterio.script.java;

import lombok.SneakyThrows;

public class ScriptClassLoader extends ClassLoader
{
	public ScriptClassLoader()
	{
		super(ScriptClassLoader.class.getClassLoader());
	}

	@SneakyThrows
	public Class<?> loadByte(byte[] classByte)
	{
		return defineClass(null, classByte, 0, classByte.length);
	}
}
