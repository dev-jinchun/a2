package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A rectangle that can be drawn in paint. 
 * Every rectangle can have  various size, thickness, and color. 
 */

public class Rectangle extends Shape {
	private Point corner; // Initial corner of rectangle
	private int length; // X-axis of rectangle
	private int width; // Y-axis of rectangle
	private int thickness; // Line thickness
	private boolean filled; // Determine if interior of rectangle is filled
	private Point draw_corner; // Draw corner of rectangle
	
	/**
	 Constructor for rectangle
	 * @param corner corner which rectangle is drawn from
	 * @param length x_axis of rectangle
	 * @param width y_axis of rectangle
	 * @param c colour of rectangle
	 * @param th thickness of rectangle
	 * @param f rectangle fill
	 */
	public Rectangle(Point corner, int length, int width, Color c, int th, boolean f) {
		super(c,f,th);
		this.corner = corner;
		this.length = length;
		this.width = width;
		this.draw_corner = corner;
	}
	
	public Point getCorner() {
		return this.corner;
	}
	
	public void setCorner(Point corner) {
		this.corner = corner;
	}
	
	public void setLength(int l) {
		this.length = l;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setWidth(int w) {
		this.width = w;
	}
	
	public int getWidth() {
		return width;
	}
	public void setThickness(int thickness)	{
		this.thickness = thickness;
	}
	
	public int getThickness() {
		return this.thickness;
	}
	
	public void setFilled(boolean filled)	{
		this.filled = filled;
	}
	
	public boolean getFilled()	{
		return this.filled;
	}
	
	public Point getDrawCorner() {
		return this.draw_corner;
	}
	
	@Override
	/**
	 * Draw the rectangle object
	 */
	public void execute(Graphics2D g2d, Color color) {
		int x = this.getCorner().getX(); 
		int y = this.getCorner().getY();
		g2d.setColor(this.color);
		g2d.setStroke(new BasicStroke(this.thickness));
		if(this.getFilled()) {
			g2d.fillRect(x, y, this.length, this.width);
		}
		g2d.drawRect(x, y, this.length, this.width);
	}
}

