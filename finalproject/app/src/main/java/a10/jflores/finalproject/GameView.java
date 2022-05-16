package a10.jflores.finalproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import android.os.Handler;

import androidx.annotation.Nullable;

import java.util.ArrayList;
//import java.util.logging.Handler;

public class GameView extends View {
    private Bird bird;
    private ArrayList<Pipe> pipesArray;
    private int pipesum;
    private int pipedistance;
    private Handler handler;
    private Runnable r;
    private int score; // start at 0
    private int bestscore = 0;
    private boolean start;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        start = false;
        score = 0;
        initbird();
        initpipe();
        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }

    private void initbird() {
        bird = new Bird();

        // set size of bird
        bird.setWidth(100 * ConstValues.SCREEN_WIDTH/ 1080);
        bird.setHeight(100 * ConstValues.SCREEN_HEIGHT / 1920);

        // set position of the bird
        bird.setXpos(100 * ConstValues.SCREEN_WIDTH/ 1080);
        bird.setYpos(ConstValues.SCREEN_HEIGHT / 2 - bird.getHeight() / 2);

        // create bitmaps array for the bird's parameters
        ArrayList<Bitmap> bitmaps_array = new ArrayList<>();
        bitmaps_array.add(BitmapFactory.decodeResource(this.getResources(),
                R.drawable.birdup));
        bitmaps_array.add(BitmapFactory.decodeResource(this.getResources(),
                R.drawable.birddown));
        bird.setBitmaps_array(bitmaps_array);
    }

    private void initpipe() {
        pipesum = 6;
        pipedistance = 300 * ConstValues.SCREEN_HEIGHT / 1920;
        pipesArray = new ArrayList<>();
        for (int i = 0; i < pipesum; i++) {
            if (i < pipesum / 2) {
                float x = ConstValues.SCREEN_WIDTH + i *
                        ((ConstValues.SCREEN_WIDTH + 200 * ConstValues.SCREEN_WIDTH / 1080) / (pipesum / 2));
                float y = 0;
                int w = 200 * ConstValues.SCREEN_WIDTH / 1080;
                int h = ConstValues.SCREEN_HEIGHT / 2;
                this.pipesArray.add(new Pipe(w, h, x, y));

                // initialize positions
                this.pipesArray.get(this.pipesArray.size() - 1).setBitmap(BitmapFactory.decodeResource(this.getResources(),
                                    R.drawable.pipe2));
                this.pipesArray.get(this.pipesArray.size() - 1).yRandom();
            }
            else {
                float x = this.pipesArray.get(i - pipesum / 2).getXpos();
                float y = this.pipesArray.get(i - pipesum / 2).getYpos()
                        + this.pipesArray.get(i - pipesum / 2).getHeight() + this.pipedistance;
                int w = 200 * ConstValues.SCREEN_WIDTH / 1080;
                int h = ConstValues.SCREEN_HEIGHT / 2;
                this.pipesArray.add(new Pipe(w, h, x, y));
                this.pipesArray.get(this.pipesArray.size() - 1).setBitmap(BitmapFactory.decodeResource(this.getResources(),
                                    R.drawable.pipe1));
            }
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (start) {
            bird.draw(canvas);
            for (int i = 0; i < pipesum; i++) {

                //collisions
                boolean collision = bird.getRectangle().intersect(pipesArray.get(i).getRectangle());
                boolean ground_col = bird.getYpos() - bird.getHeight() < 0;
                boolean sky_col = bird.getYpos() > ConstValues.SCREEN_HEIGHT;

                if (collision || ground_col || sky_col) {
                    Pipe.speed = 0;
                    MainActivity.game_over_score_text.setText(MainActivity.text_score.getText());
                    MainActivity.best_score_text.setText("Best: " + bestscore);
                    MainActivity.text_score.setVisibility(INVISIBLE);
                    MainActivity.game_over_rl.setVisibility(VISIBLE);
                }

                float birdpos = this.bird.getXpos() + this.bird.getWidth();
                float score_threshold1 = pipesArray.get(i).getXpos() + pipesArray.get(i).getWidth() / 2;
                float score_threshold2 = pipesArray.get(i).getXpos() + pipesArray.get(i).getWidth() / 2 + Pipe.speed;

                // when to increase the score
                if(birdpos > score_threshold1 && birdpos <= score_threshold2 && i < pipesum / 2) {
                    score++;
                    if (score > bestscore) {
                        bestscore = score;
                    }
                    MainActivity.text_score.setText("" + score);
                }

                if (this.pipesArray.get(i).getXpos() < -1 * pipesArray.get(i).getWidth()) {
                    this.pipesArray.get(i).setXpos(ConstValues.SCREEN_WIDTH);
                    if (i < pipesum / 2) {
                        pipesArray.get(i).yRandom();
                    }
                    else {
                        pipesArray.get(i).setYpos(this.pipesArray.get(i - pipesum / 2).getYpos()
                                + this.pipesArray.get(i - pipesum / 2).getHeight() + this.pipedistance);
                    }
                }
                this.pipesArray.get(i).draw(canvas);
            }
        }
        else {
            if (bird.getYpos() > ConstValues.SCREEN_HEIGHT / 2) {
                bird.setDrop(-14 * ConstValues.SCREEN_HEIGHT / 1920);
            }
            bird.draw(canvas);
        }

        handler.postDelayed(r, 10);
        //canvas.drawColor(Color.BLUE);
    }

    // increase bird's height when tapping the screen
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            bird.setDrop(-7);
        }
        return true;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void reset() {
        MainActivity.text_score.setText("0");
        score = 0;
        initpipe();
        initbird();
    }
}
