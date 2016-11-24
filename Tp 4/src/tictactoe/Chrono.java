package tictactoe;

import java.util.Timer;
import java.util.TimerTask;

public class Chrono {
	public double temps = 0;
	private Timer chrono = null;
	private TimerTask ajouterTemps = null;

	Chrono() {
		chrono = new Timer();
		ajouterTemps = new TimerTask() {

			@Override
			public void run() {
				temps += 0.02;
			}
		};
		chrono.scheduleAtFixedRate(ajouterTemps, 0, 20);
	}

	public void stopChrono() {
		chrono.cancel();
	}

	public void restartChrono() {
		temps = 0;
		chrono.cancel();
		ajouterTemps.cancel();
		chrono = null;
		ajouterTemps = null;
		chrono = new Timer();
		ajouterTemps = new TimerTask() {

			@Override
			public void run() {
				temps += 0.02;
			}
		};
		chrono.scheduleAtFixedRate(ajouterTemps, 0, 20);
	}
}
