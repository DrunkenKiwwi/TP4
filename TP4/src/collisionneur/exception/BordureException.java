package collisionneur.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BordureException extends Exception {
	public BordureException() {
		Alert err = new Alert(AlertType.ERROR);
		err.setTitle("Erreur");
		err.setHeaderText("Balle à l'extérieur de l'espace");
		err.setContentText("Veuillez cliquer un peu plus vers le centre!");
		err.showAndWait();
	}


}
