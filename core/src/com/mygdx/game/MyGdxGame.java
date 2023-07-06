package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

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

    Vector3 touch;

    @Override
    public void create() {
        batch = new SpriteBatch(); // создать объект, отвечающий за вывод изображений
        camera = new OrthographicCamera();
        touch = new Vector3();

        SCR_WIDTH = Gdx.graphics.getWidth();
        SCR_HEIGHT = Gdx.graphics.getHeight();

        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);

        imgBackGround = new Texture("swamp0.jpg");

        // Задание: сократить через цикл
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
            mosquito[i] = new Mosquito();
        }
    }

    @Override
    public void render() {
        batch.begin();

        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);

        // касания экрана
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            for (int i = mosquito.length - 1; i >= 0; i--) {
                if (mosquito[i].isAlive && mosquito[i].hit(touch.x, touch.y)) {
                    // увеличить очки
                }
            }
        }

        for (int i = 0; i < mosquito.length; i++) {
            batch.draw(imges[mosquito[i].phase], mosquito[i].x, mosquito[i].y, mosquito[i].width, mosquito[i].height, 0, 0, 500, 500, mosquito[i].isFlip(), false);
        }

        for (int i = 0; i < mosquito.length; i++) {
            mosquito[i].move();
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
