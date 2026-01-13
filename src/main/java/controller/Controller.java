package controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import mazeLogic.GameState;
import mazeLogic.Rules;
import model.MazeCells;
import model.MazeCellsState;
import model.MoveSet;
import model.Player;
import utils.MazeFactory;

public class Controller {
	
	private static final Color WALL_COLOR = Color.CHOCOLATE;
	private static final Color PATH_COLOR = Color.BEIGE;
	private static final Color PLAYER_COLOR = Color.DARKGOLDENROD;
	private  Player player;
	private Canvas[][] renderCanvas;
	private  MazeCells[][] maze;
	private Rules rules;
	private int cellSize = 20;
	private int margin = 5;
	private int previousPositionX;
	private int previousPositionY;
	/*private IntegerProperty playerPositionX;
	private IntegerProperty playerPositionY;*/
	
	@FXML
	GridPane mazeLayout;
	
	@FXML
	BorderPane generalLayout;
	
	
	@FXML
	public void initialize() {
		
	}
	
	@FXML
	public void onClickReset() {
		System.out.println("reset!");
	}
	
	public void initMazeRender() {
		for (int i = 0; i < this.maze.length; i++) {
			for (int j = 0; j < this.maze[i].length; j++) {
				Canvas mazePart= new Canvas(this.cellSize,this.cellSize) ;
				GraphicsContext gc= mazePart.getGraphicsContext2D();
				if(this.maze[i][j].getState()== MazeCellsState.WALL) {
					gc.setFill(WALL_COLOR);
				}else {
					gc.setFill(PATH_COLOR);
				}
				gc.fillRect(0,0,this.cellSize,this.cellSize);
				
				drawStartAndGoal(i, j, gc);
				this.mazeLayout.add(mazePart,i,j);
				this.renderCanvas[i][j] = mazePart;
			}
		}
		drawPlayer();
	}
	
	private void updateRender() {
		redrawPlayerPositionCanvas();
		redrawPreviousPositionCanvas();
	}
	
	private void redrawPlayerPositionCanvas() {
		Canvas canvas = this.renderCanvas[this.player.getxCoordonate()][this.player.getyCoordonate()];
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, cellSize, cellSize);
		gc.setFill(PATH_COLOR);
		gc.fillRect(0,0,this.cellSize,this.cellSize);
		drawPlayer();
		
	}
	
	private void redrawPreviousPositionCanvas() {
		Canvas canvas = this.renderCanvas[this.previousPositionX][this.previousPositionY];
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, cellSize, cellSize);
		gc.setFill(PATH_COLOR);
		gc.fillRect(0,0,this.cellSize,this.cellSize);
		drawStartAndGoal(this.previousPositionX,this.previousPositionY,gc);
	}
	
	private void drawPlayer() {
		Canvas canvas = this.renderCanvas[this.player.getxCoordonate()][this.player.getyCoordonate()];
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(PLAYER_COLOR);
		gc.fillOval(this.margin/2, this.margin/2, this.cellSize-this.margin, this.cellSize-this.margin);
	}
	
	private void drawStartAndGoal(int x, int y, GraphicsContext gc) {
		switch(this.maze[x][y].getState()){
			case GOAL ->{
				gc.setFill(Color.BLUE);
				gc.fillOval(this.margin/2, this.margin/2, this.cellSize-this.margin, this.cellSize-this.margin);
			}
			case START ->{
				gc.setFill(Color.GREEN);
				gc.fillOval(this.margin/2, this.margin/2, this.cellSize-this.margin, this.cellSize-this.margin);	
			}
		default -> {}
		};
	}
	
	private void savePlayerPosition() {
		this.previousPositionX = this.player.getxCoordonate();
		this.previousPositionY = this.player.getyCoordonate();
	}
	
	public void setBindingOnScene() {
		this.generalLayout.getScene().addEventHandler(KeyEvent.KEY_PRESSED,e->{
			switch(e.getCode()) {
			case DOWN ->{
					if(this.rules.applyMove(MoveSet.DOWN)) {
						updateRender();
						savePlayerPosition();
					}
					e.consume();
				}
			case UP ->{
				if(this.rules.applyMove(MoveSet.UP)) {
					updateRender();
					savePlayerPosition();
				}
				e.consume();
			}
			case LEFT ->{
				if(this.rules.applyMove(MoveSet.LEFT)) {
					updateRender();
					savePlayerPosition();
				}
				e.consume();
			}
			case RIGHT ->{
				if(this.rules.applyMove(MoveSet.RIGHT)) {
					updateRender();
					savePlayerPosition();
				}
				e.consume();
			}
			default -> {}
			};
		});
	}

	public void controllerInitData(MazeCells[][] maze, Rules rules, Player player ){
		this.maze = maze;
		this.rules = rules;
		this.renderCanvas = new Canvas[maze.length][maze[0].length];
		this.player = player;
		savePlayerPosition();
		
		/*this.playerPositionX = new SimpleIntegerProperty(this.player.getxCoordonate());
		this.playerPositionY = new SimpleIntegerProperty(this.player.getyCoordonate());
		this.playerPositionX.addListener(i->{
			redrawPreviousPositionCanvas();
			redrawPlayerPositionCanvas();
			savePlayerPosition();
		});
		this.playerPositionY.addListener(i->{
			redrawPreviousPositionCanvas();
			redrawPlayerPositionCanvas();
			savePlayerPosition();
		});*/
		initMazeRender();
	}
	
}
