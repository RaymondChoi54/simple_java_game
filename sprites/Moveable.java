package sprites;

/** A representation of what it means to be Moveable. */
public interface Moveable {
	
	/** Changes the row and column.
	 * 
	 * @param row the new row.
	 * @param column the new column.
	 */
	public void moveTo(int row, int column);
}
