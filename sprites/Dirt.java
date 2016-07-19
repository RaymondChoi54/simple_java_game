package sprites;

/** A representation of Dirt. */
public class Dirt extends Sprite {
	
	/** The value of this Dirt. */
	protected int value;
	
	/** Initializes a Dirt with a given symbol and value, located at row row
	 *  and column column.
	 *  
	 * @param symbol the symbol of this Dirt.
	 * @param row the row this Dirt is located on.
	 * @param column the column this Dirt is located on.
	 * @param value the value of this Dirt.
	 */
	public Dirt(char symbol, int row, int column, int value) {
		super(symbol, row, column);
		this.value = value;
	}
	
	/** Returns the value of this Dirt.
	 * 
	 * @return the value of this Dirt.
	 */
	public int getValue() {
		return this.value;
	}
}
