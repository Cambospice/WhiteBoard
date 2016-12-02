package cs151.hw7;

import java.awt.Graphics;

public class DLine extends DShape {

	public DLine(DShapeModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics g) {
		g.setColor(model.getColor());
		g.drawLine(model.getX(), model.getY(), (model.getX() + model.getWidth()), (model.getY() + model.getHeight()));
		DLineModel.showKnobsIfSelected(g);
	}
}
