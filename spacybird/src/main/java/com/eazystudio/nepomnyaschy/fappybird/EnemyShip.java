package com.eazystudio.nepomnyaschy.fappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by Ilya on 29.02.2016.
 */
public class EnemyShip {
    private Bitmap bitmap;
    private Rect hitbox;

    private int x;
    private int y;
    private int speed = 1;

    private int maxY;
    private int minY;

    private int maxX;
    private int minX;

    public EnemyShip(Context context, int screenX, int screenY){
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);

        maxX = screenX;
        maxY = screenY;

        minX = 0;
        minY = 0;

        Random generator = new Random();
        speed = generator.nextInt(6) + 10;

        x = screenX;
        y = generator.nextInt(maxX) - bitmap.getHeight();

        hitbox = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void update(int playerSpeed){

        //move to left;
        x -= playerSpeed;
        x -= speed;

        if(x < minX - bitmap.getWidth())
        {
            Random generator = new Random();
            speed = generator.nextInt(10) + 10;

            x = maxX;
            y = generator.nextInt(maxY) - bitmap.getHeight();
        }

        hitbox.left = x;
        hitbox.top = y;
        hitbox.right = x + bitmap.getWidth();
        hitbox.bottom = y + bitmap.getHeight();
    }

    public Bitmap getBitmap()
    {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Rect getHitbox() {
        return hitbox;
    }


}
