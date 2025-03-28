package bcc.tictactoe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class TicTacToe extends Game {
    private Player player1;
    private Player player2;
    private Board boardState;
    private int curPlayer = 0;
    private int numberOfRounds;
    private boolean isSimulated;
    private int round = 1;

    @Override
    public void create() {
        setScreen(new MainMenu(this));
    }

    @Override
    public void dispose() {}

    public void setPlayer(int curPlayer, String option) {
        if (option.equalsIgnoreCase("Human")) {
            if (curPlayer == 0) {
                player1 = new Human();
            } else {
                player2 = new Human();
            }
        } else if (option.equalsIgnoreCase("Random AI")) {
            if (curPlayer == 0) {
                player1 = new RandomAI();
            } else {
                player2 = new RandomAI();
            }
         } else if (option.equalsIgnoreCase("Slightly Smart AI")) {
                if (curPlayer == 0) {
                    player1 = new SlightlySmartAI();
                } else {
                    player2 = new SlightlySmartAI();
                }
            }
            else if(option.equalsIgnoreCase("Smart AI")){
                if (curPlayer == 0) {
                    player1 = new SmartAI();
                } else {
                    player2 = new SmartAI();
                }
            
        }
    
        if (curPlayer == 0) {
            setScreen(new PlayerSelectionScreen(this, 1));
        } else {
            boardState = new Board();
            setScreen(new GameDisplay(this));
        }
    }

    public void setSimulated(boolean isSimulated) {
        this.isSimulated = isSimulated;
    }

    public void startPlayerSelection() {
        setScreen(new PlayerSelectionScreen(this, 0));
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public boolean getIsSimulated() {
        return isSimulated;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Board getBoardState() {
        return boardState;
    }

    public void setBoardState(Board boardState) {
        this.boardState = boardState;
    }

    public int getCurPlayer() {
        return curPlayer;
    }

    public void setCurPlayer(Mark mark) {
        this.curPlayer = (mark == Mark.X) ? 0 : 1; 
    }

    public Player getCurPlayerObj(){

        if(curPlayer == 0){
            return player1;
        }
        else{
            return player2;
        }
    } 

    public Player getCurrentPlayer() {  
        return (curPlayer == 0) ? player1 : player2;
    }

    public Mark getCurPlayerMark() {
        return (curPlayer == 0) ? Mark.X : Mark.O;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void nextPlayer() {
        curPlayer = (curPlayer + 1) % 2;
    }

    public void resetCurPlayer() {
        curPlayer = 0;
    }

    public int getRound() {
        return round;
    }

    public void incrementRound() {
        round++;
    }
}
