package utils;


import model.MoveSet;

public class CellHistory {
	private final int xCoordinate;
	private final int yCoordinate;
	private boolean visited;
	private MoveSet[] freeDirection;
	
	
	public CellHistory(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.visited = false;
		this.freeDirection = new MoveSet[]{MoveSet.UP,MoveSet.DOWN,MoveSet.RIGHT,MoveSet.LEFT};
	}


	public boolean isVisited() {
		return visited;
	}


	public void setVisited() {
		this.visited = true;
	}


	public void deleteFreeDirection(MoveSet toDelete) {
		for (int i = 0; i < freeDirection.length; i++) {
			if(this.freeDirection[i] != null) {
				if(this.freeDirection[i].equals(toDelete)) {
					this.freeDirection[i] = null;
				}
			}
		}
	}
	
	public boolean directionIsEmpty() {
		for (int i = 0; i < freeDirection.length; i++) {
			if(this.freeDirection[i] != null) {
				return false;
			}
		}
		return true;
	}
	
	public int countDirection() {
		int result = 0;
		for (int i = 0; i < freeDirection.length; i++) {
			if(this.freeDirection[i] != null) {
				result++;
			}
		}
		return result;
	}
	
	public boolean canMove(MoveSet direction) {
		for (int i = 0; i < freeDirection.length; i++) {
			if(this.freeDirection[i]!=null) {
				if(this.freeDirection[i].equals(direction)) {
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


	public MoveSet[] getFreeDirection() {
		return freeDirection;
	}
	
	public MoveSet[] getDirectionsOnly() {
		int counter = 0;
		for (int i = 0; i < freeDirection.length; i++) {
			if(this.freeDirection[i]!=null) {
				counter++;
			}
		}
		MoveSet[] result= new MoveSet[counter];
		counter = 0;
		for (int i = 0; i < freeDirection.length; i++) {
			if(this.freeDirection[i]!=null) {
				result[counter]=this.freeDirection[i];
			}
		}
		return result;
	}



	
	
}
