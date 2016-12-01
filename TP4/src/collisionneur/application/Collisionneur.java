package collisionneur.application;

import collisionneur.controleur.CtrlCollisionneur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Collisionneur extends Application {

	private CtrlCollisionneur CDF = null;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/collisionneur/vue/VueCollisionneur.fxml"));

		BorderPane root = (BorderPane) loader.load();
		CDF = loader.getController();

		Scene scene = new Scene(root);

		stage.setTitle("Collsionneur");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		

	}

}
