package collisionneur.modele;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ChambreDeParticules {

	private double posX = 0;
	private double posY = 0;
	private double rayon = 0;
	private double vitesse = 0;
	private double angle = 0;
	private Color color;
	private Circle cercle;

	public ChambreDeParticules(double x, double y, double r, double v, double a, Color c) {
		setPosX(x);
		setPosY(y);
		setRayon(r);
		setVitesse(r);
		setAngle(a);
		setColor(c);
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double x) {
		this.posX = x;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double y) {
		this.posY = y;
	}

	public double getRayon() {
		return rayon;
	}

	public void setRayon(double r) {
		this.rayon = r;
	}

	public double getVitesse() {
		return vitesse;
	}

	public void setVitesse(double r) {
		this.vitesse = r;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double a) {
		this.angle = a;
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

		return new Particule(getPosX(), getPosY(), getRayon(), getVitesse(), getAngle(), getColor());

	}

}
