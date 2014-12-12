

package com.dspit.options_menu.main;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.dspit.fx.nav.SideTab;
import com.dspit.fx.nav.TestNode;
import com.dspit.nav.Navigatable.NavNode;
import com.dspit.options_menu.resources.ApplicationString;
import com.dspit.race.resources.RaceStrings;
import com.dspit.race.ui.RacePane;
import com.dspit.sudoku.resources.SudokuStrings;
import com.dspit.sudoku.ui.SudokuPane;

/**
 * The main UI when the user opens this program. Displays a set of buttons (controls)
 * so the user can choose what part of the application to explore.
 * 
 * @author David Boivin (Spit)
 */
public class MainFrame extends Application{
	
// Constants --------------------------------------------------------------- //
	
	private final String TITLE = ApplicationString.APPLICATION_TITLE;
	
// Override ---------------------------------------------------------------- //
	
	@Override
	public void start(Stage primaryStage) {
		
		ArrayList<NavNode> contentList = new ArrayList<NavNode>();
		
		contentList.add(new SudokuPane("Sudoku Game", 
				SudokuStrings.class.getResource("game_icon.png").toString()));
		contentList.add(new RacePane("Race Day", 
				RaceStrings.class.getResource("race_icon.png").toString()));
		contentList.add(new TestNode("Unimplemented 3", "green"));
		contentList.add(new TestNode("Unimplemented 4", "red"));
		
		IntroPane home = new IntroPane();
		
		primaryStage.setScene(new SideTab(home, contentList));
		primaryStage.setTitle(TITLE);
		
		primaryStage.show();
		
	}
	
// Private Inner Class ----------------------------------------------------- //
	
	private class IntroPane extends StackPane implements NavNode{

		public IntroPane(){
			super();
			
			this.setStyle("-fx-background-color: #F0F0F0;");
			
			Label l = new Label(ApplicationString.INTRO_MESSAGE);
			
			l.setStyle("-fx-font-size: 16pt;" + 
					"-fx-font-weight: bold;");
			
			this.getChildren().add(l);
		}
		
		@Override
		public String getTitle() {
			return "Home";
		}

		@Override
		public String getIcon(){
			return ApplicationString.HOME_ICON;
		}

		/**
		 * Ain't gonna work cause these are constants
		 */
		@Override
		public void setTitle(String title) {
			//don't need to do this
		}

		/**
		 * Ain't gonna work cause these are constants
		 */
		@Override
		public void setIcon(String iconImage) {
			//don't need to do this
		}
		
	}
	
// MAIN -------------------------------------------------------------------- //

	public static void main(String[] args) {
		launch(args);
	}
}
