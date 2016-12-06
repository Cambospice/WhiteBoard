package cs151.hw7;
import javafx.scene.shape.*;

import java.awt.*;
/**
 * Created by Roshni Velluva Puthanidam on 05/12/16.
 */
public class DText extends DShape{
    public DText(){
            setModel(new DTextModel());
        }

    public DText(DTextModel model){
            super(model);
        }

    public void draw(Graphics g) {

        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.PLAIN, 96);
        g2.drawString(Whiteboard.text.getText(), model.getX() ,model.getY() + model.getHeight());
        g2.setFont(font);
    }
}
