package cs151.hw7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Whiteboard extends JFrame {
	BorderLayout border = new BorderLayout();
	Canvas canvas = new Canvas();
	static JPanel controlPanel;
	static JFrame boardFrame;
	// static Canvas canvas;

	static String item;
	static JTextField text;

	public Whiteboard() {
		super("Whiteboard");
		this.setLayout(border);
		this.setSize(800, 400);
		Box controls = controls();
		Box color = color();
		Box movement = movement();
		Box text = text();
		Box vertical = Box.createVerticalBox();
		vertical.add(controls);
		vertical.add(Box.createVerticalStrut(70));
		vertical.add(color);
		vertical.add(Box.createVerticalStrut(70));
		vertical.add(text);
		vertical.add(Box.createVerticalStrut(70));
		vertical.add(movement);
		vertical.add(Box.createVerticalStrut(70));
		for (Component comp : vertical.getComponents()) {
			((JComponent) comp).setAlignmentX(Box.LEFT_ALIGNMENT);
		}
		this.add(vertical, BorderLayout.WEST);
		this.setVisible(true);
	}

	public Box controls() {
		Box b = Box.createHorizontalBox();
		JButton rect = new JButton("Rect");
		rect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.addShape(new DRect());
				canvas.repaint();
			}
		});
		b.add(rect);
		b.add(Box.createHorizontalStrut(40));
		JButton oval = new JButton("Oval");
		oval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.addShape(new DOval());
				canvas.repaint();
			}
		});
		b.add(oval);
		b.add(Box.createHorizontalStrut(40));
		JButton line = new JButton("Line");
		line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.addShape(new DLine());
				canvas.repaint();
			}
		});
		b.add(line);
		b.add(Box.createHorizontalStrut(40));
		JButton text = new JButton("Text");
		text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.addShape(new DText());
				canvas.repaint();
			}
		});
		b.add(text);
		this.add(canvas, BorderLayout.CENTER);
		return b;
	}

	public Box color() {
		Box b = Box.createHorizontalBox();
		JButton color = new JButton("Set Color");
		color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		b.add(color);
		return b;
	}

	public Box movement() {
		Box b = Box.createHorizontalBox();
		JButton moveToFront = new JButton("Move to Front");
		moveToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		b.add(moveToFront);
		b.add(Box.createHorizontalStrut(40));
		JButton moveToBack = new JButton("Move to Back");
		moveToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		b.add(moveToBack);
		b.add(Box.createHorizontalStrut(40));
		JButton remove = new JButton("Remove");
		moveToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		b.add(remove);
		
		return b;
	}

	public Box text() { // not written
		Box b = Box.createHorizontalBox();
		b.add(Box.createHorizontalStrut(40));
		text = new JTextField();
		text.setMaximumSize(new Dimension(400, 500));
		text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				DTextModel.setText(text.getText());
			}
		});
		b.add(text);
		return b;
	}

	public JTable table() { // not written
		JTable table = new JTable();
		return table;
	}

	public static void main(String[] args) {
		Whiteboard w = new Whiteboard();
	}
}