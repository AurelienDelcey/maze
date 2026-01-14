package ui;

import java.net.URL;

import controller.Controller;
import controller.MazeRenderer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mazeLogic.GeneralGameStateManager;
import mazeLogic.Rules;
import model.MazeCells;
import model.Player;
import utils.MazeFactory;

public class ViewManager extends Application{
	
	private MazeCells[][] maze;
	private Rules rules;
	private Player player;
	private GeneralGameStateManager gameState;
	private MazeRenderer renderer;
	
	

	@Override
	public void init() throws Exception {
		MazeFactory factory = new MazeFactory(21);
		this.maze = factory.create();
		this.player = new Player("player", this.maze.length-2, this.maze.length-2);
		this.gameState = new GeneralGameStateManager();
		this.rules = new Rules(maze, player, gameState);
		this.renderer = new MazeRenderer(this.player, this.maze);
	}



	@Override
	public void start(Stage stage) throws Exception {
		
		URL fxml = getClass().getResource("/views/mainView.fxml");
		FXMLLoader loader = new FXMLLoader(fxml);
		Parent mainView = loader.load();
		
		Controller controller = loader.getController();
		controller.controllerInitData(this.rules, this.gameState, this.renderer);
		
		Scene scene = new Scene(mainView);
		controller.setBindingOnScene(true);
		stage.setScene(scene);
		stage.show();
	}
	
}
