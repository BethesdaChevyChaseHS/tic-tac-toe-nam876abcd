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

    public PlayerSelectionScreen(TicTacToe game, int curPlayer) {
        this.game = game;
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        // Load the UI skin
        this.skin = new Skin(Gdx.files.internal("skins/glassy/glassy-ui.json"));

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
    
        String titleText = "Select Player " + (curPlayer + 1);
        Container<Label> titleLabel = Constants.createLabelWithBackgrounColor(titleText, Color.BLUE, skin);
        table.add(titleLabel).padBottom(20).row(); 
    
        for (String option : Constants.PLAYER_OPTIONS) {
            if (game.getIsSimulated() && option.equals("Human")) {
                continue; 
            }
    
            TextButton button = new TextButton(option, skin);
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setPlayer(curPlayer, option);
                }
            });
            table.add(button).pad(10).row();
        }
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