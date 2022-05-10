package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.projectap.ProjectAP;

public class MainMenuScreen implements Screen {

    ProjectAP game;

    Texture mainMenu;
    Texture playButton;
    Texture exitButton;
    Texture optionsButton;

    public MainMenuScreen(ProjectAP game) {
        this.game = game;

        mainMenu = new Texture("Main Menu.drawio.png");
        playButton = new Texture("Main Menu Play Button.drawio.png");
        optionsButton = new Texture("Main Menu Options Button.drawio.png");
        exitButton = new Texture("Main Menu Exit Button.drawio.png");

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(mainMenu, 0, 0, 1600, 900);
        game.batch.draw(playButton, 675, 580, 250, 100);
        if (Gdx.input.getY() >= ProjectAP.HEIGHT - 680 && Gdx.input.getY() <= ProjectAP.HEIGHT - 580 && Gdx.input.getX() >= 675 && Gdx.input.getX() <= 925) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new LevelMenuScreen(game));
            }
        }
        game.batch.draw(optionsButton, 595, 420, 400, 100);
        if (Gdx.input.getY() >= ProjectAP.HEIGHT - 520 && Gdx.input.getY() <= ProjectAP.HEIGHT - 420 && Gdx.input.getX() >= 595 && Gdx.input.getX() <= 995) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new OptionsMenuScreen(game));
            }
        }
        game.batch.draw(exitButton, 700, 260, 200, 100);
        if (Gdx.input.getY() >= ProjectAP.HEIGHT - 360 && Gdx.input.getY() <= ProjectAP.HEIGHT - 260 && Gdx.input.getX() >= 700 && Gdx.input.getX() <= 900) {
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        }


        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
