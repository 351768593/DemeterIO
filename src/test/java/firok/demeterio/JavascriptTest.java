package firok.demeterio;

import firok.demeterio.script.javascript.JavascriptScript;
import firok.demeterio.store.MemorySource;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class JavascriptTest
{
	@Test
	void testDirectly() throws Exception
	{
		var mem1 = new MemorySource("""
        console.log("hello world");
        let now = new Date();
        console.log(now);
        console.log(`key is '${key}'`);
        """);
		var src1 = new JavascriptScript(mem1);
		src1.executeScript("test-key", Map.of());
	}
}
