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
		this.theFont = defaultFont;
	}
	
	public DTextModel(DShapeModel model){
		super(model);
		theFont = defaultFont;
	}

	
	public void setText(String t) {
		if(t.isEmpty())
		{
			theWord = defaultWord;
		}
		else
		{
		theWord = t;
		}
	}

	public void setFont(String f) {
		theFont = f;
	}


	public String getText() {
		return theWord;
	}

	public String getFont() {
		return theFont;
	}
}