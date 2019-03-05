package ui.labels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public abstract class AbstractLabel extends JLabel {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 6404214869764741262L;

	/*
	 * JLabel without frame
	 */
	public AbstractLabel(String title, int width, int height) {
		this.setText(title);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setPreferredSize(new Dimension(width, height));
	}
	
	/*
	 * JLabel with frame
	 */
	public AbstractLabel(String title, int width, int height, Color bColor, int tickness, boolean isRounded) {
		this(title, width, height);
		this.setBorder(BorderFactory.createLineBorder(bColor, tickness, isRounded));
	}
}
