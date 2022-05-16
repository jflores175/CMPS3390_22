package a10.jflores.finalproject;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class StartingObject {
    protected int width, height;
    protected float xpos, ypos;
    protected Bitmap bitmap;
    protected Rect rectangle;

    public StartingObject() {

    }

    public StartingObject(int width, int height, float xpos, float ypos) {
        this.width = width;
        this.height = height;
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getXpos() {
        return xpos;
    }

    public void setXpos(float xpos) {
        this.xpos = xpos;
    }

    public float getYpos() {
        return ypos;
    }

    public void setYpos(float ypos) {
        this.ypos = ypos;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Rect getRectangle() {
        return new Rect((int) this.xpos, (int) this.ypos,
                    (int) this.xpos + this.width, (int) this.ypos + this.height);
    }

    public void setRectangle(Rect rectangle) {
        this.rectangle = rectangle;
    }
}
