package ca.utoronto.utm.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ShapeEditorPanel where user can choose thickness of shapes and whether they
 * are filled.
 */
public class ShapeEditorPanel extends JPanel implements ActionListener {

	private JLabel thicknessLabel;
	private JTextField thickness;
	private JComboBox filled;
	private View v;

	/**
	 * A panel presenting options for thickness and whether drawing should be filled
	 * or not.
	 * 
	 * @param v
	 *            view that the user interacts with.
	 */
	public ShapeEditorPanel(View v) {
		this.v = v;

		thicknessLabel = new JLabel("Line Size");
		this.add(this.thicknessLabel);

		this.thickness = new JTextField(2);
		this.thickness.setText("1");
		this.add(this.thickness);
		this.thickness.addActionListener(this);

		String filledTypes[] = { "Outline", "Filled" };
		filled = new JComboBox(filledTypes);
		this.add(this.filled);
		this.filled.addActionListener(this);
		this.setMaximumSize(new Dimension(230, 50));
	}

	/**
	 * Sets drawing mode to what the user choosses to be through this panel
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		v.getPaintPanel().setThickness(Integer.parseInt(this.thickness.getText()));

		if (e.getActionCommand() == "comboBoxChanged") {
			JComboBox cb = (JComboBox) e.getSource();
			v.getPaintPanel().setFilled(cb.getSelectedItem() == "Filled");
		}

	}
}
