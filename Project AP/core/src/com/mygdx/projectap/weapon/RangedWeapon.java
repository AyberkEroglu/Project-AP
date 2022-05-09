package com.mygdx.projectap.weapon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.projectap.projectile.Projectile;


public class RangedWeapon extends Weapon{
    protected Array<Projectile> projectiles;
    protected Vector2 direction;
    protected int projectileCount;

    public RangedWeapon(int range, Texture texture, float velocity, int projectileCount) {
        this(range,texture,projectileCount, velocity, Projectile.BULLET);
    }

    public RangedWeapon(float range, Texture texture, int projectileCount, float velocity, Projectile projectileType) {
        super(range, texture, velocity);
        this.projectileCount = projectileCount;
        createProjectiles(projectileCount,projectileType);
    }

    private void createProjectiles(int projectileCount, Projectile projectileType) {
        projectiles = new Array<>();
        for (int i = 0; i < projectileCount; i++) {
            projectiles.add(projectileType);
        }
    }

    @Override
    public void shoot(Vector2 direction) {

    }
}
