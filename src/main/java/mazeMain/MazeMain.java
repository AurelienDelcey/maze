package mazeMain;

import static javafx.application.Application.launch;

import utils.MazeTemplateGenerator;

public class MazeMain {

	public static void main(String[] args) {
		
		MazeTemplateGenerator i = new MazeTemplateGenerator(10);
		i.createTemplate();
		
		try{
			launch(ui.ViewManager.class);
		}catch(Exception e) {
			System.out.println("main class ERROR: " + e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
		

	}

}
