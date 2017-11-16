package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;


/**
 * Model for paint program. It contains data of multiple shapes and sizes,
 * colors.
 */
public class PaintModel extends Observable {

	private LinkedHashMap<Point,Color> points=new LinkedHashMap<Point,Color>();
	
	private Squiggle squiggle; // Squiggle object
	private PolyLine polyline; // Polyline object
	private ArrayList<Shape> shapes = new ArrayList<>(); // List of shape objects
	private Shape shape; // Shape object 
	private ArrayList<Shape> undone = new ArrayList<>(); // Keeps copy of action to redo
	private ArrayList<Point> point_list = new ArrayList<>(); // List of points

	/**
	 * Add points with colour to list of points
	 * @param p Points
	 * @param cl Colour of points
	 */
	public void addPoint(Point p, Color cl) {
		this.points.put(p, cl);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Removes polyline section
	 */
	public void removePoint() {
		Point pts = this.polyline.getLastPoint();
		this.polyline.removePoint();
		if (this.polyline.getNumPoints() == 0){
			this.polyline = null;
			this.point_list.clear();
		}
		this.point_list.add(pts);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Removes all shapes from canvas
	 */
	public void eraseAll() {
		this.shapes.clear();
		this.undone.clear();
	}
	
	public LinkedHashMap<Point, Color> getPoints(){
		return points;
	}
	
	/**
	 * Set current shape to last drawn shape
	 * @param s
	 */
	public void setShape(Shape s) {
		this.shape = s;
		this.setChanged();
		this.notifyObservers();
	}
	/**
	 * Remove shape from list
	 */
	public void removeShape() {
		if (this.polyline != null) {
			this.removePoint();
		}
		
		else if (this.shapes.size() != 0) {
			int last = this.shapes.size() - 1;
			Shape s = this.shapes.get(last);
			this.undone.add(s);
			this.shapes.remove(last);
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Redos last action 
	 */
	public void redoShape() {
		if (this.polyline != null && point_list.size() != 0) {
			Point pts = point_list.get(point_list.size() - 1 );
			this.polyline.addPoint(pts.getX(), pts.getY());
			point_list.remove(pts);
		}
		
		else if (this.undone.size() != 0) {
			int last = this.undone.size() - 1;
			Shape s = this.undone.get(last);
			this.shapes.add(s);
			this.undone.remove(last);
		}
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Add shape object to list of shapes
	 * @param s Shape object
	 */
	public void addShape(Shape s) {
		this.shapes.add(s);
		this.shape = null;
		this.undone.clear();
		this.setChanged();
		this.notifyObservers();
	}
	
	public Shape getShape() {
		return this.shape;
	}
	
	/**
	 * Add squiggle object with colour to list of squiggles
	 * @param c  Colour of squiggle    
	 */
	public void addSquiggle(Color c) {
		this.shapes.add(this.squiggle);
		this.squiggle = null;
		this.undone.clear();
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Set squiggle to current squiggle drawn
	 * @param sq Squiggle object
	 */
	public void setSquiggle(Squiggle sq) {
		this.squiggle = sq;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Add points with colour to list of squiggle points
	 * @param p Points in squiggle
	 * @param color Color of points
	 */
	public void addSquigglePoint(Point p, Color color) {
		this.squiggle.addPoint(p);
		this.setChanged();
		this.notifyObservers();
	}

	public Squiggle getSquiggle() {
		return this.squiggle;
	}

	/**
	 * Add polyline with colour to list of polyines
	 * @param pl Polyline object        
	 * @param c Current color         
	 */
	public void addPolyLine(PolyLine pl, Color c) {
		this.shapes.add(this.polyline);
		this.polyline = null;
		this.undone.clear();
		this.point_list.clear();
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Set polyline to current polyline drawn
	 * @param pl Polyline object
	 */
	public void setPolyLine(PolyLine pl) {
		this.polyline = pl;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Add polyline points to respective x and y lists
	 * @param x X-value of polyline 
	 * @param y Y-value of polyline     
	 */
	public void addPolyLinePoint(int x, int y) {
		this.polyline.addPoint(x, y);
		this.point_list.clear();
		this.undone.clear();
		this.setChanged();
		this.notifyObservers();
	}

	public PolyLine getPolyLine() {
		return this.polyline;
	}

	/**
	 * Draws the shapes
	 * @param g2d Graphics 2D 
	 * @param color Colour of shape          
	 */
	public void execute(Graphics2D g2d, Color color) {

		// Draw shapes
		for (Shape s: this.shapes) {
			s.execute(g2d, color);
		}
		
		// Show squiggle while drawn
		if (this.squiggle != null) {
			this.squiggle.execute(g2d, color);
		}
		
		// Show polyline while drawn
		if (this.polyline != null) {
			this.polyline.execute(g2d, color);
		}
		
		// Show shapes (circle, square and rectangle) while drawn
		if (this.shape != null) {
			this.shape.execute(g2d, color);
		}
	}
}
