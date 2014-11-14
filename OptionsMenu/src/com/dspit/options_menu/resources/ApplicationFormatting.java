

package com.dspit.options_menu.resources;

import java.awt.Dimension;

import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * An interface which stores general fonts that are used in this
 * application.
 * 
 * @author David Boivin (Spit)
 */
public interface ApplicationFormatting {
	
	public static final int MENU_BUTTON_TEXT_SIZE = 20;
	public static final int SPACING = 15;
	public static final int HEADER_TEXT_SIZE = 30;
	public static final int HEADER_COMPONENT_HEIGHT = 30;
	public static final Dimension MENU_BUTTON_SIZE = new Dimension(160, 70);
	public static final Insets WINDOW_PADDING = new Insets(50);
	public static final Insets HEADER_PADDING = new Insets(10);
	public static final int NOT_IMPLEMENTED_MESSAGE_DURATION = 1000;
	public static final Dimension NOT_IMPLEMENTED_SIZE = new Dimension(300, 150);
	public static final String APPLICATION_FONT_NAME = "Impact";
	public static final Font MENU_BUTTON_FONT = Font.font(APPLICATION_FONT_NAME,
												FontWeight.BOLD, 
												MENU_BUTTON_TEXT_SIZE);
	public static final Font HEADER_TITLE_FONT = Font.font(APPLICATION_FONT_NAME,
												FontWeight.BOLD,
												HEADER_TEXT_SIZE);
}
