package utils;

import model.MazeCells;
import model.MazeCellsState;
import model.MoveSet;

public class MazeFactory {
	
	private int size;
	private MazeCells[][] finalMaze;
	private MazeTemplateGenerator mazeGen;
	
	
	
	public MazeFactory(int size) throws IllegalArgumentException{
		if(size%2 == 0 ) {
			throw new IllegalArgumentException(
					"Invalid maze size: must be odd to preserve structure"
					);
		}
		this.size = size;
		this.mazeGen = new MazeTemplateGenerator((this.size-1)/2);
		finalMaze = new MazeCells[this.size][this.size];
	}

	public MazeCells[][] create() {
		return mapMaze(mazeGen.createTemplate());
	}
	
	private MazeCells[][] mapMaze(TemplateCell[][] template){
		this.finalMaze = initMaze(this.finalMaze);
		
		for (int i = 0; i < template.length; i++) {
			for (int j = 0; j < template.length; j++) {
				int col = (i*2)+1;
				int row = (j*2)+1;
				
				if(template[i][j].hasWall(MoveSet.UP)) {
					drawUp(this.finalMaze[col][row]);
				}
				if(template[i][j].hasWall(MoveSet.DOWN)) {
					drawDown(this.finalMaze[col][row]);
				}
				if(template[i][j].hasWall(MoveSet.LEFT)) {
					drawLeft(this.finalMaze[col][row]);
				}
				if(template[i][j].hasWall(MoveSet.RIGHT)) {
					drawRight(this.finalMaze[col][row]);
				}
			}
		}
		finalizeMaze();
		initStartAndGoal();
		return finalMaze;
	}
	
	private void initStartAndGoal() {
		this.finalMaze[1][1].setState(MazeCellsState.GOAL);
		this.finalMaze[this.size-2][this.size-2].setState(MazeCellsState.START);
	}
	
	private void finalizeMaze() {
		for (int col = 0; col < finalMaze.length; col++) {
			for (int row = 0; row < finalMaze.length; row++) {
				if(col==0 || col==finalMaze.length-1 || row==0 || row==finalMaze.length-1 ) {
					finalMaze[col][row].setState(MazeCellsState.WALL);
					continue;
				}
				if(col%2==0 && row%2==0) {
					finalMaze[col][row].setState(MazeCellsState.WALL);
					continue;
				}
			}
		}
	}
	private void drawUp(MazeCells cell) {
		this.finalMaze[cell.getxCoordonate()][cell.getyCoordonate()-1].setState(MazeCellsState.WALL);
	}
	private void drawLeft(MazeCells cell) {
		this.finalMaze[cell.getxCoordonate()-1][cell.getyCoordonate()].setState(MazeCellsState.WALL);
	}
	private void drawDown(MazeCells cell) {
		this.finalMaze[cell.getxCoordonate()][cell.getyCoordonate()+1].setState(MazeCellsState.WALL);
	}
	private void drawRight(MazeCells cell) {
		this.finalMaze[cell.getxCoordonate()+1][cell.getyCoordonate()].setState(MazeCellsState.WALL);
	}
	
	private MazeCells[][] initMaze(MazeCells[][] maze){
		for (int col = 0; col < maze.length; col++) {
			for (int row = 0; row < maze.length; row++) {
				maze[col][row] = new MazeCells(col,row);
			}
		}
		return maze;
	}
}
