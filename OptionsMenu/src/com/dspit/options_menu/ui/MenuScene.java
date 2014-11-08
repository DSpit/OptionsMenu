

package com.dspit.options_menu.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import com.dspit.options_menu.resources.ApplicationColourPalette;
import com.dspit.options_menu.resources.ApplicationString;

/**
 * This is the part of the user interface which presents the user with 
 * A menu which allows them to select what area of the program they wish
 * to interact with. This is effectively the main menu of the program.
 * 
 * @author David Boivin (Spit)
 */
public class MenuScene extends Scene {													//class MenuScene

// Constants --------------------------------------------------------------- //
	
	private final Color BACKGROUND = ApplicationColourPalette.BACKGROUND;
	
// Constructors ------------------------------------------------------------ //	
	
	/**
	 * Default constructor which builds a Scene with the default root object.
	 */
	public MenuScene() {
		super(new MenuPane());
		
		//set formatting
		this.setFill(BACKGROUND);
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
	
	private final double SPACING = 20;
	
// Constructors ------------------------------------------------------------ //
	
	/**
	 * Default constructor which builds the graphical menu.
	 */
	public MenuPane(){
		super();
		
		//sets wanted formatting
		this.setAlignment(Pos.CENTER);
		this.setSpacing(SPACING);
		

		//set all buttons
		this.setMenuOptions();
		
	}
	
// Private ----------------------------------------------------------------- //
	
	/**
	 * Method which does the basic setup for each button in the Main
	 * Menu.
	 */
	private void setMenuOptions(){
		
		//initialize the main handler for all main menu buttons
		EventHandler<ActionEvent> handler = new MenuButtonClick();
		
		//iterate through all known button names and create a button for each
		for(int i = 0; i < ApplicationString.MENU_BUTTON_NAMES.length; ++i){
			
			//create and add new button
			this.getChildren().add(new MenuButton(ApplicationString.MENU_BUTTON_NAMES[i], handler));
		}
	}
}
