package ui;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mazeLogic.GameState;
import mazeLogic.Rules;
import model.MazeCells;
import model.Player;
import utils.MazeFactory;

public class ViewManager extends Application{
	
	private MazeCells[][] maze;
	private Rules rules;
	private Player player;
	private GameState gameState;
	
	

	@Override
	public void init() throws Exception {
		MazeFactory factory = new MazeFactory(21);
		this.maze = factory.create();
		this.player = new Player("player", this.maze.length-2, this.maze.length-2);
		this.gameState = GameState.IN_GAME;
		this.rules = new Rules(maze, player, gameState);
	}



	@Override
	public void start(Stage stage) throws Exception {
		
		URL fxml = getClass().getResource("/views/mainView.fxml");
		FXMLLoader loader = new FXMLLoader(fxml);
		Parent mainView = loader.load();
		
		Controller controller = loader.getController();
		controller.controllerInitData(this.maze, this.rules);
		
		Scene scene = new Scene(mainView);
		stage.setScene(scene);
		stage.show();
	}
	
}
