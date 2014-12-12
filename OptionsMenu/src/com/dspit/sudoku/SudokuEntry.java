

package com.dspit.sudoku;

/**
 * A virtual representation of each entry in a sudoku board.
 * 
 * @author David Boivin (Spit)
 */
public final class SudokuEntry{

// Members ----------------------------------------------------------------- //
	
	private int mRow;
	private int mCol;
	private String mValue;
	private boolean mIsHint;
	
// Constructor ------------------------------------------------------------- //
	
	/**
	 * Constructor which builds the entry from the given values.
	 * 
	 * @param row The row of the entry.
	 * @param col The column of the entry.
	 * @param value The Value of the entry.
	 * @param isHint Whether this is entry is a hint to the player (starting tile)
	 */
	public SudokuEntry(int row, int col, int value, boolean isHint){
		mRow = row;
		mCol = col;
		mValue = String.valueOf(value);
		mIsHint = isHint;
	}
	
	/**
	 * Copy Constructor.
	 * 
	 * @param entry The entry to copy.
	 */
	public SudokuEntry(SudokuEntry entry){
		mRow = entry.getRow();
		mCol = entry.getCol();
		mValue = entry.getValue();
		mIsHint = entry.isHint();
	}
	
// Public Methods ---------------------------------------------------------- //
	
	/**
	 * Returns the row of the entry.
	 * 
	 * @return Entry row.
	 */
	public int getRow(){
		return mRow;
	}
	
	/**
	 * Returns the column of the entry.
	 * 
	 * @return Entry Column.
	 */
	public int getCol(){
		return mCol;
	}
	
	/**
	 * Returns the value of this entry is string form.
	 * 
	 * @return String representation of the value of this entry.
	 */
	public String getValue(){
		return mValue;
	}
	
	/**
	 * Returns whether this entry is a hint to the user or not.
	 * 
	 * @return<b>true</b> if the entry is a hint and <b>false</b> otherwise.
	 */
	public boolean isHint(){
		return mIsHint;
	}
}
