package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;

// Draw command
public interface DrawingCommand {
	
	/**
	 * Draws each shape
	 * @param g2d graphics2D
	 * @param color color of shape drawn
	 */
	public abstract void execute(Graphics2D g2d, Color color);
}
