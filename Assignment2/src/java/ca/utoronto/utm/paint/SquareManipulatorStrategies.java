package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class SquareManipulatorStrategies implements ShapeManipulatorStrategy{
	Square square; // Square object
	@Override
	/**
	 * Mouse pressed action
	 */
	public void mousePressed(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled) {
		Point corner = new Point(e.getX(), e.getY());
		int length = 0;
		this.square = new Square(corner, length, length, color, thickness, filled);
	}

	@Override
	/**
	 * Mouse dragged action
	 */
	public void mouseDragged(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled) {
		int length = e.getX() - this.square.getDrawCorner().getX();
		int width = e.getY() - this.square.getDrawCorner().getY();
		int x = this.square.getDrawCorner().getX();
		int y = this.square.getDrawCorner().getY();
		int sq_length = Math.max(Math.abs(length), Math.abs(width)); 
		// Change the starting corner when dragged towards a negative direction (up/right)
		this.square.setLength(sq_length);
		if (length < 0 ) {
			x = this.square.getDrawCorner().getX() - sq_length;
		}
		if (width < 0) {
			y = this.square.getDrawCorner().getY() - sq_length;
		}
		Point start_point = new Point(x,y);
		this.square.setCorner(start_point); // New start corner if lengths < 0
		this.square.setThickness(thickness);
		this.square.setFilled(filled);
		model.setShape(this.square);
	}

	@Override
	/**
	 * Mouse released function
	 */
	public void mouseReleased(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled) {
		// TODO Auto-generated method stub
		int length = e.getX() - this.square.getDrawCorner().getX();
		int width = e.getY() - this.square.getDrawCorner().getY();
		int x = this.square.getDrawCorner().getX();
		int y = this.square.getDrawCorner().getY();
		
		int sq_length = Math.max(Math.abs(length), Math.abs(width)); // length of square
		this.square.setLength(sq_length);
		
		// End corner is where the mouse is released, change if lengths is negative
		if (length < 0 ) {
			x = this.square.getDrawCorner().getX() - sq_length;
		}
		if (width < 0) {
			y = this.square.getDrawCorner().getY() - sq_length;
		}
		Point start_point = new Point(x,y);
		this.square.setCorner(start_point); // New start corner if lengths < 0
		model.addShape(this.square);
		this.square = null;
	}

}
