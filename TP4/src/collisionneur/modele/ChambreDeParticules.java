package collisionneur.modele;

import collisionneur.exception.BordureException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ChambreDeParticules {

	private double posX = 0;
	private double posY = 0;
	private double rayon = 0;
	private double vitesseX = 0;
	private double vitesseY = 0;
	private double angle = 0;
	private Color color;
	private Circle cercle;

	public ChambreDeParticules(double x, double y, double r, double vx, double vy, double a, Color c)
			throws BordureException {
		setPosX(x);
		setPosY(y);
		setRayon(r);
		setVitesseX(vx);
		setVitesseY(vy);
		setAngle(a);
		setColor(c);
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double x) throws BordureException {

		if (x >= 0 || x <= 728) {
			this.posX = x;
		} else {
			throw new BordureException();

		}

	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double y) {
		if (y >= 0 || y <= 348) {
			this.posY = y;
		}
	}

	public double getRayon() {
		return rayon;
	}

	public void setRayon(double r) {
		if (r >= 4 && r <= 10) {
			this.rayon = r;
		}

	}

	public double getVitesseX() {
		return vitesseX;
	}

	public void setVitesseX(double r) {
		
			this.vitesseX = r;
		

	}

	public double getVitesseY() {
		return vitesseY;
	}

	public void setVitesseY(double r) {
	
			this.vitesseY = r;
		

	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) throws BordureException {
		if (angle >= 0 && angle <= 360) {
			this.angle = angle;
		} else {
			throw new BordureException();
		}

	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Circle getCercle() {
		return cercle;
	}

	public void setCercle(Circle cercle) {
		this.cercle = cercle;
	}

	public Particule getParticule() {

		return new Particule(getPosX(), getPosY(), getRayon(), getVitesseX(), getVitesseY(), getAngle(), getColor());

	}

}
