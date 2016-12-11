package cs151.hw7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;

public class Whiteboard extends JFrame {
	BorderLayout border = new BorderLayout();
	Canvas canvas = new Canvas();
	TableModel tableModel = new TableModel();
	JLabel status;
	private ClientHandler clientHandler;
	private ServerAccepter serverAccepter;
	//ServerButtons serverButtons;

	public Whiteboard() {
		super("Whiteboard");
		this.setLayout(border);
		this.setSize(800, 400);
		Box controls = controls();
		Box openSave=openSave();
		Box color = color();
		Box network=network();
		Box movement = movement();
		Box text = text();
		Box table = table();
		Box vertical = Box.createVerticalBox();
		vertical.add(controls);
		vertical.add(Box.createVerticalStrut(40));
		vertical.add(color);
		vertical.add(Box.createVerticalStrut(40));
		vertical.add(openSave);
		vertical.add(Box.createVerticalStrut(40));
		vertical.add(network);
		vertical.add(Box.createVerticalStrut(40));
		vertical.add(text);
		vertical.add(Box.createVerticalStrut(40));
		vertical.add(movement);
		vertical.add(Box.createVerticalStrut(40));
		vertical.add(table);
		for (Component comp : vertical.getComponents()) {
			((JComponent) comp).setAlignmentX(Box.LEFT_ALIGNMENT);
		}
		this.add(vertical, BorderLayout.WEST);
		this.setVisible(true);
	}

	public Box controls() {
		//ListenForButton listenForButton = new ListenForButton();
		Box b = Box.createHorizontalBox();
		Box vb = Box.createVerticalBox();
		JLabel add=new JLabel();
		add.setText("Add");
		b.add(add);
		JButton rect = new JButton("Rect");
		rect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DRect rect = new DRect();
				canvas.addShape(rect);
				tableModel.addModel(rect.getModel());
				canvas.repaint();
			}
		});
		b.add(rect);
		b.add(Box.createHorizontalStrut(40));
		JButton oval = new JButton("Oval");
		oval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DOval oval = new DOval();
				canvas.addShape(oval);
				tableModel.addModel(oval.getModel());
				canvas.repaint();
			}
		});
		b.add(oval);
		b.add(Box.createHorizontalStrut(40));
		JButton line = new JButton("Line");
		line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DLine line = new DLine();
				canvas.addShape(line);
				tableModel.addModel(line.getModel());
				canvas.repaint();
			}
		});
		b.add(line);
		b.add(Box.createHorizontalStrut(40));


		this.add(canvas, BorderLayout.CENTER);
		return b;
	}
    public Box openSave(){
	    Box b = Box.createHorizontalBox();
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String result = JOptionPane.showInputDialog("File Name", null);
				if (result != null) {
					File f = new File(result);
					save(f);
				}
			}

		});
		b.add(save);

		b.add(Box.createHorizontalStrut(40));
		JButton saveImg = new JButton("Save PNG");
		saveImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String result = JOptionPane.showInputDialog("File Name", null);
				if (result != null) {
					File f = new File(result);
					saveImage(f);
				}
			}
		});
		b.add(saveImg);

		b.add(Box.createHorizontalStrut(40));
		JButton open = new JButton("Open");
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String result = JOptionPane.showInputDialog("File Name", null);
				if (result != null) {
					File f = new File(result);
					open(f);
				}
			}
		});
		b.add(open);
		return b;
    }
	public Box network() {
		Box b = Box.createHorizontalBox();
		JButton button;
		button = new JButton("Start Server");
		b.add(button);
		button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doServer();
			}
		});

		button = new JButton("Start Client");
		b.add(button);
		button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doClient();
			}
		});

		status = new JLabel();
		b.add(status);

		//this.add(canvas, BorderLayout.CENTER);
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
					color.setBackground(c);
				canvas.paintSelected(c);
				canvas.repaint();
			}
		});


		b.add(color);
		return b;
	}
	// Starts the sever accepter to catch incoming client connections.
	// Wired to Server button.
	public void doServer() {
		status.setText("Server Mode");
		String result = JOptionPane.showInputDialog("Run server on port", "39587");
		if (result!=null) {
			System.out.println("server: start");
			serverAccepter = new ServerAccepter(Integer.parseInt(result.trim()));
			serverAccepter.start();
		}
	}
	// Runs a client handler to connect to a server.
	// Wired to Client button.
	public void doClient() {
		status.setText("Client");
		String result = JOptionPane.showInputDialog("Connect to host:port", "127.0.0.1:39587");
		if (result!=null) {
			String[] parts = result.split(":");
			System.out.println("client: start");
			clientHandler = new ClientHandler(parts[0].trim(), Integer.parseInt(parts[1].trim()));
			clientHandler.start();
		}
	}
	public Box movement() {
		Box b = Box.createHorizontalBox();
		JButton moveToFront = new JButton("Move to Front");
		moveToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DShape selected = canvas.moveToFront();
				tableModel.moveToFront(selected.getModel());
				canvas.repaint();
			}
		});
		b.add(moveToFront);
		b.add(Box.createHorizontalStrut(40));
		JButton moveToBack = new JButton("Move to Back");
		moveToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DShape selected = canvas.moveToBack();
				tableModel.moveToBack(selected.getModel());
				canvas.repaint();
			}
		});
		b.add(moveToBack);
		b.add(Box.createHorizontalStrut(40));
		JButton remove = new JButton("Remove");
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.deleteShape();
				canvas.repaint();
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
				if(!text.getText().equals(null)){
					model.setText(text.getText());
				}
				textfile.setModel(model);
				canvas.addShape(textfile);
				tableModel.addModel(textfile.getModel());
				canvas.repaint();
			}
		});
		combinedBox.add(font);
		b.add(text);
		b.add(textButton);
		combinedBox.add(b);
		return combinedBox;
	}

