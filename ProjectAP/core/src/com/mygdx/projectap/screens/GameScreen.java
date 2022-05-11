package com.mygdx.projectap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.projectap.ProjectAP;
import com.mygdx.projectap.bodies.entities.Enemy;
import com.mygdx.projectap.bodies.helper.TileMapHelper;
import com.mygdx.projectap.bodies.entities.Player;
import com.mygdx.projectap.bodies.entities.Bullet;

import java.util.ArrayList;

import static com.mygdx.projectap.bodies.helper.Constants.PPM;

public class GameScreen extends ScreenAdapter implements ContactListener {

    protected OrthographicCamera camera;
    protected SpriteBatch batch;
    protected World world;
    protected Box2DDebugRenderer box2DDebugRenderer;
    protected OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    protected TileMapHelper tileMapHelper;
    protected Player player;
    protected Enemy enemy;
    protected ProjectAP game;
    protected int levelNum;

    public ArrayList<Enemy> enemies;

    public GameScreen(OrthographicCamera camera, ProjectAP game, int levelNum) {
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0, -9.81f), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();

        this.tileMapHelper = new TileMapHelper(this, levelNum);
        this.orthogonalTiledMapRenderer = tileMapHelper.setupMap();

        world.setContactListener(this);

        this.game = game;
        this.levelNum = levelNum;
    }

    void update() {
        world.step(1 / 60f, 6, 2);
        cameraUpdate();

        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenuScreen(game));
        }

        player.update();
        for (Enemy enemy : enemies) {
            enemy.update(Gdx.graphics.getDeltaTime());

        }
        if (Bullet.bullets != null) {
            for (int i = 0; i < Bullet.bullets.size(); i++) {
                Bullet.bullets.get(i).update(Gdx.graphics.getDeltaTime());
            }
        }
    }

    void cameraUpdate() {
        Vector3 position = camera.position;
        position.x = Math.round(player.getBody().getPosition().x * PPM * 10) / 10f;
        position.y = Math.round(player.getBody().getPosition().y * PPM * 10) / 10f;
        camera.position.set(position);
        camera.update();
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

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new LevelMenuScreen(game));
        }

        batch.end();
        box2DDebugRenderer.render(world, camera.combined.scl(PPM));
    }

    public World getWorld() {
        return world;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
                    game.setScreen(new GameScreen(camera, game, levelNum));
                }
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
