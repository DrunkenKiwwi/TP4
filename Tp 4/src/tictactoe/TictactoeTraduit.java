package tictactoe;

public class TictactoeTraduit {

	// NOTE: Première game, ordi commence, sinon le gagnant commence

	// Pour les cases, 'E' est vide, 'C' est une case de l'ordi,
	// 'P' est une case du joueur
	private char[] cases = { 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E' };
	private char symboleOrdi = ' ';

	// Chances de rater des types de coups
	// Dans l'ordre: généraux, faciles, techniques
	private final float[] chancesFacile = { 1, 1, 1 };
	private final float[] chancesDifficile = { 0.1f, 0.01f, 0.2f };
	private final float[] chancesExpert = { 0, 0, 0 };

	private float[] chancesChoisies = new float[3];
	private int nombreTours = 0;
	private int coins = 0;
	private int bordures = 0;
	private int centre = 0;
	private int caseClic = 0;
	private boolean drapeauCoin = false;
	private boolean drapeauBordure = false;
	private boolean drapeauAttaqueRapide = false;
	private char gagnant = 'X';

	TictactoeTraduit() {
	}

	private int jouerSelonPresquePlein(char filtre) {
		// filtre permet de choisir si on veut gagner ('C') ou bloquer ('P')
		int choix = -1;
		// Vérifier lignes
		for (int i = 0; i < 7; i += 3) {
			choix = ((cases[i] == 'E') && (cases[i + 1] == filtre) && (cases[i + 2] == filtre)) ? i : choix;
		}
		for (int i = 1; i < 8; i += 3) {
			choix = ((cases[i] == 'E') && (cases[i + 1] == filtre) && (cases[i - 1] == filtre)) ? i : choix;
		}
		for (int i = 2; i < 9; i += 3) {
			choix = ((cases[i] == 'E') && (cases[i - 1] == filtre) && (cases[i - 2] == filtre)) ? i : choix;
		}
		// Vérifier colonnes
		for (int i = 0; i < 3; i++) {
			choix = ((cases[i] == 'E') && (cases[i + 3] == filtre) && (cases[i + 6] == filtre)) ? i : choix;
		}
		for (int i = 3; i < 6; i++) {
			choix = ((cases[i] == 'E') && (cases[i + 3] == filtre) && (cases[i - 3] == filtre)) ? i : choix;

		}
		for (int i = 6; i < 3; i++) {
			choix = ((cases[i] == 'E') && (cases[i - 3] == filtre) && (cases[i - 6] == filtre)) ? i : choix;
		}
		// Vérifier diagonales
		choix = ((cases[0] == 'E') && (cases[4] == filtre) && (cases[8] == filtre)) ? 0 : choix;
		choix = ((cases[4] == 'E') && (cases[0] == filtre) && (cases[8] == filtre)) ? 4 : choix;
		choix = ((cases[8] == 'E') && (cases[4] == filtre) && (cases[0] == filtre)) ? 8 : choix;
		choix = ((cases[2] == 'E') && (cases[4] == filtre) && (cases[6] == filtre)) ? 2 : choix;
		choix = ((cases[4] == 'E') && (cases[2] == filtre) && (cases[6] == filtre)) ? 4 : choix;
		choix = ((cases[6] == 'E') && (cases[4] == filtre) && (cases[2] == filtre)) ? 6 : choix;

		return choix;
	}

	private int jouerCoinsEntoures(int filtre) {
		// filtre est le nombre de cases du joueur qu'on veut voir autour du
		// coin pour jouer dedans
		int choix = -1;
		int case0 = 0;
		int case2 = 0;
		int case6 = 0;
		int case8 = 0;
		if (cases[1] == 'P') {
			case0++;
			case2++;
		}
		if (cases[3] == 'P') {
			case0++;
			case6++;
		}
		if (cases[5] == 'P') {
			case2++;
			case8++;
		}
		if (cases[7] == 'P') {
			case6++;
			case8++;
		}
		choix = (case0 == filtre) ? 0 : choix;
		choix = (case2 == filtre) ? 2 : choix;
		choix = (case6 == filtre) ? 6 : choix;
		choix = (case8 == filtre) ? 8 : choix;

		return choix;
	}

	private int jouerAuHasard(char filtre) {
		// filtre donne le type de case cible
		// 'O' est n'importe où, 'C' est dans un coin, 'B' est sur une bordure
		int choix = -1;
		boolean fin = false;
		int nombreAlea = -1;
		switch (filtre) {
		case 'O':
			while (!fin) {
				nombreAlea = (int) Math.floor(Math.random() * 9);
				if (cases[nombreAlea] == 'E') {
					choix = nombreAlea;
					fin = true;
				}
			}
			break;
		case 'C':
			while (!fin) {
				nombreAlea = (int) Math.floor(Math.random() * 4);
				switch (nombreAlea) {
				case 0:
					if (cases[0] == 'E') {
						choix = 0;
						fin = true;
					}
					break;
				case 1:
					if (cases[2] == 'E') {
						choix = 2;
						fin = true;
					}
					break;
				case 2:
					if (cases[6] == 'E') {
						choix = 6;
						fin = true;
					}
					break;
				case 3:
					if (cases[8] == 'E') {
						choix = 8;
						fin = true;
					}
					break;
				}
			}
			break;
		case 'B':
			while (!fin) {
				nombreAlea = (int) Math.floor(Math.random() * 4) * 2 + 1;
				if (cases[nombreAlea] == 'E') {
					choix = nombreAlea;
					fin = true;
				}
			}
			break;
		}

		return choix;
	}

	private int jouerCoinOppose() {
		int choix = -1;

		if (symboleOrdi == 'X') {
			choix = (cases[0] == 'C') ? 8 : choix;
			choix = (cases[2] == 'C') ? 6 : choix;
			choix = (cases[6] == 'C') ? 2 : choix;
			choix = (cases[8] == 'C') ? 0 : choix;
		} else {
			choix = (cases[0] == 'P') ? 8 : choix;
			choix = (cases[2] == 'P') ? 6 : choix;
			choix = (cases[6] == 'P') ? 2 : choix;
			choix = (cases[8] == 'P') ? 0 : choix;
		}

		return choix;
	}

	private boolean raterCoup(char filtre) {
		// filtre donne le type de coup tenté
		// 'G' pour général, 'F' pour facile et 'T' pour technique
		boolean retour = false;
		switch (filtre) {
		case 'G':
			retour = (Math.random() < chancesChoisies[0]);
			break;
		case 'F':
			retour = (Math.random() < chancesChoisies[1]);
			break;
		case 'T':
			retour = (Math.random() < chancesChoisies[2]);
			break;
		}

		return retour;
	}

	public char trouverGagnant() {
		char gagnant = 'X';
		// Vérifier lignes
		for (int i = 0; i < 7; i += 3) {
			gagnant = ((cases[i] == cases[i + 1]) && (cases[i] == cases[i + 2])) ? cases[i] : gagnant;
		}
		// Vérifier colonnes
		for (int i = 0; i < 3; i++) {
			gagnant = ((cases[i] == cases[i + 3]) && (cases[i] == cases[i + 6])) ? cases[i] : gagnant;
		}
		// Diagonales
		gagnant = ((cases[0] == cases[4]) && (cases[0] == cases[8])) ? cases[0] : gagnant;
		gagnant = ((cases[2] == cases[4]) && (cases[2] == cases[6])) ? cases[0] : gagnant;

		return gagnant;
	}

	public char[] grilleJeu() {
		char[] grille = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < 9; i++) {
			if (cases[i] == 'E') {
				grille[i] = '-';
			} else if (cases[i] == 'C') {
				grille[i] = symboleOrdi;
			} else {
				if (symboleOrdi == 'X') {
					grille[i] = 'O';
				} else {
					grille[i] = 'X';
				}
			}
		}

		return grille;
	}

