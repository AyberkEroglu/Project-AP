package com.mygdx.projectap.weapon;

import com.badlogic.gdx.graphics.Texture;

public abstract class Weapon {
    protected int range;
    protected Texture texture;

    public Weapon(int range, Texture texture) {
        this.range = range;
        this.texture = texture;
    }

    public abstract void shoot();


}