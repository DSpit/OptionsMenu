

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
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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
	
	
	
// Constructors ------------------------------------------------------------ //
	
	public AbsSubNavScene(EventHandler<ActionEvent> controller, String header){
		super(new VBox());
		
		HeaderPane masthead = new HeaderPane(header, controller);
		ContentWrapper wrapper = new ContentWrapper();
		
		masthead.minWidthProperty().bind(this.widthProperty());
		
		wrapper.minWidthProperty().bind(this.widthProperty());
		wrapper.minHeightProperty().bind(this.heightProperty().subtract(masthead.getHeight()));
		((VBox)this.getRoot()).getChildren().addAll(masthead, wrapper);
		
		this.setSizing();
	}
	
	public AbsSubNavScene(EventHandler<ActionEvent> controller, String header, Pane mainPane){
		this(controller, header);
		
		this.setMainPane(mainPane);
	}
	
// Protected Methods ------------------------------------------------------- //
	
	/**
	 * Assigns the new main pane to the wrapper of this scene.
	 * <br><br><b>NOTE: </b>This method will remove any current main pane
	 * which is stored within the content wrapper.
	 * 
	 * @param mainPane
	 */
	protected void setMainPane(Pane mainPane){
		ContentWrapper wrapper;
		
		//find the content wrapper
		for(Node n : ((Pane)this.getRoot()).getChildren()){
			if(n instanceof ContentWrapper){
				
				//sets the new main pane
				wrapper = (ContentWrapper)n;
				wrapper.populate(mainPane);
				this.setSizing();
			}
		}
	}
	
// Private Methods --------------------------------------------------------- //
	
	private void setSizing(){
		Pane root = (Pane)this.getRoot();	//convenience
		
		//TODO fix sizing issue
		System.out.println("height: " + root.getHeight());
		System.out.println("Width: " + root.getWidth());
		root.setPrefHeight(root.getHeight());
		root.setPrefWidth(root.getWidth());
		root.setMinHeight(Pane.USE_PREF_SIZE);
		root.setMinWidth(Pane.USE_PREF_SIZE);
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
		private Insets PADDING = ApplicationFormatting.HEADER_PADDING;
		private int COMPONENT_HEIGHT = ApplicationFormatting.HEADER_COMPONENT_HEIGHT;
		private int SPACING = ApplicationFormatting.SPACING;
		
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
			this.setSpacing(SPACING);
			
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
			
			//sets the filler region
			Region filler = new Region();
			HBox.setHgrow(filler, Priority.ALWAYS);
			
			//add components to pane
			this.getChildren().addAll(headerText, filler, home, exit);
			
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
	
	/**
	 * A wrapper for the main pane, whatever it is. This is used to
	 * set the background colour and the proper padding regardless
	 * of that the given pane is.
	 * 
	 * @author David Boivin (Spit)
	 */
	private class ContentWrapper extends StackPane{
		
	// Constants ----------------------------------------------------------- //
		
		private final Background BACKGROUND = new Background(
				new BackgroundFill(ApplicationColourPalette.BACKGROUND,
						CornerRadii.EMPTY, Insets.EMPTY));
		private final Insets PADDING = new Insets(30,
											ApplicationFormatting.WINDOW_PADDING.getRight(),
											ApplicationFormatting.WINDOW_PADDING.getBottom(),
											ApplicationFormatting.WINDOW_PADDING.getLeft());
		
	// Constructors -------------------------------------------------------- //
		
		/**
		 * Doesn't set the main pane because non is given. Should be filled later
		 * using {@link #populate(Pane)}.
		 */
		public ContentWrapper(){
			super();
			
			//formatting
			this.setBackground(BACKGROUND);
			this.setPadding(PADDING);
		}
		
	//Public methods ------------------------------------------------------- //
		
		/**
		 * Sets the new main pane which fits in the wrapper.
		 * <br><br><b>NOTE: </b>This method will remove whatever is
		 * held within this wrapper using {@link #depopulate()}before setting the pane.
		 * 
		 * @param mainPane The new main panel
		 */
		public void populate(Pane mainPane){
			
			if(!this.getChildren().isEmpty()){
				this.depopulate();
			}
			
			this.getChildren().add(mainPane);
		}
		
		/**
		 * Removes all children Nodes in the Wrapper.
		 */
		public void depopulate(){
			this.getChildren().removeAll();
		}
	}
}
 