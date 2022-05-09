package com.mygdx.projectap.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Weapon {
    protected float range;
    protected Texture texture;
    protected float velocity;

    public Weapon(float range, Texture texture, float velocity) {
        this.range = range;
        this.texture = texture;
        this.velocity = velocity;
    }

    public abstract void shoot(Vector2 direction);


}