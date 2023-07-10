package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

/**
 * @author y.gladkikh
 */
public class Mosquito extends Insect {

    int phase, nPhase = 10;

    Mosquito() {
        super();

        phase = MathUtils.random(0, nPhase - 1);
    }

    @Override
    void move() {
        super.move();
        if (isAlive) {
            if (++phase == nPhase) phase = 0;
        }
    }

    boolean hit(float tx, float ty) {
        return super.hit(tx, ty, new HitAction() {
            @Override
            public void hitAction() {
                phase = 10;
            }
        });
    }
}
