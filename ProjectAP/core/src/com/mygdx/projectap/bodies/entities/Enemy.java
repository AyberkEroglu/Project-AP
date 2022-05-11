package com.mygdx.projectap.bodies.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.projectap.bodies.helper.BodyHelperService;
import com.mygdx.projectap.bodies.helper.Constants;

import static com.mygdx.projectap.bodies.helper.Constants.PPM;

public class Enemy {
    private int decider = 0;

    private Player player;

    private Body sensorBody;
    private Sprite sprite;
    private World world;
    private Body body;
    public boolean kill;
    private boolean isLeftSide = true;

    float elapsed = 0;
    int bulletPerSecond = 1;

    public Enemy(Rectangle rectangle, World world) {

        Body body = BodyHelperService.createBody(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2, 30, 60, false, world, new Object[]{this, "Enemy"});
        this.body = body;

        this.world = world;
        sprite = new Sprite(new Texture("entity assets/zombie.png"));
        sprite.setSize(90,60);
        float x = body.getPosition().x;
        float y = body.getPosition().y;
        sensorBody = BodyHelperService.createBody(x, y, 30 * 10, 60 * 10, false, world, true, new Object[]{this, "EnemySensor"});
    }

    public void update() {

    }

    public void update(float delta) {
        if (kill) {
            body.getWorld().destroyBody(body);
        }
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

        if(!isLeftSide){
            sprite.setPosition(getBody().getPosition().x * PPM - sprite.getWidth() / 2 +21, getBody().getPosition().y * PPM - sprite.getHeight() / 2);
        }
        if(isLeftSide){
            sprite.setPosition(getBody().getPosition().x * PPM - sprite.getWidth() / 2 -21, getBody().getPosition().y * PPM - sprite.getHeight() / 2);
        }


        elapsed += delta;
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private boolean attack;

    public void enter() {
        attack = true;

    }


    public void exit() {
        attack = false;
    }

    public void move(){
        if(attack && player.getBody().getPosition().x < this.getBody().getPosition().x){
            this.getBody().setLinearVelocity(-1,this.getBody().getLinearVelocity().y);
            if(!isLeftSide){
                sprite.flip(true,false);
            }
            isLeftSide = true;
        }
        if(attack && player.getBody().getPosition().x > this.getBody().getPosition().x){
            this.getBody().setLinearVelocity(1,this.getBody().getLinearVelocity().y);
            if(isLeftSide){
                sprite.flip(true,false);
            }
            isLeftSide = false;
        }
        if(!attack){
            this.getBody().setLinearVelocity(this.getBody().getLinearVelocity().x,this.getBody().getLinearVelocity().y);
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

    public void setWorld(World world) { this.world = world; }
}