package ui.panels;

import java.awt.Color;


import org.springframework.stereotype.Component;

import javax.swing.JPanel;

@Component
public abstract class AbstractPanel extends JPanel {
	
	/**
	 * Constants
	 */
	private final static Color BACKGROUND_COLOR = Color.BLACK;

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	public AbstractPanel() {	
		this.setBackground(BACKGROUND_COLOR);
	}
}
