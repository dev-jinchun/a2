package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class SquiggleManipulatorStrategies implements ShapeManipulatorStrategy{
	Squiggle squiggle; //Squiggle object
	@Override
	/**
	 * Mouse pressed action
	 */
	public void mousePressed(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled) {
		// TODO Auto-generated method stub
		Point point = new Point(e.getX(), e.getY());
		model.setSquiggle(new Squiggle(point, color, thickness, filled));
	}

	@Override
	/**
	 * Mouse dragged function
	 */
	public void mouseDragged(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled) {
		// TODO Auto-generated method stub
		model.getSquiggle().setThickness(thickness);
		model.addSquigglePoint(new Point(e.getX(), e.getY()), color);
		
	}

	@Override
	/**
	 * Mouse released function
	 */
	public void mouseReleased(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled) {
		// TODO Auto-generated method stub
		model.getSquiggle().setThickness(thickness);
		model.getSquiggle().setColor(color);
		model.addSquiggle(color);
		this.squiggle = null;
	}

}
