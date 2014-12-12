

package com.dspit.sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.dspit.sudoku.resources.SudokuStrings;

/**
 * A class representing a sudoku board virtually (Not visual but data)
 * 
 * @author David Boivin (Spit)
 */
public class SudokuBoard {
	
// Constants --------------------------------------------------------------- //
	
	private static final int TOTAL_ENTRIES = 81;
	
// Members ----------------------------------------------------------------- //
	
	private SudokuEntry[][] mEntry;
	private boolean mIsReady;

// Constructor ------------------------------------------------------------- //
	
	/**
	 * Constructor which initializes the entry array.
	 */
	public SudokuBoard(){
		super();
		
		mEntry = new SudokuEntry[9][9];
		mIsReady = false;
	}
	
// Public Methods ---------------------------------------------------------- //
	
	/**
	 * Returns a copy of the sudoku entry at location given row and column. 
	 * 
	 * @param row the row of the entry to retrieve.
	 * @param col The column of the entry to retrieve.
	 * 
	 * @return A copy of the entry at the given column and row.
	 * 
	 * @throws NullPointerException if the board isn't ready (isn't populated)
	 */
	public SudokuEntry getEntry(int row, int col) throws NullPointerException{
		
		if(this.isReady()){
			//check if valid row and col entry
			if(!((0 <= row && row < 9) && (0 <= col && col < 9))){
				throw new IndexOutOfBoundsException();
			}
			
			// returns copy
			return new SudokuEntry(mEntry[row][col]);
		}
		
		throw new NullPointerException();
	}
	
	/**
	 * Returns whether the sudoku board is ready to be displayed or not.
	 * 
	 * @return <b>true</b> if the board is populated and ready to be displayed, 
	 * 			otherwise <b>false</b>.
	 */
	public boolean isReady(){
		return mIsReady;
	}
	
	/**
	 * Method which imports the the text file located at the url and checks if it
	 * is valid. 
	 * 
	 * @param url The url of the file to retrieve.
	 * 
	 * @return <b>true</b> if the file can be retrieved and it is valid and 
	 * 			<b>false</b> otherwise.
	 */
	public boolean populate(File url){
		
		SudokuFileParser parser = new SudokuFileParser(url);
		
		//checks if the file is a valid sudoku file I.E. contains <sudoku> @ top
		if(!parser.isValidPath()){
			return false;
		}

		try{
			//parses the file
			ArrayList<SudokuEntry> temp = parser.parse();
			
			//organizes mEntry so that when getEntry is called, it can return
			//the correct entry for the given column and row. Also checks for
			//validity
			if(this.organize(temp)){
				mIsReady = true;
				return true;
			}
			
			return false;
			
		}catch(Exception e){
			//return failure
			return false;
		}
	}
	
	/**
	 * Organizes the list of entries and check if the entry is valid, whether
	 * it is doubled or not. 
	 * 
	 * @param entryList List of SudokuEntry
	 * 
	 * @return <b>true</b> if no entries are doubled and <b>false</b> otherwise.
	 */
	private boolean organize(ArrayList<SudokuEntry> entryList){
		
		for(int i = 0; i < entryList.size(); ++i){
			SudokuEntry temp = entryList.get(i);
			
			//check to make sure that the entry which is referenced by this entry
			//is empty (if not the number is considered doubled and it an invalid grid)
			if(mEntry[temp.getRow()][temp.getCol()] == null){
				mEntry[temp.getRow()][temp.getCol()] = temp;
			}else{
				return false;
			}
		}
		
		return true;
	}
	
// Private Inner Class ----------------------------------------------------- //
	
	/**
	 * A text file parser which obtains sudoku entries from a file which begins
	 * with {@link SudokuStrings#PARSER_VALID_FILE} and contains entries in format
	 * row, col, value, hint(or not) contained within < and >.
	 * 
	 * @author David Boivin (Spit)
	 */
	private class SudokuFileParser{
		
		
		private File mPath;
		private boolean mIsValidPath;
		
		/**
		 * Constructor which initializes members and checks for validity.
		 * 
		 * @param path The path to the file to be parsed.
		 */
		public SudokuFileParser(File path){
			//create scanner and validate path
			mPath = path;
			mIsValidPath = this.checkValidity();
			
		}
		
		/**
		 * Returns whether the path is valid or not based on the internal 
		 * {@link #checkValidity()} class.
		 * 
		 * @return <b>true</b> if the path is valid and <b>false</b> otherwise.
		 */
		public boolean isValidPath(){
			return mIsValidPath;
			
		}
		
		/**
		 * Parses through the file. Ignores anything in the file which is not 
		 * contained within < and >. Will only take the first four parameters
		 * within a < and > section, each parameter being separated by a ";". 
		 * 
		 * @return A list of SudokuEntry
		 * 
		 * @throws Exception If there was an error in parsing.
		 */
		public ArrayList<SudokuEntry> parse() throws Exception{
			
			ArrayList<SudokuEntry> entry = new ArrayList<SudokuEntry>();
			Scanner scanner;
			
			try{
				//create scanner and get data
				scanner = new Scanner(mPath);
				String c = "";
				while(scanner.hasNext()){
					c += scanner.next();
				}
				scanner.close();
				
				int count = 0;
				//start iterating through data
				while(count < c.length()){
					//find opening entry bracket
					if(c.charAt(count) == '<'){
						//build entry
						ArrayList<String> baseEntryBuild = new ArrayList<String>();
						String param = "";	//temp holder
						while(count < c.length()-1){
							count++;
							
							//get next string
							char s = c.charAt(count);
							
							//if its a separator then dump param into baseEntryBuilder
							if(s == ';'){
								baseEntryBuild.add(param);
								param = "";
							}else if(s == '>'){	//closing entry marker
								baseEntryBuild.add(param);
								param = "";
								
								entry.add(new SudokuEntry(
										Integer.parseInt(baseEntryBuild.get(0)),
										Integer.parseInt(baseEntryBuild.get(1)),
										Integer.parseInt(baseEntryBuild.get(2)), 
										(baseEntryBuild.get(3).equals("1"))? true : false));
								break;
								
							}else{
							
								//add the new string to the param temporary holder
								param += String.valueOf(s);
							}
						}
					}	
					count++;
				}
				//check for length validity
				if(entry.size() != TOTAL_ENTRIES){
					throw new Exception();
				}
				
			}catch(Exception e){
				throw e;
			}
			
			return entry;
		}
		
		/**
		 * Method which check the validity of a file by opening it and 
		 * checking its first line to see if a 
		 * {@link SudokuStrings#PARSER_VALID_FILE} pattern is found.
		 * 
		 * @return <b>true</b> if the pattern is found and the file can be open
		 * and <b>false</b> otherwise.
		 */
		private boolean checkValidity(){
			
			Scanner scanner;
			
			//checks path validity
			try {
				scanner = new Scanner(mPath);
				
			} catch (FileNotFoundException e) {
				return false;
			}
			
			scanner.close();
			
			return true;
		}
	}
}
