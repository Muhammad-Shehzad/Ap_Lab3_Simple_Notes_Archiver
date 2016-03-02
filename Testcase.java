import static org.junit.Assert.*;

import org.junit.Test;

public class Testcase {

	@Test
	public void test() {
		Server.stored.add(new Note("shehzad","running test"));
		if(Server.stored.get(0).user_name=="shehzad"){
		assertEquals("running test", Server.stored.get(0).data);
		}
	}
}
