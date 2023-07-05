package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.math.MathUtils;

import java.util.Random;

/**
 * @author y.gladkikh
 */
public class Mosquito {

    float x, y;
    float vx, vy;
    float width, height;
    int faza, nFaz = 10;

    Mosquito(
            float x,
            float y,
            float width,
            float height
    ) {
        this.x = x;
        this.y = y;
        float size = width * 1 / (new Random()).nextInt(3);
        this.width = size;
        this.height = size;

        vx = MathUtils.random(-7f, 7);
        vy = MathUtils.random(-7f, 7f);

        faza = MathUtils.random(0, nFaz - 1);
    }

    void checkDirections() {
        if (x < 0 || x > SCR_WIDTH - width) vx = -vx;
        if (y < 0 || y > SCR_HEIGHT - height) vy = -vy;
    }

    void move() {
        x += vx;
        y += vy;

        if (++faza == nFaz) faza = 0;
    }

    boolean isFlip() {
        if (vx > 0) {
            return true;
        }
        return false;
    }
}
