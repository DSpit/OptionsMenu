

package com.dspit.options_menu.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import com.dspit.options_menu.resources.ApplicationColourPalette;
import com.dspit.options_menu.resources.ApplicationFormatting;

class MenuButton extends Button {
	
// Constants --------------------------------------------------------------- //
	
	private final double MIN_HEIGHT = ApplicationFormatting.MENU_BUTTON_SIZE.getHeight();
	private final double MIN_WIDTH = ApplicationFormatting.MENU_BUTTON_SIZE.getWidth();
	private final Background BACKGROUND = new Background( new BackgroundFill(
			ApplicationColourPalette.COMPONENT_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY));
	private final Background BACKGROUND_SELECTED = new Background( new BackgroundFill(
			ApplicationColourPalette.COMPONENT_BACKGROUND.darker(), CornerRadii.EMPTY, Insets.EMPTY));
	private final Color FORGROUND = ApplicationColourPalette.COMPONENT_FORGROUND;
	private final Font FONT =  Font.font(ApplicationFormatting.BUTTON_FONT.getName(), 
			FontWeight.BOLD,  ApplicationFormatting.MEMU_BUTTON_TEXT_SIZE);

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
		
		//set size formatting of the button (trust me, this thing shouldn't change size xP)
		this.setPrefHeight(MIN_HEIGHT);
		this.setPrefWidth(MIN_WIDTH);
		this.setMinHeight(Control.USE_PREF_SIZE);
		this.setMinWidth(Control.USE_PREF_SIZE);
		this.setMaxHeight(Control.USE_PREF_SIZE);
		this.setMaxWidth(Control.USE_PREF_SIZE);
		
		//set color formatting
		this.setBackground(BACKGROUND);
		this.setTextFill(FORGROUND);
		
		//set text formatting
		this.setFont(FONT);
		this.setWrapText(true);
		
		//set the given handler to the button
		this.setOnAction(handler);
		
		//set visuals for the mouse click on the button (was broken by the setBackground() method.)
		//To Teacher: If you want me to explain some more just ask me
		EventHandler<MouseEvent> workaroundHandler = new VisualClickWorkaroundHandler();
		this.setOnMousePressed(workaroundHandler);
		this.setOnMouseReleased(workaroundHandler);
	}
	
//Private Listeners -------------------------------------------------------- //
	
	private class VisualClickWorkaroundHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent e) {
			
			//action to perform if the button is pressed
			if(e.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
				setBackground(BACKGROUND_SELECTED);
			}
			
			//action to perform if the button is released
			if(e.getEventType().equals(MouseEvent.MOUSE_RELEASED)){
				setBackground(BACKGROUND);
			}
			
		}
		
	}
}
