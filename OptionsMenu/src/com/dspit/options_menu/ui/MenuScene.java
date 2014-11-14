

package com.dspit.options_menu.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import com.dspit.options_menu.resources.ApplicationColourPalette;
import com.dspit.options_menu.resources.ApplicationFormatting;

/**
 * This is the part of the user interface which presents the user with 
 * A menu which allows them to select what area of the program they wish
 * to interact with. This is effectively the main menu of the program.
 * 
 * @author David Boivin (Spit)
 */
public class MenuScene extends Scene {														//class MenuScene
	
// Members ---------------------------------------------------------------- //
	
	private String[] mOptions;
	
// Constructors ------------------------------------------------------------ //	
	
	/**
	 * Constructor which builds the Menu from a list of options and assigns
	 * the handler to the every single button which is created to represent 
	 * the options.<br><br>
	 * 
	 * <b>NOTE: </b>The Handler should be able to differentiate each button
	 * 				by the ID of the button (which will be the same as the 
	 * 				text of the button).
	 * 
	 * @param controlHandler The Handler which will go on all the buttons, 
	 * 				each representing one option.
	 * @param options An array of all the options which will be available to 
	 * 				the user on.
	 */
	public MenuScene(EventHandler<ActionEvent> controlHandler, String[] options) {
		//dummy pane until I set the real pane (workaround because my root is a private inner class)
		super(new Pane());		
		
		mOptions = options;
		
		//sets the proper root Pane
		this.setRoot(new MenuPane(options.length));
		
		//sets all the buttons to the root pane
		this.setMenuOptions(controlHandler);
	}
	
// Private Members --------------------------------------------------------- //
	
	/**
	 * Method which does the basic setup for each button in the Main
	 * Menu.
	 * 
	 * @param control The Event handler that is set to each button.
	 */
	private void setMenuOptions(EventHandler<ActionEvent> control){
		
		//iterate through all known button names and create a button for each
		for(int i = 0; i < mOptions.length; ++i){
			
			//create new button
			Button b = new MenuButton(mOptions[i], control);
			b.setId(mOptions[i]);
			//add new button
			((Pane)this.getRoot()).getChildren().add(b);
		}
	}
	
// Private Menu Scene Components ------------------------------------------- //
	
	/**
	 * A customized version of {@link HBox} which displays a set of buttons which 
	 * act as the menu for this program.
	 * 
	 * @author David Boivin (Spit)
	 */
	private class MenuPane extends HBox{														//class MenuPane
		
	// Constants --------------------------------------------------------------- //
		
		private final double SPACING = ApplicationFormatting.MENU_SPACING;
		private final Color BACKGROUND = ApplicationColourPalette.BACKGROUND;
		
	// Constructors ------------------------------------------------------------ //
		
		/**
		 * Default constructor which builds the graphical menu.
		 * 
		 * @param buttonCount The number of buttons that will be contained in this pane.
		 */
		public MenuPane(int buttonCount){
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
			
			this.setPrefWidth(buttonCount*(											//Dynamic way of setting the width.
					ApplicationFormatting.MENU_BUTTON_SIZE.getWidth()+				//Algorithm: NumberOfCol*(ComponentWidth + Spacing) +
					ApplicationFormatting.MENU_SPACING) + 							//LeftPadding + RightPadding = totalWidth
					ApplicationFormatting.WINDOW_PADDING.getLeft() +
					ApplicationFormatting.WINDOW_PADDING.getRight());
			this.setMinHeight(Pane.USE_PREF_SIZE);
			this.setMinWidth(Pane.USE_PREF_SIZE);
		}
	}
	
	/**
	 * Button used in the menu of this program.
	 * 
	 * @author David Boivin (Spit)
	 */
	private class MenuButton extends AbsCustomButton {										//Menu Buttons
		
	// Constants ----------------------------------------------------------- //
			
		private final double MIN_HEIGHT = ApplicationFormatting.MENU_BUTTON_SIZE.getHeight();
		private final double MIN_WIDTH = ApplicationFormatting.MENU_BUTTON_SIZE.getWidth();
		private final Background BACKGROUND = new Background( new BackgroundFill(
				ApplicationColourPalette.COMPONENT_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY));
		private final Background BACKGROUND_SELECTED = new Background( new BackgroundFill(
				ApplicationColourPalette.ACCENT_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY));
		private final Color FORGROUND = ApplicationColourPalette.COMPONENT_FORGROUND;
		private final Font FONT =  ApplicationFormatting.MENU_BUTTON_FONT;

	// Constructor --------------------------------------------------------- //
			
		/**
		 * Constructor which takes the name of the button and the handler which
		 * takes care of handling the on click event.
		 * 
		 * @param text The text that will be appearing on the button.
		 * @param handler The EventHandler which takes care of what happens to
		 * 		this button when it gets clicked.
		 */
		public MenuButton(String text, EventHandler<ActionEvent> handler){
			super();
			
			//sets the background colours to the buttons
			super.setUnselectedBackground(BACKGROUND);
			super.setSelectedBackground(BACKGROUND_SELECTED);
			
			this.setText(text);
			
			//set size formatting of the button (trust me, this thing shouldn't change size xP)
			this.setPrefHeight(MIN_HEIGHT);
			this.setPrefWidth(MIN_WIDTH);
			this.setMinHeight(Control.USE_PREF_SIZE);
			this.setMinWidth(Control.USE_PREF_SIZE);
			this.setMaxHeight(Control.USE_PREF_SIZE);
			this.setMaxWidth(Control.USE_PREF_SIZE);
			
			this.setTextFill(FORGROUND);
			
			//set text formatting
			this.setFont(FONT);
			this.setWrapText(true);
			
			//set the given handler to the button
			this.setOnAction(handler);
		}
		

	}
}

