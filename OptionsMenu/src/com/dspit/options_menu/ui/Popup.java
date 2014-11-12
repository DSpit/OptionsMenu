package com.dspit.options_menu.ui;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import com.dspit.options_menu.resources.ApplicationFormatting;
import com.dspit.options_menu.resources.ApplicationString;

/**
 * Debugging "NOT IMPLEMENTED" message.
 * 
 * @author David Boivin (Spit)
 */
public class Popup extends Stage {
	
// Constructor ------------------------------------------------------------- //
	
	public Popup(String title){
		super(StageStyle.UTILITY);
		
		//set the close timer
		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		pause.setOnFinished(new CloseTimeTask());
		
		this.setHeight(ApplicationFormatting.NOT_IMPLEMENTED_SIZE.getHeight());
		this.setWidth(ApplicationFormatting.NOT_IMPLEMENTED_SIZE.getWidth());
		this.setTitle(ApplicationString.NOT_IMPLEMENTED_TITLE);
		this.setScene(new Scene(new StackPane(new Label(ApplicationString.NOT_IMPLEMENTED_MESSAGE))));
		this.show();
		
		//start the close timer
		pause.play();
	}
	
// Timer Tasks ------------------------------------------------------------- //
	
	private class CloseTimeTask implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			close();	//closes stage	
		}
	}
}
