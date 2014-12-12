

package com.dspit.race.ui;

import java.util.Random;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import com.dspit.race.resources.RaceStrings;

public class Car extends StackPane{
	
// Constants --------------------------------------------------------------- //
	
	public static final String[] COLORS = {"red", "green", "lightblue", "yellow"};
	
// Members ----------------------------------------------------------------- //
	
	private double mSpeed;
	private int mNum;
	private String mColor;
	
// Constructor ------------------------------------------------------------- //
	
	/**
	 * Builds a car object.
	 */
	public Car(){
		super();
		
		this.getStylesheets().add(RaceStrings.class.getResource(
				"race-styles.css").toExternalForm());
		
		this.setId(RaceStrings.ID_THE_CAR);
		
		Random r = new Random();
		mSpeed = (5*((5+r.nextInt(10))/10.0));
		
		mNum = (int)Math.round(Math.random()*10);
		
		mColor = COLORS[r.nextInt(COLORS.length)];
		
		Rectangle body = new Rectangle();
		body.setId(RaceStrings.ID_BODY);
		body.setStyle("-fx-background-color: " + mColor + ";");
		
		Rectangle top = new Rectangle();
		top.setId(RaceStrings.ID_TOP);
		top.setStyle("-fx-border-color: " + mColor + ";");
		
		Circle wheel1 = new Circle();
		wheel1.setId(RaceStrings.ID_WHEEL);
		wheel1.setTranslateY(20);
		wheel1.setTranslateX(-50);
		
		Circle wheel2 = new Circle();
		wheel2.setId(RaceStrings.ID_WHEEL);
		wheel2.setTranslateY(20);
		wheel2.setTranslateX(50);
		
		this.getChildren().addAll(wheel1, wheel2, top, body);
	}
	
// Overrides --------------------------------------------------------------- //
	
	/**
	 * Returns the speed of the car by returning a given time. 
	 * 
	 * @return time it takes to complete course.
	 */
	public double getSpeed(){
		return mNum;
	}
	
	/**
	 * The number of the car.
	 * 
	 * @return Number representing the car
	 */
	public int getNumber(){
		return mNum;
	}

}
