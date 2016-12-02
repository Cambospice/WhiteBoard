package cs151.hw7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class DOvalModel extends DShapeModel {

	public DOvalModel(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
		// TODO Auto-generated constructor stub
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
