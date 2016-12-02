package cs151.hw7;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Canvas extends JPanel{
	ArrayList<DShape> shapes;
	
	public Canvas() {
		super();
		this.setPreferredSize(new Dimension(400,400));
		this.setBackground(Color.WHITE);
		shapes = new ArrayList<>();
	}
	
	public void addShape(DShape shape){
		addShape(shape.getModel());
	}
	
	public void addShape(DShapeModel shape){
		if (shape instanceof DRectModel) {
			DRectModel rectModel = (DRectModel) shape;
			DRect rect = new DRect(rectModel);
			shapes.add(rect);
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(DShape shape: shapes){
			if (shape instanceof DRect){
				DRect rect = (DRect)shape;
				rect.draw(g);
			}
		}
	}
	
}
