package com.mygdx.projectap.bodies.helper;

import com.badlogic.gdx.physics.box2d.*;

public class BodyHelperService {

    public static Body createBody(float x, float y, float width, float height, boolean isStatic, World world, boolean isSensor, boolean isCircular, Object[] info) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / Constants.PPM, y / Constants.PPM);
        bodyDef.fixedRotation = true;
        Body body = world.createBody(bodyDef);

        if (isCircular) {
            CircleShape circleShape = new CircleShape();
            circleShape.setRadius(width / Constants.PPM);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = circleShape;
            if (isSensor) {
                fixtureDef.isSensor = true;
            }
            Fixture fixture = body.createFixture(fixtureDef);
            fixture.setUserData(info);
            circleShape.dispose();
        } else {
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(width / 2 / Constants.PPM, height / 2 / Constants.PPM);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            if (isSensor) {
                fixtureDef.isSensor = true;
            }
            Fixture fixture = body.createFixture(fixtureDef);
            fixture.setUserData(info);
            shape.dispose();
        }
        return body;
    }
}
