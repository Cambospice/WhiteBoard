package cs151.hw7;

import java.awt.*;

/**
 * Created by Roshni Velluva Puthanidam on 28/11/16.
 */
public class DRectModel extends DShapeModel {

    public DRectModel() {
        super();
    }
    
    public DRectModel(DShapeModel model){
    	super(model);
    }

    public static void showKnobsIfSelected(Graphics g) {
        if (DShape.isSelected) {
            Rectangle[] knobs = getKnobs();
            for (int i = 0; i < knobs.length; i++) {
                g.setColor(Color.GRAY);
                g.fillRect(knobs[i].x, knobs[i].y, knobs[i].width, knobs[i].height);
            }
        }

    }
}



