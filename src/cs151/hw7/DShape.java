package cs151.hw7;

import java.awt.*;

import javax.swing.JComponent;
/**
 * Created by Roshni Velluva Puthanidam on 28/11/16.
 */
public class DShape {

    public DShapeModel model;
    protected boolean needsRecomputeKnobs;
    public static final int KNOB_SIZE = 9;
    public static boolean isSelected = false;



    public DShape(){
    	model = new DShapeModel();
    }
    
    public DShape(DShapeModel model) {
        this.model = model;
    }

    public void setModel(DShapeModel model) {
        this.model = model;
    }

    public DShapeModel getModel() {
        return this.model;
    }
    public void move(int dx, int dy) {
        needsRecomputeKnobs = true;
        model.move(dx, dy);
    }

    public void modifyShapeWithPoints(Point anchorPoint, Point movingPoint) {
        needsRecomputeKnobs = true;
        model.modifyWithPoints(anchorPoint, movingPoint);
    }



    public Point[] getKnobs(){
    	Point[] knobs = new Point[4];
    	knobs[0] = new Point(getModel().getX(),getModel().getY());
    	knobs[1] = new Point(getModel().getX()+getModel().getWidth(),getModel().getY());
    	knobs[2] = new Point(getModel().getX(), getModel().getY()+getModel().getHeight());
    	knobs[3] = new Point(getModel().getX()+getModel().getWidth(), getModel().getY()+getModel().getHeight());
    	return knobs;
    }
    
    
}