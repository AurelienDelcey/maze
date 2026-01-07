package model;



public class MazeCells {
	
	private final int xCoordonate;
	private final int yCoordonate;
	private  MazeCellsState state;
	
	public MazeCells(int xCoordonate, int yCoordonate) {
		super();
		this.xCoordonate = xCoordonate;
		this.yCoordonate = yCoordonate;
		this.state = MazeCellsState.EMPTY;
	}

	public MazeCellsState getState() {
		return state;
	}

	public void setState(MazeCellsState state) {
		this.state = state;
	}

	public int getxCoordonate() {
		return xCoordonate;
	}

	public int getyCoordonate() {
		return yCoordonate;
	}
	
	
}
