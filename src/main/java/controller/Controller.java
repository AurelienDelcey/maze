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
	
	private static final Color WALL_COLOR = Color.CHOCOLATE;
	private static final Color PATH_COLOR = Color.BEIGE;
	private  MazeCells[][] maze;
	private Rules rules;
	private int cellSize = 20;
	private int margin = 5;
	
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
				Canvas mazePart= new Canvas(this.cellSize,this.cellSize) ;
				GraphicsContext gc= mazePart.getGraphicsContext2D();
				if(this.maze[i][j].getState()== MazeCellsState.WALL) {
					gc.setFill(WALL_COLOR);
				}else {
					gc.setFill(PATH_COLOR);
				}
				gc.fillRect(0,0,this.cellSize,this.cellSize);
				
				if(this.maze[i][j].getState()== MazeCellsState.GOAL) {
					gc.setFill(Color.BLUE);
					gc.fillOval(this.margin/2, this.margin/2, this.cellSize-this.margin, this.cellSize-this.margin);
				}
				if(this.maze[i][j].getState()== MazeCellsState.START) {
					gc.setFill(Color.GREEN);
					gc.fillOval(this.margin/2, this.margin/2, this.cellSize-this.margin, this.cellSize-this.margin);
				}
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
