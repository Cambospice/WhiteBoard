package cs151.hw7;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import javax.swing.JApplet;
import javax.swing.JFrame;

public class DLine extends DShape {
	
	public DLine(){
		setModel(new DLineModel());
	}
	
	public DLine(DShapeModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(Color.gray);
		g2.setColor(model.getColor());
		g2.drawLine(model.getX(), model.getY(), model.getX() + model.getHeight(), model.getY() + model.getHeight());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		//DLineModel.showKnobsIfSelected(g2);
	}
}
