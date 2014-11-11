package com.dspit.options_menu.ui;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import com.dspit.options_menu.resources.ApplicationColourPalette;
import com.dspit.options_menu.resources.ApplicationFormatting;

public class Popup extends Stage {
	
// Constants --------------------------------------------------------------- //
	
	private final Font FONT = ApplicationFormatting.POPUP_FONT;
	private final LinearGradient BACKGROUND = new LinearGradient(0, 0, 1, 1, 
											true, CycleMethod.NO_CYCLE, 
											new Stop(0, Color.TRANSPARENT), new Stop(0.1, ApplicationColourPalette.POPUP_BACKGROUND),
											new Stop(0.9, Color.TRANSPARENT));
	private final Color FORGROUND = ApplicationColourPalette.POPUP_FORGROUND;
	
// Constructor ------------------------------------------------------------- //
	
	public Popup(String title){ //TODO not working very well
		super(StageStyle.TRANSPARENT);
		
		//set the close timer
		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		pause.setOnFinished(new CloseTimeTask());
		
		//Text Label setup
		Label text = new Label(title);
		text.setFont(FONT);
		text.setTextFill(FORGROUND);
		text.setBackground(new Background(new 
				BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		
		//background setup
		Rectangle background = new Rectangle();
		background.heightProperty().bind(text.heightProperty());
		background.widthProperty().bind(text.widthProperty());
		background.setFill(BACKGROUND);
		
		//set everything to the stage
		StackPane root = new StackPane();
		root.getChildren().addAll(background, text);
		
		
		Scene scene = new Scene(root);
		this.setScene(scene);
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
