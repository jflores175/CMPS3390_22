package a10.jflores.mindmaster;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {
    private final int FPS = 1000/30;
    private Thread thread;
    private boolean isPlaying; //see if running or not
    private Board board;

    //private int x = 100, y = 100; // x and y position of circle


    public GameView(Context context, Point screenSize) {
        super(context);
        board = new Board(screenSize, getResources()); //needs point that has screensize but need to get it from activity
        // screensize must be passed into us
    }

    @Override
    public void run() {
        while(isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    private void update() {
        //y += 4;

    }

    private void draw() {
        if(getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            //clear canvas everytime
            canvas.drawColor(Color.BLACK);
            board.draw(canvas);

            //--------------------------------------
            // old code for example below
            // Paint paint = new Paint();
            //paint.setARGB(255, 255, 0, 0);
            //canvas.drawCircle(x, y, 25, paint);
            //unlockcanvas and post to screen
            //---------------------------------------

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {
        try {
            isPlaying = false;
            thread.join(); //joins thread running in loop to our main thread which will pause this thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void sleep() {
        try {
            thread.sleep(FPS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            int x = Math.round(event.getX());
            int y = Math.round(event.getY());
            board.onClick(x, y);
        }
        return true;
    }
}
