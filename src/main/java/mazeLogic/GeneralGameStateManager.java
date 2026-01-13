package mazeLogic;

public class GeneralGameStateManager {
	
	private GameState globalState;

	public GeneralGameStateManager() {
		this.globalState = GameState.SETUP;
	}

	public GameState getGlobalState() {
		return globalState;
	}

	public void setGlobalState(GameState globalState) {
		this.globalState = globalState;
	}
	
}
