

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
import com.dspit.options_menu.ui.MenuScene;
import com.dspit.options_menu.ui.Popup;
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
	
// Members ----------------------------------------------------------------- //
	
	Stage mMainStage;
	
// Override ---------------------------------------------------------------- //
	
	@Override
	public void start(Stage primaryStage) {
		
		mMainStage = primaryStage;
		mMainStage.setTitle(TITLE);
		
		this.setMenuScene();
		
		mMainStage.show();
	}
	
	@Override
	public void stop(){
		//free up space and close the application
		mMainStage.close();
		mMainStage = null;
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
	
	private void setMenuScene(){
		this.setScene(new MenuScene(new ControlHandler(), 
				ApplicationString.MENU_BUTTON_OPTIONS));
	}
	
// Handlers ---------------------------------------------------------------- //
	
	private class ControlHandler implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent e) {
			
			AbsSubNavScene scene = null;
			
			switch(((Button)e.getSource()).getId()){
			case ApplicationString.NAV_OPTION_1:					//case: SUDOKU
				scene = new SudokuScene(new HomeHandler());
				break;
				
			case ApplicationString.NAV_OPTION_2:					//case: TBA
				//Not implemented
				
			case ApplicationString.NAV_OPTION_3: 					//case: TBA
				//Not implemented
				
			case ApplicationString.NAV_OPTION_4:					//case: TBA
				//Not implemented
				new Popup(ApplicationString.NOT_IMPLEMENTED_MESSAGE);
				return;	//so that the final scene isn't output as null
			
			case ApplicationString.NAV_OPTION_EXIT: 				//case: EXIT
				stop();	//closes application
				break;
			}
			
			//changes the scene of the stage
			setScene(scene);
		}
	}
	
	private class HomeHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e){
			
			switch(((Button)e.getSource()).getId()){
			case ApplicationString.NAV_OPTION_HOME:
				setMenuScene();
				break;
				
			case ApplicationString.NAV_OPTION_EXIT:
				stop();		//closes application
				break;	
			}
		}
		
	} 
	
// MAIN -------------------------------------------------------------------- //

	public static void main(String[] args) {
		launch(args);
	}
}
