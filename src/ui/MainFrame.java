package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import org.springframework.stereotype.Component;

import ui.panels.mainPanels.MainPanel;

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
		
		MainTabbedPanel.getInstance().addTab("Pogoda", new MainPanel());
		
		MainTabbedPanel.getInstance().addTab("Testowy", new JPanel()
				.add(new JButton("Przycisk Tstowy")));
		this.add(MainTabbedPanel.getInstance());
	}
}
