package cs151.hw7;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
		Dragger drag = new Dragger();
		this.addMouseListener(drag);
		this.addMouseMotionListener(drag);
	}
			
	public void addShape(DShape shape){
		addShape(shape.getModel());
	}

	public void deleteShape(){
		shapes.remove((DShape)selected);
		selected = null;
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
	
	public void paintSelected(Color c){
		selected.getModel().setColor(c);
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
		if(selected != null){
			Point[] knobs = selected.getKnobs();
			for(int i=0; i < knobs.length; i++){
				g.setColor(Color.BLACK);
				g.fillRect((int)knobs[i].getX()-4,(int)knobs[i].getY()-4,9,9);
			}
		}
	}
	
	public DShape moveToBack(){
		shapes.remove(selected);
		shapes.add(shapes.size()-1, selected);
		return selected;
	}
	
	public DShape moveToFront(){
		shapes.remove(selected);
		shapes.add(selected);
		return selected;
	}
	
	private class Dragger implements MouseListener, MouseMotionListener {
		int distX;
		int distY;
		public void mousePressed(MouseEvent e){
			int x = e.getX();
			int y = e.getY();
			selected = null;
			for(DShape shape : shapes){
				if(shape.getModel().getBounds().contains(new Point(x,y))){
					selected = shape;
					distX = e.getX() - shape.getModel().getX(); 
					distY = e.getY() - shape.getModel().getY();
				}
			} 
			repaint();
		}
		public void mouseDragged(MouseEvent e){
			if(selected != null){
			int x = e.getX() - distX;
			int y = e.getY() - distY;
			selected.getModel().setX(x);
			selected.getModel().setY(y);
			repaint();
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseReleased(MouseEvent e) {
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
