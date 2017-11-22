package model;

import static org.junit.Assert.*;
import org.junit.Test;

public class PlayerShipTest {
	
	@Test
	public void test_TextConstructor() {
		PlayerShip ship = new PlayerShip(10, 10, 0, 5 );
		assertEquals("Unexpected X coordinate", 5, ship.getXCoord());
		assertEquals("Unexpected Y coordinate", 10, ship.getYCoord());
		assertEquals("Unexpected width", 0, ship.getWidth());
		assertEquals("Unexpected height", 0, ship.getHeight());
		assertEquals("Unexpected speed", 5, ship.getHSpeed());
	}
	
	
	@Test
	public void test_GUIConstructor() {
		PlayerShip ship = new PlayerShip(10, 10, 4, 5 );
		assertEquals("Unexpected X coordinate", 5, ship.getXCoord());
		assertEquals("Unexpected Y coordinate", 10, ship.getYCoord());
		assertEquals("Unexpected width", 4, ship.getWidth());
		assertEquals("Unexpected height", 4, ship.getHeight());
		assertEquals("Unexpected speed", 5, ship.getHSpeed());
	}
	@Test
	public void test_TextConstructor_NegativeX() {
		PlayerShip ship = new PlayerShip(-10, 10, 0 ,5);
		assertEquals("X-coordinate cannot be negative, should remain at default value", 0, ship.getXCoord());
	}
	
	@Test
	public void test_TextConstructor_NegativeY() {
		PlayerShip ship = new PlayerShip(5, -10, 0 ,5);
		assertEquals("Y-coordinate cannot be negative, should remain at default value", 0, ship.getYCoord());
	}
	
	
	@Test
	public void test_inBounds_Right() {
		PlayerShip ship = new PlayerShip(10, 10, 0, 5);
		ship.setXCoord(31);
		ship.inBounds(30);
		assertEquals("Ship will move off-screen on the right, so X should be reset to the initial value", 25, ship.getXCoord());
	}

	@Test
	public void test_inBounds_Left() {
		PlayerShip ship = new PlayerShip(10, 10, 0, 5);
		ship.setXCoord(-1);
		ship.inBounds(30);
		assertEquals("Ship will move off-screen on the left, so X should be reset to the initial value", 0, ship.getXCoord());
	}
	
	@Test
	public void test_moveShip_Left() {
		PlayerShip ship = new PlayerShip(20, 10, 0, 5);
		ship.move("A");
		
		assertEquals("Unexpected X-coordinate", 5, ship.getXCoord());
	}
	
	@Test
	public void test_moveShip_Right() {
		PlayerShip ship = new PlayerShip(20, 10, 0, 5);
		ship.move("D");
		
		assertEquals("Unexpected X-coordinate", 15, ship.getXCoord());
	}
}