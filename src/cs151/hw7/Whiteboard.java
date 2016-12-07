package cs151.hw7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class Whiteboard extends JFrame {
	BorderLayout border = new BorderLayout();
	Canvas canvas = new Canvas();

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
		Box vb = Box.createVerticalBox();
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
		vb.add(line);
		vb.add(b);
		b.add(Box.createHorizontalStrut(40));
		this.add(canvas, BorderLayout.CENTER);
		return b;
	}

	public Box color() {
		Box b = Box.createHorizontalBox();
		JButton color = new JButton("Set Color");

		color.addActionListener(new ActionListener() {
			Color c;

			public void actionPerformed(ActionEvent e) {
				c = JColorChooser.showDialog(null, "Pick a Color", canvas.getForeground());
				if (c != null)
					color.setForeground(c);
			}

			public void mouseClicked(MouseEvent e) {

				canvas.setBackground(c);

				repaint();
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
		Box combinedBox = Box.createVerticalBox();
		b.add(Box.createHorizontalStrut(40));
		JTextField text = new JTextField();
		text.setMaximumSize(new Dimension(400, 500));
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		int dialogIndex = 0;
		for(int i=0; i < fonts.length; i++){
			if(fonts[i].equals("Dialog")){
				dialogIndex = i;
			}
		}
		JComboBox<String> font = new JComboBox<>(fonts);
		font.setSelectedIndex(dialogIndex);
		font.setMaximumSize(new Dimension(100, 100));
		JButton textButton = new JButton("Text");
		textButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DText textfile = new DText();
				DTextModel model = new DTextModel();
				model.setFont((String)font.getSelectedItem());
				if(text.getText().equals(null)){
				model.setText(text.getText());
				}
				textfile.setModel(model);
				canvas.addShape(textfile);
				canvas.repaint();
			}
		});
		combinedBox.add(font);
		b.add(text);
		b.add(textButton);
		combinedBox.add(b);
		return combinedBox;
	}
	
	public void saveImage(File file) {
		// Create an image bitmap, same size as ourselves
		BufferedImage image = (BufferedImage) createImage(getWidth(), getHeight());
		// Get Graphics pointing to the bitmap, and call paintAll()
		// This is the RARE case where calling paint() is appropriate
		// (normally the system calls paint()/paintComponent())
		Graphics g = image.getGraphics();
		paintAll(g);
		g.dispose(); // Good but not required--
		// dispose() Graphics you create yourself when done with them.
		try {
			javax.imageio.ImageIO.write(image, "PNG", file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public JTable table() { // not written
		JTable table = new JTable();
		return table;
	}

	public static void main(String[] args) {
		Whiteboard w = new Whiteboard();
	}
}