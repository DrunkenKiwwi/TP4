package collisionneur.modele;

import java.util.ArrayList;

public class InfoParticule extends Thread {

	ArrayList<Particule> array;
	private static final int BUFFER = 2;
	private static final int BUFFERC = 20;
	private boolean run = true;

	public InfoParticule(ArrayList<Particule> arrayB) {
		setArrayBall(arrayB);
	}

	@Override
	public void run() {

		while (run) {

			for (int i = 0; i < array.size(); i++) {

				Particule p = array.get(i);

				if (verifierCollisionMurX(p)) {
					p.setVitesseX(-p.getVitesseX());

				} else if (verifierCollisionMurY(p)) {

					p.setVitesseY(-p.getVitesseY());

				} else if ((p.getPropX() + p.getVitesseX() + p.getRayon() + BUFFER) <= 728
						|| (p.getPropY() + p.getVitesseY() + p.getRayon() + BUFFER) <= 348) {

					for (int k = 0; k < array.size() - 1; k++) {
						for (int j = k + 1; j < array.size(); j++) {
							if (verifCollisions(array.get(k), array.get(j))) {
								faireCollisions(array.get(k), array.get(j));

							}
						}
					}

					deplacerX(p);
					deplacerY(p);
				}

				// System.out.println(p.getPropX());
				// System.out.println(p.getPropY());
			}
			try {
				sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean verifierCollisionMurX(Particule p) {

		return ((p.getPropX() + p.getVitesseX() + p.getRayon()) > 728 - BUFFER
				|| (p.getPropX() + p.getVitesseX() + p.getRayon()) < 0 + BUFFER);
	}

	public boolean verifierCollisionMurY(Particule p) {

		return ((p.getPropY() + p.getVitesseY() + p.getRayon()) > 348 - BUFFER
				|| (p.getPropY() + p.getVitesseY() + p.getRayon()) < 0 + BUFFER);
	}

	public boolean verifCollisions(Particule p1, Particule p2) {

		boolean ret = false;

		double distX = p1.getPropX() - p2.getPropX();
		double distY = p1.getPropY() - p2.getPropY();

		System.out.println(Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2)));
		double distBalles = Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
		double sommeRayon = p1.getRayon() + p2.getRayon();

		if (distBalles <= sommeRayon + 10) {
			ret = true;
		}

		return ret;
	}

	public void faireCollisions(Particule p1, Particule p2) {
		// TODO - refaire les calculs physiques
		double nvx1 = (p1.getVitesseX() * (p1.getRayon() - p2.getRayon())
				+ (2 * p2.getRayon() * p2.getVitesseX()) / (p1.getRayon() + p2.getRayon()));
		double nvy1 = (p1.getVitesseY() * (p1.getRayon() - p2.getRayon())
				+ (2 * p2.getRayon() * p2.getVitesseY()) / (p1.getRayon() + p2.getRayon()));

		double nvx2 = (p2.getVitesseX() * (p2.getRayon() - p1.getRayon())
				+ (2 * p1.getRayon() * p1.getVitesseX()) / (p1.getRayon() + p2.getRayon()));
		double nvy2 = (p2.getVitesseY() * (p2.getRayon() - p1.getRayon())
				+ (2 * p1.getRayon() * p1.getVitesseY()) / (p1.getRayon() + p2.getRayon()));

		p1.setVitesseX(nvx1);
		p1.setVitesseY(nvy1);

		p2.setVitesseX(nvx2);
		p2.setVitesseY(nvy2);
	}

	public void deplacerX(Particule p) {
		p.setPropX(p.getPropX() + p.getVitesseX());
	}

	public void deplacerY(Particule p) {
		p.setPropY(p.getPropY() + p.getVitesseY());
	}

	public ArrayList<Particule> getArrayBall() {
		return array;
	}

	public void setArrayBall(ArrayList<Particule> arrayBall) {
		this.array = arrayBall;
	}

	public void arreter() {
		run = false;
		stop();

	}

}
