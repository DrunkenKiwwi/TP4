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

	@Test
	public void testSetRayon() {
		c1.setRayon(-1);
		assertTrue(c1.getRayon() == 5);
		c2.setRayon(7);
		assertTrue(c2.getRayon() == 7);
		c3.setRayon(11);
		assertTrue(c3.getRayon() == 5);
	}

	@Test
	public void testSetX() throws BordureException {

		c1.setPosX(-1);
		assertTrue(c2.getPosX() == 15);
		c2.setPosX(15);
		assertTrue(c1.getPosX() == 10);
		c3.setPosX(750);
		assertTrue(c3.getPosX() == 10);

	}

	@Test
	public void testSetY() throws BordureException {

		c1.setPosY(-1);
		assertTrue(c2.getPosY() == 15);
		c2.setPosY(15);
		assertTrue(c1.getPosY() == 10);
		c3.setPosY(750);
		assertTrue(c3.getPosY() == 10);

	}

}
