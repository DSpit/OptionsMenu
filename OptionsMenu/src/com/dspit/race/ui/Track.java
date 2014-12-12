

package com.dspit.race.ui;

import java.util.ArrayList;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import com.dspit.race.resources.RaceStrings;
import com.dspit.sudoku.resources.SudokuStrings;

public class Track extends StackPane{
	
// Constants --------------------------------------------------------------- //
	
	private static final int STOPPING_DISTANCE = 50;
	
// Members ----------------------------------------------------------------- //
	
	private Car[] mCars;
	private int mCurrentTotalCars;

// Constructors ------------------------------------------------------------ //
	
	/**
	 * Constructor which builds the track graphically.
	 */
	public Track(int numCars){
		super();
		
		this.setId(RaceStrings.ID_TRACK);
		
		mCars = new Car[numCars];
		mCurrentTotalCars = 0;
		
		this.getChildren().add(this.buildBase());
	}
	
// Public Methods ---------------------------------------------------------- //
	
	/**
	 * Method which adds cars to the race. Will not add the car if the race 
	 * track is full.
	 * 
	 * @param car An instance of a car.
	 */
	public void add(Car car){
		if(mCurrentTotalCars < mCars.length){
			mCars[mCurrentTotalCars] = car;
			mCurrentTotalCars++;
		}
	}
	
	/**
	 * Starts the race.
	 * 
	 * @return A list of all the cars in order they finished in.
	 */
	public String[] startRace(){
		
		//place cars in correct location
		this.placeCars();
		
		//setup audio
		AudioClip startingSound = new AudioClip(RaceStrings.class.getResource(
				RaceStrings.START_SOUND)
				.toExternalForm());
		//set up the different components needed to make the cars animate
		TranslateTransition[] transitionHolder = new 
				TranslateTransition[mCars.length];
		ArrivalHandler aHandler= new ArrivalHandler();
		
		//creates a new animation for each car
		for(int i = 0; i < mCars.length; ++i){
			//create new transition
			transitionHolder[i] = new TranslateTransition(
					Duration.seconds(mCars[i].getSpeed()), mCars[i]);
			
			transitionHolder[i].setDelay(Duration.seconds(
					(startingSound.getPan()*2)));
			transitionHolder[i].setToX(this.getWidth() - 
					STOPPING_DISTANCE);
			transitionHolder[i].setOnFinished(aHandler);
		}
		
		//setup the parallel transition
		ParallelTransition transitions = new ParallelTransition(transitionHolder);

		//start race
		startingSound.play();
		transitions.play();
		
		return aHandler.getLeaderBoard();
	}
	
// Private ----------------------------------------------------------------- //
	
	/**
	 * Creates the background graphic of the race track.
	 * 
	 * @return The Pane containing representing the background of this pane.
	 */
	private AnchorPane buildBase(){
		
		AnchorPane container = new AnchorPane();
		container.setId(RaceStrings.ID_TRACK_CONTAINER);
		
		Label start = new Label(RaceStrings.START_LINE);
		start.setId(RaceStrings.ID_START);
		AnchorPane.setBottomAnchor(start, 0.0);
		AnchorPane.setLeftAnchor(start, 0.0);
		AnchorPane.setTopAnchor(start, 0.0);
		
		Label end = new Label(RaceStrings.END_LINE);
		end.setId(RaceStrings.ID_END);
		AnchorPane.setBottomAnchor(end, 0.0);
		AnchorPane.setRightAnchor(end, 0.0);
		AnchorPane.setTopAnchor(end, 0.0);
		
		container.getChildren().addAll(start, end);
		
		return container;
	}
	
	/**
	 * Places the cars in a the correct location on the panel.
	 */
	private void placeCars(){
		for(int i = 0; i <  mCurrentTotalCars; ++i){
			mCars[i].setTranslateX(10);
			mCars[i].setTranslateY(150*i+10);
			
			this.getChildren().add(mCars[i]);
		}
	}
	
// Handlers ---------------------------------------------------------------- //
	
	/**
	 * Class which handles what to do in the case that a car finishes the race.
	 * 
	 * @author David Boivin (Spit)
	 */
	private class ArrivalHandler implements EventHandler<ActionEvent>{

		private ArrayList<String> mLeaderBoard = new ArrayList<String>();
		
		@Override
		public void handle(ActionEvent event) {
			
			String carNum = String.valueOf(((Car)
					((TranslateTransition)event.getSource()).getNode())
					.getNumber());	
			
			mLeaderBoard.add(carNum);
		}
		
		public String[] getLeaderBoard(){
			return mLeaderBoard.toArray(new String[mCars.length]);
		}
		
	}
}
