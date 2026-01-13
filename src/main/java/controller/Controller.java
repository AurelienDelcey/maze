package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import mazeLogic.GameState;
import mazeLogic.Rules;
import model.MazeCells;
import model.MazeCellsState;
import model.Player;
import utils.MazeFactory;

public class Controller {
	
	private MazeCells[][] maze;
	private Rules rules;
	
	@FXML
	GridPane mazeLayout;
	
	
	@FXML
	public void initialize() {
		
		
	}
	
	@FXML
	public void onClickReset() {
		System.out.println("reset!");
	}
	
	public void initMazeRender() {
		for (int i = 0; i < this.maze.length; i++) {
			for (int j = 0; j < this.maze.length; j++) {
				Canvas mazePart= new Canvas(20,20) ;
				GraphicsContext gc= mazePart.getGraphicsContext2D();
				if(this.maze[i][j].getState()== MazeCellsState.WALL) {
					gc.setFill(Color.CHOCOLATE);
				}else {
					gc.setFill(Color.BEIGE);
				}
				gc.fillRect(0,0,20,20);
				this.mazeLayout.add(mazePart,i,j);
			}
		}
	}

	public void controllerInitData(MazeCells[][] maze, Rules rules ){
		this.maze = maze;
		this.rules = rules;
		initMazeRender();
	}
	
}
