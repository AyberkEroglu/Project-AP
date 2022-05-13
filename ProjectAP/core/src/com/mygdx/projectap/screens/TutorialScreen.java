package com.mygdx.projectap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.mygdx.projectap.ProjectAP;
import com.mygdx.projectap.bodies.entities.Bullet;
import com.mygdx.projectap.bodies.entities.Enemy;

import static com.mygdx.projectap.bodies.helper.Constants.PPM;

public class TutorialScreen extends GameScreen implements ContactListener {

    public TutorialScreen(OrthographicCamera camera, ProjectAP game, int levelNum) {
        super(camera, game, levelNum);
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
            game.setScreen(new PlayMenuScreen(game));
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
        box2DDebugRenderer.render(world, camera.combined.scl(PPM));
    }

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
                    game.setScreen(new TutorialScreen(camera, game, levelNum));
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
}
