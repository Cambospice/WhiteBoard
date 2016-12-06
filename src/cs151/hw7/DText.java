package cs151.hw7;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class DText extends DShape {

	private double startingSize = 1.0;

	public DText() {
		setModel(new DTextModel());
	}

	public DText(DShapeModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font(DTextModel.getFont(), Font.PLAIN, 96);
		g2.setFont(font);
		g2.drawString(DTextModel.getText(), model.getX(), model.getY());

	}
}
