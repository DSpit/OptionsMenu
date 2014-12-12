package com.dspit.sudoku.ui;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import com.dspit.sudoku.SudokuBoard;
import com.dspit.sudoku.SudokuEntry;
import com.dspit.sudoku.resources.SudokuStrings;

/**
 * A pane containing a grid of labels which display a sudoku grid. 
 * The entries can either be starting table with only hinted entries 
 * being displayed or it can have all entries displayed, the hinted ones 
 * should have a different font.
 * 
 * @author David Boivin (Spit)
 */
public class SudokuGrid extends GridPane{
	
//Members ------------------------------------------------------------------ //
	
	private SudokuBoard mBoard;
	private boolean mHideAns;
	
// Constructor ------------------------------------------------------------- //
	
	/**
	 * Constructor which fills up the Sudoku grid with all the entries
	 * of the given sudoku board.
	 * 
	 * @param mBoard The Boards which this class will use to fill up the
	 * 		itself up.
	 * @param hideAns If the class should display the answer (every entry)
	 * 		of not.
	 */
	public SudokuGrid(SudokuBoard board, boolean hideAns){
		super();
		
		this.getStyleClass().add(SudokuStrings.ID_GRID);
		
		mBoard  = board;
		mHideAns = hideAns;
		
		//fill up the grid pane with smaller grids
		//row loop
		for(int i = 0; i < 3; ++i){
			//col loop
			for(int k = 0; k < 3; ++k){
				SubSudokuGrid subGrid = new SubSudokuGrid(i, k);
				subGrid.getStyleClass().add(SudokuStrings.ID_SUB_GRID);
				this.add(subGrid, k, i);
			}
		}
		
		//loop through new children do add the specific entries
		for(Node node: this.getChildren()){
			SubSudokuGrid subGrid = (SubSudokuGrid)node;
			
			int baseCol = GridPane.getColumnIndex(subGrid)*3;
			int baseRow = GridPane.getRowIndex(subGrid)*3;
			
			//row loops
			for(int i = 0; i < 3; ++i){
				//col loop
				for(int k = 0; k < 3; ++k){
					SudokuEntry entry = mBoard.getEntry(baseRow + i,
														baseCol + k);
					
					//check to see whether to add the text or not
					if(!mHideAns){
						subGrid.set(entry);
					}else if(entry.isHint()){
						subGrid.set(entry);
					}
				}
			}
		}
	}

// Private Inner Classes --------------------------------------------------- //

	/**
	 * Sub Grid of the main {@link SudokuGrid}, used to add thicker borders
	 * and other possible visual coolies.
	 * 
	 * @author David Boivin (Spit)
	 */
	private class SubSudokuGrid extends GridPane{
		
		private Label[][] mCells;
		private int mRowRelativeToSuperGrid;
		private int mColRelativeToSuperGrid;
		
		/**
		 * Constructor which initializes the array of Labels which represent 
		 * the entire grid.
		 */
		public SubSudokuGrid(int row, int col){
			super();
			
			mCells = new Label[3][3];
			mRowRelativeToSuperGrid = row;
			mColRelativeToSuperGrid = col;
			
			//row loop
			for(int i = 0; i < 3; ++i){
				//col loop
				for(int k = 0; k < 3; ++k){
					mCells[i][k] = new Label();
					mCells[i][k].setId(SudokuStrings.ID_CELL);
					this.add(mCells[i][k], k, i);
				}
			}
		}
		
		/**
		 * Sets the value of the label based on the entry.
		 * 
		 * @param entry Object containing all information about this 
		 * 				sudoku grid entry entry.
		 */
		public void set(SudokuEntry entry){
			
			int entryRow = entry.getRow() - mRowRelativeToSuperGrid*3;
			int entryCol = entry.getCol() - mColRelativeToSuperGrid*3;
			
			//temp storage for easy access
			Label l = mCells[entryRow][entryCol];
			
			//sets the value to the label
			l.setText(entry.getValue());
			
			//changes the id if the label is supposed to be a hinted label
			if(entry.isHint()){
				l.setId(SudokuStrings.ID_HINT_CELL);
			}
		}
	}
}
