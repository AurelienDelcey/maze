package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import model.MazeCells;
import model.MazeCellsState;
import model.MoveSet;

public class MazeFactoryTest {
	
	private MazeFactory factory;
	private TemplateCell[][] template;
	
	@Test
	void factory_shouldThrowException_whenSizeIsEven() {
		assertThrows(IllegalArgumentException.class, ()->new MazeFactory(10));
	}
	
	@Test
	void Create_ShouldReturnMazeWithBorder() {
		this.factory = new MazeFactory(11);
		MazeCells[][] maze = this.factory.create();
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if(i==0 || i == maze.length-1 || j==0 || j==maze[0].length-1) {
					assertEquals(MazeCellsState.WALL,maze[i][j].getState());
				}
			}
		}
	}
	
	@Test
	void Create_ShouldReturnMazeWithWallInIntersectioOnTemplate() {
		this.factory = new MazeFactory(11);
		MazeCells[][] maze = this.factory.create();
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if(i%2==0 && j%2==0) {
					assertEquals(MazeCellsState.WALL,maze[i][j].getState());
				}
			}
		}
	}
	
	private TemplateCell[][] createTemplateWithHorizontalWall(){
		TemplateCell[][] HorizontalTemplate = new TemplateCell[6][6];
		for (int i = 0; i < HorizontalTemplate.length; i++) {
			for (int j = 0; j < HorizontalTemplate.length; j++) {
				HorizontalTemplate[i][j]= new TemplateCell(i,j);
				HorizontalTemplate[i][j].removeWall(MoveSet.LEFT);
				HorizontalTemplate[i][j].removeWall(MoveSet.RIGHT);
			}
		}
		return HorizontalTemplate;
	}
	
	private TemplateCell[][] createTemplateWithVerticalWall(){
		TemplateCell[][] VerticalTemplate = new TemplateCell[6][6];
		for (int i = 0; i < VerticalTemplate.length; i++) {
			for (int j = 0; j < VerticalTemplate.length; j++) {
				VerticalTemplate[i][j] = new TemplateCell(i,j);
				VerticalTemplate[i][j].removeWall(MoveSet.LEFT);
				VerticalTemplate[i][j].removeWall(MoveSet.RIGHT);
			}
		}
		return VerticalTemplate;
	}
}
