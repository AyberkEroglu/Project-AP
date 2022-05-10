package com.mygdx.projectap.bodies.helper;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.projectap.screens.GameScreen;
import com.mygdx.projectap.bodies.entities.Enemy;
import com.mygdx.projectap.bodies.entities.Player;

import java.util.ArrayList;

import static com.mygdx.projectap.bodies.helper.Constants.PPM;

public class TileMapHelper {

    private TiledMap tiledMap;
    private GameScreen gameScreen;
    private int levelNum;

    public TileMapHelper(GameScreen gameScreen, int levelNum) {
        this.gameScreen = gameScreen;
        this.levelNum = levelNum;
    }

    public OrthogonalTiledMapRenderer setupMap() {
        tiledMap = new TmxMapLoader().load("maps/map" + levelNum + "/map" + levelNum +".tmx");
        parseMapObjects(tiledMap.getLayers().get("objects").getObjects());
        return new OrthogonalTiledMapRenderer(tiledMap);
    }

    private void parseMapObjects(MapObjects mapObjects) {
        gameScreen.enemies = new ArrayList<>();

        for (MapObject mapObject : mapObjects) {

            if (mapObject instanceof PolygonMapObject) {
                createStaticBody((PolygonMapObject) mapObject);
            }

            if (mapObject instanceof RectangleMapObject) {
                Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
                String rectangleName = mapObject.getName();

                if (rectangleName.equals("player")) {
                    Body body = BodyHelperService.createBody(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2, 30, 60, false, gameScreen.getWorld(), new Object[]{this, "Player"});

                    gameScreen.setPlayer(new Player(rectangle.getWidth(), rectangle.getHeight(), body));
                }

                if (rectangleName.equals("enemy")) {
                    Body body = BodyHelperService.createBody(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2, 30, 60, false, gameScreen.getWorld(), new Object[]{this, "Enemy"});
                    Enemy enemy = new Enemy(30, 60, gameScreen.getWorld(), body);
                    enemy.setPlayer(gameScreen.getPlayer());

                    gameScreen.enemies.add(enemy);
                }
            }
        }
    }

    private void createStaticBody(PolygonMapObject polygonMapObject) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = gameScreen.getWorld().createBody(bodyDef);
        Shape shape = createPolygonShape(polygonMapObject);
        Fixture fixture = body.createFixture(shape, 1000);
        fixture.setUserData(new Object[]{this, "Wall"});
        shape.dispose();
    }

    private Shape createPolygonShape(PolygonMapObject polygonMapObject) {
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; i++) {
            Vector2 current = new Vector2(vertices[i * 2] / PPM, vertices[i * 2 + 1] / PPM);
            worldVertices[i] = current;
        }

        PolygonShape shape = new PolygonShape();
        shape.set(worldVertices);
        return shape;
    }
}
