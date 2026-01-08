package utils;


import java.util.EnumSet;
import java.util.Set;

import model.MoveSet;

public class TemplateCell {
	private final int xCoordinate;
	private final int yCoordinate;
	private boolean visited;
	private final Set<MoveSet> walls;
	
	
	public TemplateCell(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.visited = false;
		this.walls = EnumSet.allOf(MoveSet.class);
	}


	public boolean isVisited() {
		return visited;
	}


	public void setVisited() {
		this.visited = true;
	}


	public void removeWall(MoveSet toDelete) {
		if(walls.contains(toDelete)) {
			walls.remove(toDelete);
		}
	}
	public int countWalls() {
		return walls.size();
	}
	
	public boolean hasWall(MoveSet direction) {
		return walls.contains(direction);
	}


	public int getxCoordinate() {
		return xCoordinate;
	}


	public int getyCoordinate() {
		return yCoordinate;
	}


	public Set<MoveSet> getWalls() {
		return walls;
	}
}
