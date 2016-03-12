package com.eazystudio.nepomnyaschy.fappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created by Ilya on 28.02.2016.
 */
public class SpaceShip {
    private Bitmap bitmap;
    private Rect hitbox;

    private int x,y ;
    private int speed = 0;
    private boolean boosting;

    private int maxY;
    private int minY;

    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    private final int GRAVITY = -12;

    public SpaceShip(Context context, int screenX, int screenY)
    {
        this.x = 50;
        this.y = 50;
        speed = 1;
        boosting = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship);
        hitbox = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());

        maxY = screenY - bitmap.getHeight();
        minY = 0;

        hitbox = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void update()
    {
        if(boosting)
        {
            speed += 2;
        }
        else
        {
            speed -= 5;
        }

        if(speed > MAX_SPEED)
        {
            speed = MAX_SPEED;
        }
        if(speed < MIN_SPEED)
        {
            speed = MIN_SPEED;
        }

        y -= speed + GRAVITY;

        if(y < minY)
        {
            y = minY;
        }
        if(y > maxY)
        {
            y = maxY;
        }

        hitbox.left = x;
        hitbox.top = y;
        hitbox.right = x + bitmap.getWidth();
        hitbox.bottom = y + bitmap.getHeight();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }


    public Rect getHitbox() {
        return hitbox;
    }

    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setBoosting()
    {
        boosting = true;
    }

    public void stopBoosting()
    {
        boosting = false;
    }
}
