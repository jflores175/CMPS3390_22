package a10.jflores.finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;

public class Bird extends StartingObject {
    private ArrayList<Bitmap> bitmaps_array = new ArrayList<>(); // will be used to animate the birds
    private int count;
    private int flap;
    private int currBitmapid;
    private float drop;

    public Bird() {
        this.count = 0;
        this.flap = 5;
        this.currBitmapid = 0;
        this.drop = 0;
    }

    public void draw(Canvas canvas) {
        drop();
        canvas.drawBitmap(this.getBitmap(), this.xpos, this.ypos, null);
    }

    private void drop() {
        this.drop += 0.6;
        this.ypos += this.drop;
    }

    public float getDrop() {
        return drop;
    }

    public void setDrop(float drop) {
        this.drop = drop;
    }

    public ArrayList<Bitmap> getBitmapsArray() {
        return bitmaps_array;
    }

    public void setBitmaps_array(ArrayList<Bitmap> bitmaps_array) {
        this.bitmaps_array = bitmaps_array;
        for (int i = 0; i < bitmaps_array.size(); i++) {
            this.bitmaps_array.set(i, Bitmap.createScaledBitmap(this.bitmaps_array.get(i),
                                    this.width, this.height, true));
        }
    }

    @Override
    public Bitmap getBitmap() {
        count++;
        if (this.count == this.flap) {
            for (int i = 0; i < bitmaps_array.size(); i++) {
                if (i == bitmaps_array.size() - 1) {
                    this.currBitmapid = 0;
                    break;
                }
                else if (this.currBitmapid == i) {
                    currBitmapid = i + 1;
                    break;
                }
            }
            count = 0;
        }
        if (this.drop < 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(-25);
            return Bitmap.createBitmap(bitmaps_array.get(currBitmapid), 0, 0,
                                        bitmaps_array.get(currBitmapid).getWidth(),
                                        bitmaps_array.get(currBitmapid).getHeight(),
                                        matrix, true);
        }
        else if (drop >= 0) {
            Matrix matrix = new Matrix();
            if (drop < 70) {
                matrix.postRotate(-25 + drop * 2);
            }
            else {
                matrix.postRotate(45);
            }
            return Bitmap.createBitmap(bitmaps_array.get(currBitmapid), 0, 0,
                    bitmaps_array.get(currBitmapid).getWidth(),
                    bitmaps_array.get(currBitmapid).getHeight(),
                    matrix, true);
        }
        return this.bitmaps_array.get(currBitmapid);
    }

}
