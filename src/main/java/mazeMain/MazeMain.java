package mazeMain;

import static javafx.application.Application.launch;

import model.MazeCells;
import model.MazeCellsState;
import utils.MazeFactory;
import utils.MazeTemplateGenerator;

public class MazeMain {

	public static void main(String[] args) {
		
		MazeFactory factory = new MazeFactory(10);
		
		MazeCells[][] maze = factory.create();
		
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
		/*try{
			launch(ui.ViewManager.class);
		}catch(Exception e) {
			System.out.println("main class ERROR: " + e.getMessage());
			System.out.println(e.getStackTrace());
		}*/
		
		

	}

}
