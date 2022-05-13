package com.mygdx.projectap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.projectap.ProjectAP;
import com.mygdx.projectap.bodies.entities.Bullet;
import com.mygdx.projectap.bodies.entities.Enemy;

public class TimeRaceScreen extends GameScreen implements ContactListener {

    private float timeCount;
    //private Label timeLabel;
    //private Table timeTable;
    private float worldTimer;
    //private Label countdownLabel;
    private BitmapFont font;

    public TimeRaceScreen(OrthographicCamera camera, ProjectAP game, int levelNum, float worldTimer) {
        super(camera, game, levelNum);
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
        if (timeCount >= 1) {
            worldTimer--;
            //this.countdownLabel.setText((int) worldTimer);
            timeCount = 0;
        }
        return "" + worldTimer;
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
            game.setScreen(new TimeRaceLevelMenuScreen(game));
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

        if (worldTimer <= 0) {
            worldTimer = 15;
            game.setScreen(new TimeRaceScreen(camera, game, levelNum, worldTimer));
            if (enemyBullets != null) {
                enemyBullets.clear();
            }
            if (playerBullets != null) {
                playerBullets.clear();
            }
        }

        font.draw(batch, timeUpdate(Gdx.graphics.getDeltaTime()), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());

        batch.end();
        // box2DDebugRenderer.render(world, camera.combined.scl(PPM));
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
                    game.setScreen(new TimeRaceScreen(camera, game, levelNum, worldTimer));
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
                    //enemies.remove(enemy);
                }
            }

            if (isContact("Player", "EndOfMap", objectsA[1], objectsB[1])) {
                game.setScreen(new TimeRaceLevelMenuScreen(game));
                if (enemyBullets != null) {
                    enemyBullets.clear();
                }
                if (playerBullets != null) {
                    playerBullets.clear();
                }
            }
        }
    }
}
