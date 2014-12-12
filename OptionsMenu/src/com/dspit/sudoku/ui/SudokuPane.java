

package com.dspit.sudoku.ui;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;

import com.dspit.nav.Navigatable.NavNode;
import com.dspit.sudoku.SudokuBoard;
import com.dspit.sudoku.resources.SudokuStrings;

/**
 * A mock sudoku game. Assignment 3.
 * 
 * @author David Boivin (Spit)
 */
public class SudokuPane extends VBox implements NavNode{
	
// Members ----------------------------------------------------------------- //
	
	private String mTitle;
	private String mIcon;
	private Pane mHintGrid;
	private Pane mFullGrid;
	private SudokuBoard mBoard;
	private GridPane mGridContainer;
	
	
// Constructors ------------------------------------------------------------ //
	
	/**
	 * Constructor which builds the entirety of the sudoku pane, containing
	 * a title and an icon for navigation purposes.
	 * 
	 * @param title The title to set the navigation header to.
	 * @param icon The image to associate with this pane.
	 */
	public SudokuPane(String title, String icon) {
		super();
		
		this.getStylesheets().add(SudokuStrings.class.getResource("sudoku-styles.css")
				.toExternalForm());
		
		mTitle = title;
		mIcon = icon;
		mBoard = new SudokuBoard();
		
		mHintGrid = new EmptyPane(SudokuStrings.EMPTY_GRID_HINT_MSG);
		mFullGrid = new EmptyPane(SudokuStrings.EMPTY_GRID_FULL_MSG);
		
		mGridContainer = new GridPane();
		mGridContainer.add(new Label(SudokuStrings.EMPTY_GRID_HINT) ,0, 0);
		mGridContainer.add(mHintGrid , 0, 1);
		mGridContainer.add(new Label(SudokuStrings.EMPTY_GRID_FULL), 1, 0);
		mGridContainer.add(mFullGrid, 1, 1);
		mGridContainer.setId(SudokuStrings.ID_GRID_CONTAINER);
		
		this.getChildren().addAll(mGridContainer, new SelectorPane());
		
	}
	
// Overrides --------------------------------------------------------------- //

	@Override
	public String getTitle() {
		return mTitle;
	}

	@Override
	public String getIcon() {
	 return mIcon;
	}

	@Override
	public void setTitle(String title) {
		mTitle = title;
	}

	@Override
	public void setIcon(String iconImage) {
		mIcon = iconImage;
		
	}
	
// Private Inner classes --------------------------------------------------- //
	
	/**
	 * This class is to be used as a place holder for other panes, all you 
	 * have to do is set the size and location. 
	 * 
	 * @author David Boivin (Spit)
	 */
	private class EmptyPane extends StackPane{
		
		/**
		 * Constructor which sets the id of this pane to "empty-pane"
		 * and sets the given text to a label which is centered in this pane.
		 * 
		 * @param text Text displayed in center of pane
		 */
		public EmptyPane(String text){
			super();
			
			this.setId(SudokuStrings.ID_EMPTY_PANE);
			
			this.getChildren().add(new Label(text));
		}
	} 
	
	/**
	 * Basic control panel for the SudokuPane.
	 * 
	 * @author David Boivin (Spit)
	 */
	private class SelectorPane extends HBox{
		
		private Button mImportHintGrid ;
		private Button mImportFullGrid;
		private Button mMatch;
		private Label mEnd;
		private Line mLine1, mLine2, mLine3;
		
