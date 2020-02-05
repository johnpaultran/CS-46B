package sudoku;

import java.util.*;


public class Grid 
{
	private int[][]						values;
	

	//
	// DON'T CHANGE THIS.
	//
	// Constructs a Grid instance from a string[] as provided by TestGridSupplier.
	// See TestGridSupplier for examples of input.
	// Dots in input strings represent 0s in values[][].
	//
	public Grid(String[] rows)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
		{
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i=0; i<9; i++)
			{
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}
	
	
	//
	// DON'T CHANGE THIS.
	//
	public String toString()
	{
		String s = "";
		for (int j=0; j<9; j++)
		{
			for (int i=0; i<9; i++)
			{
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char)('0' + n);
			}
			s += "\n";
		}
		return s;
	}


	//
	// DON'T CHANGE THIS.
	// Copy ctor. Duplicates its source. You’ll call this 9 times in next9Grids.
	//
	Grid(Grid src)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
			for (int i=0; i<9; i++)
				values[j][i] = src.values[j][i];
	}
	
	// Set a specific x-y coordinate of a Grid to a given value.
		public void setValue(int x, int y, int val) 
		{
			this.values[y][x] = val;
		}
	
	//
	// COMPLETE THIS
	//
	//
	// Finds an empty member of values[][]. Returns an array list of 9 grids that look like the current grid,
	// except the empty member contains 1, 2, 3 .... 9. Returns null if the current grid is full. Don’t change
	// “this” grid. Build 9 new grids.
	// 
	//
	// Example: if this grid = 1........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//
	// Then the returned array list would contain:
	//
	// 11.......          12.......          13.......          14.......    and so on     19.......
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	//
	public ArrayList<Grid> next9Grids()
	{		
		int xOfNextEmptyCell = 0;
		int yOfNextEmptyCell = 0;

		boolean found = false;
		
		// Find x,y of an empty cell.
		for (int j=0; j<9; j++)
		{	
			for (int i=0; i<9; i++)
			{
				if (!found && (values[j][i] == 0)) {									// Find first instance of "0" in row, col ([j], [i])
					found = true;
					xOfNextEmptyCell = i; 
					yOfNextEmptyCell = j; 
				}
			}
		}
		
		// Check if no empty cell has been found
				if (!found) 
				{
					return null;
				}
				
		// Construct array list to contain 9 new grids.
		ArrayList<Grid> grids = new ArrayList<Grid>();

		// Create 9 new grids as described in the comments above. Add them to grids.
		for (int testVal = 1; testVal<=9; testVal++) 
		{
			Grid newGrid = new Grid(this);
			newGrid.setValue(xOfNextEmptyCell, yOfNextEmptyCell, testVal); 
			grids.add(newGrid);
		}
		
		return grids;
	}
	
	
	//
	// COMPLETE THIS
	//
	// Returns true if this grid is legal. A grid is legal if no row, column, or
	// 3x3 block contains a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
	//
	public boolean isLegal()
	{
		// Check every row. If you find an illegal row, return false.
		for (int row = 0; row < values[0].length; row++) 
		{
			if (!isLegalRow(row))
			{
				return false;
			}
		} 
		
		// Check every column. If you find an illegal column, return false.
		for (int col = 0; col < values[0].length; col++)
		{
			if (!isLegalCol(col))
			{
				return false;
			}
		}
		
		// Check every block. If you find an illegal block, return false.
		int[][] block = new int[3][3];
		
		// Divide into blocks
		for (int blockStartRow = 0; blockStartRow < values[0].length; blockStartRow += 3) 
		{
			for (int blockStartCol = 0; blockStartCol < values[0].length; blockStartCol += 3) 
			{
						
				// Go through each item in each block
				for (int blockRow = blockStartRow; blockRow < 3; blockRow++) 
				{
					for (int blockCol = blockStartCol; blockCol < 3; blockCol++) 
					{
						block[blockRow][blockCol] = values[blockStartRow + blockRow][blockStartCol + blockCol];
					}
				}
						
			// Check if sub-block (3x3) is legal
				if (!isLegalBlock(block))
				{
					return false;
				}
			}
		}
		
		// All rows/cols/blocks are legal.
		return true;
	}

	// Checks if a given set of values (exactly 9 values for a 9x9 Sudoku board 
	// have repeats if they are non-zero. 
	public boolean containsNonZeroRepeat(int[] possibleDuplicates) 
	{
		// Sort out of place to retain original grid pattern
		int[] sortedPossibleDuplicates = possibleDuplicates.clone();						
		Arrays.sort(sortedPossibleDuplicates);
			
		for (int i = 0; i < 8; i++) 
		{
			if ((sortedPossibleDuplicates[i] != 0) && (sortedPossibleDuplicates[i] == sortedPossibleDuplicates[i+1])) 
			{				
				return true;
			}
		}
			
			return false;
	}

	// Check if row is legal
	public boolean isLegalRow(int row) 
	{
		int[] rowVals = values[row];
		
		return !containsNonZeroRepeat(rowVals);
	}
	
	// Check if column is legal
	public boolean isLegalCol(int col) 
	{
		int[] colVals = new int[values[0].length];
		
		// Check all rows of a given column
		for (int row = 0; row < values[0].length; row++) 
		{
			colVals[row] = values[row][col];
		}
		
		return !containsNonZeroRepeat(colVals);
	}
	
	// Assumes a 9x9 Sudoku puzzle, defines start of block by top left corner 
	// of each block and bottom of block by bottom right corner of each block	
	public boolean isLegalBlock(int[][] block) 
	{
		int[] blockVals = new int[9];
		int blockValIndex = 0;
			
		for (int j = 0; j < block[0].length; j++) 
		{
			for (int i = 0; i < block[0].length; i++) 
			{
				blockVals[blockValIndex] = block[j][i];
				blockValIndex++;
			}
		}
			
		return !containsNonZeroRepeat(blockVals);
	}
	
	//
	// COMPLETE THIS
	//
	// Returns true if every cell member of values[][] is a digit from 1-9.
	//
	public boolean isFull()
	{
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				if (values[j][i] == 0){
					return false;
				}
			}
		}
		
		return true;
	}
	

	//
	// COMPLETE THIS
	//
	// Returns true if x is a Grid and, for every (i,j), 
	// x.values[i][j] == this.values[i][j].
	//
	public boolean equals(Object x)
	{
		Grid that = (Grid)x;
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				if (this.values[j][i] != that.values[j][i]) {
					return false;
				}
			}
		}
		
		return true;
	}
}
