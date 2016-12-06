package cs151.hw7;

import java.awt.*;

/**
 * Created by Roshni Velluva Puthanidam on 05/12/16.
 */
public class DTextModel extends DShapeModel {
    public DTextModel(){
        super();
    }

    public DTextModel(DTextModel model){
        super(model);
    }
    public static void showKnobsIfSelected(Graphics g) {
        if (DShape.isSelected) {
            for(int i = 0; i < knobs.length; i++) {
                g.setColor(Color.GRAY);
                g.fillRect(knobs[i].x, knobs[i].y, knobs[i].width, knobs[i].height);
            }
        }
    }
}
