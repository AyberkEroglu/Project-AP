package com.mygdx.projectap.bodies.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.projectap.bodies.entities.Bullet;
import com.mygdx.projectap.bodies.helper.Constants;
import com.mygdx.projectap.bodies.entities.Bullet;
import com.mygdx.projectap.bodies.helper.Constants;
import jdk.internal.net.http.common.Log;

import static com.mygdx.projectap.bodies.helper.Constants.PPM;

public class Player extends GameEntity {

    private boolean isRightSide = true;


    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 4f;
    }

    @Override
    public void update() {


        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            getBody().setLinearVelocity(5, getBody().getLinearVelocity().y);
            isRightSide = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            getBody().setLinearVelocity(-5, getBody().getLinearVelocity().y);
            isRightSide = false;
        } else {
            getBody().setLinearVelocity(0, getBody().getLinearVelocity().y);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            getBody().applyLinearImpulse(new Vector2(0, 10), getBody().getLocalCenter(), true);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            float angle = MathUtils.degreesToRadians *  180;
            if(!isRightSide) {
                angle =0;
            }
            Bullet b = new Bullet(getBody().getPosition().x * Constants.PPM, getBody().getPosition().y
                    * Constants.PPM, getBody().getWorld(), angle,false);
            Bullet.bullets.add(b);

        }
    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
