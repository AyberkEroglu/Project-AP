package com.mygdx.projectap.bodies.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.projectap.bodies.helper.BodyHelperService;
import com.mygdx.projectap.bodies.helper.Constants;
import com.mygdx.projectap.bodies.player.Player;

public class Enemy extends Entity {
    private Player player;

    private Body sensorBody;

    private World world;

    float elapsed = 0;
    int bulletPerSecond = 1;

    public Enemy(float width, float height, float x, float y, float density, Vector2 velocity, World world) {
        super(width, height, x, y, density, velocity);

        this.world = world;

        setBody(BodyHelperService.createBody(x, y, width, height, false, world, new Object[]{this, "Enemy"}));
        sensorBody = BodyHelperService.createBody(x, y, width * 10, width * 10, false, world, true, new Object[]{this, "EnemySensor"});
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    private boolean attack;

    public void girdi() {
        attack = true;
        System.out.println("Girdi");

        //a.ıyı bul

        //60
    }


    public void cikti() {
        attack = false;
    }
}