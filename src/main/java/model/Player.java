package model;

import java.util.EnumSet;

public class Player {
	
	private final String name;
	private int xCoordonate;
	private int yCoordonate;
	private final EnumSet<MoveSet> moveSet;

	public Player(String name, int xCoordonate, int yCoordonate) {
		this.name = name;
		this.xCoordonate = xCoordonate;
		this.yCoordonate = yCoordonate;
		this.moveSet = EnumSet.allOf(MoveSet.class);
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

	public EnumSet<MoveSet> getMoveSet() {
		return EnumSet.copyOf(this.moveSet);
	}

}
