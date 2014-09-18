import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TextBuddyTest {

	TextBuddy tb;
	
	@Before
	public void setUp() throws Exception {
		String[] args = {"test.txt"};
		tb = new TextBuddy(args);
	}

	@After
	public void tearDown() throws Exception {
		tb.executeCommand("clear");
	}

	//Sort nothing.
	@Test
	public void sortTc1() {
		assertEquals("Successfully sorted test.txt", tb.executeCommand("sort"));
	}

}
