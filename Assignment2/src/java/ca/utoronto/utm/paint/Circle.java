package ca.utoronto.utm.paint;

import java.awt.*;

/**
 * A circle to be drawn.
 *
 */

public class Circle extends Shape {

	private Point centre; // Center of circle
	private int radius; // Distance from center to edge
	private int thickness; // Thickness of circle
	private boolean filled; // Determine if interior of circle is filled
	
	/**
	 * Construct a circle
	 * @param centre integer centre of circle
	 * @param radius integer radius of circle
	 * @param c colour of circle
	 * @param th thickness of circle
	 * @param f circle fill
	 */
	public Circle(Point centre, int radius, Color c, int th, boolean f){
		super(c,f,th);
		this.centre = centre;
		this.radius = radius;
	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	public int getThickness() {
		return this.thickness;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	public boolean getFilled() {
		return this.filled;
	}


	/**
	 * Executes command to represent graphically the drawing of a circle. Elements,
	 * such as color, and thickness are taken into consideration.
	 */
	@Override
	public void execute(Graphics2D g2d, Color color) {
		int x = this.getCentre().getX() - this.getRadius();
		int y = this.getCentre().getY() - this.getRadius();
		g2d.setColor(this.color);
		g2d.setStroke(new BasicStroke(this.thickness));
		if (this.getFilled()) {
			g2d.fillOval(x, y, this.radius * 2, this.radius * 2);
		}
		g2d.drawOval(x, y, this.radius * 2, this.radius * 2);
	}
}
