
package bcc.tictactoe;
public class Board {
    private Mark[][] grid;
    public Board() {
        //initialize grid to be 3x3 
        grid = new Mark[3][3];
        reset(); // Make sure the board starts clean
    }

    public void reset() {
        //should restart the game - set all cells to empty
    for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = Mark.EMPTY;
            }
        }
    }

    public boolean makeMove(int row, int col, Mark mark) {
        //make a move on the grid
        if (grid[row][col] == Mark.EMPTY) {
            grid[row][col] = mark;
            return true;
        }
        return false;
    }

    public void clearCell(int row, int col) {
       //set the given grid cell to empty
       grid[row][col] = Mark.EMPTY;
    }
    public boolean isFull() {
       //check if grid is full(and thus game is a tie)
       for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (grid[i][j] == Mark.EMPTY) {
                return false;
            }
        }
    }
    return true;
    }

    public Mark[][] getGrid() {
        return grid;
    }

    /**
     * return 'Mark.X' if X wins, 'Mark.O' if O wins, 'Mark.Tie' if tie, or 'null' if still in progress
     */
    public Mark checkWin() {//return null if game not over
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] && grid[i][0] != Mark.EMPTY) {
                return grid[i][0]; // Return the winner mark (X or O)
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i] && grid[0][i] != Mark.EMPTY) {
                return grid[0][i]; // Return the winner mark (X or O)
            }
        }
        // Check diagonals
        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[0][0] != Mark.EMPTY) {
            return grid[0][0]; // Return the winner mark (X or O)
        }
        if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[0][2] != Mark.EMPTY) {
            return grid[0][2]; // Return the winner mark (X or O)
        }
        // Check tie
        if (isFull()) {
            return Mark.TIE; // Return a tie if the board is full and no winner
        }
        return Mark.EMPTY; // Return null if game is still in progress
    }

    public Board clone() {
        //return a copy of the grid
        Board clonedBoard = new Board();
        for (int i = 0; i < 3; i++) {
            System.arraycopy(this.grid[i], 0, clonedBoard.grid[i], 0, 3);
        }
        return clonedBoard;
    }
}
