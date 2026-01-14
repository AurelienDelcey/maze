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
	
	
	private GeneralGameStateManager state;
	private Rules rules;
	private MazeRenderer renderer;
	private EventHandler<KeyEvent> commandHandler;
	
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
	
	public void controllerInitData(Rules rules, GeneralGameStateManager state, MazeRenderer renderer){
		
		this.rules = rules;
		this.renderer = renderer;
		this.state = state;
		this.commandHandler = e->{
			switch(e.getCode()) {
			case DOWN ->{movePlayer(MoveSet.DOWN);e.consume();}
			case UP ->{movePlayer(MoveSet.UP);e.consume();}
			case LEFT ->{movePlayer(MoveSet.LEFT);e.consume();}
			case RIGHT ->{movePlayer(MoveSet.RIGHT);e.consume();}
			default -> {}
			};
			if(this.state.getGlobalState() == GameState.VICTORY) {
				drawVictory();
			}
		};
		renderer.savePlayerPosition();
		renderer.initMazeRender(this.mazeLayout);
	}
	
	public void setBindingOnScene(boolean state) {
		if(state) {
			this.generalLayout.getScene().addEventHandler(KeyEvent.KEY_PRESSED,this.commandHandler);			
		}else {
			this.generalLayout.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, this.commandHandler);		
		}
	}
	
	private void drawVictory() {
		victoryLabel.setOpacity(1);
		setBindingOnScene(false);
	}

	private void movePlayer(MoveSet move) {
		if(this.rules.applyMove(move)) {
			renderer.updateRender();
			renderer.savePlayerPosition();
		}
	}
}
