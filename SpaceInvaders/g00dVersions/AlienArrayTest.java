import static org.junit.Assert.*;
import org.junit.Test;

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
		Alien alien = new Alien(5, 3, 5 );
		alien.setYCoord(6);
		alienArray.setMove(false);
		
		alienArray.aliensMovement(30);
		
		assertEquals("AlienArray reaches end of board, should return true ", 7, alien.getYCoord());
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
	/**
	@Test
	public void test_reachedEnd_False() {
		AlienArray alienArray = new AlienArray(5, 3, 5);
		alienArray.setYCoord(20);
		assertFalse("AlienArray does not reaches end of board, should return false", alienArray.reachedEnd(30));
	}
	/**

	@Test
	public void test_moveRight() {
		AlienArray alienArray = new AlienArray(5, 3, 5);
		alienArrayArray.setXCoord(10);
		alienArrayArray.moveRight();
		assertEquals("AlienArray must move right by horizontal speed", 15, alienArray.getXCoord());
	}
	
	@Test
	public void test_moveLeft() {
		AlienArray alienArray = new AlienArray(5, 3, 5);
		alienArray.setXCoord(10);
		alienArray.moveLeft();
		assertEquals("AlienArray must move right by horizontal speed", 5, alienArray.getXCoord());
	}
	
	@Test
	public void test_moveDown(){
		AlienArray alienArray = new AlienArray(5, 3, 5);
		alienArray.setYCoord(10);
		alienArray.moveDown();
		assertEquals("AlienArray must move right by horizontal speed", 13, alienArrayArray.getYCoord());
	}
	*/
}