		public SelectorPane(){
			super();
			
			this.setId(SudokuStrings.ID_CONTROL_CONTAINER);
			
			ControlHandler handler = new ControlHandler();
			
			//set up buttons
			mImportHintGrid = new Button(SudokuStrings.IMPORT_BUTTON);
			mImportHintGrid.setId(SudokuStrings.IMPORT_BUTTON);
			mImportHintGrid.setOnAction(handler);
			
			mImportFullGrid = new Button(SudokuStrings.SOLUTION_BUTTON);
			mImportFullGrid.setId(SudokuStrings.SOLUTION_BUTTON);
			mImportFullGrid.setOnAction(handler);
			mImportFullGrid.setDisable(true);
			
			mMatch = new Button(SudokuStrings.MATCH_BUTTON);
			mMatch.setId(SudokuStrings.MATCH_BUTTON);
			mMatch.setOnAction(handler);
			mMatch.setDisable(true);
			
			mEnd = new Label();
			mEnd.setId(SudokuStrings.END_BUTTON);
			mEnd.setGraphic(new ImageView(
					new Image(SudokuStrings.END_BUTTON_Q)));
			
			//set up lines
			mLine1 = new Line(0, 0, 40, 0);
			mLine1.getStyleClass().add(SudokuStrings.ID_LINE);
			mLine1.setId(SudokuStrings.ID_NOT_COMPLETE);
			
			mLine2 = new Line(0, 0, 40, 0);
			mLine2.getStyleClass().add(SudokuStrings.ID_LINE);
			mLine2.setId(SudokuStrings.ID_NOT_COMPLETE);
			
			mLine3 = new Line(0, 0, 40, 0);
			mLine3.getStyleClass().add(SudokuStrings.ID_LINE);
			mLine3.setId(SudokuStrings.ID_NOT_COMPLETE);
			
			//Add all the children
			this.getChildren().addAll(
				mImportHintGrid,
				mLine1,
				mImportFullGrid,
				mLine2,
				mMatch,
				mLine3,
				mEnd);
		}
		
		/**
		 * A handler which handles all the buttons in the selector pane.
		 * 
		 * @author David Boivin (Spit)
		 */
		private class ControlHandler implements EventHandler<ActionEvent>{

			@SuppressWarnings("unused")
			@Override
			public void handle(ActionEvent event) {
				
				Button source = (Button) event.getSource();
				
				switch(source.getId()){
				case SudokuStrings.IMPORT_BUTTON:
					
					FileChooser fc = new FileChooser();
					fc.setTitle("Open Sudoku Board");
					
					File userF = fc.showOpenDialog(null);
					
					if(userF != null){
					
						if(mBoard.populate(userF)){
							//reformat button and line
							mLine1.setStyle("-fx-stroke: green;");
							mImportFullGrid.setDisable(false);
							
							//change out the temporary grid holder
							mGridContainer.getChildren().remove(mHintGrid);
							mHintGrid = new SudokuGrid(mBoard, true);
							mGridContainer.add(mHintGrid, 0, 1);
							
						}
					}
					
					break;
					
				case SudokuStrings.SOLUTION_BUTTON:
					//always successful because the board is check when 
					//imported
					//reformat button and line
					mLine2.setStyle("-fx-stroke: green;");
					mMatch.setDisable(false);
					
					mGridContainer.getChildren().remove(mFullGrid);
					mFullGrid = new SudokuGrid(mBoard, false);
					mGridContainer.add(mFullGrid, 1, 1);
					break;
					
				case SudokuStrings.MATCH_BUTTON:
					AudioClip audio;	//set up audio
					
					//the solution grid is always the same as the hint grid 
					//(because they are the same damn grid) but if I was to add
					//a condition for winning, here it would be.
					if(true){
						
						audio = new AudioClip(SudokuStrings.class.getResource(
								SudokuStrings.END_BUTTON_SOUND_HAPPY)
								.toExternalForm());
						
						//reformat button and line
						mLine3.setStyle("-fx-stroke: green;");
						mEnd.setGraphic(new ImageView(
								SudokuStrings.END_BUTTON_C));
					}else{
						audio = new AudioClip(SudokuStrings.class.getResource(
								SudokuStrings.END_BUTTON_SOUND_SAD)
								.toExternalForm());
						
						mEnd.setGraphic(new ImageView(SudokuStrings.END_BUTTON_X));
					}
					
					//play correct audio
					audio.play();
					
					break;
				}
			}
		}
	}
}