	public void jeu(int caseJeu) {
		// TODO finition ptêtre
		// Vérification que la case choisie est vide
		if (cases[caseJeu] == 'E') {
			// On inscrit le jeu dans la grille
			cases[caseJeu] = 'P';
			// Incrémenter le compteur approprié
			if ((caseJeu == 0) || (caseJeu == 2) || (caseJeu == 6) || (caseJeu == 8)) {
				coins++;
			} else if ((caseJeu == 1) || (caseJeu == 3) || (caseJeu == 5) || (caseJeu == 7)) {
				bordures++;
			} else {
				centre = 1;
			}
			int choixOrdi = -1;
			char typeChance = ' ';
			if (trouverGagnant() == 'X') {
				if (symboleOrdi == 'X') {
					switch (nombreTours) {
					case 1:
						if (centre == 1) {
							choixOrdi = jouerCoinOppose();
							typeChance = 'T';
						} else if (coins == 1) {
							choixOrdi = jouerAuHasard('C');
							typeChance = 'G';
							drapeauCoin = true;
						} else {
							choixOrdi = 4; // centre
							typeChance = 'T';
							drapeauBordure = true;
						}
						break;
					case 2:
						if ((centre == 1) && (coins == 1)) {
							choixOrdi = jouerAuHasard('C');
							typeChance = 'F';
						}
						if (drapeauCoin) {
							choixOrdi = jouerAuHasard('C');
							typeChance = 'G';
						}
						if (drapeauBordure) {
							choixOrdi = jouerSelonPresquePlein('P');
							typeChance = 'F';
							if (choixOrdi == -1) {
								choixOrdi = jouerCoinsEntoures(0);
								typeChance = 'G';
							}
						}
						break;
					}
				} else {
					// Quand ordi est O
					switch (nombreTours) {
					case 0:
						if (centre == 0) {
							choixOrdi = 4; // centre
							typeChance = 'T';
						} else {
							choixOrdi = jouerAuHasard('C');
							typeChance = 'T';
						}
						break;
					case 1:
						choixOrdi = jouerSelonPresquePlein('P');
						if (choixOrdi != 0) {
							drapeauAttaqueRapide = true;
							typeChance = 'F';
						}
						if ((coins == 1) && (bordures == 1)) {
							choixOrdi = jouerCoinOppose();
							typeChance = 'T';
						}
						if (bordures == 2) {
							choixOrdi = jouerCoinsEntoures(2);
							if (choixOrdi == -1) {
								choixOrdi = jouerAuHasard('B');
								typeChance = 'T';
							} else {
								typeChance = 'T';
							}
						}
						if ((centre == 1) && (choixOrdi == -1)) {
							choixOrdi = jouerAuHasard('C');
							typeChance = 'T';
						}
						if (choixOrdi == -1) {
							choixOrdi = jouerAuHasard('E');
							typeChance = 'T';
						}
						break;
					case 2:
						if (drapeauAttaqueRapide) {
							choixOrdi = jouerSelonPresquePlein('P');
							if (choixOrdi == -1) {
								choixOrdi = jouerAuHasard('B');
								typeChance = 'T';
							}
						}
						if (((cases[1] == 'P') && (cases[7] == 'P')) || ((cases[3] == 'P') && (cases[5] == 'P'))) {
							choixOrdi = jouerCoinsEntoures(1);
							typeChance = 'G';
						}
						break;
					case 4:
						// Grille pleine
						gagnant = 'E';
						// 'E' pour égalité
						break;

					}
				}
			}
			if (raterCoup(typeChance)) {
				// Si l'ordi rate son coup, on annule la décision prise
				choixOrdi = -1;
			}
			// Bloquer ou gagner (gagner se fait après pour qu'il
			// prenne la priorité)
			int resultatBloc = jouerSelonPresquePlein('P');
			int resultatGagner = jouerSelonPresquePlein('C');
			// Vérifier si un des tests est concluant, et voir si
			// l'ordi le rate
			if (resultatBloc != -1) {
				if (!raterCoup('F')) {
					choixOrdi = resultatBloc;
				}
			}
			if (resultatGagner != -1) {
				if (!raterCoup('F')) {
					choixOrdi = resultatGagner;
				}
			}
			// Si l'ordi n'a toujours rien choisi (ou qu'il a
			// raté un coup), il joue au hasard
			if (choixOrdi == -1) {
				choixOrdi = jouerAuHasard('O');
			}
			// Écrire dans la grille le coup de l'ordi
			cases[choixOrdi] = 'C';
			nombreTours++;
			gagnant = trouverGagnant();
			if (nombreTours > 4) {
				gagnant = 'E';
			}
			// TODO faut dealer avec le fait qu'il y a un gagnant (ou pas)
			// pis probablement caller une boîte de dialogue
		}

	}

	public void nouvellePartie(boolean premier) {
		for (int i = 0; i < 9; i++) {
			cases[i] = 'E';
		}
		coins = 0;
		bordures = 0;
		centre = 0;
		drapeauCoin = false;
		drapeauBordure = false;
		drapeauAttaqueRapide = false;
		nombreTours = 0;
		gagnant = 'X';
		if (premier) {
			symboleOrdi = 'X';
			int jeu = -1;
			if (raterCoup('T')) {
				jeu = jouerAuHasard('O');
			} else {
				jeu = jouerAuHasard('C');
			}
			cases[jeu] = 'C';
			nombreTours++;
		} else {
			symboleOrdi = 'O';
		}
	}

	public void changerDifficulte(char nouvelleDiff) {
		// 'F' facile, 'D' difficile, 'E' expert
		switch (nouvelleDiff) {
		case 'F':
			chancesChoisies = chancesFacile;
			break;
		case 'D':
			chancesChoisies = chancesDifficile;
			break;
		case 'E':
			chancesChoisies = chancesExpert;
			break;
		}
		nouvellePartie(true);
	}
}
