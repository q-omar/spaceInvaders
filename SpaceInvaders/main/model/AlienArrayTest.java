package model;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;


public class AlienArrayTest {
	
	@Test
	public void test_TextConstructor() {
		AlienArray alienArray = new AlienArray("GUI");
		assertEquals("Unexpected rows of aliens", 8, alienArray.getNumAliens());
		assertEquals("Unexpected columns of aliens", 3, alienArray.getRowsAliens());
	}
	
	@Test
	public void test_GUIConstructor() {
		AlienArray alienArray = new AlienArray("Text");
		assertEquals("Unexpected rows of aliens", 5, alienArray.getNumAliens());
		assertEquals("Unexpected columns of aliens", 2, alienArray.getRowsAliens());
	}
}