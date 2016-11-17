package collisionneur.modele;

import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;

public class Particule {
	private double vitesse;
	private double rayon;
	private double posX;
	private double posY;
	private double angle = 0;
	private Color couleur;
	private DoubleProperty propX;
	private DoubleProperty propY;

	public Particule(double x, double y, double r, double v, double a, Color c) {
		setPosX(x);
		setPosY(y);
		setRayon(r);
		setAngle(a);
		setVitesse(r);
		setCouleur(c);
	}

	public double getVitesse() {
		return vitesse;
	}

	public void setVitesse(double r) {
		this.vitesse = r;
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

	public DoubleProperty getPropX() {
		return propX;
	}

	public void setPropX(DoubleProperty propX) {
		this.propX = propX;
	}

	public DoubleProperty getPropY() {
		return propY;
	}

	public void setPropY(DoubleProperty propY) {
		this.propY = propY;
	}

}
