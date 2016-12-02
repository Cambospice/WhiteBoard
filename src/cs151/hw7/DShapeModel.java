package cs151.hw7;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

/**
 * Created by Roshni Velluva Puthanidam on 28/11/16.
 */
public class DShapeModel implements Serializable {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;
    private Rectangle shapeRectangle;

    public static final int KNOB_SIZE = 12;
    public static final Rectangle[] knobs = new Rectangle[4];


    public DShapeModel(int x, int y, int width, int height, Color color) {
        this.x = new Random().nextInt(800);
        this.y = new Random().nextInt(550);
        this.width = 50;
        this.height = 50;
        this.color = Color.GRAY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public Rectangle getShapeRectangle() {
        return shapeRectangle;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setShapeRectangle(Rectangle shapeRectangle) {
        this.shapeRectangle = shapeRectangle;
    }

    public static Rectangle[] getKnobs() {
        return knobs;
    }
}