//	public void saveImage(File file) {
//		// Create an image bitmap, same size as ourselves
//		BufferedImage image = (BufferedImage) createImage(getWidth(), getHeight());
//		// Get Graphics pointing to the bitmap, and call paintAll()
//		// This is the RARE case where calling paint() is appropriate
//		// (normally the system calls paint()/paintComponent())
//		Graphics g = image.getGraphics();
//		paintAll(g);
//		g.dispose(); // Good but not required--
//		// dispose() Graphics you create yourself when done with them.
//		try {
//			javax.imageio.ImageIO.write(image, "PNG", file);
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//	}

	public Box table() { // not written
		Box b = Box.createHorizontalBox();
		JTable table = new JTable(tableModel);
		JScrollPane scroller = new JScrollPane(table);
		scroller.setPreferredSize(new Dimension(300,400));
		b.add(scroller);
		return b;
	}
	// Client runs this to handle incoming messages
	// (our client only uses the inputstream of the connection)
	private class ClientHandler extends Thread {
		private String name;
		private int port;

		ClientHandler(String name, int port) {
			this.name = name;
			this.port = port;
		}
		// Connect to the server, loop getting messages
		public void run() {
			try {
				// make connection to the server name/port
				Socket toServer = new Socket(name, port);
				// get input stream to read from server and wrap in object input stream
				ObjectInputStream in = new ObjectInputStream(toServer.getInputStream());
				System.out.println("client: connected!");
				// we could do this if we wanted to write to server in addition
				// to reading
				// out = new ObjectOutputStream(toServer.getOutputStream());
//				while (true) {
//					// Get the xml string, decode to a Message object.
//					// Blocks in readObject(), waiting for server to send something.
//					String xmlString = (String) in.readObject();
//					XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(xmlString.getBytes()));
//					Message message = (Message) decoder.readObject();
//
//					System.out.println("client: read " + message);
//				//	invokeToGUI(message);
//				}
			}
			catch (Exception ex) { // IOException and ClassNotFoundException
				ex.printStackTrace();
			}
			// Could null out client ptr.
			// Note that exception breaks out of the while loop,
			// thus ending the thread.
		}
	}
	// (this and sendToOutputs() are synchronzied to avoid conflicts)
//	public synchronized void addOutput(ObjectOutputStream out) {
//		outputs.add(out);
//	}
//	private java.util.List<ObjectOutputStream> outputs =
//			new ArrayList<ObjectOutputStream>();

	// Server thread accepts incoming client connections
	class ServerAccepter extends Thread {
		private int port;
		ServerAccepter(int port) {
			this.port = port;
		}
		public void run() {
			try {
				ServerSocket serverSocket = new ServerSocket(port);
				while (true) {
					Socket toClient = null;
					// this blocks, waiting for a Socket to the client
					toClient = serverSocket.accept();
					System.out.println("server: got client");
					// Get an output stream to the client, and add it to
					// the list of outputs
					// (our server only uses the output stream of the connection)
					//addOutput(new ObjectOutputStream(toClient.getOutputStream()));
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}



	}
	public void save(File file) {
		try {
			XMLEncoder xmlOut = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
			ArrayList<DShapeModel> shapeModel = new ArrayList<DShapeModel>();
			for(int i = 0; i < canvas.shapes.size(); i++) {
				for(int j = 0; j < canvas.shapes.size(); j++) {
					shapeModel.add(j, canvas.shapes.get(i).model);
				}
			}
			xmlOut.writeObject(shapeModel);
			xmlOut.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void open(File file) {
		ArrayList<DShapeModel> shapeModel;
		try {
			XMLDecoder xmlIn = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
			shapeModel = (ArrayList<DShapeModel>) xmlIn.readObject();
			xmlIn.close();
			canvas.clear();
			for(DShapeModel s: shapeModel) {
				canvas.addShape(s);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveImage(File file) {
		BufferedImage image = (BufferedImage) createImage(getWidth(), getHeight());
		Graphics g = image.getGraphics();
		paintAll(g);
		g.dispose();
		try {
			javax.imageio.ImageIO.write(image, "PNG", file);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Whiteboard w = new Whiteboard();
	}
}