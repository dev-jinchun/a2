package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A Square that can be drawn on paint. It can have varying thickness, color,
 * and either filled or empty.
 */
public class Square extends Rectangle {
	private Point draw_corner; // Draw corner of square

	/**
	 * Constructor of square
	 * @param corner corner which square is drawn
	 * @param length x-axis of square
	 * @param width y-axis of square, equal to length
	 * @param c colour of square
	 * @param th thickness of square
	 * @param f square fill
	 */
	public Square(Point corner, int length, int width, Color c, int th, boolean f) {
		super(corner, length, width, c, th, f);
		this.draw_corner = corner;
		this.setWidth(length);
	}

	@Override
	/**
	 * Draw square object
	 */
	public void execute(Graphics2D g2d, Color color) {
		int x = this.getCorner().getX();
		int y = this.getCorner().getY();
		g2d.setColor(this.color);
		g2d.setStroke(new BasicStroke(this.getThickness()));
		if (this.getFilled()) {
			g2d.fillRect(x, y, this.getLength(), this.getLength());
		}
		g2d.drawRect(x, y, this.getLength(), this.getLength());
	}
}
