package com.dspit.sudoku.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

import com.dspit.options_menu.resources.ApplicationString;
import com.dspit.options_menu.ui.AbsSubNavScene;

public class SudokuScene extends AbsSubNavScene {

	public SudokuScene(EventHandler<ActionEvent> homeHandler) {
		super(homeHandler, ApplicationString.NAV_OPTION_1, new SudokuPane());
		//just some TODO
	}
	
// Private Inner Classes --------------------------------------------------- //

}

class SudokuPane extends Pane{
	
}
