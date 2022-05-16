package a10.jflores.finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Pipe extends StartingObject {
    // pipes will be moving with speed
    public static int speed;

    public Pipe(int width, int height, float xpos, float ypos) {
        super(width, height, xpos, ypos);
        speed = 10 * ConstValues.SCREEN_WIDTH / 1080;
    }

    public void draw(Canvas canvas) {
        this.xpos -= speed;
        canvas.drawBitmap(this.bitmap, this.xpos, this.ypos, null);
    }

    public void yRandom() {
        Random r = new Random();
        this.ypos = r.nextInt((0 + this.height / 4) + 1) - this.height / 4;
    }

    @Override
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
    }
}
