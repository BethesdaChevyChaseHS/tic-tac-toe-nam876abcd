package bcc.tictactoe;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainMenu extends ScreenAdapter {
    private final TicTacToe game;
    private Stage stage;
    private Skin skin;

    public MainMenu(TicTacToe game) {
        this.game = game;
        System.out.println("created!!");
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("skins/glassy/glassy-ui.json"));
        // Load the skin

        //load image
        Texture backgroundTexture = new Texture(Gdx.files.internal("space_tictactoe.png"));//note that this is stored in assets directory
        TextureRegionDrawable backgroundDrawable =
                new TextureRegionDrawable(new TextureRegion(backgroundTexture));

        //title
        Container<Label> titleLabel = Constants.createLabelWithBackgrounColor("Nam's Tic Tac Toe",Color.BLACK,  skin);
        // Buttons with the skin
        TextButton simulateButton = new TextButton("Simulate Many Games", skin);
        TextButton playButton = new TextButton("Play a Game", skin);
        
        //resize text on simulate button
        simulateButton.getLabel().setFontScale(.6f);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setSimulated(false);
                game.startPlayerSelection();
                //game.setScreen(new GameScreen(game));
            }
        });

        simulateButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               game.setSimulated(true);
                game.startPlayerSelection();
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(titleLabel).pad(10).row();
        table.add(playButton).pad(10).row();
        table.add(simulateButton).pad(10).row();
        table.setBackground(backgroundDrawable);

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
