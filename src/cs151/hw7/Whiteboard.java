package cs151.hw7;

import java.awt.*;

import javax.swing.JFrame;

public class Whiteboard extends JFrame{
	BorderLayout border = new BorderLayout();
	public Whiteboard(){
		super("Whiteboard");
		this.setLayout(border);
		this.add(new Canvas(),BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		Canvas c = new Canvas();
	}
}
