package com.mygdx.projectap.bodies.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.projectap.bodies.helper.BodyHelperService;
import com.mygdx.projectap.bodies.helper.Constants;
import com.mygdx.projectap.screens.GameScreen;

import static com.mygdx.projectap.bodies.helper.Constants.PPM;

public class Enemy {

    // private int decider = 0;
    private boolean isLeftSide;
    private float elapsed = 0;
    private int bulletPerSecond = 1;
    private boolean attack;
    private Player player;
    private Body body;
    private Body sensorBody;
    private World world;
    private GameScreen gameScreen;
    private Sprite sprite;

    public boolean kill;

    public Enemy(Rectangle rectangle, World world, GameScreen gamescreen) {
        this.body = BodyHelperService.createBody(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2, 30, 60, false, world, false, false, new Object[]{this, "Enemy"});
        this.world = world;
        this.sprite = new Sprite(new Texture("entity assets/enemy.png"));
        sprite.setSize(30, 60);
        float x = body.getPosition().x;
        float y = body.getPosition().y;
        sensorBody = BodyHelperService.createBody(x, y, 30 * 10, 60 * 10, false, world, true, true, new Object[]{this, "EnemySensor"});
        this.gameScreen = gamescreen;
        this.isLeftSide = true;
    }

    public void update(float delta) {
        if (kill) {
            dispose();
            this.sprite = null;
            body.getWorld().destroyBody(body);
            gameScreen.enemies.remove(this);
        } else {
            sensorBody.setTransform(getBody().getPosition().x, getBody().getPosition().y, getBody().getAngle());

            if (attack) {
                if (elapsed > 1f / bulletPerSecond) {

                    float angle = (float) Math.atan2(getBody().getPosition().y - player.getBody().getPosition().y, getBody().getPosition().x - player.getBody().getPosition().x);

                    Bullet b = new Bullet(getBody().getPosition().x * Constants.PPM, getBody().getPosition().y * Constants.PPM, world, angle, true, gameScreen);
                    gameScreen.enemyBullets.add(b);
                    elapsed = 0;
                }
            }
            this.move();

            if (!isLeftSide) {
                sprite.setPosition(getBody().getPosition().x * PPM - sprite.getWidth() / 2, getBody().getPosition().y * PPM - sprite.getHeight() / 2);
            }
            if (isLeftSide) {
                sprite.setPosition(getBody().getPosition().x * PPM - sprite.getWidth() / 2, getBody().getPosition().y * PPM - sprite.getHeight() / 2);
            }

            elapsed += delta;
        }
    }

    public void render(SpriteBatch batch) {
        if (!kill) {
            sprite.draw(batch);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void enter() {
        attack = true;
    }

    public void exit() {
        attack = false;
    }

    public void move() {
        if (attack && player.getBody().getPosition().x < this.getBody().getPosition().x) {
            this.getBody().setLinearVelocity(-5, this.getBody().getLinearVelocity().y);
            if (!isLeftSide) {
                sprite.flip(true, false);
            }
            isLeftSide = true;
        }

        if (attack && player.getBody().getPosition().x > this.getBody().getPosition().x) {
            this.getBody().setLinearVelocity(5, this.getBody().getLinearVelocity().y);
            if (isLeftSide) {
                sprite.flip(true, false);
            }
            isLeftSide = false;
        }

        if (!attack) {
            this.getBody().setLinearVelocity(this.getBody().getLinearVelocity().x, this.getBody().getLinearVelocity().y);
        }
        /*
        if (decider < 10) {
            this.getBody().setLinearVelocity(10, this.getBody().getLinearVelocity().y);
            decider++;
        }
        else {
            this.getBody().setLinearVelocity(-10, this.getBody().getLinearVelocity().y);
            decider ++;
            if (decider >= 20) decider = 0;
        }
         */
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public void setSensorBody(Body body) {
        this.sensorBody = body;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }
}
