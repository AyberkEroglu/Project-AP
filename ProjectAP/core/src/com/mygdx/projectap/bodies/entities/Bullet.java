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

    private Body body;
    public float angle;
    public boolean fromEnemy;
    private Sprite sprite;
    public static ArrayList<Bullet> bullets;
    public boolean kill;

    public Bullet(float x, float y, World world, float angle, boolean fromEnemy) {
        this.body = BodyHelperService.createBody(x, y, 10, 1, false, world, true, new Object[]{this, "Bullet"});
        float speed = 20;
        body.setLinearVelocity((float) (-1 * Math.cos(angle) * speed), (float) (-1 * Math.sin(angle) * speed));
        if (bullets == null) {
            bullets = new ArrayList<>();
        }
        this.angle = angle;
        this.fromEnemy = fromEnemy;

        this.sprite = new Sprite(new Texture("badlogic.jpg"));
        sprite.setSize(10, 10);
    }

    public void update(float deltaTime) {
        if (kill) {
            dispose();
            this.sprite = null;
            body.getWorld().destroyBody(body);
            bullets.remove(this);
        } else {
            sprite.setPosition(getBody().getPosition().x * PPM - sprite.getWidth() / 2 - 21, getBody().getPosition().y * PPM - sprite.getHeight() / 2);
        }
    }

    public void render(SpriteBatch batch) {
        if (!kill)
            sprite.draw(batch);
    }

    public Body getBody() {
        return body;
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }
}
