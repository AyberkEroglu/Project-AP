package com.mygdx.projectap.projectile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Projectile {

    public static final Projectile BULLET = new Bullet();
    protected float damage;
    protected float velocity;
    protected Vector2 direction;
    protected Texture texture;


    public Projectile(Texture texture, float velocity, float damage) {


    }




}
