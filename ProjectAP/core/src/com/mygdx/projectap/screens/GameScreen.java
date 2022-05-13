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
import com.mygdx.projectap.bodies.entities.EndOfMap;
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
    protected int levelNum;
    protected ProjectAP game;
    protected Player player;
    protected Enemy enemy;
    protected EndOfMap end;
    public ArrayList<Enemy> enemies;
    public ArrayList<Bullet> playerBullets;
    public ArrayList<Bullet> enemyBullets;

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

        if (enemyBullets == null) {
            enemyBullets = new ArrayList<>();
        }
        if (playerBullets == null) {
            playerBullets = new ArrayList<>();
        }
    }

    public void update() {
        world.step(1 / 60f, 6, 2);
        cameraUpdate();

        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);

        player.update();

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i) != null) {
                enemies.get(i).update(Gdx.graphics.getDeltaTime());
            }
        }

        if (enemyBullets != null) {
            for (int i = 0; i < enemyBullets.size(); i++) {
                enemyBullets.get(i).update(Gdx.graphics.getDeltaTime());
            }
        }

        if (playerBullets != null) {
            for (int i = 0; i < playerBullets.size(); i++) {
                playerBullets.get(i).update(Gdx.graphics.getDeltaTime());
            }
        }
    }

    public void cameraUpdate() {
        Vector3 position = camera.position;
        position.x = Math.round(player.getBody().getPosition().x * PPM * 10) / 10f;
        position.y = Math.round(player.getBody().getPosition().y * PPM * 10) / 10f;
        camera.position.set(position);
        camera.update();
    }

    @Override
    public void render(float delta) {
        this.update();

        if (enemyBullets != null) {
            for (Bullet bullet : enemyBullets) {
                if (bullet.getBody().getLinearVelocity().x == 0 && bullet.getBody().getLinearVelocity().y == 0)
                    bullet.kill = true;
            }
        }

        if (playerBullets != null) {
            for(Bullet bullet : playerBullets) {
                if(bullet.getBody().getLinearVelocity().x == 0 && bullet.getBody().getLinearVelocity().y == 0) {
                    bullet.kill = true;
                }
            }
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        orthogonalTiledMapRenderer.render();

        batch.begin();

        player.render(batch);

        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }

        if (enemyBullets != null) {
            for (int i = 0; i < enemyBullets.size(); i++) {
                enemyBullets.get(i).render(batch);
            }
        }

        if (playerBullets != null) {
            for (int i = 0; i < playerBullets.size(); i++) {
                playerBullets.get(i).render(batch);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new LevelMenuScreen(game));
            if (enemyBullets != null) {
                enemyBullets.clear();
            }
            if (playerBullets != null) {
                playerBullets.clear();
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            game.timeScale = ProjectAP.SLOWED_TIME_SCALE;
            adjustVelocities(ProjectAP.SLOWED_TIME_SCALE);
        } else {
            game.timeScale = ProjectAP.FAST_TIME_SCALE;
            adjustVelocities(ProjectAP.FAST_TIME_SCALE);
        }

        batch.end();
        //box2DDebugRenderer.render(world, camera.combined.scl(PPM));
    }

    protected void adjustVelocities(float timeScale) {
        //player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x * timeScale, player.getBody().getLinearVelocity().y);
        for (Enemy enemy : enemies) {
            if (game.timeScale != 1) {
                enemy.getBody().setLinearVelocity(enemy.getBody().getLinearVelocity().x * timeScale, 0);
            }
        }
        if (enemyBullets != null) {
            for (Bullet bl : enemyBullets) {
                if (timeScale == ProjectAP.SLOWED_TIME_SCALE)
                    bl.getBody().setLinearVelocity((float) (-1 * Math.cos(bl.angle) * ProjectAP.BULLET_SPEED * timeScale), (float) (-1 * Math.sin(bl.angle) * ProjectAP.BULLET_SPEED * timeScale));
                else
                    bl.getBody().setLinearVelocity((float) (-1 * Math.cos(bl.angle) * ProjectAP.BULLET_SPEED), (float) (-1 * Math.sin(bl.angle) * ProjectAP.BULLET_SPEED));
            }
        }
        if (playerBullets != null) {
            for (Bullet bl : playerBullets) {
                if (timeScale == ProjectAP.SLOWED_TIME_SCALE)
                    bl.getBody().setLinearVelocity((float) (-1 * Math.cos(bl.angle) * ProjectAP.BULLET_SPEED * timeScale), (float) (-1 * Math.sin(bl.angle) * ProjectAP.BULLET_SPEED * timeScale));
                else
                    bl.getBody().setLinearVelocity((float) (-1 * Math.cos(bl.angle) * ProjectAP.BULLET_SPEED), (float) (-1 * Math.sin(bl.angle) * ProjectAP.BULLET_SPEED));
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

            if (isContact("Bullet", "Bullet", objectsA[1], objectsB[1])) {
                Bullet bullet1 = (Bullet) objectsA[0];
                Bullet bullet2 = (Bullet) objectsB[0];
                if (bullet1.fromEnemy) {
                    bullet1.kill = true;
                } else if (bullet2.fromEnemy) {
                    bullet2.kill = true;
                }
            }

            if (isContact("Bullet", "Wall", objectsA[1], objectsB[1])) {
                Bullet bullet;
                if (objectsA[1].equals("Bullet")) {
                    bullet = (Bullet) objectsA[0];
                } else {
                    bullet = (Bullet) objectsB[0];
                }
                bullet.kill = true;
            }

            if (isContact("Player", "Bullet", objectsA[1], objectsB[1])) {
                Bullet bullet;
                if (objectsA[1].equals("Player")) {
                    bullet = (Bullet) objectsB[0];
                } else {
                    bullet = (Bullet) objectsA[0];
                }
                if (bullet.fromEnemy) {
                    game.setScreen(new GameScreen(camera, game, levelNum));
                    if (enemyBullets != null) {
                        enemyBullets.clear();
                    }
                    if (playerBullets != null) {
                        playerBullets.clear();
                    }
                }
            }

            if (isContact("Enemy", "Bullet", objectsA[1], objectsB[1])) {
                Bullet bullet;
                Enemy enemy;
                if (objectsA[1].equals("Enemy")) {
                    enemy = (Enemy) objectsA[0];
                    bullet = (Bullet) objectsB[0];
                } else {
                    enemy = (Enemy) objectsB[0];
                    bullet = (Bullet) objectsA[0];
                }
                if (!bullet.fromEnemy) {
                    enemy.kill = true;
                    bullet.kill = true;
                }
            }

            if (isContact("Player", "EndOfMap", objectsA[1], objectsB[1])) {
                game.setScreen(new LevelMenuScreen(game));
                if (enemyBullets != null) {
                    enemyBullets.clear();
                }
                if (playerBullets != null) {
                    playerBullets.clear();
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

    public boolean isContact(String id1, String id2, Object a, Object b) {
        if (a == null || b == null) return false;
        return ((a.equals(id1) && b.equals(id2)) ||
                (a.equals(id2) && b.equals(id1)));
    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setEndOfMap(EndOfMap end) {
        this.end = end;
    }
}
