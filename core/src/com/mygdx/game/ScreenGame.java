package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

/**
 * @author y.gladkikh
 */
public class ScreenGame implements Screen {

    MyGdxGame mgg;

    Mosquito[] mosquito;
    Bee[] bees;

    Texture[] imges;
    Texture beeImage;

    Texture imgBackGround; // фоновое изображение

    Sound[] sounds;

    Random random;

    public ScreenGame(MyGdxGame g) {
        mgg = g;

        random = new Random();
        imgBackGround = new Texture("swamp0.jpg");
        beeImage = new Texture("bee.png");

        sounds = new Sound[4];
        for (int i = 0; i < sounds.length; i++) {
            sounds[i]= Gdx.audio.newSound(Gdx.files.internal("mos" + i + ".mp3"));
        }

        imges = new Texture[11];
        for (int i = 0; i < 11; i++) {
            imges[i] = new Texture("mosq" + i + ".png");
        }

        mosquito = new Mosquito[25];
        for (int i = 0; i < mosquito.length; i++) {
            mosquito[i] = new Mosquito();
        }

        bees = new Bee[10];
        for (int i = 0; i < bees.length; i++) {
            bees[i] = new Bee();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        mgg.batch.begin();

        mgg.batch.draw(imgBackGround, 0, 0, MyGdxGame.SCR_WIDTH, MyGdxGame.SCR_HEIGHT);

        // касания экрана
        if (Gdx.input.justTouched()) {
            mgg.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            mgg.camera.unproject(mgg.touch);
            for (int i = mosquito.length - 1; i >= 0; i--) {
                if (mosquito[i].isAlive && mosquito[i].hit(mgg.touch.x, mgg.touch.y)) {
                    sounds[random.nextInt(4)].play(1.0f);
                    // увеличить очки
                }
            }
        }

        for (int i = 0; i < bees.length; i++) {
            mgg.batch.draw(
                    beeImage,
                    bees[i].x,
                    bees[i].y,
                    bees[i].width,
                    bees[i].height,
                    0,
                    0,
                    500,
                    500,
                    bees[i].isFlip(),
                    false);
        }

        for (int i = 0; i < mosquito.length; i++) {
            mgg.batch.draw(
                    imges[mosquito[i].phase],
                    mosquito[i].x,
                    mosquito[i].y,
                    mosquito[i].width,
                    mosquito[i].height,
                    0,
                    0,
                    500,
                    500,
                    mosquito[i].isFlip(),
                    false);
        }

        for (int i = 0; i < mosquito.length; i++) {
            mosquito[i].move();
        }
        for (int i = 0; i < bees.length; i++) {
            bees[i].move();
        }

        mgg.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for (int i = 0; i < mosquito.length; i++) {
            imges[i].dispose();
        }
        imgBackGround.dispose();
    }
}
