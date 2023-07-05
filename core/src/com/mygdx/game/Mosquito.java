package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * @author y.gladkikh
 */
public class Mosquito {

    float x, y;
    float vx, vy;
    float width, height;
    Texture img;

    Mosquito(
            float x,
            float y,
            float width,
            float height,
            Texture img
    ) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;

        vx = 5;
        vy = 5;
    }

    void checkDirections(float SCR_WIDTH, float SCR_HEIGHT) {
        if (x < 0 || x > SCR_WIDTH - width) vx = -vx;
        if (y < 0 || y > SCR_HEIGHT - height) vy = -vy;
    }
}
