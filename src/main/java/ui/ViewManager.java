package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ViewManager extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		Label label = new Label("hello");
		Scene scene = new Scene(label);
		stage.setScene(scene);
		stage.show();
	}
	
}
