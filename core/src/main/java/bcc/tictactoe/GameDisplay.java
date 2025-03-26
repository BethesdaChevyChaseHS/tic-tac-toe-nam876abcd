package bcc.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameDisplay extends ScreenAdapter {
    private final TicTacToe game;
    private Stage stage;
    private Skin skin;

    //up to you to modify these if you'd like!
    private final float BOARD_X = 90;
    private final float BOARD_Y = 70;
    private final float BOARD_WIDTH = 300;
    private final float BOARD_HEIGHT = 300;
    Table boardTable;


    private boolean gameOver = false;
    private Label resultLabel;
    private TextButton playAgainButton;
    private Label turnLabel;
    private Label player1RecordLabel;
    private Label player2RecordLabel;
    private Board board;


    public GameDisplay(TicTacToe game) {
        this.game = game;
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("skins/glassy/glassy-ui.json"));
        Gdx.input.setInputProcessor(stage);
        board = new Board();
        game.setBoardState(board);

        turnLabel = new Label("Current player: " + game.getCurPlayerMark(), skin);
        player1RecordLabel = new Label("Player 1 Record: 0W - 0L - 0T", skin);
        player2RecordLabel = new Label("Player 2 Record: 0W - 0L - 0T", skin);
    
        // Proceed to initialize the game elements like the board
        initTableDisplay();  // This will display the Tic-Tac-Toe grid
        updateBoardDisplay(); // Update the grid with the initial state
        //set up the screen you you like
    }


    public void initTableDisplay() {// initializes tic tac toe board - no changes needed 
        boardTable = new Table();
        boardTable.setPosition(BOARD_X, BOARD_Y);
        boardTable.setSize(BOARD_WIDTH, BOARD_HEIGHT);

        // Set the background image.
        Texture backgroundTexture = new Texture(Gdx.files.internal("tictactoe_board.png"));
        TextureRegionDrawable backgroundDrawable = new TextureRegionDrawable(new TextureRegion(backgroundTexture));
        boardTable.setBackground(backgroundDrawable);

       
        // Force the table to layout from the top (so that row 0 appears at the top).
        boardTable.top();

        boardTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                // The click coordinates (clickX, clickY) are relative to the table's
                // bottom-left.
                float cellWidth = BOARD_WIDTH / 3;
                float cellHeight = BOARD_HEIGHT / 3;

                int col = (int) (clickX / cellWidth);
                int rowFromBottom = (int) (clickY / cellHeight);
                // Convert the y-coordinate to a row index where row 0 is at the top.
                int row = 2 - rowFromBottom;

                // Call your board click handler.
                handleBoardClick(row, col);
            }
        });
        stage.addActor(boardTable);
    }

    
    public void handleBoardClick(int row, int col) {
        //checkpoint 2
        //this position was clicked, play the move, then call handle move made
        if (!gameOver) {
            // Get current player's mark
            Mark currentMark = game.getCurPlayerMark();

            // Make move
            boolean moveMade = game.getBoardState().makeMove(row, col, currentMark);

            if (moveMade) {
                handleMoveMade();
            }
            updateBoardDisplay();
        }
    }

    public void handleMoveMade(){//checkpoint 2
        //call updateBoardDisplay
        //check for a win or tie. If there is one, call showResult() with a message containing the winner, and update the player stats. 
        Mark winner = game.getBoardState().checkWin();
        Mark currentPlayerMark = game.getCurPlayerMark();
        if (board.checkWin() == Mark.X) {
            showResult("Player " + currentPlayerMark + " Wins!");
        } 
        else if (board.isFull()) {
            showResult("It's a Tie!");
        } else {
            game.nextPlayer(); // Switch to the next player
            updateTurnLabel(); // Update whose turn it is
    
        }

        //checkpoint 3 modification
        //if game is simulated, instead of having a popup by calling showresult, start the next game if we have not run all the simulations
        
    }

    private void updateTurnLabel() {
        // Update whose turn it is
        if (game.getCurPlayer() == 0) {
            turnLabel.setText("Player 1's Turn");
        } else {
            turnLabel.setText("Player 2's Turn");
        }
    }    

    private void showResult(String result) {
        // Create an overlay to show the result. Include a button to play again. 

        // when the button is clicked, it should dissappear - you can do this using the .remove() command. 
            // Create a Label or a dialog to display the result, with the message passed in as a parameter.
    resultLabel = new Label(result, skin);
    resultLabel.setFontScale(1.5f);  // Make the text larger for visibility
    resultLabel.setColor(Color.RED);  // Change the color to white (or any other color)
    resultLabel.setPosition(BOARD_X + BOARD_WIDTH / 3 - resultLabel.getWidth() / 2, BOARD_Y + BOARD_HEIGHT + 20);  // Center the label below the board

    // Add resultLabel to the stage
    stage.addActor(resultLabel);

    // Show the "Play Again" button after the result is displayed
    playAgainButton = new TextButton("Play Again", skin);
    playAgainButton.setPosition(BOARD_X + BOARD_WIDTH / 2 - playAgainButton.getWidth() / 2, BOARD_Y + 80);  // Center the button below the result label
    playAgainButton.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            // Reset the game and remove the result and button
            resetGame();
            resultLabel.remove();  // Remove the result label
            playAgainButton.remove();  // Remove the play again button
        }
    });

    // Add playAgainButton to the stage
    stage.addActor(playAgainButton);
    }

    public void resetGame() {
        //update board state, current player, etc. 
        game.getBoardState().reset();
        game.resetCurPlayer();
        gameOver = false;
        updateBoardDisplay();
        stage.clear(); // Remove the result and play-again button
    }

    public void updateBoardDisplay() {//updates the board, you should call this if a move is made. No need to change. 
        boardTable.clearChildren();
        Mark[][] grid = game.getBoardState().getGrid();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Mark mark = grid[row][col];
                String text = "";
                if (mark == Mark.X) {
                    text = "X";
                } else if (mark == Mark.O) {
                    text = "O";
                }
                Label cellLabel = new Label(text, skin);
                cellLabel.setAlignment(Align.center);     // Center text within the label.
                cellLabel.setFontScale(5f); 
                boardTable.add(cellLabel).width(BOARD_WIDTH / 3).height(BOARD_HEIGHT / 3);
                player1RecordLabel.setText("Player 1 Record: " + game.getPlayer1().getRecord());
                player2RecordLabel.setText("Player 2 Record: " + game.getPlayer2().getRecord());
            }
            boardTable.row();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

        //checkpoint 3 - if it is not a humans turn, automate the AI's move here
        //call handleMoveMade afterwards
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
