package ui.labels;

import java.awt.Color;
import java.awt.Font;

public class TitleLabel extends AbstractLabel {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 6770778380484862452L;
	
	/**
	 * Constants
	 */
	private static final int WIDTH = 80;
	private static final int HEIGHT = 27;
	private static final Color FONT_COLOR = Color.GREEN;

	public TitleLabel(String title) {
		super(title, WIDTH, HEIGHT);
		this.setFont(new Font(Font.DIALOG, Font.PLAIN, HEIGHT - 10));
		this.setForeground(FONT_COLOR);
		
	}
}
