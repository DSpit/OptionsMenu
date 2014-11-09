package com.dspit.options_menu.ui;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import com.dspit.options_menu.resources.ApplicationFormatting;
import com.dspit.options_menu.resources.ApplicationString;

public class FeatureNotImplementedStage extends Stage {
	
// Constructor ------------------------------------------------------------- //
	
	public FeatureNotImplementedStage(){
		super();
		
		//set the close timer
		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		pause.setOnFinished(new CloseTimeTask());
		
		//build the stage
		StackPane root = new StackPane();
		root.getChildren().add(new Label(ApplicationString.NOT_IMPLEMENTED_MESSAGE));
		
		Scene scene = new Scene(root);
		
		this.setScene(scene);
		this.setTitle(ApplicationString.NOT_IMPLEMENTED_TITLE);
		this.show();
		
		this.setHeight(ApplicationFormatting.NOT_IMPLEMENTED_SIZE.getHeight());
		this.setWidth(ApplicationFormatting.NOT_IMPLEMENTED_SIZE.getWidth());
		
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
