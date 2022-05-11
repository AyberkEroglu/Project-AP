package com.mygdx.projectap.bodies.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.projectap.bodies.helper.BodyHelperService;

import java.util.ArrayList;

public class Bullet {

    public static ArrayList<Bullet> bullets;
    public boolean kill;
    Body body;
    public float angle;

    public boolean fromEnemy;

    public Bullet(float x, float y, World world, float angle, boolean fromEnemy) {
        float speed = 20;
        body = BodyHelperService.createBody(x, y, 10, 1, false, world, true, new Object[]{this, "Bullet"});
        body.setLinearVelocity((float) (-1 * Math.cos(angle) * speed), (float) (-1 * Math.sin(angle) * speed));
        if (bullets == null) {
            bullets = new ArrayList<>();
        }
        this.fromEnemy = fromEnemy;
        this.angle = angle;
    }

    public void update(float deltaTime) {
        if (kill) {
            body.getWorld().destroyBody(body);
            bullets.remove(this);
        }
    }

    public Body getBody() {
        return body;
    }
}
