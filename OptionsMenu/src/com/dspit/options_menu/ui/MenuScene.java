

package com.dspit.options_menu.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import com.dspit.options_menu.resources.ApplicationColourPalette;
import com.dspit.options_menu.resources.ApplicationFormatting;
import com.dspit.options_menu.resources.ApplicationString;

/**
 * This is the part of the user interface which presents the user with 
 * A menu which allows them to select what area of the program they wish
 * to interact with. This is effectively the main menu of the program.
 * 
 * @author David Boivin (Spit)
 */
public class MenuScene extends Scene {													//class MenuScene
	
// Constructors ------------------------------------------------------------ //	
	
	/**
	 * Default constructor which builds a Scene with the default root object.
	 */
	public MenuScene(EventHandler<ActionEvent> controlHandler) {
		super(new MenuPane(controlHandler));
	}

}

/**
 * A customized version of {@link HBox} which displays a set of buttons which 
 * act as the menu for this program.
 * 
 * @author David Boivin (Spit)
 */
class MenuPane extends HBox{															//class MenuPane
	
// Constants --------------------------------------------------------------- //
	
	private final double SPACING = ApplicationFormatting.MENU_SPACING;
	private final Color BACKGROUND = ApplicationColourPalette.BACKGROUND;
	
// Constructors ------------------------------------------------------------ //
	
	/**
	 * Default constructor which builds the graphical menu.
	 */
	public MenuPane(EventHandler<ActionEvent> controlHandler){
		super();
		
		//sets wanted formatting
		this.setAlignment(Pos.CENTER);
		this.setBackground(new Background(
				new BackgroundFill(BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setSpacing(SPACING);
		
		//set dynamic size formatting
		this.setPrefHeight(ApplicationFormatting.MENU_BUTTON_SIZE.getHeight()+ 	//Dynamic way of setting the height.
				ApplicationFormatting.WINDOW_PADDING.getTop() +					// Algorithm: NumberOfRows(1)*ComponentHeight + 
				ApplicationFormatting.WINDOW_PADDING.getBottom());				//TopPadding + BottomPadding = totalHeight
		
		this.setPrefWidth(ApplicationString.MENU_BUTTON_OPTIONS.length*(		//Dynamic way of setting the width.
				ApplicationFormatting.MENU_BUTTON_SIZE.getWidth()+				//Algorithm: NumberOfCol*(ComponentWidth + Spacing) +
				ApplicationFormatting.MENU_SPACING) + 							//LeftPadding + RightPadding = totalWidth
				ApplicationFormatting.WINDOW_PADDING.getLeft() +
				ApplicationFormatting.WINDOW_PADDING.getRight());
		this.setMinHeight(Pane.USE_PREF_SIZE);
		this.setMinWidth(Pane.USE_PREF_SIZE);

		//set all buttons
		this.setMenuOptions(controlHandler);
	}
	
// Private Members --------------------------------------------------------- //
	
	/**
	 * Method which does the basic setup for each button in the Main
	 * Menu.
	 */
	private void setMenuOptions(EventHandler<ActionEvent> handler){
		
		//iterate through all known button names and create a button for each
		for(int i = 0; i < ApplicationString.MENU_BUTTON_OPTIONS.length; ++i){
			
			//create new button
			Button b = new MenuButton(ApplicationString.MENU_BUTTON_OPTIONS[i], handler);
			b.setId(ApplicationString.MENU_BUTTON_OPTIONS[i]);
			//add new button
			this.getChildren().add(b);
		}
	}
	
// Private Inner Classes --------------------------------------------------- //
	
	class MenuButton extends Button {
		
		// Constants --------------------------------------------------------------- //
			
			private final double MIN_HEIGHT = ApplicationFormatting.MENU_BUTTON_SIZE.getHeight();
			private final double MIN_WIDTH = ApplicationFormatting.MENU_BUTTON_SIZE.getWidth();
			private final Background BACKGROUND = new Background( new BackgroundFill(
					ApplicationColourPalette.COMPONENT_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY));
			private final Background BACKGROUND_SELECTED = new Background( new BackgroundFill(
					ApplicationColourPalette.ACCENT_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY));
			private final Color FORGROUND = ApplicationColourPalette.COMPONENT_FORGROUND;
			private final Font FONT =  ApplicationFormatting.BUTTON_FONT;

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
}
