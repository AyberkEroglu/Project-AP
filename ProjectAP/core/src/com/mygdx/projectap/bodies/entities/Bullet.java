package com.mygdx.projectap.bodies.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.projectap.bodies.helper.BodyHelperService;

import java.util.ArrayList;

import static com.mygdx.projectap.bodies.helper.Constants.PPM;

public class Bullet {

    public static ArrayList<Bullet> bullets;
    public boolean kill;
    Body body;
    public float angle;
    private Sprite sprite;

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

        sprite = new Sprite(new Texture("badlogic.jpg"));
        sprite.setSize(10,10);
    }

    public void update(float deltaTime) {
        if (kill) {
            body.getWorld().destroyBody(body);
            bullets.remove(this);
        }

        sprite.setPosition(getBody().getPosition().x * PPM - sprite.getWidth() / 2 -21, getBody().getPosition().y * PPM - sprite.getHeight() / 2);

    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public Body getBody() {
        return body;
    }
}
