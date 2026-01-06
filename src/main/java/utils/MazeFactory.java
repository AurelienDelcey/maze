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
						MazeCellsState state =template[i/2][(j+1)/2].canMove(MoveSet.RIGHT) ? MazeCellsState.WALL : MazeCellsState.EMPTY;
						finalMaze[i][j].setState(state);
						continue;
					}
					MazeCellsState state =template[i/2][(j+1)/2].canMove(MoveSet.LEFT) ? MazeCellsState.WALL : MazeCellsState.EMPTY;
					finalMaze[i][j].setState(state);
					continue;
				}else if(i%2==0 && j%2==1){
					if(i==2) {
						MazeCellsState state =template[i/2][this.size-1].canMove(MoveSet.RIGHT) ? MazeCellsState.WALL : MazeCellsState.EMPTY;
						finalMaze[i][j].setState(state);
						continue;
					}
					MazeCellsState state =template[i/2][this.size-1].canMove(MoveSet.LEFT) ? MazeCellsState.WALL : MazeCellsState.EMPTY;
					finalMaze[i][j].setState(state);
					continue;
				} 
				
				if((i%2==1 && j%2==0) && i<finalSize-2) {
					if(j==2) {
						MazeCellsState state =template[(i+1)/2][j/2].canMove(MoveSet.DOWN) ? MazeCellsState.WALL : MazeCellsState.EMPTY;
						finalMaze[i][j].setState(state);
						continue;
					}
					MazeCellsState state =template[(i+1)/2][j/2].canMove(MoveSet.UP) ? MazeCellsState.WALL : MazeCellsState.EMPTY;
					finalMaze[i][j].setState(state);
					continue;
				}else if(i%2==1 && j%2==0){
					if(j==2) {
						MazeCellsState state =template[this.size-1][j/2].canMove(MoveSet.DOWN) ? MazeCellsState.WALL : MazeCellsState.EMPTY;
						finalMaze[i][j].setState(state);
						continue;
					}
					MazeCellsState state =template[this.size-1][j/2].canMove(MoveSet.UP) ? MazeCellsState.WALL : MazeCellsState.EMPTY;
					finalMaze[i][j].setState(state);
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

}
