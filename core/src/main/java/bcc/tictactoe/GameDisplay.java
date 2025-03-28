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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class GameDisplay extends ScreenAdapter {
    private final TicTacToe game;
    private Stage stage;
    private Skin skin;

    private final float BOARD_X = 90;
    private final float BOARD_Y = 70;
    private final float BOARD_WIDTH = 300;
    private final float BOARD_HEIGHT = 300;
    private Table boardTable;

    private boolean gameOver = false;
    private Label resultLabel;
    private TextButton playAgainButton;

    private Label recordLabelX;
    private Label recordLabelO;
    private Label turnLabel;

    public GameDisplay(TicTacToe game) {
        this.game = game;
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        this.skin = new Skin(Gdx.files.internal("skins/glassy/glassy-ui.json"));

        initTableDisplay();
        updateBoardDisplay();

        recordLabelX = new Label("X Record: " + game.getPlayer1().getRecord(), skin);
        recordLabelX.setFontScale(2f);
        recordLabelX.setColor(Color.BLUE); 
        recordLabelX.setStyle(new Label.LabelStyle(recordLabelX.getStyle().font, Color.BLUE));
        recordLabelX.setPosition(20, Gdx.graphics.getHeight() - 50);
        
        recordLabelO = new Label("O Record: " + game.getPlayer2().getRecord(), skin);
        recordLabelO.setFontScale(2f); 
        recordLabelO.setColor(Color.WHITE); 
        recordLabelO.setStyle(new Label.LabelStyle(recordLabelO.getStyle().font, Color.RED));
        recordLabelO.setPosition(20, Gdx.graphics.getHeight() - 80);
        
        turnLabel = new Label("Turn: " + game.getCurPlayerMark(), skin);
        turnLabel.setFontScale(2f); 
        turnLabel.setColor(Color.WHITE); 
        turnLabel.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() - 100, Align.center);
        
        stage.addActor(recordLabelX);
        stage.addActor(recordLabelO);
        stage.addActor(turnLabel);
    }

    public void initTableDisplay() {
        boardTable = new Table();
        boardTable.setPosition(BOARD_X, BOARD_Y);
        boardTable.setSize(BOARD_WIDTH, BOARD_HEIGHT);

        Texture backgroundTexture = new Texture(Gdx.files.internal("tictactoe_board.png"));
        TextureRegionDrawable backgroundDrawable = new TextureRegionDrawable(new TextureRegion(backgroundTexture));
        boardTable.setBackground(backgroundDrawable);

        boardTable.top();

        boardTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                float cellWidth = BOARD_WIDTH / 3;
                float cellHeight = BOARD_HEIGHT / 3;
                int col = (int) (clickX / cellWidth);
                int rowFromBottom = (int) (clickY / cellHeight);
                int row = 2 - rowFromBottom;

                handleBoardClick(row, col);
            }
        });
        stage.addActor(boardTable);
    }

    public void handleBoardClick(int row, int col) {
        if (game.getBoardState().isCellEmpty(row, col) && !gameOver) {
            game.getBoardState().makeMove(row, col, game.getCurPlayerMark());
            handleMoveMade();
            game.nextPlayer();
            updateTurnLabel();
        }
    }

    public void handleMoveMade() {
        updateBoardDisplay();

        Mark winner = game.getBoardState().checkWin();
        if (winner != null) {
            if (winner == Mark.TIE) {
                showResult("It's a tie!");
            } else {
                showResult(winner + " wins!");
            }
            gameOver = true;
        } else {
            turnLabel.setText("Turn: " + game.getCurPlayerMark());
        }
    }

    private void updateTurnLabel() {
        turnLabel.setText("Turn: " + game.getCurPlayerMark());
    }

    private void showResult(String result) {
        resultLabel = new Label(result, skin);
        resultLabel.setFontScale(2f);
        resultLabel.setColor(Color.WHITE);
        resultLabel.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, Align.center);
    
        playAgainButton = new TextButton("Play Again", skin);
        playAgainButton.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - 50, Align.center);
        playAgainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resetGame();
                resultLabel.remove();
                playAgainButton.remove();
            }
        });
    
        stage.addActor(resultLabel);
        stage.addActor(playAgainButton);
    
        if (result.contains("X wins")) {
            game.getPlayer1().incrementWins();
            game.getPlayer2().incrementLosses();
        } else if (result.contains("O wins")) {
            game.getPlayer2().incrementWins();
            game.getPlayer1().incrementLosses();
        } else if (result.contains("tie")) {
            game.getPlayer1().incrementTies();
            game.getPlayer2().incrementTies();
        }
    
        updateRecordLabels();
    }

    private void updateRecordLabels() {
        recordLabelX.setText("X Record: " + game.getPlayer1().getRecord());
        recordLabelO.setText("O Record: " + game.getPlayer2().getRecord());
    }

    public void resetGame() {
        game.getBoardState().reset();
        game.setCurPlayer(Mark.X);
        updateBoardDisplay();
        updateTurnLabel();
        gameOver = false;
    }

    public void updateBoardDisplay() {
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
                cellLabel.setAlignment(Align.center);
                cellLabel.setFontScale(5f);
                boardTable.add(cellLabel).width(BOARD_WIDTH / 3).height(BOARD_HEIGHT / 3);
            }
            boardTable.row();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

        if (!(game.getCurPlayerObj() instanceof Human)) {
            Move m = game.getCurPlayerObj().makeMove(game.getBoardState(), game.getCurPlayerMark());
            game.getBoardState().makeMove(m, game.getCurPlayerMark());
            handleMoveMade();
            game.nextPlayer();
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}