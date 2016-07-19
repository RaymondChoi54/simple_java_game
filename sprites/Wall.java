package sprites;

/** A representation of a Wall. */
public class Wall extends Sprite {
	
	/** Initializes a Wall with a given symbol, located at row row and
	 *  column column.
	 *  
	 * @param symbol the symbol of this Wall.
	 * @param row the row this Wall is located on.
	 * @param column the column this Wall is located on.
	 */
	public Wall(char symbol, int row, int column) {
		super(symbol, row, column);
	}
}
