package ui.indicators;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import ui.labels.AbstractLabel;

public class Indicator extends AbstractLabel {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 563537229811622444L;
	/**
	 * Constants
	 */
	private final static String DEFAULT_TEXT = "0"; 

	public Indicator(int width, int height, Color textColor) {
		super(DEFAULT_TEXT, width, height);
		this.setForeground(textColor);
		this.setFont(getDigitalFont(height));
	}
	
	private Font getDigitalFont(int height) {
		//file exist for path when used on eclipse env
		String filename="src/main/resources/fonts/digital-7.ttf";
		File file = new File(filename);
		Font font;
		try {
			if(file.exists()) {
				font = Font.createFont(Font.TRUETYPE_FONT, file);
			} else {
				InputStream is = getClass().getResourceAsStream("/resources/fonts/digital-7.ttf");
				font = Font.createFont(Font.TRUETYPE_FONT, is);
			}
			font = font.deriveFont(Font.PLAIN,height - 2);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (FontFormatException | IOException e) {
			font = null;
		}
		return font;
		
	}
	
	
}
