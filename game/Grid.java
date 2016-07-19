package game;

/** A representation of a Grid. */
public interface Grid<T> {
	
	/** Sets the cell in row row and column column to item. */
	public void setCell(int row, int column, T item);
	
	/** Returns the T in row row and column column.
	 * 
	 * @return the T in row row and column column.
	 */
	public T getCell(int row, int column);
	
	/** Returns the number of rows in this Grid.
	 * 
	 * @return the number of rows in this Grid.
	 */
	public int getNumRows();
	
	/** Returns the number of columns in this Grid.
	 * 
	 * @return the number of columns in this Grid.
	 */
	public int getNumColumns();
	
	/** Returns true iff Grid is equal to other.
	 * 
	 * @param other The object to compare with.
	 * @return      true iff all the cells in this Grid are identical to
	 *              other and the number of rows and columns are equal.
	 */
	public boolean equals(Object other);
	
	/** Returns the String representation of Grid.
	 * 
	 * @return the concatenation of the String representations of T in each
	 *         row of Grid, each row is on a newline.
	 */
	public String toString();
}
