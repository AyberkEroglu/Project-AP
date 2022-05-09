package com.mygdx.projectap.weapon;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.projectap.projectile.Projectile;


public class RangedWeapon extends Weapon{
    protected Projectile[] projectiles;

    public RangedWeapon(int range, Texture texture) {
        super(range, texture);
    }

    @Override
    public void shoot() {

    }
}
