package collisionneur.controleur;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import collisionneur.modele.ChambreDeParticules;
import collisionneur.modele.Particule;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CtrlCollisionneur {

	@FXML
	private Label compteur;

	@FXML
	private Pane pane;

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

	@FXML
	public void initialize() {
		arrayBalle = new ArrayList<Particule>();
	}

	@FXML
	private void genererBoutton() {
		Particule particule = new Particule(
				ThreadLocalRandom.current().nextDouble(0 + sliderR.getValue(), pane.getWidth() - sliderR.getValue()),
				ThreadLocalRandom.current().nextDouble(0 + sliderR.getValue(), pane.getHeight() - sliderR.getValue()),
				sliderR.getValue(), sliderV.getValue(), sliderA.getValue(), colorp.getValue());

		ChambreDeParticules chambre = new ChambreDeParticules(particule.getPosX(), particule.getPosY(),
				particule.getRayon(), particule.getVitesse(), particule.getAngle(), particule.getCouleur());
		arrayBalle.add(chambre.getParticule());
	}

	@FXML
	private void genererClick(MouseEvent event) {
		System.out.println("dans la fonction");
		Particule particule = new Particule(event.getX(), event.getY(), sliderR.getValue(), sliderV.getValue(),
				sliderA.getValue(), colorp.getValue());

		ChambreDeParticules chambre = new ChambreDeParticules(particule.getPosX(), particule.getPosY(),
				particule.getRayon(), particule.getVitesse(), particule.getAngle(), particule.getCouleur());
		arrayBalle.add(chambre.getParticule());

	}

	@FXML
	private void reset() {
		arrayBalle.clear();
	}

	@FXML
	private void fermer() {
		Stage stage = (Stage) borderp.getScene().getWindow();
		stage.close();
	}

	public Pane getPane() {
		return pane;
	}

	public void setPane(Pane pane) {
		this.pane = pane;
	}

	public void ajouterParticules() {
		for (int i = 0; i < arrayBalle.size() - 1; i++) {
			pane.getChildren().add(new Circle(arrayBalle.get(i).getPosX(), arrayBalle.get(i).getPosY(),
					arrayBalle.get(i).getRayon(), arrayBalle.get(i).getCouleur()));

		}
	}

}
