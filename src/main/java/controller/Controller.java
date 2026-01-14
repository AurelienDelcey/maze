package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import mazeLogic.GameState;
import mazeLogic.GeneralGameStateManager;
import mazeLogic.Rules;
import model.MoveSet;

public class Controller {
	
	
	private GeneralGameStateManager gameStateManager;
	private Rules rules;
	private MazeRenderer mazeRenderer;
	private EventHandler<KeyEvent> inputHandler;
	
	@FXML
	GridPane mazeLayout;
	
	@FXML
	BorderPane generalLayout;
	
	@FXML
	Label victoryLabel;
	
	
	@FXML
	public void onClickReset() {
		System.out.println("reset!");
	}

	@FXML
	public void initialize() {
		
	}
	
	public void initializeGame(Rules rules, GeneralGameStateManager state, MazeRenderer renderer){
		
		this.rules = rules;
		this.mazeRenderer = renderer;
		this.gameStateManager = state;
		this.inputHandler = e->{
			switch(e.getCode()) {
			case DOWN ->{movePlayer(MoveSet.DOWN);e.consume();}
			case UP ->{movePlayer(MoveSet.UP);e.consume();}
			case LEFT ->{movePlayer(MoveSet.LEFT);e.consume();}
			case RIGHT ->{movePlayer(MoveSet.RIGHT);e.consume();}
			default -> {}
			};
			if(this.gameStateManager.getGlobalState() == GameState.VICTORY) {
				drawVictory();
			}
		};
		renderer.storePreviousPlayerPosition();
		renderer.renderMaze(this.mazeLayout);
	}
	
	public void enableInput(boolean state) {
		if(state) {
			this.generalLayout.getScene().addEventHandler(KeyEvent.KEY_PRESSED,this.inputHandler);			
		}else {
			this.generalLayout.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, this.inputHandler);		
		}
	}
	
	private void drawVictory() {
		victoryLabel.setOpacity(1);
		enableInput(false);
	}

	private void movePlayer(MoveSet move) {
		if(this.rules.applyMove(move)) {
			mazeRenderer.renderPlayerMove();
			mazeRenderer.storePreviousPlayerPosition();
		}
	}
}
