package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PolyLineManipulatorStrategies implements ShapeManipulatorStrategy{
	PolyLine polyLine; // Polyline object
	@Override
	/**
	 * Mouse pressed action
	 */
	public void mousePressed(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled) {
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.BUTTON1){ // Left mouse button
			this.polyLine = model.getPolyLine();
			if (this.polyLine == null) {
				ArrayList<Integer> x_points = new ArrayList<>();
				ArrayList<Integer> y_points = new ArrayList<>();
				x_points.add(e.getX());
				y_points.add(e.getY());
				this.polyLine = new PolyLine(x_points, y_points,  color, thickness);
				this.polyLine.setThickness(thickness);
				model.setPolyLine(this.polyLine);
				model.getPolyLine().setColor(color);
				}
			else {
				model.addPolyLinePoint(e.getX(), e.getY());
			}	
		}
		else if (e.getButton() == MouseEvent.BUTTON3) { // Right mouse button
			if (this.polyLine != null) {
				model.addPolyLine(this.polyLine, color);
			}
			this.polyLine = null;
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e, PaintModel model, Color color, int thickness, boolean filled) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * End polyline chain
	 * @param model Paintmodel
	 * @param color Colour of Polyline
	 */
	public void disrupt(PaintModel model, Color color) {
		model.addPolyLine(this.polyLine, model.getPolyLine().getColor());
		this.polyLine = null;
	}
}
