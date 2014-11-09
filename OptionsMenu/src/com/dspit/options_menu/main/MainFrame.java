

package com.dspit.options_menu.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import com.dspit.options_menu.resources.ApplicationString;
import com.dspit.options_menu.ui.AbsSubNavScene;
import com.dspit.options_menu.ui.FeatureNotImplementedStage;
import com.dspit.options_menu.ui.MenuScene;
import com.dspit.sudoku.ui.SudokuScene;

/**
 * The main UI when the user opens this program. Displays a set of buttons (controls)
 * so the user can choose what part of the application to explore.
 * 
 * @author David Boivin (Spit)
 */
public class MainFrame extends Application{
	
// Constants --------------------------------------------------------------- //
	
	private final String TITLE = ApplicationString.APPLICATION_TITLE;
	
// Members ---------------------------------------------------------------- //
	
	private Stage mMainStage;
	private Scene mMenuScene;
	
	@Override
	public void start(Stage primaryStage) {
		
		//initialize a ControlListener, so the application
		//has the final word on what each menu button does 
		//and the total possible 
		
		ControlHandler controlHandler = new ControlHandler();
		
		mMainStage = primaryStage;
		mMenuScene = new MenuScene(controlHandler);
		
		this.setScene(mMenuScene);
		
		mMainStage.setTitle(TITLE);
		mMainStage.show();
		

		
	}
	
// Private ----------------------------------------------------------------- //
	
	private void setScene(Scene scene){
		//set the scene to the main stage
		mMainStage.setScene(scene);
		
		//makes sure that the stage is the right size for the scene
		mMainStage.setMinHeight(((Pane)scene.getRoot()).getPrefHeight());
		mMainStage.setMinWidth(((Pane)scene.getRoot()).getPrefWidth());
		mMainStage.setHeight(((Pane)scene.getRoot()).getPrefHeight());
		mMainStage.setWidth(((Pane)scene.getRoot()).getPrefWidth());
	}
	
// Handlers ---------------------------------------------------------------- //
	
	public class ControlHandler implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent e) {
			
			AbsSubNavScene scene = null;
			
			switch(((Button)e.getSource()).getId()){
			case ApplicationString.MENU_OPTION_1:					//case: SUDOKU
				scene = new SudokuScene(mMenuScene);
				break;
				
			case ApplicationString.MENU_OPTION_2:					//case: TBA
				//Not implemented
				
			case ApplicationString.MENU_OPTION_3: 					//case: TBA
				//Not implemented
				
			case ApplicationString.MENU_OPTION_4:					//case: TBA
				//Not implemented
				new FeatureNotImplementedStage();
				break;
			
			case ApplicationString.MENU_OPTION_EXIT: 				//case: EXIT
				try {			//closes the application
					stop();
				} catch (Exception e1) {
					//don't see why this would happen (or matter)
				}
				break;
			}
			
			setScene(scene);			
		}
	}
	
// MAIN -------------------------------------------------------------------- //

	public static void main(String[] args) {
		launch(args);
	}
}
