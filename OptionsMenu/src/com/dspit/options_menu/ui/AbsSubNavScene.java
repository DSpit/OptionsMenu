

package com.dspit.options_menu.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import com.dspit.options_menu.resources.ApplicationColourPalette;
import com.dspit.options_menu.resources.ApplicationFormatting;
import com.dspit.options_menu.resources.ApplicationString;

/**
 * This abstract scene which provides the basic framework for any
 * scene which is below the Menu Scene in the navigation hierarchy 
 * (which means pretty much everything else).
 * 
 * @author David Boivin (Spit)
 */
public abstract class AbsSubNavScene extends Scene {

// Constants --------------------------------------------------------------- //
	
	
// Members ----------------------------------------------------------------- //
	
	private boolean mIsFull;
	
// Constructors ------------------------------------------------------------ //
	
	public AbsSubNavScene(EventHandler<ActionEvent> controller, String header){
		super(new VBox());
		
		//convenience
		VBox mainContainer = (VBox)this.getRoot();
		
		mainContainer.getChildren().add(new HeaderPane(header, controller));
		
		//TODO set proper sizing
		
		mIsFull = false;
	}
	
	public AbsSubNavScene(EventHandler<ActionEvent> controller, String header, Pane mainPane){
		this(controller, header);
		
		this.setMainPane(mainPane);
	}
	
// Protected Methods ------------------------------------------------------- //
	
	protected void setMainPane(Pane mainPane){
		
		if(mIsFull){
			
			for(Node n : ((Pane)this.getRoot()).getChildren()){
				if(!(n instanceof HeaderPane)){
					((Pane)this.getRoot()).getChildren().remove(n);
				}
			}
			
			mIsFull = false;
		}
		
		((Pane)this.getRoot()).getChildren().add(new ContinerWrapper(mainPane));
		
		mIsFull = true;
	}
	
// Private Inner Classes --------------------------------------------------- //
	
	/**
	 * Private inner class of the sub navigation scene which represents the 
	 * header of the scene, which will be displayed on every sub navigation screen.
	 * 
	 * @author David Boivin (Spit)
	 */
	private class HeaderPane extends HBox{
		
	// Constants ----------------------------------------------------------- //
		
		private Font HEADER_FONT = ApplicationFormatting.HEADER_TITLE_FONT;
		private Background BACKGROUND = new Background(
				new BackgroundFill(ApplicationColourPalette.ACCENT_BACKGROUND,
									CornerRadii.EMPTY, Insets.EMPTY));
		private Color FORGROUND = ApplicationColourPalette.ACCENT_FORGROUND;
		private Insets PADDING = new Insets(ApplicationFormatting.WINDOW_PADDING.getTop(),
										ApplicationFormatting.WINDOW_PADDING.getRight(),
										20,
										ApplicationFormatting.WINDOW_PADDING.getLeft());
		private int COMPONENT_HEIGHT = ApplicationFormatting.HEADER_COMPONENT_HEIGHT;
		
	//Constructors --------------------------------------------------------- //
		
		/**
		 * Builds the pane using the title of the navigation option and the
		 * controller which will bring the stage back to the menu screen.
		 * 
		 * @param title The title header of the header.
		 * @param controller The event handler which handles navigation in the application.
		 */
		public HeaderPane(String title, EventHandler<ActionEvent> controller){
			super();
			
			//set the background to the header
			this.setBackground(BACKGROUND);
			this.setPadding(PADDING);
			
			//create header label and set the proper formatting to it.
			Label headerText = new Label(title);
			headerText.setTextFill(FORGROUND);
			headerText.setFont(HEADER_FONT);
			headerText.setAlignment(Pos.CENTER_LEFT);
			
			//create home button
			ImageView homeImage = new ImageView(new Image("com/dspit/options_menu/resources/1_navigation_back.png", 0, COMPONENT_HEIGHT, true, false));
			Button home = new HeaderButton(homeImage, ApplicationString.NAV_OPTION_HOME, controller);
			
			//create exit button
			ImageView exitImage = new ImageView(new Image("com/dspit/options_menu/resources/1-navigation-cancel.png", 0, COMPONENT_HEIGHT, true, false));
			Button exit = new HeaderButton(exitImage, ApplicationString.NAV_OPTION_EXIT, controller);
			
			//add components to pane
			this.getChildren().addAll(headerText, home, exit);
			
			this.setFillHeight(false);
		}
		
	// Private Inner Classes ----------------------------------------------- //
		
		/**
		 * Class which represent a navigation option button.
		 * 
		 * @author David Boivin (Spit)
		 */
		private class HeaderButton extends AbsCustomButton{
			
		// Constants ------------------------------------------------------- //
			
			private final Background BACKGROUND = new Background( new BackgroundFill(
					ApplicationColourPalette.COMPONENT_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY));
			private final Background BACKGROUND_SELECTED = new Background( new BackgroundFill(
					ApplicationColourPalette.ACCENT_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY));
			
		// Constructor ----------------------------------------------------- //
			
			/**
			 * Builds a button to the basic design which matches with the Header style
			 * 
			 * @param graphic The picture to indicate to the user what this button is used for.
			 * @param id The id of the button (Should correspond to the Id used to identify this button in the controller)
			 * @param controller The Event Handler which takes care of navigating thought the application.
			 */
			public HeaderButton(ImageView graphic, String id, EventHandler<ActionEvent> controller){
				super();
				
				super.setUnselectedBackground(BACKGROUND);
				super.setSelectedBackground(BACKGROUND_SELECTED);
				
				this.setGraphic(graphic);
				this.setId(id);
				this.setTooltip(new Tooltip(id));
				this.setOnAction(controller);
				this.setAlignment(Pos.CENTER_RIGHT);
			}
		}
	}
	
	private class ContentWrapper extends StackPane{
		//TODO finish writing wrapper
	}
}
 