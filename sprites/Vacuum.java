package sprites;

import game.Constants;

/** A representation of a Vacuum. */
public class Vacuum extends Sprite implements Moveable {
	
	/** The score of this Vacuum. */
	private int score;
	
	/** The capacity of this Vacuum. */
	private int capacity;
	
	/** The fullness of this Vacuum. */
	private int fullness;
	
	/** The Sprite that is under this Vacuum. */
	private Sprite under;
	
	/** Initializes a Vacuum with a given symbol and capacity. Located at
	 *  row row and column column. It is also given a score of zero, a
	 *  fullness of zero, and a CleanHallway under it.
	 * 
	 * @param symbol the symbol of this Vacuum.
	 * @param row the row this Vacuum is located at.
	 * @param column the column this Vacuum is located at.
	 * @param capacity the capacity of this Vacuum.
	 */
	public Vacuum(char symbol, int row, int column, int capacity) {
		super(symbol, row, column);
		this.capacity = capacity;
		this.score = Constants.INIT_SCORE;
		this.fullness = Constants.EMPTY;
		this.under = new CleanHallway(Constants.CLEAN, row, column);
	}

	@Override
	/** Changes the row and column this Vacuum is located on.
	 *  row is changed to row and column is changed to column.
	 * 
	 * @param row the new row this Vacuum is located on.
	 * @param column this new column this Vacuum is located on.
	 */
	public void moveTo(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	/** Returns true if the capacity is greater than or equal the fullness
	 *  plus the fullness increment and increments the fullness. If true,
	 *  score is also added to the score of this vacuum and the Sprite 
	 *  under this Vacuum becomes a CleanHallway.
	 * 
	 * @param score the score added to the score of this Vacuum.
	 * @return      returns true if the capacity is greater than or equal to
	 *              the fullness plus the fullness increment.
	 */
	public boolean clean(int score) {
		if (this.capacity >= this.fullness + Constants.FULLNESS_INC) {
			this.score = this.score + score;
			this.fullness = this.fullness + Constants.FULLNESS_INC;
			this.under = new CleanHallway(Constants.CLEAN, this.row, this.column);
			return true;
		}
		return false;
	}
	
	/** Empties the Vacuum, which reduces the fullness to zero. */
	public void empty() {
		this.fullness = Constants.EMPTY;
	}
	
	/** Returns the score of this Vacuum.
	 * 
	 * @return the score of this Vacuum.
	 */
	public int getScore() {
		return this.score;
	}
	
	/** Changes the Sprite that is under this Vacuum to under. */
	public void setUnder(Sprite under) {
		this.under = under;
	}
	
	/** Returns the Sprite that is under this Vacuum.
	 * 
	 * @return the Sprite that is under this Vacuum.
	 */
	public Sprite getUnder() {
		return this.under;
	}
}
