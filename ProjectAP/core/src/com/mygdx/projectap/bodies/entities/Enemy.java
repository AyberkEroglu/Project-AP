package com.mygdx.projectap.bodies.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.projectap.bodies.helper.BodyHelperService;
import com.mygdx.projectap.bodies.helper.Constants;

public class Enemy extends GameEntity {
    private Player player;

    private Body sensorBody;

    private World world;

    float elapsed = 0;
    int bulletPerSecond = 1;

    public Enemy(float width, float height, World world, Body body) {
        super(width, height, body);

        this.world = world;

        sensorBody = BodyHelperService.createBody(x, y, width * 10, width * 10, false, world, true, new Object[]{this, "EnemySensor"});
    }

    @Override
    public void update() {

    }

    public void update(float delta) {
        sensorBody.setTransform(getBody().getPosition().x, getBody().getPosition().y, getBody().getAngle());

        if (attack) {
            if (elapsed > 1f / bulletPerSecond) {

                float angle = (float) Math.atan2(getBody().getPosition().y - player.getBody().getPosition().y, getBody().getPosition().x - player.getBody().getPosition().x);

                Bullet b = new Bullet(getBody().getPosition().x * Constants.PPM, getBody().getPosition().y * Constants.PPM, world, angle, true);
                Bullet.bullets.add(b);
                elapsed = 0;
            }
        }

        elapsed += delta;
    }

    @Override
    public void render(SpriteBatch batch) {

    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private boolean attack;

    public void enter() {
        attack = true;
        System.out.println("Girdi");

    }


    public void exit() {
        attack = false;
    }
}