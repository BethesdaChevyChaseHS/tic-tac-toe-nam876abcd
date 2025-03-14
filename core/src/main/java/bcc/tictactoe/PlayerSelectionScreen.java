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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PlayerSelectionScreen extends ScreenAdapter{
    private final TicTacToe game;
    private Stage stage;
    private Skin skin;
    private String titleText;
    private int curPlayer;

    public PlayerSelectionScreen(TicTacToe game, int curPlayer, String titleText) {
        this.game = game;
        this.curPlayer = curPlayer;
        this.titleText = titleText;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
    
        skin = new Skin(Gdx.files.internal("skins/glassy/glassy-ui.json"));
    
        Container<Label> titleLabel = Constants.createLabelWithBackgrounColor(titleText, Color.BLACK, skin);
    
        TextButton humanButton = new TextButton("Human", skin);
        TextButton randomAIButton = new TextButton("Random AI", skin);
        TextButton slightlySmartAIButton = new TextButton("Slightly Smart AI", skin);
    
        humanButton.getLabel().setFontScale(0.6f);
        randomAIButton.getLabel().setFontScale(0.6f);
        slightlySmartAIButton.getLabel().setFontScale(0.6f);
    
        humanButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setPlayer(curPlayer, "Human");
                if (curPlayer == 0) {
                    game.setScreen(new PlayerSelectionScreen(game, 1, "Select Player 2"));
                } else {
                    game.setScreen(new GameDisplay(game));
                    
                }
            }
        });
    
        randomAIButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setPlayer(curPlayer, "RandomAI");
                if (curPlayer == 0) {
                    game.setScreen(new PlayerSelectionScreen(game, 1, "Select Player 2"));
                } else {
                    game.setScreen(new GameDisplay(game));  // Both players selected, go to the game screen
                }
            }
        });
    
        slightlySmartAIButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setPlayer(curPlayer, "SlightlySmartAI");
                if (curPlayer == 0) {
                    game.setScreen(new PlayerSelectionScreen(game, 1, "Select Player 2"));
                } else {
                    game.setScreen(new GameDisplay(game));  // Both players selected, go to the game screen
                }
            }
        });
    
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(titleLabel).pad(10).row();
        table.add(humanButton).pad(10).row();
        table.add(randomAIButton).pad(10).row();
        table.add(slightlySmartAIButton).pad(10).row();
    
        stage.addActor(table);
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
