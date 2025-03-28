package bcc.tictactoe;

public abstract class Player {
    private int numWins = 0;
    private int numLosses = 0;
    private int numTies = 0;
    public abstract Move makeMove(Board board, Mark mark);

    public void incrementWins() {
        numWins++;
    }
    public void incrementLosses() {
        numLosses++;
    }
    public void incrementTies() {
        numTies++;
    }
    public int getNumWins() {
        return numWins;
    }
    public int getNumLosses() {
        return numLosses;
    }
    public int getNumTies() {
        return numTies;
    }
    public String getRecord() {
        //print w/l/d record
        return getNumWins() + "W / " + getNumLosses() + "L / " + getNumTies() + "D";
    }
}
