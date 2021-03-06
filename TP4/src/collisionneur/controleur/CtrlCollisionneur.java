package collisionneur.controleur;

import java.util.ArrayList;
import collisionneur.exception.BordureException;
import collisionneur.modele.ChambreDeParticules;
import collisionneur.modele.InfoParticule;
import collisionneur.modele.Particule;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CtrlCollisionneur {

	private static final int NBPARTICULES = 2;
	

	@FXML
	private Label compteur;

	@FXML
	private StackPane pane;

	@FXML
	private BorderPane borderp;

	@FXML
	private Slider sliderV;

	@FXML
	private Slider sliderA;

	@FXML
	private Slider sliderR;

	@FXML
	private ColorPicker colorp;

	private ArrayList<Particule> arrayBalle;

	private InfoParticule t = null;

	@FXML

	public void initialize() {
		arrayBalle = new ArrayList<Particule>();
		t = new InfoParticule(getArrayBalle());
		colorp.setValue(Color.AQUA);
		t.start();

	}

	/**
	 * Genere une particule lorsqu'on clique sur le boutton "Générer", verifie
	 * ses paramêtre à l'aide d'une objet transitoire puis l'ajoute au tableau
	 * de particule.
	 */
	@FXML
	private void genererBoutton() {
		for (int i = 0; i < NBPARTICULES; i++) {
			Color randomColor = new Color(Math.random(), Math.random(), Math.random(), 1);

			Particule particule = new Particule(Math.random() * (700), Math.random() * (330),
					4 + Math.random() * (11 - 4), Math.random() * 11, Math.random() * (11), Math.random() * (360),
					randomColor);

			ChambreDeParticules chambre = null;
			try {
				chambre = new ChambreDeParticules(particule.getPosX(), particule.getPosY(), particule.getRayon(),
						particule.getVitesseX(), particule.getVitesseY(), particule.getAngle(), particule.getCouleur());
			} catch (BordureException e) {

			}
			if (chambre != null) {
				ajouterParticules(chambre.getParticule());
			}
		}

	}

	/**
	 * Genere une particule lorsqu'on clique sur le StackPane, verifie ses
	 * paramêtre à l'aide d'une objet transitoire puis l'ajoute au tableau de
	 * particule.
	 *
	 * @param event
	 */
	@FXML
	private void genererClick(MouseEvent event) {

		Particule particule = new Particule(event.getX(), event.getY(), sliderR.getValue(),
				(sliderV.getValue() * Math.cos(Math.toRadians(sliderA.getValue()))),
				(sliderV.getValue() * Math.sin(Math.toRadians(-sliderA.getValue()))), sliderA.getValue(),
				colorp.getValue());

		ChambreDeParticules chambre = null;
		try {
			chambre = new ChambreDeParticules(particule.getPosX(), particule.getPosY(), particule.getRayon(),
					particule.getVitesseX(), particule.getVitesseY(), particule.getAngle(), particule.getCouleur());
		} catch (BordureException e) {

		}
		if (chambre != null) {
			ajouterParticules(chambre.getParticule());
		}

	}

	/**
	 * Clear le StackPane des particules et vide le tableau Remts le compteur à
	 * zéro
	 */
	@FXML
	private void reset() {
		arrayBalle.clear();
		pane.getChildren().clear();
		compteur.setText("0");
	}

	/**
	 * Ferme le stage lorsqu'on appuie sur "Quitter"
	 */
	@FXML
	private void fermer() {
		Stage stage = (Stage) borderp.getScene().getWindow();
		t.arreter();
		stage.close();

	}

	public Pane getPane() {
		return pane;
	}

	public void setPane(StackPane pane) {
		this.pane = (StackPane) pane;
	}

	/**
	 * Crée une shape à l'aide du la particule reçu en paramêtre et l'ajoute à
	 * la StackPane. Change la String du compteur afin de l'adapter au nombre de
	 * particule
	 *
	 * @param p
	 */
	public void ajouterParticules(Particule p) {

		Circle c = new Circle(p.getPosX(), p.getPosY(), p.getRayon(), p.getCouleur());

		c.centerXProperty().bind(p.propXProperty());
		c.centerYProperty().bind(p.propYProperty());
		c.setManaged(false);
		
		pane.getChildren().add(c);
		
		t.setArrayBall(getArrayBalle());
		arrayBalle.add(p);
		compteur.setText(String.valueOf(arrayBalle.size()));

	}

	public ArrayList<Particule> getArrayBalle() {
		return arrayBalle;
	}

}
