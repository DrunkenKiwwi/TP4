package collisionneur.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import collisionneur.exception.BordureException;
import collisionneur.modele.ChambreDeParticules;
import javafx.scene.paint.Color;

public class ChambreFactory {
	private ChambreDeParticules c1, c2, c3;

	@Before
	public void setUp() throws BordureException {
		c1 = new ChambreDeParticules(10, 10, 5, 5, 5, 45, Color.AQUA);
		c2 = new ChambreDeParticules(10, 10, 5, 5, 5, 45, Color.AQUA);
		c3 = new ChambreDeParticules(10, 10, 5, 5, 5, 45, Color.AQUA);
	}
	
	@Test
	public void testSetAngle() {
		c1.setAngle(-1);
		assertTrue(c1.getAngle() == 45);
		c2.setAngle(15);
		assertTrue(c2.getAngle() == 15);
		c3.setAngle(361);
		assertTrue(c3.getAngle() == 45);
	}
}
