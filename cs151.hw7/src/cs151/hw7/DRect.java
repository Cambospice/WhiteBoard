package cs151.hw7;

/**
 * Created by Roshni Velluva Puthanidam on 28/11/16.
 */
import java.awt.*;

public class DRect {

    DShapeModel model;

    public void draw(Graphics g) {
        g.setColor(model.getColor());
        g.fillRect(model.getX(), model.getY(), model.getWidth(), model.getHeight());
        DRectModel.showKnobsIfSelected(g);
    }

}