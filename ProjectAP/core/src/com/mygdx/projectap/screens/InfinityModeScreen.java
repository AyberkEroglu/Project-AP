package com.mygdx.projectap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.projectap.ProjectAP;
import com.mygdx.projectap.bodies.entities.Bullet;
import com.mygdx.projectap.bodies.entities.EndOfMap;
import com.mygdx.projectap.bodies.entities.Enemy;
import com.mygdx.projectap.bodies.entities.Player;
import com.mygdx.projectap.bodies.helper.TileMapHelper;

import java.util.ArrayList;

import static com.mygdx.projectap.bodies.entities.Bullet.bullets;
import static com.mygdx.projectap.bodies.helper.Constants.PPM;

public class InfinityModeScreen extends GameScreen implements ContactListener {
    private float timeCount;
    //    private Label timeLabel;
//    private Table timeTable;
    private float worldTimer;
    //    private Label countdownLabel;
    private BitmapFont font;
//

    public InfinityModeScreen(OrthographicCamera camera, ProjectAP game, int levelNum) {
        super(camera,game,levelNum);
        //this.countdownLabel = new Label("" + worldTimer, new Label.LabelStyle(new BitmapFont(), Color.LIGHT_GRAY));
        //this.timeLabel = new Label("Time", new Label.LabelStyle(new BitmapFont(), Color.LIGHT_GRAY));
        //this.timeTable = new Table();
        //timeTable.add(timeLabel).expandX().padTop(10);
        //timeTable.row();
        //timeTable.add(countdownLabel).expandX().padTop(10);

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
        if (bullets != null) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).render(batch);
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new LevelMenuScreen(game));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            game.timeScale = ProjectAP.SLOWED_TIME_SCALE;
            adjustVelocities(ProjectAP.SLOWED_TIME_SCALE);
        } else{
            game.timeScale = ProjectAP.FAST_TIME_SCALE;
            adjustVelocities(ProjectAP.FAST_TIME_SCALE);
        }

        if (worldTimer <= 0) {
            worldTimer = 15;
            game.setScreen(new TimeRaceScreen(camera, game, levelNum, worldTimer));
        }
        batch.end();
        // box2DDebugRenderer.render(world, camera.combined.scl(PPM));
    }

    private void adjustVelocities(float timeScale) {

        //player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x * timeScale, player.getBody().getLinearVelocity().y);
        for (Enemy enemy : enemies) {

            if (game.timeScale != 1)
                enemy.getBody().setLinearVelocity(enemy.getBody().getLinearVelocity().x * timeScale, 0);
        }
        if (bullets != null){
            for (Bullet bl :
                    bullets) {
                if (timeScale == ProjectAP.SLOWED_TIME_SCALE)
                    bl.getBody().setLinearVelocity((float) (-1 * Math.cos(bl.angle) * ProjectAP.BULLET_SPEED * timeScale), (float) (-1 * Math.sin(bl.angle) * ProjectAP.BULLET_SPEED * timeScale));
                else bl.getBody().setLinearVelocity((float) (-1 * Math.cos(bl.angle) * ProjectAP.BULLET_SPEED), (float) (-1 * Math.sin(bl.angle) * ProjectAP.BULLET_SPEED));
            }
        }
        world.setGravity(new Vector2(0, ProjectAP.GRAVITY * timeScale));
    }

    @Override
    public void beginContact(Contact contact) {
        Object[] objectsA = (Object[]) contact.getFixtureA().getUserData();
        Object[] objectsB = (Object[]) contact.getFixtureB().getUserData();

        if (objectsA != null && objectsB != null) {

            if (isContact("Player", "EnemySensor", objectsA[1], objectsB[1])) {
                if (objectsA[1].equals("Player")) {
                    ((Enemy) objectsB[0]).enter();
                } else {
                    ((Enemy) objectsA[0]).enter();
                }
            }
            if (objectsB[1].equals("Bullet") || objectsA[1].equals("Bullet")) {
                Bullet bullet;
                if (objectsB[1].equals("Bullet")) {
                    bullet = (Bullet) objectsB[0];
                } else {
                    bullet = (Bullet) objectsA[0];
                }

                if (isContact("Enemy", "Bullet", objectsA[1], objectsB[1]) && bullet.fromEnemy) {

                } else if (isContact("EnemySensor", "Bullet", objectsA[1], objectsB[1])) {

                } else if (isContact("Player", "Bullet", objectsA[1], objectsB[1]) && !bullet.fromEnemy) {

                } else {
                    bullet.kill = true;
                }
            }
            if (isContact("Player", "Bullet", objectsA[1], objectsB[1])) {
                Bullet bullet;
                //Player player;
                if (objectsA[1].equals("Player")) {
                    //player = (Player) objectsA[0];
                    bullet = (Bullet) objectsB[0];
                }
                else {
                    //player = (Player) objectsB[0];
                    bullet = (Bullet) objectsA[0];
                }
                if (bullet.fromEnemy) {
                    game.setScreen(new InfinityModeScreen(camera, game, levelNum));
                }
            }
            if (isContact("Enemy", "Bullet", objectsA[1], objectsB[1])) {
                Bullet bullet;
                Enemy enemy;
                if (objectsA[1].equals("Enemy")) {
                    enemy = (Enemy) objectsA[0];
                    bullet = (Bullet) objectsB[0];
                }
                else {
                    enemy = (Enemy) objectsB[0];
                    bullet = (Bullet) objectsA[0];
                }
                if (!bullet.fromEnemy) {
                    enemy.kill = true;
                    enemies.remove(enemy);
                }
            }
            if (isContact("Player", "EndOfMap", objectsA[1], objectsB[1])) {
                EndOfMap endOfMap;
                Player player;
                if (objectsA[1].equals("Player")) {
                    //player = (Player) objectsA[0];
                    //endOfMap = (endOfMap) objectsB[0];
                }
                else {
                    //player = (Player) objectsB[0];
                    //endOfMap = (endOfMap) objectsA[0];
                }
                game.setScreen(new LevelMenuScreen(game));
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Object[] objectsA = (Object[]) contact.getFixtureA().getUserData();
        Object[] objectsB = (Object[]) contact.getFixtureB().getUserData();

        if (objectsA != null && objectsB != null) {

            if (isContact("Player", "EnemySensor", objectsA[1], objectsB[1])) {
                if (objectsA[1].equals("Player")) {
                    ((Enemy) objectsB[0]).exit();
                } else {
                    ((Enemy) objectsA[0]).exit();
                }
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private boolean isContact(String id1, String id2, Object a, Object b) {
        if (a == null || b == null) return false;
        return ((a.equals(id1) && b.equals(id2)) ||
                (a.equals(id2) && b.equals(id1)));
    }

    public Player getPlayer() {
        return player;
    }
}
