package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;

/**
 * Strategy pattern for drawing shapes
 */
public interface ShapeManipulatorStrategy {
	public static final String CIRCLE= "Circle";
	public static final String RECTANGLE= "Rectangle";
	public static final String SQUARE= "SQUARE";
	public static final String SQUIGGLE= "Squiggle";
	public static final String POLYLINE= "PolyLine";

	public void mousePressed(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled);
	public void mouseDragged(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled);
	public void mouseReleased(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled);
}