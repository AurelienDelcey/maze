package mazeMain;

import static javafx.application.Application.launch;

import model.MazeCells;
import model.MazeCellsState;
import utils.MazeFactory;

public class MazeMain {

	public static void main(String[] args) {
		
		/*MazeCells[][] maze=null;
		try {
			MazeFactory factory = new MazeFactory(21);
			maze = factory.create();
		} catch (IllegalArgumentException e) {
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
		
		if (maze != null) {
			for (int y = 0; y < maze.length; y++) {
				for (int x = 0; x < maze[y].length; x++) {
					if (maze[x][y].getState() == MazeCellsState.WALL) {
						System.out.print("|#");
					} else {
						System.out.print("| ");
					}
				}
				System.out.println("|");
			}*/

		launch(ui.ViewManager.class);
		
	}
}
