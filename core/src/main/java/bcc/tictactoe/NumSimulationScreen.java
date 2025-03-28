package bcc.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class NumSimulationScreen extends ScreenAdapter {
    private final TicTacToe game;
    private Stage stage;
    private Skin skin;
    private Texture backgroundTexture;
    private Image backgroundImage;

    public NumSimulationScreen(TicTacToe game) {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skins/glassy/glassy-ui.json"));
        
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        
        Label titleLabel = new Label("Simulation Setup", skin, "title");
        table.add(titleLabel).padBottom(30).row();

        TextField roundsInput = new TextField("", skin);
        roundsInput.setMessageText("Enter number of rounds");
        table.add(roundsInput).width(250).padBottom(20).row();

        TextButton continueButton = new TextButton("Continue", skin);
        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    int numRounds = Integer.parseInt(roundsInput.getText());
                    if (numRounds > 0) {
                        game.setNumberOfRounds(numRounds);
                        game.setScreen(new GameDisplay(game)); // Start simulation
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number of rounds");
                }
            }
        });
        table.add(continueButton).width(200).height(50).padTop(10);
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
        backgroundTexture.dispose();
    }
}