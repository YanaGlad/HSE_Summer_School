package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class MyGdxGame extends Game {
	// ширина и высота экрана
	public static final float SCR_WIDTH = 1280;
	public static final float SCR_HEIGHT = 720;

	// системные объекты
	SpriteBatch batch; // ссылка на объект, отвечающий за вывод изображений
	OrthographicCamera camera; // пересчитывает размеры для различных экранов

	@Override
	public void create () {
		batch = new SpriteBatch(); // создать объект, отвечающий за вывод изображений
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
