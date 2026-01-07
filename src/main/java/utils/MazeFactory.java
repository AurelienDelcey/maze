package utils;

import model.MazeCells;
import model.MazeCellsState;
import model.MoveSet;

public class MazeFactory {
	
	private int size;
	private MazeCells[][] finalMaze;
	private MazeTemplateGenerator mazeGen;
	
	
	
	public MazeFactory(int size) {
		this.size = size;
		this.mazeGen = new MazeTemplateGenerator(size);
		finalMaze = new MazeCells[(this.size*2)+1][(this.size*2)+1];
	}

	public MazeCells[][] create() {
		MazeCells[][] maze = mapMaze(mazeGen.createTemplate());
		return maze;
	}
	
	private MazeCells[][] mapMaze(TemplateCell[][] template){
		this.finalMaze = initMaze(this.finalMaze);
		
		for (int i = 0; i < template.length; i++) {
			for (int j = 0; j < template.length; j++) {
				int x = (i*2)+1;
				int y = (j*2)+1;
				
				if(template[i][j].canMove(MoveSet.UP)) {
					drawUp(this.finalMaze[x][y]);
				}
				if(template[i][j].canMove(MoveSet.DOWN)) {
					drawDown(this.finalMaze[x][y]);
				}
				if(template[i][j].canMove(MoveSet.LEFT)) {
					drawLeft(this.finalMaze[x][y]);
				}
				if(template[i][j].canMove(MoveSet.RIGHT)) {
					drawRight(this.finalMaze[x][y]);
				}
			}
		}
		finalizeMaze();
		return finalMaze;
	}
	
	
	
	private void finalizeMaze() {
		for (int i = 0; i < finalMaze.length; i++) {
			for (int j = 0; j < finalMaze.length; j++) {
				if(i==0 || i==finalMaze.length-1 || j==0 || j==finalMaze.length-1 ) {
					finalMaze[i][j].setState(MazeCellsState.WALL);
					continue;
				}
				if(i%2==0 && j%2==0) {
					finalMaze[i][j].setState(MazeCellsState.WALL);
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
	public void drawRight(MazeCells cell) {
		this.finalMaze[cell.getxCoordonate()+1][cell.getyCoordonate()].setState(MazeCellsState.WALL);
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
