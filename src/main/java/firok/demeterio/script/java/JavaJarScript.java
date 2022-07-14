package firok.demeterio.script.java;

import firok.demeterio.exception.InitScriptException;
import firok.demeterio.exception.NoSuchScriptException;
import firok.demeterio.script.AbstractScript;
import firok.demeterio.store.AbstractSource;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

public class JavaJarScript extends AbstractScript
{
	private final URLClassLoader cl;
	public JavaJarScript(URL[] urls)
	{
		this.cl = new URLClassLoader(urls, URLClassLoader.class.getClassLoader());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, ?> executeScript(String key, Map<String, ?> params) throws NoSuchScriptException, InitScriptException
	{
		var words = key.split("#");
		var className = words[0];
		var methodName = words[1];

		Class<?> classScript;
		Method methodScript;
		try
		{
			classScript = cl.loadClass(className);
			methodScript = classScript.getMethod(methodName, Map.class);
			if(methodScript.getReturnType() != Map.class)
				throw new NoSuchMethodException("return type of '%s' is not Map".formatted(key));
		}
		catch (ClassNotFoundException | NoSuchMethodException e)
		{
			throw new NoSuchScriptException(e);
		}

		try
		{
			var isStatic = Modifier.isStatic(methodScript.getModifiers());
			Object instance = null;
			if(!isStatic)
			{
				instance = classScript.getConstructor().newInstance();
			}
			return (Map<String, ?>) methodScript.invoke(instance, params);
		}
		catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
		{
			throw new InitScriptException(e);
		}
	}
}
