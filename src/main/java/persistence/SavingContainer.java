package persistence;

import model.MazeCells;
import model.Player;

public class SavingContainer {
	
	private final MazeCells[][] mazeToSave;
	private final Player playerToSave;
	
	public SavingContainer(MazeCells[][] mazeToSave, Player playerToSave) {
		this.mazeToSave = mazeToSave;
		this.playerToSave = playerToSave;
	}

	public MazeCells[][] getMazeToSave() {
		return mazeToSave;
	}

	public Player getPlayerToSave() {
		return playerToSave;
	}
	
	
}
