package ca.utoronto.utm.paint;

import javax.imageio.ImageIO;
import javax.swing.*;

//import javafx.geometry.Rectangle2D;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Panel displaying the shapes drawn and also where users can draw whatever
 * shapes they like. 
 *
 */
class PaintPanel extends JPanel implements Observer, MouseMotionListener, MouseListener {
	private int i = 0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	private int savedCount = 0;

	private Color color; // Colour of shape
	private ShapeManipulatorStrategy mode; // Current mode
	private int thickness;	// Line thickness
	private boolean filled;	// Shaped filled or not
	
	public PaintPanel(PaintModel model, View view){
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		this.setColor(Color.RED);
		this.model = model;
		this.model.addObserver(this);

		this.view = view;
	}

	/**
	 * View aspect of this
	 */
	public void paintComponent(Graphics g) {
		// Use g to draw on the JPanel, lookup java.awt.Graphics in
		// the javadoc to see more of what this can do for you!!

		super.paintComponent(g); // paint background
		Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
		// Origin is at the top left of the window 50 over, 75 down
		i = i + 1;
		
		// Draw Lines
		LinkedHashMap<Point, Color> pointsMap = this.model.getPoints();
		ArrayList<Point> points = new ArrayList<>(pointsMap.keySet());
		for (int i = 0; i < points.size()-1; i++) {
			Point p1 = points.get(i);
			Point p2 = points.get(i + 1);
			g2d.setColor(pointsMap.get(p1));
			
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
		
		// Draws circle, rectangle, square, squiggle and polyline
		this.model.execute(g2d, this.color);
		
		g2d.dispose();
	}

	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint(); // Schedule a call to paintComponent
	}
	
	/**
	 * Erase all graphical drawings and revert canvas back to blank.
	 */
	public void erase() {
		Graphics g = this.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	/**
	 * Save current drawings as an image png file in the folder where this project is inside of.
	 * @throws IOException
	 */
	public void save() throws IOException{
		BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = image.createGraphics();
		this.printAll(g);
		g.dispose();
        ImageIO.write(image, "png", new File("saved_image"+String.valueOf(savedCount)+".png"));
        savedCount++;
	}
	
	// Undo previous action
	public void undo() {
		this.model.removeShape();
	}
	
	// Redo previous action
	public void redo() {
		this.model.redoShape();
	}

	
	/**
	 *  Controller aspect of this
	 */
	public void setMode(ShapeManipulatorStrategy mode){
		// Keeps previous polyline drawn on canvas if another shape is selected before clicking right mouse button
		if (!(mode instanceof PolyLineManipulatorStrategies) && this.mode instanceof PolyLineManipulatorStrategies 
				&& this.model.getPolyLine() != null) {
			PolyLineManipulatorStrategies plstrategy = (PolyLineManipulatorStrategies)this.mode;
			plstrategy.disrupt(model, color);
		}
		
		this.mode= mode;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setThickness(int thickness)	{
		this.thickness = thickness;
	}
	
	public void setFilled(boolean filled)	{
		this.filled = filled;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(mode != null) {
			mode.mousePressed(e,model, color, thickness, filled);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(mode != null) {
			mode.mouseDragged(e,model, color, thickness, filled);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(mode != null) {
			mode.mouseReleased(e,model, color, thickness, filled);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}
}