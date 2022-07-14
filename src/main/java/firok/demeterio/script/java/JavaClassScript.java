package firok.demeterio.script.java;

import firok.demeterio.exception.InitScriptException;
import firok.demeterio.exception.NoSuchScriptException;
import firok.demeterio.script.AbstractScript;
import firok.demeterio.store.AbstractSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * use a custom ClassLoader to construct instance of a class and invoke a method
 */
public class JavaClassScript extends AbstractScript
{
	protected final ScriptClassLoader cl;
	protected final Class<?> classScript;
	protected final Object instance;
	public JavaClassScript(AbstractSource source) throws InitScriptException
	{
		this.cl = new ScriptClassLoader();
		try
		{
			var classLoad = cl.loadByte(source.readBytes());
			this.classScript = classLoad;
			this.instance = classLoad.getConstructor().newInstance();
		}
		catch (Exception e)
		{
			throw new InitScriptException(e);
		}

	}

	/**
	 * @param key method name you want to call
	 * @param params params you want to pass to the method
	 * @return return value of the method
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, ?> executeScript(String key, Map<String, ?> params) throws NoSuchScriptException
	{
		try
		{
			var methodExecute = classScript.getMethod(key, Map.class);
			var isStatic = Modifier.isStatic(methodExecute.getModifiers());
			return (Map<String, ?>) methodExecute.invoke(isStatic ? null : instance, params);
		}
		catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e)
		{
			throw new NoSuchScriptException(e);
		}
	}
}
