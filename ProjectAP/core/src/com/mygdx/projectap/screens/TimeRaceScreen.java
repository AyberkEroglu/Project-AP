package com.mygdx.projectap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.projectap.ProjectAP;
import com.mygdx.projectap.bodies.entities.Bullet;
import com.mygdx.projectap.bodies.entities.Enemy;
import com.mygdx.projectap.bodies.entities.Player;
import com.mygdx.projectap.bodies.helper.TileMapHelper;

import java.awt.*;
import java.util.ArrayList;

import static com.mygdx.projectap.bodies.helper.Constants.PPM;

public class TimeRaceScreen extends GameScreen implements ContactListener {

    private float timeCount;
//    private Label timeLabel;
//    private Table timeTable;
    private float worldTimer;
//    private Label countdownLabel;
    private BitmapFont font;
//

    public TimeRaceScreen(OrthographicCamera camera, ProjectAP game, int levelNum,float worldTimer) {
        super(camera,game,levelNum);
        timeCount = 0;
        this.worldTimer = worldTimer;
        //this.countdownLabel = new Label("" + worldTimer, new Label.LabelStyle(new BitmapFont(), Color.LIGHT_GRAY));
        //this.timeLabel = new Label("Time", new Label.LabelStyle(new BitmapFont(), Color.LIGHT_GRAY));
        //this.timeTable = new Table();
        //timeTable.add(timeLabel).expandX().padTop(10);
        //timeTable.row();
        //timeTable.add(countdownLabel).expandX().padTop(10);
        font = new BitmapFont();



    }

    public String timeUpdate(float dt) {

        timeCount += dt;
        if(timeCount >= 1) {
            worldTimer--;
            //this.countdownLabel.setText((int) worldTimer);
            timeCount = 0;
        }
        return "" + worldTimer;
    }

    @Override
    public void render(float delta) {
        this.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        orthogonalTiledMapRenderer.render();

        batch.begin();

        player.render(batch);
        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }

        font.draw(batch, timeUpdate(Gdx.graphics.getDeltaTime()), Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
        batch.end();
        box2DDebugRenderer.render(world, camera.combined.scl(PPM));
    }






}
