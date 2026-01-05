package utils;

import model.MazeCells;

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
	
	private MazeCells[][] mapMaze(CellHistory[][] template){
		MazeCells[][] finalMaze = new MazeCells[1][1];
		
		//implement mapping:
		//first fori->j set all cells in lines; size = size-1(inside structure) +2(border,wall)
		//next forj->i set all cells in collumns; size = size-1(inside structure) +2(border wall)
		//check if all cells are set
		//set start and goal?? maybe let the calling class do it...
		
		return finalMaze;
	}

}
