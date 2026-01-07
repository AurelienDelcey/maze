package utils;


import model.MoveSet;

public class TemplateCell {
	private final int xCoordinate;
	private final int yCoordinate;
	private boolean visited;
	private MoveSet[] walls;
	
	
	public TemplateCell(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.visited = false;
		this.walls = new MoveSet[]{MoveSet.UP,MoveSet.DOWN,MoveSet.RIGHT,MoveSet.LEFT};
	}


	public boolean isVisited() {
		return visited;
	}


	public void setVisited() {
		this.visited = true;
	}


	public void removeWall(MoveSet toDelete) {
		for (int i = 0; i < walls.length; i++) {
			if(this.walls[i] != null) {
				if(this.walls[i].equals(toDelete)) {
					this.walls[i] = null;
				}
			}
		}
	}
	public int countWalls() {
		int result = 0;
		for (int i = 0; i < walls.length; i++) {
			if(this.walls[i] != null) {
				result++;
			}
		}
		return result;
	}
	
	public boolean hasWall(MoveSet direction) {
		for (int i = 0; i < walls.length; i++) {
			if(this.walls[i]!=null) {
				if(this.walls[i].equals(direction)) {
					return true;
				}
			}
		}
		return false;
	}


	public int getxCoordinate() {
		return xCoordinate;
	}


	public int getyCoordinate() {
		return yCoordinate;
	}


	public MoveSet[] getWalls() {
		return walls;
	}



	
	
}
