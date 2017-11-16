package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class RectangleManipulatorStrategies implements ShapeManipulatorStrategy{
	Rectangle rectangle; // Rectangle object
	
	@Override
	/**
	 * Mouse pressed action
	 */
	public void mousePressed(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled) {
		Point corner = new Point(e.getX(),e.getY());
		this.rectangle = new Rectangle(corner, 0, 0, color, thickness, filled);
	}

	@Override
	/**
	 * Mouse dragged action
	 */
	public void mouseDragged(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled) {
		int length = e.getX()-this.rectangle.getDrawCorner().getX();
		int width = e.getY() - this.rectangle.getDrawCorner().getY();
		int x = this.rectangle.getDrawCorner().getX(); 
		int y = this.rectangle.getDrawCorner().getY();
		int length2 = Math.abs(length); 
		int width2 = Math.abs(width); 
		this.rectangle.setLength(length2);
		this.rectangle.setWidth(width2);
		// Change the starting corner when dragged towards a negative direction (up/right)
		if (length < 0 ) {
			x = this.rectangle.getDrawCorner().getX() - length2;
		}
		if (width < 0) {
			y = this.rectangle.getDrawCorner().getY() - width2;
		}
		Point start_point = new Point(x,y);
		this.rectangle.setCorner(start_point); // New start corner if length/width < 0
		this.rectangle.setThickness(thickness);
		this.rectangle.setFilled(filled);
		model.setShape(this.rectangle);
	}

	@Override
	/**
	 * Mouse released action
	 */
	public void mouseReleased(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled) {
		int length = e.getX()-this.rectangle.getDrawCorner().getX();
		int width = e.getY() - this.rectangle.getDrawCorner().getY();
		int x = this.rectangle.getDrawCorner().getX(); 
		int y = this.rectangle.getDrawCorner().getY();
		int length2 = Math.abs(length); // length of rectangle
		int width2 = Math.abs(width); // width of rectangle
		this.rectangle.setLength(length2);
		this.rectangle.setWidth(width2);
		
		// End corner is where the mouse is released, change if length/width is negative
		if (length < 0 ) {
			x = this.rectangle.getDrawCorner().getX() - length2;
		}
		if (width < 0) {
			y = this.rectangle.getDrawCorner().getY() - width2;
		}
		Point start_point = new Point(x,y);
		this.rectangle.setCorner(start_point); // New start corner if length/width < 0
		this.rectangle.setThickness(thickness);
		this.rectangle.setFilled(filled);
		model.addShape(this.rectangle);
		this.rectangle = null;
	}

}
