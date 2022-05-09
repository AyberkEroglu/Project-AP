package com.mygdx.projectap.projectile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Projectile {

    public static final Projectile BULLET = new Projectile(new Texture("kenneyindustrialpack/kenney_platformerpack_industrial/PNG/Default size/platformIndustrial_009.png"), 200, 1);

    protected float damage;
    protected float velocity;
    protected Vector2 direction;
    protected Texture texture;


    public Projectile(Texture texture, float velocity, float damage) {


    }




}
