package ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import org.springframework.stereotype.Component;

import ui.panels.mainPanels.CentrePanel;
import ui.panels.mainPanels.LeftPanel;
import ui.panels.mainPanels.NorthPanel;
import ui.panels.mainPanels.RightPanel;
import ui.panels.mainPanels.SouthPanel;

@Component
public class MainFrame extends JFrame {
	
	/**
	 * Serial version ID - default
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constants
	 */
	private final static int HEIGHT_SIZE = 320;
	private final static int WIDTH_SIZE = 480;
	private final static Color BACK_COLOR = Color.BLACK;

	public MainFrame () {
		this.setUndecorated(true);
		this.setSize(WIDTH_SIZE, HEIGHT_SIZE);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(BACK_COLOR);
		this.add(new CentrePanel(), BorderLayout.CENTER);
		this.add(new LeftPanel(), BorderLayout.WEST);		
		this.add(new RightPanel(), BorderLayout.EAST);
		this.add(new NorthPanel(), BorderLayout.NORTH);
		this.add(new SouthPanel(), BorderLayout.SOUTH);
	}
}
