package sprites;

/** A representation of a DustBall. */
public class DustBall extends Dirt implements Moveable {
	
	/** Initializes a DustBall with a given symbol and value, at row row and
	 *  column col.
	 * 
	 * @param symbol the symbol of this DustBall.
	 * @param row the row this DustBall is located on.
	 * @param col the column this DustBall is located on.
	 * @param value the value of this DustBall.
	 */
	public DustBall(char symbol, int row, int col, int value) {
		super(symbol, row, col, value);
	}

	@Override
	/** Changes the row and column this DustBall is located on.
	 *  row is changed to row and col is changed to column.
	 * 
	 * @param row the new row this DustBall is located on.
	 * @param column this new column this DustBall is located on.
	 */
	public void moveTo(int row, int column) {
		this.row = row;
		this.column = column;
	}
}
