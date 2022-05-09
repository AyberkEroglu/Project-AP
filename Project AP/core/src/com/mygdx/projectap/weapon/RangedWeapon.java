package com.mygdx.projectap.weapon;
import com.badlogic.gdx.graphics.Texture;


public class RangedWeapon extends Weapon{
    protected Projectile[] prpojectiles;

    public RangedWeapon(int range, Texture texture) {
        super(range, texture);
    }

    @Override
    public void shoot() {

    }
}
