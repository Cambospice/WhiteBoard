package cs151.hw7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class DLineModel extends DShapeModel {

	public DLineModel() {
		super();
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
