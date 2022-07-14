package firok.demeterio;

import firok.demeterio.script.java.JavaClassScript;
import firok.demeterio.script.java.JavaJarScript;
import firok.demeterio.store.FilesystemSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Map;

public class JavaTest
{
	@Test
	public void testDirectly() throws Exception
	{
		var cpr1 = JavaTest.class.getResource("/TC1.java");
		Assertions.assertNotNull(cpr1);

		var pathTC1java = "./TC1.java";
		var pathTC1class = "./TC1.class";
		new File(pathTC1java).deleteOnExit();
		new File(pathTC1class).deleteOnExit();

		try(var is = cpr1.openStream();
			var out = new FileOutputStream(pathTC1java))
		{
			is.transferTo(out);
			out.flush();
		}

		Runtime.getRuntime().exec("javac " + pathTC1java).waitFor();

		var source1 = new FilesystemSource(pathTC1class);
		var script1 = new JavaClassScript(source1);
		var ret = script1.executeScript("testMethod", Map.of());
		Assertions.assertEquals("world", ret.get("hello"));
	}

	@Test
	public void testJar() throws Exception
	{
		var script1 = new JavaJarScript(new URL[] {
				new File("./TC1.zip").toURL()
		});
		var ret = script1.executeScript("TC1#executeScript", Map.of());
		Assertions.assertEquals("world", ret.get("hello"));
	}
}
