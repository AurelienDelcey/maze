package model;

import utils.MazeFactory;

public class Maze {
	
	private final MazeCells[][] maze;
	private final MazeFactory factory;
	private final int size;
	
	
	public Maze(MazeFactory factory, int size) {
		this.factory = factory;
		this.size = size;
		this.maze = initMaze(size);
	}


	public int getSize() {
		return size;
	}


	public MazeCells[][] getMaze() {
		return maze;
	}


	private MazeCells[][] initMaze(int size) {
		return this.factory.create();
	}
}
