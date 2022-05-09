package com.mygdx.projectap.entities;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.projectap.weapon.Weapon;

public abstract class Entity {
    private int hitsTaken;
    Weapon[] weapons;

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;
    private Vector2 velocity;

    public Entity(float width, float height, float x, float y, float density, Vector2 velocity) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.density = density;
        this.velocity = velocity;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDensity() {
        return density;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

}

