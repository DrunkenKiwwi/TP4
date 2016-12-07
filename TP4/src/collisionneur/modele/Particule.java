package collisionneur.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;

public class Particule {
	private double vitesseX = 0;
	private double vitesseY = 0;
	private double rayon;
	private double posX;
	private double posY;
	private double angle = 0;
	private Color couleur;
	private DoubleProperty propX = new SimpleDoubleProperty();
	private DoubleProperty propY = new SimpleDoubleProperty();

	public Particule(double x, double y, double r, double vx, double vy, double a, Color c) {
		setPosX(x);
		setPosY(y);
		setRayon(r);
		setAngle(a);
		setVitesseX(vx);
		setVitesseY(vy);
		setCouleur(c);
		setPropX(x);
		setPropY(y);
	}

	public double getRayon() {
		return rayon;
	}

	public void setRayon(double r) {
		this.rayon = r;
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

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getPropX() {
		return propX.get();
	}

	public void setPropX(double propX) {
		this.propX.set(propX);

	}

	public double getPropY() {
		return propY.get();
	}

	public void setPropY(double propY) {
		this.propY.set(propY);

	}

	public DoubleProperty propXProperty() {
		return propX;
	}

	public DoubleProperty propYProperty() {
		return propY;
	}

	public double getVitesseX() {
		return vitesseX;
	}

	public void setVitesseX(double vitesseX) {

		this.vitesseX = vitesseX;

	}

	public double getVitesseY() {
		return vitesseY;
	}

	public void setVitesseY(double vitesseY) {

		this.vitesseY = vitesseY;

	}

}
