package firok.demeterio.script.javascript;

import com.oracle.truffle.js.scriptengine.GraalJSScriptEngine;
import firok.demeterio.script.AbstractScript;
import firok.demeterio.store.AbstractSource;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.util.Map;

public class JavascriptScript extends AbstractScript
{
	protected static final ScriptEngineManager mgr;
	protected static final GraalJSScriptEngine engine;
	static
	{
		mgr = new ScriptEngineManager();
		Context.Builder builder = Context.newBuilder().allowHostAccess(HostAccess.ALL).allowAllAccess(true);
		engine = GraalJSScriptEngine.create(null, builder);
	}

	private final String script;
	public JavascriptScript(AbstractSource source) throws Exception
	{
		this(source.readString());
	}
	public JavascriptScript(String script)
	{
		this.script = script;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, ?> executeScript(String key, Map<String, ?> context) throws ScriptException
	{
		var bindings = new SimpleBindings();
		bindings.putAll(context);
		if(key != null)
			bindings.put("key", key);
		var result = engine.eval(script, bindings);
		return result instanceof Map ? (Map<String, ?>) result : null;
	}
}
