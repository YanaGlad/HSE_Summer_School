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

    Mosquito[] mosquito;
    Texture[] imges;

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

        float width = SCR_WIDTH / 5;
        float height = SCR_HEIGHT / 5 + 100;

        imges = new Texture[]{
                new Texture("mosq0.png"),
                new Texture("mosq1.png"),
                new Texture("mosq2.png"),
                new Texture("mosq3.png"),
                new Texture("mosq4.png"),
                new Texture("mosq5.png"),
                new Texture("mosq6.png"),
                new Texture("mosq7.png"),
                new Texture("mosq8.png"),
                new Texture("mosq9.png"),
                new Texture("mosq10.png"),
        };

        mosquito = new Mosquito[25];
        for (int i = 0; i < mosquito.length; i++) {
            mosquito[i] = new Mosquito(0, 0, width, height);
        }
    }

    @Override
    public void render() {
        batch.begin();

        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);

        for (int i = 0; i < mosquito.length; i++) {
            mosquito[i].checkDirections();
            mosquito[i].move();
        }

        for (int i = 0; i < mosquito.length; i++) {
            batch.draw(imges[mosquito[i].faza], mosquito[i].x, mosquito[i].y, mosquito[i].width, mosquito[i].height, 0, 0, 500, 500, mosquito[i].isFlip(), false);
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        for (int i = 0; i < mosquito.length; i++) {
            imges[i].dispose();
        }
        imgBackGround.dispose();
    }
}
