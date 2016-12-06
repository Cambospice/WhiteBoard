package cs151.hw7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class DTextModel extends DShapeModel {
	private String defaultWord = "Hello";
	private String defaultFont = "Dialog";
	private static String theWord;
	private static String theFont;

	public DTextModel() {
		super();
		theWord = defaultWord;
		theFont = defaultFont;
	}

	public void setText(String t) {
		theWord = t;
	}

	public void setFont(String f) {
		theFont = f;
	}

	public static String getText() {
		return theWord;
	}

	public static String getFont() {
		return theFont;
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