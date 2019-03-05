package ui.labels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;

public class UnitLabel extends AbstractLabel {
	
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -4127356065320133767L;

	public UnitLabel(String text, int width, int height, Color fontColor) {
		super(text, width, height);
		this.setForeground(fontColor);
		this.setFont(new Font(Font.DIALOG, Font.PLAIN, height - 2));
		this.setVerticalAlignment(SwingConstants.TOP);
	}
}
