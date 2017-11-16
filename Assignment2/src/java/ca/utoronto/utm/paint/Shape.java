package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.*;

/**
 * Shape class used by circle, rectangle, square, squiggle and polyline
 */
public class Shape implements DrawingCommand{
	
	protected Color color; // Colour of shape
	protected boolean fill; // Shape fill
	protected int thickness; // Thickness of shape
	
	/**
	 * Constructor for shape
	 * @param c Colour of shape
	 * @param f Shape fill
	 * @param th Thickness of shape
	 */
	public Shape(Color c, boolean f, int th) {
		this.color = c;
		this.fill = f;
		this.thickness = th;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Draw the shape
	 * @param g2d Graphics2D
	 * @param c Colour of shape
	 */
	public void execute(Graphics2D g2d, Color c) {
		
	}
}
