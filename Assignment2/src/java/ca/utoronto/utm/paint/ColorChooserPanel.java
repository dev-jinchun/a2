package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * This panel displays a spectrum of selectable colors, which when chosen,
 * allows shapes to be drawn in said color.
 * 
 */
public class ColorChooserPanel extends JPanel implements ActionListener {
	private View view;
	private LinkedHashMap<String, Color> colorMap;

	/**
	 * Constructs a ColorChoosperPanel with spectrum of choosable colors for drawing
	 * shapes. Previously drawn shapes' colors remain same however.
	 * 
	 * @param view
	 *            the graphic user interface that the user interacts with.
	 * 
	 */
	public ColorChooserPanel(View view) {
		this.view = view;
		createColorMap();
		createColorSelector(colorMap);
		this.setMaximumSize(new Dimension(240,30));
	}
	
	/**
	 * Creates a JComboBox that displays spectrum of choosable drawing colors, which
	 * when chosen, sets drawing color to it.
	 * 
	 * @param colorMap
	 *            LinkedHashMap of image files and corresponding colors.
	 */
	private void createColorSelector(LinkedHashMap<String, Color> colorMap) {
		JComboBox colorDisplay = new JComboBox(); //new combobox listing out all colors.
		for (Entry<String, Color> entry: colorMap.entrySet()) {
			Icon icon = new ImageIcon(this.getClass().getResource("/"+entry.getKey()).getPath(), entry.getKey());
			colorDisplay.addItem(icon);
		}
		colorDisplay.setMaximumRowCount(colorDisplay.getModel().getSize());
		add(colorDisplay); 
		colorDisplay.addActionListener(this);
	}
	
	/**
	 * Adds pairs of image file names and corresponding constructed Colors to create
	 * the spectrum of colors displayed.
	 */
	private void createColorMap() {
		colorMap = new LinkedHashMap<>();
		//All color icons and corresponding colors with rgb values.
		colorMap.put("FF0000.png", new Color(255, 0, 0));
		colorMap.put("FF4000.png", new Color(255, 64, 0));
		colorMap.put("FF8000.png", new Color(255, 128, 0));
		colorMap.put("FFBF00.png", new Color(255, 191, 0));
		colorMap.put("FFFF00.png", new Color(255, 255, 0));
		colorMap.put("BFFF00.png", new Color(191, 255, 0));
		colorMap.put("80FF00.png", new Color(128, 255, 0));
		colorMap.put("40FF00.png", new Color(64, 255, 0));
		colorMap.put("00FF00.png", new Color(0, 250, 0));
		colorMap.put("00FF80.png", new Color(0, 255, 128));
		colorMap.put("00FFBF.png", new Color(0, 255, 191));
		colorMap.put("00FFFF.png", new Color(0, 255, 255));
		colorMap.put("00BFFF.png", new Color(0, 191, 255));
		colorMap.put("0080FF.png", new Color(0, 128, 255));
		colorMap.put("0040FF.png", new Color(0, 64, 255));
		colorMap.put("0000FF.png", new Color(0, 0, 255));
		colorMap.put("4000FF.png", new Color(64, 0, 255));
		colorMap.put("8000FF.png", new Color(128, 0, 255));
		colorMap.put("553C6F.png", new Color(85, 60, 111));
		colorMap.put("000000.png", new Color(0, 0, 0));
	}

	@Override
	/**
	 * Once color is chosen, sets drawing color to it. 
	 */
	public void actionPerformed(ActionEvent e) {
		JComboBox comboBox = (JComboBox) e.getSource();
		Color color = colorMap.get(((ImageIcon) comboBox.getSelectedItem()).getDescription());
		this.view.getPaintPanel().setColor(color);
	}
}
