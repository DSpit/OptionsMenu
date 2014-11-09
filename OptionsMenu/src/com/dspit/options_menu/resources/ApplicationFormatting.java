

package com.dspit.options_menu.resources;

import java.awt.Dimension;

import javafx.geometry.Insets;
import javafx.scene.text.Font;

/**
 * An interface which stores general fonts that are used in this
 * application.
 * 
 * @author David Boivin (Spit)
 */
public interface ApplicationFormatting {
	
	public static final Font BUTTON_FONT = Font.font("Impact");
	public static final int MEMU_BUTTON_TEXT_SIZE = 20;
	public static final int MENU_SPACING = 15;
	public static final Dimension MENU_BUTTON_SIZE = new Dimension(160, 70);
	public static final Insets WINDOW_PADDING = new Insets(50,50,50,50);
	public static final int NOT_IMPLEMENTED_MESSAGE_DURATION = 1000;
	public static final Dimension NOT_IMPLEMENTED_SIZE = new Dimension(300, 150);
}
