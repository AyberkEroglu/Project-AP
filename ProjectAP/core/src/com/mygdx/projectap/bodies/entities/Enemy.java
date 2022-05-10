package com.mygdx.projectap.bodies.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.projectap.bodies.helper.BodyHelperService;
import com.mygdx.projectap.bodies.helper.Constants;

import static com.mygdx.projectap.bodies.helper.Constants.PPM;

public class Enemy extends GameEntity {
    private int decider = 0;

    private Player player;

    private Body sensorBody;
    private Sprite sprite;

    private World world;

    float elapsed = 0;
    int bulletPerSecond = 1;

    public Enemy(float width, float height, World world, Body body) {
        super(width, height, body);

        this.world = world;
        sprite = new Sprite(new Texture("entity assets/zombie.png"));
        sprite.setSize(90,60);
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
        move();

        sprite.setPosition(getBody().getPosition().x * PPM - sprite.getWidth() / 2, getBody().getPosition().y * PPM - sprite.getHeight() / 2);


        elapsed += delta;
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.draw(batch);
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

    public void move(){

        if (decider < 10) {
            this.getBody().setLinearVelocity(10, this.getBody().getLinearVelocity().y);
            decider++;
            System.out.println(decider);
        }
        else {
            this.getBody().setLinearVelocity(-10, this.getBody().getLinearVelocity().y);
            decider ++;
            if (decider >= 20) decider = 0;
            System.out.println(decider);
        }
    }
}