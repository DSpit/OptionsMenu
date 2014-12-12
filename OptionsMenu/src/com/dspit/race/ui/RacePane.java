

package com.dspit.race.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.dspit.nav.Navigatable.NavNode;
import com.dspit.race.resources.RaceStrings;

public class RacePane extends HBox implements NavNode{
	
// Constants --------------------------------------------------------------- //
	
	private static final int NUM_CARS = 4;

// Members ----------------------------------------------------------------- //
	
	private String mTitle;
	private String mIcon;
	private Track mTrack;
	
// Constructor ------------------------------------------------------------- //
	
	/**
	 * Constructor which build all the elements in this pane.
	 * 
	 * @param title The title of this navigation node.
	 * @param icon The Image associated with this navigation node.
	 */
	public RacePane(String title, String icon){
		super();
		
		this.getStylesheets().add(RaceStrings.class.getResource("race-styles.css")
				.toExternalForm());
		
		this.setStyle("-fx-background-color: #999999");
		
		mTitle = title;
		mIcon = icon;
		
		mTrack = new Track(NUM_CARS);
		ControlPane control = new ControlPane();
		
		for(int i = 0; i < NUM_CARS; ++i){
			Car c = new Car(i+1);
			mTrack.add(c);
		}
		
		this.getChildren().addAll(control, mTrack);
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
	
// Private Inner Classes --------------------------------------------------- //
	
	/**
	 * A control pane where the user can start the race and see who won.
	 * 
	 * @author David Boivin (Spit)
	 */
	private class ControlPane extends VBox{
		
		private VBox mLeaderBoard;
		
		/**
		 * Constructor which builds the control panel.
		 */
		public ControlPane(){
			super();
			
			//set id
			this.setId(RaceStrings.ID_CONTROL);
			
			//create control surfaces
			Button goButton = new Button();
			Label boardHeader = new Label(RaceStrings.LEADERBOARD_TITLE);
			boardHeader.setStyle("-fx-font-size: 14pt;");
			
			mLeaderBoard = new VBox();
			mLeaderBoard.setId(RaceStrings.ID_LEADER_BOARD);
			
			VBox leaderBoardContainer = new VBox();
			leaderBoardContainer.setId(RaceStrings.ID_LEADER_BOARD_CONTAINER);
			leaderBoardContainer.getChildren().addAll(boardHeader, mLeaderBoard);
			
			//add handler
			goButton.setOnAction(new GoHandler());
			goButton.setText(RaceStrings.START_BUTTON);
			
			//add elements to Node tree
			this.getChildren().addAll(leaderBoardContainer, goButton);
		}
		
		/**
		 * Class which starts the race and reports who won back to the 
		 * leaderboard.
		 * 
		 * @author David Boivin (Spit)
		 */
		private class GoHandler implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent event) {
				//start race
				String[] result = mTrack.startRace();
				
				//report each car's rank back to the leaderboard
				for(int i = 0; i < NUM_CARS; ++i){
					mLeaderBoard.getChildren().add(new Label("#" + i + ": " +
															result[i]));
				}
			}
		}
	}
}
