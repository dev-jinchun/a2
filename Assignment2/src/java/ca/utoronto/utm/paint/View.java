package ca.utoronto.utm.paint;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.undo.*;
/**
 * This is the top level View+Controller, it contains other aspects of the View+Controller.
 */
public class View extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private PaintModel model;
	
	// The components that make this up
	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	private ColorChooserPanel colorChooserPanel;
	private ShapeEditorPanel shapeEditorPanel;
	
	
	public View(PaintModel model) {
		super("Paint"); // set the title and do other JFrame init
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(createMenuBar());
		
		Container c=this.getContentPane();
		this.shapeChooserPanel = new ShapeChooserPanel(this);
		this.colorChooserPanel = new ColorChooserPanel(this);
		this.model=model;
		this.paintPanel = new PaintPanel(model, this);
		this.shapeEditorPanel = new ShapeEditorPanel(this);
		c.add(this.paintPanel, BorderLayout.CENTER);
		
		Container leftContainer = new Container();
		leftContainer.setLayout(new BoxLayout(leftContainer, getDefaultCloseOperation()));
		leftContainer.add(this.colorChooserPanel);
		leftContainer.add(this.shapeEditorPanel);
		leftContainer.add(this.shapeChooserPanel);
		
		c.add(leftContainer, BorderLayout.WEST);
		
		this.pack();
		this.setSize(1500,1000);
		this.setVisible(true);
	}

	public PaintPanel getPaintPanel(){
		return paintPanel;
	}
	
	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}

	/**
	 * Creates the menu bar on the top, which includes features like new, open, 
	 * save, exit, etc.
	 * @return
	 */
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;

		menu = new JMenu("File");

		// a group of JMenuItems
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("Edit");

		// a group of JMenuItems
		menuItem = new JMenuItem("Cut");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Copy");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Paste");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Undo");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Redo");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		return menuBar;
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command=="New") {
			paintPanel.erase();
			model.eraseAll();
		} else if (command=="Exit") {
			System.exit(0);
		} else if (command=="Save") {
			try {
				paintPanel.save();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if (command=="Undo") {
			paintPanel.undo();
		}
		
		else if (command=="Redo") {
			paintPanel.redo();
		}
	}
}
