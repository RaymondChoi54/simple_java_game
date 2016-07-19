package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import sprites.CleanHallway;
import sprites.Dirt;
import sprites.Dumpster;
import sprites.DustBall;
import sprites.Sprite;
import sprites.Vacuum;
import sprites.Wall;

/**
 * A class that represents the basic functionality of the vacuum game.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current state of the game.
 * 2. When a move is specified, it checks if it is a legal move and makes the
 *        move if it is legal.
 * 3. It reports information about the current state of the game when asked.
 */
public class VacuumGame {

    // a random number generator to move the DustBalls
    private Random random;

    // the grid
    private Grid<Sprite> grid;

    // the first player
    private Vacuum vacuum1;

    /// the second player
    private Vacuum vacuum2;

    // the dirt (both static dirt and mobile dust balls)
    private List<Dirt> dirts;

    // the dumpsters
    private List<Dumpster> dumpsters;

    /**
     * Creates a new VacuumGame that corresponds to the given input text file.
     * Assumes that the input file has one or more lines of equal lengths, and
     * that each character in it (other than newline) is a character that 
     * represents one of the sprites in this game.
     * @param layoutFileName path to the input grid file
     */
    public VacuumGame(String layoutFileName) throws IOException {
        this.dirts = new ArrayList<Dirt>();
        this.dumpsters = new ArrayList<Dumpster>(); // Jen: may not need this
        this.random = new Random();

        // open the file, read the contents, and determine 
        // dimensions of the grid
        int[] dimensions = getDimensions(layoutFileName);
        this.grid = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);

        // open the file again, read the contents, and store them in grid
        Scanner sc = new Scanner(new File(layoutFileName));

