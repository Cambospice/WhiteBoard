package cs151.hw7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class DTextModel extends DShapeModel {
	private String defaultWord = "Hello";
	private String defaultFont = "Dialog";
	private String theWord;
	private String theFont;
 	public DTextModel() {
		super();
		theWord = defaultWord;
		theFont = defaultFont;
	}
 	
 	public String getText()
 	{
 		return theWord;
 	}
 	
 	public String getFont()
 	{
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