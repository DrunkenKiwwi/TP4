package collisionneur.controleur;

import java.util.ArrayList;
import collisionneur.exception.BordureException;
import collisionneur.modele.ChambreDeParticules;
import collisionneur.modele.InfoParticule;
import collisionneur.modele.Particule;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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

	@FXML
	private void genererBoutton() {
		for (int i = 0; i < NBPARTICULES; i++) {
			Color randomColor = new Color(Math.random(), Math.random(), Math.random(), 1);

			Particule particule = new Particule(Math.random() * (728), Math.random() * (348),
					4 + Math.random() * (11 - 4), Math.random() * 11, Math.random() * (11), Math.random() * (360),
					randomColor);

			ChambreDeParticules chambre = null;
			try {
				chambre = new ChambreDeParticules(particule.getPosX(), particule.getPosY(), particule.getRayon(),
						particule.getVitesseX(), particule.getVitesseY(), particule.getAngle(), particule.getCouleur());
			} catch (BordureException e) {

			}

			ajouterParticules(chambre.getParticule());
		}

	}

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

		ajouterParticules(chambre.getParticule());

	}

	@FXML
	private void reset() {
		arrayBalle.clear();
		pane.getChildren().clear();
		compteur.setText("0");
	}

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

	public void ajouterParticules(Particule p) {
		arrayBalle.add(p);
		Circle c = new Circle(p.getPosX(), p.getPosY(), p.getRayon(), p.getCouleur());

		c.centerXProperty().bind(p.propXProperty());
		c.centerYProperty().bind(p.propYProperty());
		c.setManaged(false);
		pane.getChildren().add(c);
		t.setArrayBall(getArrayBalle());

		compteur.setText(String.valueOf(arrayBalle.size()));

	}

	public ArrayList<Particule> getArrayBalle() {
		return arrayBalle;
	}

}
