import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TextBuddyTest {

	TextBuddy tb;
	
	@Before
	public void setUp() throws Exception {
		String[] args = {"mytextfile.txt"};
		tb = new TextBuddy(args);
	}

	@After
	public void tearDown() throws Exception {
		tb.executeCommand("clear");
	}

	//Sort nothing.
	@Test
	public void sortTc1() {
		assertEquals("Successfully sorted! \"mytextfile.txt\"", tb.executeCommand("sort"));
	}
	
	//Sort one item.
	@Test
	public void sortTc2() {
		tb.executeCommand("add a");
		tb.executeCommand("sort");
		assertEquals("1. a",tb.executeCommand("display"));
	}
	
	//Sort two or more items.
	@Test
	public void sortTc3() {
		tb.executeCommand("add b");
		tb.executeCommand("add a");
		tb.executeCommand("sort");
		assertEquals("1. a\n2. b",tb.executeCommand("display"));
	}

	//Search for nothing.
	@Test
	public void searchTc1() {
		assertEquals("Search invalid!", tb.executeCommand("search"));
	}
	
	//Search for one exact item.
	@Test
	public void searchTc2() {
		tb.executeCommand("add apple");
		assertEquals("- apple", tb.executeCommand("search apple"));
	}
	
	//Search for one partial item.
	@Test
	public void searchTc3() {
		tb.executeCommand("add apple");
		assertEquals("- apple", tb.executeCommand("search app"));
	}

	//Search for two or more partial items.
	@Test
	public void searchTc4() {
		tb.executeCommand("add apple1");
		tb.executeCommand("add apple2");
		assertEquals("- apple1\n- apple2", tb.executeCommand("search app"));
	}
	
	//Search for non-existent item.
	@Test
	public void searchTc5() {
		tb.executeCommand("add apple1");
		tb.executeCommand("add apple2");
		assertEquals("orange is not found.", tb.executeCommand("search orange"));
	}
}
