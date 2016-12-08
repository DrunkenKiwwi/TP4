package collisionneur.modele;

import java.util.ArrayList;

public class InfoParticule extends Thread {

	ArrayList<Particule> array;
	private static final int BUFFER = 2;
	private static final int BUFFERC = 2;
	private boolean run = true;

	public InfoParticule(ArrayList<Particule> arrayB) {
		setArrayBall(arrayB);
	}

	/**
	 * Thread s'occupant de bouger les particules et de vérifier d'appeler les
	 * méthodes de verification de collisions
	 */
	@Override
	public void run() {

		while (run) {

			for (int i = 0; i < array.size(); i++) {

				Particule p = array.get(i);

				if (verifierCollisionMurX(p)) {
					p.setVitesseX(-p.getVitesseX());

				} else if (verifierCollisionMurY(p)) {

					p.setVitesseY(-p.getVitesseY());

				} else if ((p.getPropX() + p.getVitesseX() + p.getRayon()) <= 728
						|| (p.getPropY() + p.getVitesseY() + p.getRayon()) <= 348) {

					for (int k = 0; k < array.size() + 1; k++) {
						for (int j = k + 1; j < array.size(); j++) {

							if (array.size() != 0 && j != 0 && k != 0 && verifCollisions(array.get(k), array.get(j))) {
								faireCollisions(array.get(k), array.get(j));

							}

						}

					}

					deplacerX(p);
					deplacerY(p);

				}

			}
			try {
				sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Vérifie si la particule collisionne avec un des murs verticales
	 *
	 * @param p
	 * @return
	 */
	public boolean verifierCollisionMurX(Particule p) {

		return ((p.getPropX() + p.getVitesseX() + p.getRayon()) > 728
				|| (p.getPropX() + p.getVitesseX() - p.getRayon()) < 0);
	}

	/**
	 * Vérifie si la particule collisionne avec un des murs horizontales
	 *
	 * @param p
	 * @return
	 */
	public boolean verifierCollisionMurY(Particule p) {

		return ((p.getPropY() + p.getVitesseY() + p.getRayon()) > 348
				|| (p.getPropY() + p.getVitesseY() - p.getRayon()) < 0);
	}

	/**
	 * Vérifie si les deux particules collisionnent ensemble
	 *
	 * @param p1
	 * @param p2
	 * @return
	 */
	public boolean verifCollisions(Particule p1, Particule p2) {

		boolean ret = false;

		double distX = p1.getPropX() - p2.getPropX();
		double distY = p1.getPropY() - p2.getPropY();

		double distBalles = Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
		double sommeRayon = p1.getRayon() + p2.getRayon();

		if ((distBalles <= sommeRayon + BUFFERC)
				&& (((p2.getPropX() - p1.getPropX()) * (p2.getVitesseX() - p1.getVitesseX()))
						+ ((p2.getPropY() - p1.getPropY()) * (p2.getVitesseY() - p1.getVitesseY())) < 0)) {
			ret = true;
		}

		return ret;
	}

	/**
	 * Fait la modification des vitesses en X et en Y des deux particules qui se
	 * sont frapper.
	 *
	 * @param p1
	 * @param p2
	 */
	public void faireCollisions(Particule p1, Particule p2) {

		double nvx1 = (p1.getVitesseX() * (p1.getRayon() - p2.getRayon()) + (2 * p2.getRayon() * p2.getVitesseX()))
				/ (p1.getRayon() + p2.getRayon());

		double nvy1 = (p1.getVitesseY() * (p1.getRayon() - p2.getRayon()) + (2 * p2.getRayon() * p2.getVitesseY()))
				/ (p1.getRayon() + p2.getRayon());

		double nvx2 = (p2.getVitesseX() * (p2.getRayon() - p1.getRayon()) + (2 * p1.getRayon() * p1.getVitesseX()))
				/ (p1.getRayon() + p2.getRayon());
		double nvy2 = (p2.getVitesseY() * (p2.getRayon() - p1.getRayon()) + (2 * p1.getRayon() * p1.getVitesseY()))
				/ (p1.getRayon() + p2.getRayon());

		p1.setVitesseX(nvx1);
		p1.setVitesseY(nvy1);

		p2.setVitesseX(nvx2);
		p2.setVitesseY(nvy2);

	}

	/**
	 * Déplace la particule dépendant de la vitesse qu'elle a en X
	 *
	 * @param p
	 */
	public void deplacerX(Particule p) {
		p.setPropX(p.getPropX() + p.getVitesseX());
	}

	/**
	 * Déplace la particule dépendant de la vitesse qu'elle a en Y
	 *
	 * @param p
	 */
	public void deplacerY(Particule p) {
		p.setPropY(p.getPropY() + p.getVitesseY());
	}

	/**
	 * Permet d'avoir le tableau de balle de la classe
	 *
	 * @return
	 */
	public ArrayList<Particule> getArrayBall() {
		return array;
	}

	public void setArrayBall(ArrayList<Particule> arrayBall) {
		this.array = arrayBall;
	}

	/**
	 * Permet d'arrêter le thread. Utiliser lorsqu'on pèse sur le "X" de la
	 * fenetre.
	 */
	public void arreter() {
		run = false;
		stop();

	}

}
