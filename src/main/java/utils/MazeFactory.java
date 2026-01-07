package utils;

import model.MazeCells;
import model.MazeCellsState;
import model.MoveSet;

public class MazeFactory {
	
	private int size;
	private MazeTemplateGenerator mazeGen;
	
	
	
	public MazeFactory(int size) {
		
		this.size = size;
		this.mazeGen = new MazeTemplateGenerator(size);
	}

	public MazeCells[][] create() {
		MazeCells[][] maze = mapMaze(mazeGen.createTemplate());
		return maze;
	}
	
	public MazeCells[][] create(String debugmode) {
		MazeCells[][] maze = mapMaze(create_Template_Debug_Lines());
		print(maze);
		System.out.println("");
		System.out.println("");
		maze = mapMaze(create_Template_Debug_Columns());
		print(maze);
		return null;
	}
	
	private int getFinalSize() {
		return (this.size*2)+1;
	}
	
	private MazeCells[][] mapMaze(CellHistory[][] template){
		int finalSize = getFinalSize();
		MazeCells[][] finalMaze = new MazeCells[finalSize][finalSize];
		finalMaze = initMaze(finalMaze);
		
		for (int i = 0; i < finalSize; i++) {
			for (int j = 0; j < finalSize; j++) {
				if(i%2==0 && j%2==0) {
					finalMaze[i][j].setState(MazeCellsState.WALL);
					continue;
				}
				if(i==0 || i == finalSize-1 || j==0 || j == finalSize-1 ) {
					finalMaze[i][j].setState(MazeCellsState.WALL);
					continue;
				}
				if(i%2==1 && j%2==1) {
					finalMaze[i][j].setState(MazeCellsState.EMPTY);
					continue;
				}
				if((i%2==0 && j%2==1) && j<finalSize-2) {
					if(i==2) {
						if(template[i/2][(j+1)/2].canMove(MoveSet.RIGHT)&&template[(i/2)+1][(j+1)/2].canMove(MoveSet.LEFT)) {
							finalMaze[i][j].setState(MazeCellsState.WALL);
							continue;
						}
						finalMaze[i][j].setState(MazeCellsState.EMPTY);
						continue;
						
					}
					if(template[i/2][(j+1)/2].canMove(MoveSet.LEFT)&&template[(i/2)-1][(j+1)/2].canMove(MoveSet.RIGHT)) {
						finalMaze[i][j].setState(MazeCellsState.WALL);
						continue;
					}
					finalMaze[i][j].setState(MazeCellsState.EMPTY);
					continue;
					
				}else if(i%2==0 && j%2==1){
					if(i==2) {
						if(template[i/2][this.size-1].canMove(MoveSet.RIGHT)&&template[(i/2)+1][this.size-1].canMove(MoveSet.LEFT)) {
							finalMaze[i][j].setState(MazeCellsState.WALL);
							continue;
						}
						finalMaze[i][j].setState(MazeCellsState.EMPTY);
						continue;
						
					}
					if(template[i/2][this.size-1].canMove(MoveSet.LEFT)&&template[(i/2)-1][this.size-1].canMove(MoveSet.RIGHT)) {
						finalMaze[i][j].setState(MazeCellsState.WALL);
						continue;
					}
					finalMaze[i][j].setState(MazeCellsState.EMPTY);
					continue;
				}	
				if((i%2==1 && j%2==0) && i<finalSize-2) {
					if(j==2) {
						if(template[(i+1)/2][j/2].canMove(MoveSet.DOWN) && template[(i+1)/2][(j/2)+1].canMove(MoveSet.UP)) {
							finalMaze[i][j].setState(MazeCellsState.WALL);
							continue;
						}
						finalMaze[i][j].setState(MazeCellsState.EMPTY);
						continue;
						
					}
					if(template[(i+1)/2][j/2].canMove(MoveSet.UP) && template[(i+1)/2][(j/2)-1].canMove(MoveSet.DOWN)) {
						finalMaze[i][j].setState(MazeCellsState.WALL);
						continue;
					}
					finalMaze[i][j].setState(MazeCellsState.EMPTY);
					continue;
					
				}else if(i%2==1 && j%2==0){
					if(j==2) {
						if(template[this.size-1][j/2].canMove(MoveSet.DOWN) && template[this.size-1][(j/2)+1].canMove(MoveSet.UP)) {
							finalMaze[i][j].setState(MazeCellsState.WALL);
							continue;
						}
						finalMaze[i][j].setState(MazeCellsState.EMPTY);
						continue;
						
					}
					if(template[this.size-1][j/2].canMove(MoveSet.UP) && template[this.size-1][(j/2)-1].canMove(MoveSet.DOWN)) {
						finalMaze[i][j].setState(MazeCellsState.WALL);
						continue;
					}
					finalMaze[i][j].setState(MazeCellsState.EMPTY);
					continue;
					
				}
			}
		}
		return finalMaze;
	}
	
	private MazeCells[][] initMaze(MazeCells[][] maze){
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				maze[i][j] = new MazeCells(i,j);
			}
		}
		return maze;
	}
	
	private CellHistory[][] create_Template_Debug_Lines() {
		CellHistory[][] result = new CellHistory[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				result[i][j]=new CellHistory(i,j);
				result[i][j].deleteFreeDirection(MoveSet.LEFT);
				result[i][j].deleteFreeDirection(MoveSet.RIGHT);
			}
		}
		return result;
	}
	
	private CellHistory[][] create_Template_Debug_Columns() {
		CellHistory[][] result = new CellHistory[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				result[i][j]=new CellHistory(i,j);
				result[i][j].deleteFreeDirection(MoveSet.UP);
				result[i][j].deleteFreeDirection(MoveSet.DOWN);
			}
		}
		return result;
	}
	
	private void print(MazeCells[][] maze) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				if(maze[i][j].getState()==MazeCellsState.WALL) {
					System.out.print("|#");
				}else if(maze[i][j].getState()==MazeCellsState.EMPTY) {
					System.out.print("| ");
				}
			}
			System.out.print("|\n");
		}
	}

}
