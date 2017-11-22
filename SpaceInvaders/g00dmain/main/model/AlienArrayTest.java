package model;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;


public class AlienArrayTest {
	
	@Test
	public void test_TextConstructor() {
		AlienArray alienArray = new AlienArray("GUI");
		assertEquals("Unexpected rows of aliens", 6, alienArray.getNumAliens());
		assertEquals("Unexpected columns of aliens", 3, alienArray.getRowsAliens());
	}
	
	@Test
	public void test_GUIConstructor() {
		AlienArray alienArray = new AlienArray("Text");
		assertEquals("Unexpected rows of aliens", 5, alienArray.getNumAliens());
		assertEquals("Unexpected columns of aliens", 2, alienArray.getRowsAliens());
	}

	
	@Test
	public void test_moveDown_fromRight() {
		AlienArray alienArray = new AlienArray("Text");
		Random rand = new Random();
		int  r = rand.nextInt(2)
		int  r = rand.nextInt(5)
		Alien[][] aliens = new Alien [3][6];

		alienArray.setMove(false);
		
		alienArray.aliensMovement(30);
		
		assertEquals("AlienArray reaches end of board, should return true ", 5, aliens[r][c].getYCoord());
	}
	
	@Test
	public void test_moveDown_fromLeft() {
		AlienArray alienArray = new AlienArray("GUI");
		Alien alien = new Alien(5, 3, 5 );
		alienArray.setMove(true);
		alienArray.aliensMovement(30);
		
		assertEquals("AlienArray reaches end of board, should return true ", 5, alien.getYCoord());
	}
	
	@Test
	public void test_moveRight() {
		AlienArray alienArray = new AlienArray("GUI");
		Alien alien = new Alien(5, 3, 5 );
		alienArray.setMove(true);
		alienArray.aliensMovement(30);
		
		assertEquals("AlienArray reaches end of board, should return true ", 3, alien.getXCoord());
	} 

}