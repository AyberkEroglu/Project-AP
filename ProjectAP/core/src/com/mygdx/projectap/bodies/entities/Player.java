package com.mygdx.projectap.bodies.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.projectap.ProjectAP;
import com.mygdx.projectap.bodies.helper.BodyHelperService;
import com.mygdx.projectap.bodies.helper.Constants;
import com.mygdx.projectap.screens.GameScreen;

import static com.mygdx.projectap.bodies.helper.Constants.PPM;

public class Player {

    private int jumpCount;
    private boolean jumpAllowed;
    private ProjectAP game;
    private boolean isRightSide = true;
    private Sprite sprite;
    private float speed;
    private Body body;
    private World world;
    private GameScreen gameScreen;

    public Player(Rectangle rectangle, World world, GameScreen gameScreen) {
        this.body = BodyHelperService.createBody(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2, 30, 60, false, world, new Object[]{this, "Player"});
        this.speed = 8f;
        this.sprite = new Sprite(new Texture("entity assets/saa.png"));
        this.sprite.setSize(90, 60);
        jumpCount = 0;
        jumpAllowed = true;
        this.world = world;
        this.gameScreen = gameScreen;
    }

    public void update() {
        if (this.getBody().getLinearVelocity().y == 0) jumpCount = 0;
        if (jumpCount >= 2) jumpAllowed = false;
        if (jumpCount < 2) jumpAllowed = true;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            getBody().setLinearVelocity(speed, getBody().getLinearVelocity().y);
            if (!isRightSide) {
                sprite.flip(true, false);
            }
            isRightSide = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            getBody().setLinearVelocity(-speed, getBody().getLinearVelocity().y);
            if (isRightSide) {
                sprite.flip(true, false);
            }
            isRightSide = false;
        } else {
            getBody().setLinearVelocity(0, getBody().getLinearVelocity().y);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && jumpAllowed) {
            if (this.getBody().getLinearVelocity().y == 0) jumpCount = 0;
            jumpCount++;
            if (ProjectAP.staticScale == ProjectAP.FAST_TIME_SCALE)
                getBody().applyLinearImpulse(new Vector2(0, 8), getBody().getLocalCenter(), true);
            else getBody().applyLinearImpulse(new Vector2(0, 2), getBody().getLocalCenter(), true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            float angle = MathUtils.degreesToRadians * 180;
            if (!isRightSide) {
                angle = 0;
            }
            Bullet b = new Bullet(getBody().getPosition().x * Constants.PPM, getBody().getPosition().y * Constants.PPM, getBody().getWorld(), angle, false, gameScreen);
            gameScreen.playerBullets.add(b);
        }
        // Player sprite position
        if (isRightSide) {
            sprite.setPosition(getBody().getPosition().x * PPM - sprite.getWidth() / 2 + 26, getBody().getPosition().y * PPM - sprite.getHeight() / 2);
        }
        if (!isRightSide) {
            sprite.setPosition(getBody().getPosition().x * PPM - sprite.getWidth() / 2 - 26, getBody().getPosition().y * PPM - sprite.getHeight() / 2);
        }
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public Body getBody() {
        return body;
    }
}
