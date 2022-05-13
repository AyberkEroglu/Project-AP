package com.mygdx.projectap.bodies.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.projectap.bodies.helper.BodyHelperService;
import com.mygdx.projectap.screens.GameScreen;

import static com.mygdx.projectap.bodies.helper.Constants.PPM;

public class Bullet {

    private Body body;
    private Sprite sprite;
    private GameScreen gameScreen;
    public float angle;
    public boolean fromEnemy;
    public boolean kill;

    public Bullet(float x, float y, World world, float angle, boolean fromEnemy, GameScreen gameScreen) {
        this.body = BodyHelperService.createBody(x, y, 10, 1, false, world, true, true, new Object[]{this, "Bullet"});
        float speed = 20;
        body.setLinearVelocity((float) (-1 * Math.cos(angle) * speed), (float) (-1 * Math.sin(angle) * speed));
        this.angle = angle;
        this.fromEnemy = fromEnemy;

        if (fromEnemy) {
            this.sprite = new Sprite(new Texture("entity assets/enemybullet.png"));
        } else {
            this.sprite = new Sprite(new Texture("badlogic.jpg"));
        }
        sprite.setSize(10, 10);

        this.gameScreen = gameScreen;
    }

    public void update(float deltaTime) {
        if (kill) {
            dispose();
            this.sprite = null;
            body.getWorld().destroyBody(body);
            if (gameScreen.playerBullets.contains(this)) {
                gameScreen.playerBullets.remove(this);
            } else {
                gameScreen.enemyBullets.remove(this);
            }

        } else {
            sprite.setPosition(getBody().getPosition().x * PPM - sprite.getWidth() / 2, getBody().getPosition().y * PPM - sprite.getHeight() / 2);
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
