package bcc.tictactoe;

public class SmartAI extends Player {

    public Move makeMove(Board board, Mark mark){
            int bestScore = Integer.MIN_VALUE;
            Move bestMove = null;

            for(int row = 0; row < 3; row++){
                for(int col = 0; col < 3; col++){
                    if(board.getGrid()[row][col].equals(Mark.EMPTY)){
                        board.makeMove(row, col, mark);
                        int score = minimax(board, false, mark);
                        board.clearCell(row, col);
                        if(score > bestScore){
                            bestScore = score;
                            bestMove = new Move(row, col);
                        }
                    }
                }
            }
    return bestMove;
    }

    private int minimax(Board board, boolean aiTurn, Mark aiMark){

        Mark winner = board.checkWin();

        if(winner != null){
            if(winner.equals(aiMark)){
                return 1;
            } else if(winner.equals(Mark.TIE)){
                return 0;
            } else{
                return -1;
            }
        }

        if(aiTurn){  
            int bestScore = Integer.MIN_VALUE;
            for(int row = 0; row < 3; row++){
                for(int col = 0; col < 3; col++){
                    if(board.getGrid()[row][col].equals(Mark.EMPTY)){
                        board.makeMove(row, col, aiMark);
                        int score = minimax(board, false, aiMark);
                        board.clearCell(row, col);
                        if(score > bestScore){
                            bestScore = score;
                
                        }
                    }
                }
            }
    return bestScore;
        } 
        else {
            int lowestScore = Integer.MAX_VALUE;
            for(int row = 0; row < 3; row++){
                for(int col = 0; col < 3; col++){
                    if(board.getGrid()[row][col].equals(Mark.EMPTY)){
                     if(aiMark.equals(Mark.X)){
                        board.makeMove(row, col, Mark.O);
                     }
                     else{
                        board.makeMove(row, col, Mark.X);
                     }
                        int score = minimax(board, true, aiMark);
                        board.clearCell(row, col);
                        if(score < lowestScore){
                            lowestScore = score;
                        }
                    }
                }
            }
    return lowestScore;       
        }    
    }
   
    public String toString(){
        return "Smart AI";
    }
}