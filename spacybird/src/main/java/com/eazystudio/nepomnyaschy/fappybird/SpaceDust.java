package com.eazystudio.nepomnyaschy.fappybird;

import java.util.Random;

/**
 * Created by Ilya on 29.02.2016.
 */
public class SpaceDust {
    private int x;
    private int y;
    private int speed;

    private int maxX;
    private int minX;
    private int maxY;
    private int minY;

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public SpaceDust(int screenX, int screenY)
    {
        maxX = screenX;
        maxY = screenY;

        minX = 0;
        minY = 0;

        Random gen = new Random();
        speed = gen.nextInt(10);

        x = gen.nextInt(maxX);
        y = gen.nextInt(maxY);
    }

    public void update(int playerSpeed)
    {
        x-=speed;
        x-=playerSpeed;

        if(x < 0)
        {
            x = maxX;
            Random gen = new Random();
            y = gen.nextInt(maxY);
            speed = gen.nextInt(15);
        }
    }
}
