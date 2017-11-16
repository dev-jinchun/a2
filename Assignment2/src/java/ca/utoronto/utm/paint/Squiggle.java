package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;


/**
 * A Squiggle that can be drawn on paint.
 * It can have varying thickness and color.
 */
public class Squiggle extends Shape {
	
	private ArrayList<Point> points; // List of squiggle points 
	
	/**
	 * Constructor of squiggle
	 * @param point Start point for squiggle
	 * @param c Colour of squiggle
	 * @param th Thickness of squiggle
	 * @param f Squiggle fill
	 */
	public Squiggle(Point point, Color c, int th, boolean f) {
		super(c, f, th);
		this.points = new ArrayList<Point>();
		this.points.add(point);

	}

	/**
	 * Add a point for Squiggle
	 * @param p A Point with x,y coordinates
	 */
	public void addPoint(Point p) {
		this.points.add(p);
	}

	public ArrayList<Point> getPoint() {
		return points;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	public int getThickness() {
		return this.thickness;
	}
	
	public void setColor(Color color)	{
		this.color = color;
	}
	
	public Color getColor()	{
		return this.color;
	}
	
	@Override
	/**
	 * Draw squiggle object
	 */
	public void execute(Graphics2D g2d, Color color) {
		ArrayList<Point> pts = this.getPoint();
		g2d.setColor(this.color);
		g2d.setStroke(new BasicStroke(this.thickness));
		for (int i = 1; i < pts.size(); i++) {
			g2d.setStroke(new BasicStroke(this.thickness));
			g2d.drawLine(pts.get(i-1).getX(), pts.get(i-1).getY(), pts.get(i).getX(), pts.get(i).getY());
		}
			
	}

}
