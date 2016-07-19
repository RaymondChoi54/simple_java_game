package game;

/** A representation of an ArrayGrid. */
public class ArrayGrid<T> implements Grid<T> {
	
	/** The number of rows in this ArrayGrid. */
	private int numRows;
	
	/** The number of columns in this ArrayGrid. */
	private int numColumns;
	
	/** The method of storage for this ArrayGrid. */
	private T[][] array;
	
	@SuppressWarnings("unchecked")
	/** Initializes an ArrayGrid based on the given numRows and numColumns.
	 * 
	 * @param numRows The number of rows in this ArrayGrid
	 * @param numColumns The number of columns in this ArrayGrid
	 */
	public ArrayGrid(int numRows, int numColumns) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.array = (T[][]) new Object[numRows][numColumns];
	}

	@Override
	/** Sets the cell in row row and column column to item. */
	public void setCell(int row, int column, T item) {
		this.array[row][column] = item;
	}

	@Override
	/** Returns the T in row row and column column.
	 * 
	 * @return the T in row row and column column.
	 */
	public T getCell(int row, int column) {
		return this.array[row][column];
	}

	@Override
	/** Returns the number of rows in this ArrayGrid.
	 * 
	 * @return the number of rows in this ArrayGrid.
	 */
	public int getNumRows() {
		return this.numRows;
	}

	@Override
	/** Returns the number of columns in this ArrayGrid.
	 * 
	 * @return the number of columns in this ArrayGrid.
	 */
	public int getNumColumns() {
		return this.numColumns;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	/** Returns true iff ArrayGrid is equal to other.
	 * 
	 * @param other The object to compare with.
	 * @return      true iff all the cells in this ArrayGrid are identical
	 *              to other and the number of rows and columns are equal.
	 */
	public boolean equals(Object other) {
		boolean result = true;
		if (other instanceof ArrayGrid) {
			if (this.getNumRows() == ((Grid<T>) other).getNumRows()
				&& this.getNumColumns() == ((Grid<T>) other).getNumColumns()) {
				for (int row = 0; row < this.getNumRows(); row++) {
					for (int col = 0; col < this.getNumColumns(); col++) {
						if (this.getCell(row, col) != ((Grid<T>) other).getCell(row, col)) {
							result = false;
						}
					}
				}
				return result;
			}
		}
		return false;
	}
	
	/** Returns the String representation of ArrayGrid.
	 * 
	 * @return the concatenation of the String representations of T in each
	 *         row of ArrayGrid, each row is separated by a newline.
	 */
	public String toString() {
		String concat = "";
		for (int row = 0; row < this.getNumRows(); row++) {
			for (int col = 0; col < this.getNumColumns(); col++) {
				concat = concat + this.getCell(row, col);
			}
			concat = concat + '\n';
		}
		return concat;
	}
}

