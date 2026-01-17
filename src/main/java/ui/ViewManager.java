package ui;

import java.net.URL;

import controller.Controller;
import controller.MazeRenderer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mazeLogic.GameContext;
import mazeLogic.Rules;

public class ViewManager extends Application{
	
	private Rules rules;
	private GameContext context;
	private MazeRenderer mazeRenderer;
	
	

	@Override
	public void init() throws Exception {
		this.context = new GameContext();
		this.rules = new Rules(context);
		this.mazeRenderer = new MazeRenderer(this.context);
	}

               //TODO CREATE GAME CONTEXT!!

	@Override
	public void start(Stage stage) throws Exception {
		
		URL fxml = getClass().getResource("/views/mainView.fxml");
		FXMLLoader loader = new FXMLLoader(fxml);
		Parent mainView = loader.load();
		
		Controller controller = loader.getController();
		controller.initializeGame(this.rules, this.context, this.mazeRenderer);
		
		Scene scene = new Scene(mainView);
		controller.enableInput(true);
		stage.setScene(scene);
		stage.show();
	}
	
}
