package model;

import static org.junit.Assert.*;
import org.junit.Test;

public class AlienTest {
	
	@Test
	public void test_Constructor() {
		Alien alien = new Alien(5, 3, 5 );
		assertEquals("Unexpected X coordinate", 0, alien.getXCoord());
		assertEquals("Unexpected Y coordinate", 5, alien.getYCoord());
		assertEquals("Unexpected width", 5, alien.getWidth());
		assertEquals("Unexpected height", 5, alien.getHeight());
		assertEquals("Unexpected speed", 5, alien.getHSpeed());
		assertEquals("Unexpected speed", 3, alien.getVSpeed());
	}
	
	
	@Test
	public void test_reachedEnd_True() {
		Alien alien = new Alien(5, 3, 5);
		alien.setYCoord(31);
		assertTrue("Alien reaches end of board, should return true ", alien.reachedEnd(30));
	}
	
	@Test
	public void test_reachedEnd_False() {
		Alien alien = new Alien(5, 3, 5);
		alien.setYCoord(20);
		assertFalse("Alien does not reaches end of board, should return false", alien.reachedEnd(30));
	}
	

	@Test
	public void test_moveRight() {
		Alien alien = new Alien(5, 3, 5);
		alien.setXCoord(10);
		alien.moveRight();
		assertEquals("Alien must move right by horizontal speed", 15, alien.getXCoord());
	}
	
	@Test
	public void test_moveLeft() {
		Alien alien = new Alien(5, 3, 5);
		alien.setXCoord(10);
		alien.moveLeft();
		assertEquals("Alien must move left by horizontal speed", 5, alien.getXCoord());
	}
	
	@Test
	public void test_moveDown(){
		Alien alien = new Alien(5, 3, 5);
		alien.setYCoord(10);
		alien.moveDown();
		assertEquals("Alien must move down by vertical speed", 13, alien.getYCoord());
	}
}