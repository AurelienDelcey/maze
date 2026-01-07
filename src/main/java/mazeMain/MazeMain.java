package mazeMain;

import static javafx.application.Application.launch;

import model.MazeCells;
import model.MazeCellsState;
import utils.MazeFactory;

public class MazeMain {

	public static void main(String[] args) {
		
		MazeFactory factory = new MazeFactory(20);
		MazeCells[][] maze = factory.create();
		
		for (int y = 0; y < maze.length; y++) {
		    for (int x = 0; x < maze[y].length; x++) {
		        if (maze[x][y].getState() == MazeCellsState.WALL) {
		            System.out.print("|#");
		        } else {
		            System.out.print("| ");
		        }
		    }
		    System.out.println("|");
		}
		/*try{
			launch(ui.ViewManager.class);
		}catch(Exception e) {
			System.out.println("main class ERROR: " + e.getMessage());
			System.out.println(e.getStackTrace());
		}*/
		
		

	}

}
