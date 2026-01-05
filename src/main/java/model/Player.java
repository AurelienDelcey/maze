package model;

public class Player {
	
	private final String name;
	private int xCoordonate;
	private int yCoordonate;
	private final MoveSet[] moveSet;

	public Player(String name, int xCoordonate, int yCoordonate, MoveSet[] moveSet) {
		this.name = name;
		this.xCoordonate = xCoordonate;
		this.yCoordonate = yCoordonate;
		this.moveSet = moveSet;
	}

	public int getxCoordonate() {
		return xCoordonate;
	}

	public void setxCoordonate(int xCoordonate) {
		this.xCoordonate = xCoordonate;
	}

	public int getyCoordonate() {
		return yCoordonate;
	}

	public void setyCoordonate(int yCoordonate) {
		this.yCoordonate = yCoordonate;
	}

	public String getName() {
		return name;
	}

	public MoveSet[] getMoveSet() {
		return moveSet;
	}

}
