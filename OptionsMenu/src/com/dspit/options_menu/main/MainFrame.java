

package com.dspit.options_menu.main;

import javafx.application.Application;
import javafx.stage.Stage;

import com.dspit.options_menu.ui.MenuScene;

/**
 * The main UI when the user opens this program. Displays a set of buttons (controls)
 * so the user can choose what part of the application to explore.
 * 
 * @author David Boivin (Spit)
 */
public class MainFrame extends Application{
	
	private final String TITLE = "Options Menu";

	@Override
	public void start(Stage primaryStage) {

		//finish setting up the primary stage
		primaryStage.setScene(new MenuScene());
		primaryStage.setTitle(TITLE);
		primaryStage.show();
		//TODO add something that prevents the window to be resized to less than the size of the components
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
