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
    public static float SCR_WIDTH;
    public static float SCR_HEIGHT;
    Texture imgMosquito;
    float x, y;

    float vx, vy; // скорости движения по х и y
    float width, height;
    Texture imgBackGround; // фоновое изображение

    // системные объекты
    SpriteBatch batch; // Объект, отвечающий за вывод изображений
    OrthographicCamera camera; // пересчитывает размеры для различных экранов

    @Override
    public void create() {
        batch = new SpriteBatch(); // создать объект, отвечающий за вывод изображений
        camera = new OrthographicCamera();

        SCR_WIDTH = Gdx.graphics.getWidth();
        SCR_HEIGHT = Gdx.graphics.getHeight();

        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);

        imgBackGround = new Texture("swamp0.jpg");
        imgMosquito = new Texture("mosq0.png");

        vx = 5;
        vy = 5;

        x = 150;
        y = 150;

        width = SCR_WIDTH / 4;
        height = SCR_HEIGHT / 4 + 100;
    }

    @Override
    public void render() {
        batch.begin();

        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);

        batch.draw(imgMosquito, x, y, width, height);

        if (x < 0 || x > SCR_WIDTH - width) vx = -vx;
        if (y < 0 || y > SCR_HEIGHT - height) vy = -vy;

        x += vx;
        y += vy;

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        imgMosquito.dispose();
        imgBackGround.dispose();
    }
}
