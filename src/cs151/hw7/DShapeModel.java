package cs151.hw7;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    protected Rectangle bounds;

    private ArrayList<ModelListener> listeners = new ArrayList<>();

    public DShapeModel() {
        this.x = new Random().nextInt(400);
        this.y = new Random().nextInt(400);
        this.width = 50;
        this.height = 50;
        this.color = Color.GRAY;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }


    public DShapeModel(DShapeModel model){
        this.x = model.getX();
        this.y = model.getY();
        this.width = model.getWidth();
        this.height = model.getHeight();
        bounds = new Rectangle(model.getX(), model.getY(), model.getWidth(), model.getHeight());
        this.color = model.getColor();
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
        notifyListeners();
    }

    public void setY(int y) {
        this.y = y;
        notifyListeners();
    }

    public void setWidth(int width) {

        this.width = width;
        notifyListeners();
    }

    public void setHeight(int height) {
        this.height = height;
        notifyListeners();
    }

    public void setColor(Color color) {
        this.color = color;
        notifyListeners();
    }

    public void setShapeRectangle(Rectangle shapeRectangle) {
        this.shapeRectangle = shapeRectangle;
        notifyListeners();
    }

    public Rectangle getBounds(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    public void move(int dx, int dy) {
        bounds.x += dx;
        bounds.y += dy;
        notifyListeners();

    }
    public void modifyWithPoints(Point anchorPoint, Point movingPoint) {
        int x = (anchorPoint.x < movingPoint.x ? anchorPoint.x : movingPoint.x);
        int y = (anchorPoint.y < movingPoint.y ? anchorPoint.y : movingPoint.y);
        int width = Math.abs(anchorPoint.x - movingPoint.x);
        int height = Math.abs(anchorPoint.y - movingPoint.y);
        setBounds(new Rectangle(x, y, width, height));
    }
    public ArrayList<ModelListener> getListeners() {
        return listeners;
    }

    public void addListener(ModelListener l){
        listeners.add(l);
    }

    public void removeListener(ModelListener l){
        listeners.remove(l);
    }
    public void notifyListeners(){
        for(ModelListener listener: listeners){
            listener.modelChanged(DShapeModel.this);
        }
    }
    public void updateRect(){
        shapeRectangle = new Rectangle(x, y, width, height);
    }
    public void setShapeRectangle() {
        shapeRectangle = new Rectangle (x, y, width, height);
    }

}

