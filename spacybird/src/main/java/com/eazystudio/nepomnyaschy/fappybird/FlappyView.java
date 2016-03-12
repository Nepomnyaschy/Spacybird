package com.eazystudio.nepomnyaschy.fappybird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Space;

import java.util.ArrayList;

/**
 * Created by Ilya on 28.02.2016.
 */
public class FlappyView extends SurfaceView implements Runnable {

    volatile boolean isPlaying = false;
    private Thread gameThread = null;
    private SpaceShip player;

    private EnemyShip enemy1;
    private EnemyShip enemy2;
    private EnemyShip enemy3;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceholder;

    public ArrayList<SpaceDust> spaceDustList = new ArrayList<SpaceDust>();

    public FlappyView(Context context, int x, int y) {
        super(context);

        surfaceholder = getHolder();
        paint = new Paint();
        player = new SpaceShip(context, x, y);
        enemy1 = new EnemyShip(context, x, y);
        enemy2 = new EnemyShip(context, x, y);
        enemy3 = new EnemyShip(context, x, y);

        for(int i = 0; i <= 200; i++)
        {
            SpaceDust sp = new SpaceDust(x, y);
            spaceDustList.add(sp);
        }
    }

    @Override
    public void run() {
        while(isPlaying)
        {
            try {
                gameThread.sleep(17); //1000 ms / 60 fps = 17
            } catch (InterruptedException e) {
            }

            update();
            draw();
            control();
        }
    }

    public void pause()
    {
        isPlaying = false;

        try {
            gameThread.join();
        }
        catch(InterruptedException e)
        {

        }
    }

    public void resume()
    {
        isPlaying = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void update(){
        if(Rect.intersects(player.getHitbox(), enemy1.getHitbox()))
        {
            enemy1.setX(-400);
        }

        if(Rect.intersects(player.getHitbox(), enemy2.getHitbox()))
        {
            enemy2.setX(-400);
        }

        if(Rect.intersects(player.getHitbox(), enemy3.getHitbox()))
        {
            enemy3.setX(-400);
        }

        player.update();
        enemy1.update(player.getSpeed());
        enemy2.update(player.getSpeed());
        enemy3.update(player.getSpeed());

        for(SpaceDust sd : spaceDustList)
        {
            sd.update(player.getSpeed());
        }
    }

    private void control() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch(event.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                break;

            case MotionEvent.ACTION_DOWN:
                player.setBoosting();
                break;
        }
        return true;
    }

    private void draw(){
        if(surfaceholder.getSurface().isValid())
        {
            canvas = surfaceholder.lockCanvas();
            canvas.drawColor(Color.argb(255, 0, 0, 0));

            paint.setColor(Color.argb(255,255,255,255));

            for(SpaceDust sp : spaceDustList) {
                canvas.drawPoint(sp.getX(), sp.getY(), paint);
            }

                canvas.drawBitmap(player.getBitmap(), player.getX(), player.getY(), paint);
                canvas.drawBitmap(enemy1.getBitmap(), enemy1.getX(), enemy1.getY(), paint);
                canvas.drawBitmap(enemy2.getBitmap(), enemy2.getX(), enemy2.getY(), paint);
                canvas.drawBitmap(enemy3.getBitmap(), enemy3.getX(), enemy3.getY(), paint);


            surfaceholder.unlockCanvasAndPost(canvas);
        }

    }
}
