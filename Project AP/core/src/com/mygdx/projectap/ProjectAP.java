package com.mygdx.projectap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;


public class ProjectAP extends Game {
	private static final float SLOW_TIME_SCALE = 0.2f;
	private static final float FAST_TIME_SCALE = 1f;


	float speed;
	float timeScale;
	//Point playerPos;
	SpriteBatch batch;
	Texture headLeft;
	Texture headRight;
	Sprite spr;
	OrthographicCamera camera;




	@Override
	public void create () {
		speed = 300;
		batch = new SpriteBatch();
		timeScale = 1;
		headLeft = new Texture("voxelPack/voxel-pack/PNG/Characters/Player male/male_head.png");
		headRight = new Texture("voxelPack/voxel-pack/PNG/Characters/Player male/male_head_r.png");
		spr = new Sprite(headLeft);
		spr.setPosition(5,5);
		camera = new OrthographicCamera();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) timeScale = SLOW_TIME_SCALE;
		else timeScale = FAST_TIME_SCALE;
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			spr.translate(timeScale * speed*Gdx.graphics.getDeltaTime(),0);
			spr.setTexture(headRight);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			spr.translate(-timeScale * speed*Gdx.graphics.getDeltaTime(),0);
			spr.setTexture(headLeft);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			spr.translate(0,timeScale * speed*Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			spr.translate(0,-timeScale * speed*Gdx.graphics.getDeltaTime());
		}
		batch.begin();
		batch.draw(spr, spr.getX(), spr.getY());
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		headLeft.dispose();
		headRight.dispose();
	}
}
