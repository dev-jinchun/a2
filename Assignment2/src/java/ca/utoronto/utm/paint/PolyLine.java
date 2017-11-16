package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;


/**
 * A polyline that can be drawn on paint. 
 */
public class PolyLine extends Shape{

	private ArrayList<Integer> x_List; // List of x-coordinates
	private ArrayList<Integer> y_List; // List of y-coordinates
	private int points; // Endpoints that make up polyine
	private int thickness; // Thickness of polyline
	private Color color;

	/**
	 * Constructor for polyline
	 * @param x List of x-coordinates
	 * @param y List of y-coordinates
	 * @param c Colour of polyline
	 * @param th Thickness of polyline
	 */
	public PolyLine(ArrayList<Integer> x, ArrayList<Integer> y, Color c, int th) {
		super(c, false, th);
		this.x_List = x;
		this.y_List = y;
		this.points = x_List.size(); // y_list.size() == x_list.size()
	}
	
	public int getNumPoints() {
		return this.points;
	}
	
	public int[] getXValues() {
		int[] x_vals = new int[points];
		for (int i = 0; i < x_List.size(); i++) {
			x_vals[i] = x_List.get(i);
		}
		return x_vals;
	}
	
	public int[] getYValues() {
		int[] y_vals = new int[points];
		for (int i = 0; i < y_List.size(); i++) {
			y_vals[i] = y_List.get(i);
		}
		return y_vals;
	}
	
	/**
	 * Add point to x and y lists
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public void addPoint(int x, int y) {
		this.x_List.add(x);
		this.y_List.add(y);
		points += 1;
	}

	public void setThickness(int thickness)	{
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
	
	/**
	 * Remove last point of the polyline
	 */
	public void removePoint() {
		int last = x_List.size() - 1;
		x_List.remove(last);
		y_List.remove(last);
		points -= 1;
	}
	
	public Point getLastPoint() {
		int last = x_List.size() - 1;
		return (new Point(this.x_List.get(last), this.y_List.get(last)));
	}
	
	@Override
	/**
	 * Draw polyine object
	 */
	public void execute(Graphics2D g2d, Color color) {
		int[] x = this.getXValues();
		int[] y = this.getYValues();
		g2d.setColor(this.color);
		g2d.setStroke(new BasicStroke(this.thickness));
		g2d.drawPolyline(x, y, x.length);
	}

}

