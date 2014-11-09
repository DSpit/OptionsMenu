

package com.dspit.options_menu.resources;

/**
 * An interface which provides an easy readable way off accessing
 * major application wide Strings.
 * 
 * @author David Boivin (Spit)
 */
public interface ApplicationString {
	
	public static final String APPLICATION_TITLE = "Options Menu";
	
	public static final String MENU_OPTION_1 = "Sudoku";
	public static final String MENU_OPTION_2 = "TBA1";
	public static final String MENU_OPTION_3 = "TBA2";
	public static final String MENU_OPTION_4 = "TBA3";
	public static final String MENU_OPTION_EXIT = "Exit";
	
	public static final String[] MENU_BUTTON_OPTIONS = {MENU_OPTION_1,
														MENU_OPTION_2,
														MENU_OPTION_3,
														MENU_OPTION_4,
														MENU_OPTION_EXIT};
	
	public static final String NOT_IMPLEMENTED_MESSAGE = "This choice will be implemented soon";
	public static final String NOT_IMPLEMENTED_TITLE = "Feature Not Implemented";
}
