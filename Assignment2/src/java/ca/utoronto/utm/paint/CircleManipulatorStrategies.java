package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class CircleManipulatorStrategies implements ShapeManipulatorStrategy{
	Circle circle; // A circle object
	
	@Override
	/**
	 * Mouse pressed action
	 */
	public void mousePressed(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled) {
		Point centre = new Point(e.getX(),e.getY());
		this.circle = new Circle(centre,0, color, thickness, filled);
	}
	@Override
	/**
	 * Mouse dragged action
	 */
	public void mouseDragged(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled) {
		int xDistance = this.circle.getCentre().getX() - e.getX();
		int yDistance = this.circle.getCentre().getY() - e.getY();
		int radius = (int) Math.sqrt((Math.pow(xDistance,2) + Math.pow(yDistance,2))); // r^2 = x^2 + y^2
		this.circle.setRadius(radius);
		this.circle.setThickness(thickness);
		this.circle.setFilled(filled);
		model.setShape(this.circle);
	}
	@Override
	/**
	 * Mouse released action
	 */
	public void mouseReleased(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled) {
		int xDistance = this.circle.getCentre().getX() - e.getX();
		int yDistance = this.circle.getCentre().getY() - e.getY();
		int radius = (int) Math.sqrt((Math.pow(xDistance,2) + Math.pow(yDistance,2))); // r^2 = x^2 + y^2
		this.circle.setRadius(radius);
		this.circle.setThickness(thickness);
		this.circle.setFilled(filled);
		model.addShape(this.circle);
		this.circle = null;
	}
}
