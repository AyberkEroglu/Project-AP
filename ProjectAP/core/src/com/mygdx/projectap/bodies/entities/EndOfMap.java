package com.mygdx.projectap.bodies.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.projectap.bodies.helper.BodyHelperService;

public class EndOfMap {

    private Body body;

    public EndOfMap(Rectangle rectangle, World world) {
        this.body = BodyHelperService.createBody(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2, 70, 70, true, world, true, false, new Object[]{this, "EndOfMap"});
    }

    public void update(float deltaTime) {

    }

    public Body getBody() {
        return body;
    }
}
