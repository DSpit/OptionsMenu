package com.dspit.options_menu.ui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

import com.dspit.options_menu.resources.ApplicationColourPalette;

/**
 * Basic button with custom background. This class makes sure that 
 * when the button in clicked, the selected animation still is performed.
 * 
 * @author David Boivin (Spit)
 */
public abstract class AbsCustomButton extends Button {
	
// Constants --------------------------------------------------------------- //
	
	private static final Background DEFAULT_BACKGROUND = new Background(
			new BackgroundFill(ApplicationColourPalette.COMPONENT_BACKGROUND, 
					CornerRadii.EMPTY, Insets.EMPTY));
	private static final Background DEFAULT_SELECTED_BACKGROUND = new Background( new BackgroundFill(
			ApplicationColourPalette.ACCENT_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY));
	
// Members ----------------------------------------------------------------- //
	
	private Background mBackground;
	private Background mSelectedBackground;
	
// Constructors ------------------------------------------------------------ //
	
	/**
	 * Builds a basic button with the default Button background
	 */
	public AbsCustomButton(){
		this(DEFAULT_BACKGROUND, DEFAULT_SELECTED_BACKGROUND);
	}
	
	/**
	 * Builds a basic button with the given backgrounds
	 * 
	 * @param background The regular background of this button
	 * @param selectedBackground The selected background of this button
	 */
	public AbsCustomButton(Background background, Background selectedBackground){
		
		mBackground = background;
		mSelectedBackground = selectedBackground;
		
		//set color formatting
		this.setBackground(mBackground);
		
		//set visuals for the mouse click on the button (was broken by the setBackground() method.)
		//To Teacher: If you want me to explain some more just ask me
		EventHandler<MouseEvent> workaroundHandler = new VisualClickWorkaroundHandler();
		this.setOnMousePressed(workaroundHandler);
		this.setOnMouseReleased(workaroundHandler);
		
	}
	
// Public Methods ---------------------------------------------------------- //
	
	/**
	 * Sets the normal state button background.
	 * 
	 * @param background The background of the button in normal state.
	 */
	public void setUnselectedBackground(Background background){
		mBackground = background;
	}
	
	/**
	 * Sets the selected state button background.
	 * 
	 * @param selectedBackground The background of the button is selected state.
	 */
	public void setSelectedBackground(Background selectedBackground){
		mSelectedBackground = selectedBackground;
	}
	
	
//Private Listeners -------------------------------------------------------- //
	
	private class VisualClickWorkaroundHandler implements EventHandler<MouseEvent>{
		
		@Override
		public void handle(MouseEvent e) {
			
			//action to perform if the button is pressed
			if(e.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
				setBackground(mSelectedBackground);
			}
			
			//action to perform if the button is released
			if(e.getEventType().equals(MouseEvent.MOUSE_RELEASED)){
				setBackground(mBackground);
			}
		}
	}
}
