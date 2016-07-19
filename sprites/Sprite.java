package sprites;

/** A representation of a Sprite. */
public abstract class Sprite {
	
	/** The symbol of this Sprite. */
	protected char symbol;
	
	/** The row this Sprite is located at. */
	protected int row;
	
	/** The column this Sprite is located at. */
	protected int column;
	
	/** Initializes a Sprite with a given symbol, located at row row and
	 *  column column.
	 * 
	 * @param symbol the symbol of this Sprite.
	 * @param row the row this Sprite is located at.
	 * @param column the column this Sprite is located at.
	 */
	public Sprite(char symbol, int row, int column) {
		this.symbol = symbol;
		this.row = row;
		this.column = column;
	}
	
	/** Returns the symbol of this Sprite.
	 * 
	 * @return the symbol of this Sprite.
	 */
	public char getSymbol() {
		return this.symbol;
	}
	
	/** Returns the row this Sprite is located on.
	 * 
	 * @return the row this Sprite is located on.
	 */
	public int getRow() {
		return this.row;
	}
	
	/** Returns the column this Sprite is located on.
	 * 
	 * @return the column this Sprite is located on.
	 */
	public int getColumn() {
		return this.column;
	}

	/** Returns the String representation of this Sprite.
	 * 
	 * @return the symbol of this Sprite as a String.
	 */ 
	public String toString() {
		return "" + this.symbol; 
	}
}
