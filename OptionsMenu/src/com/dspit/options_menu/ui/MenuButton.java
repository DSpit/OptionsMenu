

package com.dspit.options_menu.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import com.dspit.options_menu.resources.ApplicationColourPalette;
import com.dspit.options_menu.resources.ApplicationFont;

class MenuButton extends Button {
	
// Constants --------------------------------------------------------------- //
	
	private final int MIN_HEIGHT = 70;
	private final int MIN_WIDTH = 160;
	private final double MAX_COEFFICENT = 1.5;
	private final int MAX_HEIGHT = (int) Math.round(MIN_HEIGHT*MAX_COEFFICENT);
	private final int MAX_WIDTH = (int) Math.round(MIN_WIDTH*MAX_COEFFICENT);
	private final Color BACKGROUND = ApplicationColourPalette.ACCENT_BACKGROUND;
	private final Color FORGROUND = ApplicationColourPalette.ACCENT_FORGROUND;
	private final Font FONT =  Font.font(ApplicationFont.BUTTON_FONT.getName(), FontWeight.BOLD,  20);

// Constructor ------------------------------------------------------------- //
	
	/**
	 * Constructor which takes the name of the button and the handler which
	 * takes care of handling the on click event.
	 * 
	 * @param text The text that will be appearing on the button.
	 * @param handler The EventHandler which takes care of what happens to
	 * 		this button when it gets clicked.
	 */
	public MenuButton(String text, EventHandler<ActionEvent> handler){
		super(text);
		
		//set size formatting of the button
		this.setMinHeight(MIN_HEIGHT);
		this.setMinWidth(MIN_WIDTH);
		this.setMaxHeight(MAX_HEIGHT);
		this.setMaxWidth(MAX_WIDTH);
		
		//set color formatting
		this.setBackground(new Background(
				new BackgroundFill(BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setTextFill(FORGROUND);
		
		
		//set text formatting
		this.setFont(FONT);
		
		//set the given handler to the button
		this.setOnAction(handler);
	}
}
