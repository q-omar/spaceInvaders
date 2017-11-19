import static org.junit.Assert.*;
import org.junit.Test;

public class PlayerShotTest {
	
	@Test
	public void test_TextConstructor() {
		PlayerShot shot = new PlayerShot(5, 3);
		assertEquals("Unexpected Y coordinate", 5, shot.getYCoord());
		assertEquals("Unexpected vertical speed", 3, shot.getVSpeed());
		assertEquals("Unexpected height", 0, shot.getHeight());
		assertEquals("Unexpected width", 0, shot.getWidth());
	}
	
	@Test
	public void test_TextConstructor_Negative() {
		PlayerShot shot = new PlayerShot(-10, 3);
		assertEquals("Y-coordinate cannot be negative, should remain at default value", 0, shot.getYCoord());
	}
	
	@Test
	public void test_GUIConstructor() {
		PlayerShot shot = new PlayerShot(5, 3, 10, 5);
		assertEquals("Unexpected Y coordinate", 5, shot.getYCoord());
		assertEquals("Unexpected vertical speed", 3, shot.getVSpeed());
		assertEquals("Unexpected width", 10, shot.getWidth());
		assertEquals("Unexpected height", 5, shot.getHeight());
	}
	
	@Test
	public void test_GUIConstructor_Negative() {
		PlayerShot shot = new PlayerShot(-5, 3, -4, -3);
		assertEquals("Y-coordinate cannot be negative, should remain at default value", 0, shot.getYCoord());
		assertEquals("Cannot have negative height", 0, shot.getHeight());
		assertEquals("Cannot have negative width", 0, shot.getWidth());
	}
	
	@Test
	public void test_tryShot() {
		PlayerShot shot = new PlayerShot(5, 3);
		shot.tryShot(7);
		
		assertEquals("Shot should be active after firing", true, shot.getShotFired());
		assertEquals("Unexpected x coordinate", 7, shot.getXCoord());
	}
	
	@Test
	public void test_moveShot() {
		PlayerShot shot = new PlayerShot(5, 3);
		shot.tryShot(7);
		shot.moveShot();
		
		assertEquals("Unexpected y coordinate", 2, shot.getYCoord());
	}
	
	@Test
	public void test_moveShot_OffScreen() {
		PlayerShot shot = new PlayerShot(2, 3);
		shot.tryShot(7);
		shot.moveShot();
		
		assertEquals("Shot will move off-screen, so y should be reset to the initial value", 2, shot.getYCoord());
		assertEquals("Last y coordinate should not be changed", 2, shot.getLastYCoord());
		assertEquals("Shot should be deactivated", false, shot.getShotFired());
	}
	
	@Test
	public void test_CheckHit_Failure() {
		PlayerShot shot = new PlayerShot(5, 3, 2, 2);
		shot.tryShot(2);
		assertFalse("Shot and target do not overlap", shot.checkHit(8, 10, 2));
	}
	
	@Test
	public void test_CheckHit_Success() {
		PlayerShot shot = new PlayerShot(5, 3, 2, 2);
		shot.tryShot(2);
		assertTrue("Shot and target overlap", shot.checkHit(1, 3, 5));
	}
	
	@Test
	public void test_CheckTextHit_MovingLeft() {
		PlayerShot shot = new PlayerShot(5, 3);
		shot.tryShot(5);
		assertTrue("Hit should have been detected", shot.checkTextHit(7,4,7));
	}
	
	@Test
	public void test_CheckTextHit_MovingRight() {
		PlayerShot shot = new PlayerShot(5, 3);
		shot.tryShot(5);
		assertTrue("Hit should have been detected", shot.checkTextHit(7,7,4));
	}
	
	@Test
	public void test_CheckTextHit_MovingDown() {
		PlayerShot shot = new PlayerShot(5, 3);
		shot.tryShot(5);
		assertTrue("Hit should have been detected", shot.checkTextHit(7,5,5));
	}
}

