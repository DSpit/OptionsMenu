


package com.dspit.options_menu.menu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * The main UI when the user opens this program. Displays a set of buttons (controls)
 * so the user can choose what part of the application to explore.
 * 
 * @author David Boivin (Spit)
 */
public class Menu extends Application {
	
	private final String TITLE = "Options Menu";

	@Override
	public void start(Stage primaryStage) {

		//finish setting up the primary stage
		primaryStage.setScene(new Scene(new StackPane()));
		primaryStage.setTitle(TITLE);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
