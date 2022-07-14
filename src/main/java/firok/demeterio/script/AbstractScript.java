package firok.demeterio.script;

import java.util.Map;

public abstract class AbstractScript
{
	protected AbstractScript() { }

	/**
	 * @param key which script is being executed
	 * @param params the context. any results should be put into context instance
	 */
	public abstract Map<String, ?> executeScript(String key, java.util.Map<String, ?> params) throws Exception;
}
