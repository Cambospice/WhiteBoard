package cs151.hw7;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

public class Canvas extends JPanel{
	ArrayList<DShape> shapes;
	DShape selected;
	public Canvas() {
		super();
		this.setPreferredSize(new Dimension(400,400));
		this.setBackground(Color.WHITE);
		shapes = new ArrayList<>();
		this.addMouseListener(new MouseAdapter(){
			
			public void mousePressed(MouseEvent e){
				int x = e.getX();
				int y = e.getY();
				selected = null;
				for(DShape shape : shapes){
					if(shape.getModel().getBounds().contains(new Point(x,y))){
						selected = shape;
						
						
					}
				}
			}
		});
		this.addMouseMotionListener(new MouseAdapter(){
		
			public void mousePressed(MouseEvent e){
				int x = e.getX();
				int y = e.getY();
				selected = null;
				System.out.println(x);
				for(DShape shape : shapes){
					if(shape.getModel().getBounds().contains(new Point(x,y))){
						selected = shape;
						
					}
				}
			}
			public void mouseDragged(MouseEvent e){
				if(selected != null){
				int x = e.getX();
				int y = e.getY();
				selected.getModel().setX(x);
				selected.getModel().setY(y);
				repaint();
				}
			}
		});
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
		else if(shape instanceof DOvalModel){
			DOvalModel ovalModel = (DOvalModel) shape;
			DOval oval = new DOval(ovalModel);
			shapes.add(oval);
		}
		else if(shape instanceof DLineModel){
			DLineModel lineModel = (DLineModel) shape;
			DLine line = new DLine(lineModel);
			shapes.add(line);
		}
		else if(shape instanceof DTextModel){
			DTextModel textModel = (DTextModel) shape;
			DText text = new DText(textModel);
			shapes.add(text);
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(DShape shape: shapes){
			if (shape instanceof DRect){
				DRect rect = (DRect)shape;
				rect.draw(g);
			}
			else if(shape instanceof DOval){
				DOval oval = (DOval)shape;
				oval.draw(g);
			}
			else if(shape instanceof DLine){
				DLine line= (DLine) shape;
				line.draw(g);

			}
			else if(shape instanceof DText){
				DText text= (DText) shape;
				text.draw(g);

			}
		}
	}
	
}
