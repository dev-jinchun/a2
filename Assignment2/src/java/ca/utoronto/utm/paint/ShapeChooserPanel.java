package ca.utoronto.utm.paint;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Panel with icon buttons indicating drawable shapes listed vertically. Buttons
 * include shapes for circle, rectangle, square, squiggly line and polyline.
 */
class ShapeChooserPanel extends JPanel implements ActionListener {
	private View view; // So we can talk to our parent or other components of the view
	private JButton disabledB; // the disabled button, indicating current drawing mode.

	// Containing icon file names and corresponding command strings.
	private LinkedHashMap<String, String> iconMap;

	/**
	 * A panel on the left of paint which lists various drawing options user can
	 * choose from. Each button (option) is an icon of drawing mode to be selected.
	 * 
	 * @param view
	 *            the view user interacts with
	 */
	public ShapeChooserPanel(View view) {
		createIconMap();
		this.view = view;
		this.setLayout(new GridLayout(5, 1));
		this.createShapeButtons(iconMap);
	}

	/**
	 * Create shape buttons which function as drawing options for user to choose
	 * from.
	 * 
	 * @param iconMap
	 *            LinkedHashMap containing image file names and corresponding
	 *            commands.
	 */
	private void createShapeButtons(LinkedHashMap<String, String> iconMap) {
		for (Map.Entry<String, String> entry : iconMap.entrySet()) {
			String imageFile = entry.getKey(); // Image file name
			String command = entry.getValue(); // Corresponding command string
			// Icon created from image file
			Icon icon = new ImageIcon(this.getClass().getResource("/" + imageFile).getPath());
			JButton button = new JButton(icon);
			button.setActionCommand(command);
			this.add(button);
			button.addActionListener(this);
		}
	}

	/**
	 * Creates LinkedHashMap containg image file names and corresponding commands.
	 */
	private void createIconMap() {
		iconMap = new LinkedHashMap<>();
		iconMap.put("circle.png", ShapeManipulatorStrategy.CIRCLE);
		iconMap.put("rectangle.png", ShapeManipulatorStrategy.RECTANGLE);
		iconMap.put("square.png", ShapeManipulatorStrategy.SQUARE);
		iconMap.put("squiggle.png", ShapeManipulatorStrategy.SQUIGGLE);
		iconMap.put("polyline.png", ShapeManipulatorStrategy.POLYLINE);
	}

	/**
	 * Controller aspect of this, setting paint's drawing mode. Once drawing mode is
	 * selected, that button is disabled.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ShapeManipulatorStrategy mode = new CircleManipulatorStrategies();
		// did this because i can't store a class in button's action command
		if (e.getActionCommand() == ShapeManipulatorStrategy.CIRCLE) {
			mode = new CircleManipulatorStrategies();
		} else if (e.getActionCommand() == ShapeManipulatorStrategy.RECTANGLE) {
			mode = new RectangleManipulatorStrategies();
		} else if (e.getActionCommand() == ShapeManipulatorStrategy.SQUARE) {
			mode = new SquareManipulatorStrategies();
		} else if (e.getActionCommand() == ShapeManipulatorStrategy.SQUIGGLE) {
			mode = new SquiggleManipulatorStrategies();
		} else if (e.getActionCommand() == ShapeManipulatorStrategy.POLYLINE) {
			mode = new PolyLineManipulatorStrategies();
		}

		this.view.getPaintPanel().setMode(mode);
		if (disabledB != null) {
			disabledB.setEnabled(true);
		}
		disabledB = (JButton) e.getSource();
		disabledB.setEnabled(false);
	}
}
