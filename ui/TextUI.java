package ui;

import game.VacuumGame;
import java.util.Scanner;

/** A simple TextUI for the game. */
public class TextUI implements UI {
	
	/** The game */
	private VacuumGame game;
	
	/** Initializes a TextUI with the game.
	 * 
	 * @param game the game this TextUI will be representing.
	 */
	public TextUI(VacuumGame game) {
		this.game = game;
	}

	@Override
	/** Launches the game and keeps asking for user input until the game
	 *  is over.
	 */
	public void launchGame() {
		Scanner user_input = new Scanner(System.in);
		while (!this.game.gameOver()) {
			System.out.println(this.game.getGrid());
			System.out.print("Press a key and hit enter: ");
			this.game.move(user_input.next().charAt(0));
		}
		user_input.close();
	}

	@Override
	/** Displays who the winner of the game is */
	public void displayWinner() {
		if (!this.game.gameOver()) {
	        return;
		}
	    int won = this.game.getWinner();
		if (won == 1) {
			System.out.println("Congratulations Player 1! You won the game "
								+ "with a score of "
								+ this.game.getVacuumOne().getScore() + ".");
		} else {
			System.out.println("Congratulations Player 2! You won the game "
					+ "with a score of "
					+ this.game.getVacuumTwo().getScore() + ".");
		}
	}
}