	// INITIALIZE THE GRID HERE
        int row = 0;
        String nextLine = sc.nextLine();
        while (sc.hasNext()) {
            for (int column = 0; column < nextLine.length(); column++) {
            	addToCellAndList(row, column, nextLine);
            }
            nextLine = sc.nextLine();
            row++;   
        }
        for (int column = 0; column < nextLine.length(); column++) {
        	addToCellAndList(row, column, nextLine);
        }
        sc.close();
    }

    /** Returns the Grid that is representing the state of this VacuumGame.
     * 
     * @return the Grid that is representing the state of this VacuumGame.
     */
    public Grid<Sprite> getGrid() {
    	return this.grid;
    }
    
    /** Returns the Vacuum of the first player.
     * 
     * @return the Vacuum of the first player.
     */
    public Vacuum getVacuumOne() {
    	return this.vacuum1;
    }

    /** Returns the Vacuum of the second player.
     * 
     * @return the Vacuum of the second player.
     */
    public Vacuum getVacuumTwo() {
    	return this.vacuum2;
    }
    
    /** Returns the number of rows the Grid has.
     * 
     * @return the number of rows the Grid has.
     */
    public int getNumRows() {
    	return this.grid.getNumRows();
    }
    
    /** Returns the number of columns the Grid has.
     * 
     * @return the number of columns the Grid has.
     */
    public int getNumColumns() {
    	return this.grid.getNumColumns();
    }
    
    /** Returns the Sprite at the ith row and jth column of the Grid.
     * 
     * @param i the row the Sprite to be returned is on.
     * @param j the column the Sprite to be returned is on.
     * @return  the Sprite at the ith row and jth column of the Grid.
     */
    public Sprite getSprite(int i, int j) {
    	return this.grid.getCell(i, j);
    }
    
    /** Returns true iff nextMove is a valid move character and it describes
     *  an allowed move for either Vacuum. Otherwise, false is returned. 
     *  Additionally, if next move is a valid move character, all DustBalls
     *  move randomly one cell away that contain a CleanHallway, Dirt, or
     *  DustBall, if no such cell exists the DustBall does not move.
     *  When a DustBall moves onto another one, the other ceases to
     *  exist. Also, iff a Vacuum moves to Dirt or DustBall when it has room,
     *  the Dirt or DustBall is removed. However, when a Vacuum is full the
     *  Dirt or DustBall is stored under the Vacuum. The Vacuum also empties
     *  itself when it goes on a Dumpster. Anything the Vacuum is on the cell
     *  of is stored under the Vacuum.
     * 
     * @param nextMove the move character that decides the action the Vacuum
     * 	               takes.
     * @return         true iff nextMove is a valid move character and it 
     *                 describes an allowed move for either Vacuum. Otherwise,
     *                 false is returned. 
     */
    public boolean move(char nextMove) {
    	if (validMove(nextMove)) {
    		if (nextMove == Constants.P1_LEFT || nextMove
    			== Constants.P1_RIGHT || nextMove == Constants.P1_UP
    			|| nextMove == Constants.P1_DOWN) {
    			return this.playerMove(1, nextMove);
    		} else {
    			return this.playerMove(2, nextMove);
    		}
    	}
    	return false;
    }
    
    /** Returns true iff no Dirt or DustBall exists on the Grid, including ones under
     *  Vacuums, false otherwise.
     * 
     * @return true iff no Dirt or DustBall exists on the Grid, including ones under
     *  Vacuums, false otherwise.
     */
    public boolean gameOver() {
    	return dirts.size() == 0;
    }
    
    /** Returns 1 if vacuum1 has a greater score than vacuum2, 2 otherwise.
     * 
     * @return 1 if vacuum1 has a greater score than vacuum2, 2 otherwise.
     */
    public int getWinner() {
    	if (this.vacuum1.getScore() > this.vacuum2.getScore()) {
    		return 1;
    	} else{
    		return 2;
    	}
    }

    /**
     * Returns the dimensions of the grid in the file named layoutFileName.
     * @param layoutFileName path of the input grid file
     * @return an array [numRows, numCols], where numRows is the number
     * of rows and numCols is the number of columns in the grid that
     * corresponds to the given input grid file
     * @throws IOException
     */
    private int[] getDimensions(String layoutFileName) throws IOException {

        Scanner sc = new Scanner(new File(layoutFileName));

        // find the number of columns
        String nextLine = sc.nextLine();
        int numCols = nextLine.length();

        int numRows = 1;

        // find the number of rows
        while (sc.hasNext()) {
            numRows++;
            nextLine = sc.nextLine();
        }

        sc.close();
        return new int[]{numRows, numCols};
    }
    
    private Sprite makeSprite(int row, int column, char symbol) {
    	Sprite newSprite = null;
    	if (symbol == Constants.CLEAN) {
    		newSprite =  new CleanHallway(symbol, row, column);
    	} else if (symbol == Constants.WALL) {
    		newSprite = new Wall(symbol, row, column);
    	} else if (symbol == Constants.DIRT) {
    		newSprite = new Dirt(symbol, row, column, Constants.DIRT_SCORE);
    	} else if (symbol == Constants.DUMPSTER) {
    		newSprite = new Dumpster(symbol, row, column);
    	} else if (symbol == Constants.DUST_BALL) {
    		newSprite = new DustBall(symbol, row, column,
    		Constants.DUST_BALL_SCORE);
    	} else if (symbol == Constants.P1) {
    		newSprite = new Vacuum(symbol, row, column, Constants.CAPACITY);
    	} else if (symbol == Constants.P2) {
    		newSprite = new Vacuum(symbol, row, column, Constants.CAPACITY);
    	}
    	return newSprite;
    }
    
    private boolean playerMove(int player, char move) {
    	Vacuum vacuum;
    	if (player == 1) {
    		vacuum = this.vacuum1;
    	} else {
    		vacuum = this.vacuum2;
    	}
    	int row = vacuum.getRow();
		int column = vacuum.getColumn();
		int newRow = vacuum.getRow() + moveDirection(move)[0];
		int newColumn = vacuum.getColumn() + moveDirection(move)[1];
		char symbol = this.grid.getCell(newRow, newColumn).getSymbol();
		if (symbol == Constants.CLEAN || symbol == Constants.DUMPSTER
			|| symbol == Constants.DIRT || symbol
			== Constants.DUST_BALL) {
			if (vacuum.getUnder().getSymbol()
				== Constants.DUST_BALL) {
    			dirts.add((DustBall)vacuum.getUnder());
    		}
			this.grid.setCell(row, column, vacuum.getUnder());
			vacuum.moveTo(newRow, newColumn);
			vacuum.setUnder(this.grid.getCell(newRow, newColumn));
			this.grid.setCell(newRow, newColumn, vacuum);
			if (symbol == Constants.DUMPSTER) {
				vacuum.empty();
			} else if (symbol == Constants.DIRT) {
				if (vacuum.clean(Constants.DIRT_SCORE)) {
					this.cleanDirt(newRow, newColumn);
				}
			} else if (symbol == Constants.DUST_BALL) {
				if (vacuum.clean(Constants.DUST_BALL_SCORE)) {
					this.cleanDirt(newRow, newColumn);
				} else {
					vacuum.setUnder(makeSprite(newRow, newColumn, Constants.CLEAN));
				}
			}
			this.dustMove();
			return true;
		}
		this.dustMove();
		return false;
    }
    
    private int[] moveDirection(char move) {
    	int [] arrayMove = {0, 0};
    	if (move == Constants.P1_LEFT || move == Constants.P2_LEFT) {
    		arrayMove[1] = -1;
    	} else if (move == Constants.P1_DOWN || move == Constants.P2_DOWN) {
    		arrayMove[0] = 1;
    	} else if (move == Constants.P1_RIGHT || move == Constants.P2_RIGHT) {
    		arrayMove[1] = 1;
    	} else if (move == Constants.P1_UP || move == Constants.P2_UP) {
    		arrayMove[0] = -1;
    	}
    	return arrayMove;
    }
    
    private void cleanDirt(int row, int column) {
    	for (int i = 0; i < this.dirts.size(); i++) {
    		Dirt currentDirt = this.dirts.get(i);
    		if (currentDirt.getRow() == row && currentDirt.getColumn()
    			== column) {
    			this.dirts.remove(i);
    		}
    	}
    }
    
    private void dustMove() {
    	for (int i = 0; i < this.dirts.size(); i++) {
    		if (this.dirts.get(i) instanceof DustBall) {
    			int[] move = possibleDustMoves(this.dirts.get(i)).get
    						(random.nextInt(possibleDustMoves
    						(this.dirts.get(i)).size()));
    			int row = dirts.get(i).getRow();
    			int column = dirts.get(i).getColumn();
    			if (!(this.grid.getCell(row, column) instanceof Vacuum)) {
    				this.grid.setCell(row, column, makeSprite(row, column,
    				Constants.DIRT));
    			} else {
    				int player = this.grid.getCell(row, column).getSymbol();
    				if (player == '1') {
    					this.vacuum1.setUnder(makeSprite(row, column,
    					Constants.DIRT));
    				} else {
    					this.vacuum2.setUnder(makeSprite(row, column,
    					Constants.DIRT));
    				}
    			}
    			this.cleanDirt(row, column);
    			this.dirts.add((Dirt) makeSprite(row, column, Constants.DIRT));
    			this.cleanDirt(move[0], move[1]);
    			this.dirts.add(0, (Dirt) makeSprite(move[0], move[1],
    					Constants.DUST_BALL));
    			this.grid.setCell(move[0], move[1], 
    					(Sprite) makeSprite(move[0], move[1],
    					Constants.DUST_BALL));
    		}
    	}
    }
    
    private List<int[]> possibleDustMoves(Dirt dustBall) {
    	List<int[]> lst = new ArrayList<int[]>();
    	int row = dustBall.getRow();
    	int column = dustBall.getColumn();
    	if (dustCanGoOn(this.grid.getCell(row + 1, column))) {
    		int[] move1 = {row + 1, column};
    		lst.add(move1);
    	}
    	if (dustCanGoOn(this.grid.getCell(row - 1, column))) {
    		int[] move2 = {row - 1, column};
    		lst.add(move2);
    	}
    	if (dustCanGoOn(this.grid.getCell(row, column + 1))) {
    		int[] move3 = {row, column + 1};
    		lst.add(move3);
    	}
    	if (dustCanGoOn(this.grid.getCell(row, column - 1))) {
    		int[] move4 = {row, column - 1};
    		lst.add(move4);
    	}
    	if (lst.isEmpty()) {
    		int[] noMove = {row, column};
    		lst.add(noMove);
    	}
    	return lst;
    }
    
    private boolean dustCanGoOn(Sprite sprite) {
    	if (sprite.getSymbol() == Constants.CLEAN
    		|| sprite.getSymbol() == Constants.DIRT
    		|| sprite.getSymbol() == Constants.DUST_BALL) {
    		return true;
    	}
    	return false;
    }
    
    private boolean validMove(char move) {
    	return move == Constants.P1_UP || move == Constants.P1_DOWN
    	|| move == Constants.P1_LEFT || move == Constants.P1_RIGHT
    	|| move == Constants.P2_UP || move == Constants.P2_DOWN
    	|| move == Constants.P2_LEFT || move == Constants.P2_RIGHT;
    }
    
    private void addToCellAndList(int row, int column, String line) {
    	this.grid.setCell(row, column,
        makeSprite(row, column, line.charAt(column)));
        if (line.charAt(column) == Constants.P1) {
        	this.vacuum1 = (Vacuum) makeSprite(row, column,
            line.charAt(column));
        } else if (line.charAt(column) == Constants.P2) {
            this.vacuum2 = (Vacuum) makeSprite(row, column,
            line.charAt(column));
        } else if (line.charAt(column) == Constants.DIRT) {
        	this.dirts.add((Dirt) makeSprite(row, column,
            line.charAt(column)));
        } else if (line.charAt(column) == Constants.DUST_BALL) {
            this.dirts.add((DustBall) makeSprite(row, column,
            line.charAt(column)));
        } else if (line.charAt(column) == Constants.DUMPSTER) {
            this.dumpsters.add((Dumpster) makeSprite(row, column,
            line.charAt(column)));
        }    	
    }
}
