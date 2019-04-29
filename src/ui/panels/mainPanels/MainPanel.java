package ui.panels.mainPanels;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class MainPanel extends JPanel {

	/**
	 * Generated serial UID
	 */
	private static final long serialVersionUID = 8696127545019426087L;
	
	/**
	 * Constants
	 */
	private final static int HEIGHT_SIZE = 320;
	private final static int WIDTH_SIZE = 450;
	private final static Color BACK_COLOR = Color.BLACK;
	
	public MainPanel() {
		this.setSize(WIDTH_SIZE, HEIGHT_SIZE);
		this.setBackground(BACK_COLOR);
		this.setLayout(new BorderLayout());
		this.add(new CentrePanel(), BorderLayout.CENTER);
		this.add(new LeftPanel(), BorderLayout.WEST);		
		this.add(new RightPanel(), BorderLayout.EAST);
		this.add(new NorthPanel(), BorderLayout.NORTH);
		this.add(new SouthPanel(), BorderLayout.SOUTH);
	}
 
}
