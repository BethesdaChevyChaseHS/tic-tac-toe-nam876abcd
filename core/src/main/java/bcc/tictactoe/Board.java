
package bcc.tictactoe;

public class Board {
    private Mark[][] grid;

    public Board() {
        grid = new Mark[3][3]; 
        reset(); 
    }

    public void reset() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                grid[row][col] = Mark.EMPTY; // Set all cells to EMPTY
            }
        }
    }

    public boolean makeMove(Move move, Mark mark) {
        return makeMove(move.getRow(), move.getCol(), mark);
    }

    public boolean makeMove(int row, int col, Mark mark) {
        if (grid[row][col] == Mark.EMPTY) {  // Only place mark if cell is empty
            grid[row][col] = mark;
            return true;
        }
        return false; 
    }

    public void clearCell(int row, int col) {
        grid[row][col] = Mark.EMPTY;
    }

    public boolean isFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (grid[row][col] == Mark.EMPTY) {
                    return false;  // If any cell is empty, board is not full
                }
            }
        }
        return true;
    }

    public boolean isCellEmpty(int row, int col) {
        return grid[row][col] == Mark.EMPTY;  
    }

    public Mark[][] getGrid() {
        return grid;
    }

    public Mark checkWin() {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] != Mark.EMPTY && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
                return grid[i][0];  
            }
            if (grid[0][i] != Mark.EMPTY && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
                return grid[0][i];  
            }
        }

        if (grid[0][0] != Mark.EMPTY && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            return grid[0][0];
        }
        if (grid[0][2] != Mark.EMPTY && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
            return grid[0][2];
        }

        if (isFull()) {
            return Mark.TIE;
        }

        return null;  
    }

    public Board clone() {
        Board copy = new Board();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                copy.grid[row][col] = this.grid[row][col];
            }
        }
        return copy;
    }
}