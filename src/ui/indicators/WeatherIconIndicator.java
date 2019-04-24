package ui.indicators;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ui.indicators.sun.SunRiseIndicator;

public class WeatherIconIndicator extends JLabel {

	/**
	 * Generated Serial UID
	 */
	private static final long serialVersionUID = -708498479326343309L;
	
	/**
	 * Constants
	 */
	private static final String RESOURCE_PATH = "src/resources/weather_icons/";
	private static final String GIF_NAME = ".gif";
	
	private static WeatherIconIndicator instance = null;

	private WeatherIconIndicator() {
		super();
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setVerticalAlignment(SwingConstants.CENTER);
	}
	
	public static WeatherIconIndicator getInstance() {
		if (instance == null) {
			instance = new WeatherIconIndicator();
		}
		return instance;
	}
	
	public WeatherIconIndicator setIconByNumber(int fileName) {
		//file exist for path when used on eclipse env
		File file = new File(RESOURCE_PATH + String.valueOf(fileName) + GIF_NAME);
		Icon icon = new ImageIcon(file.getPath());
		this.setIcon(icon);
		return this;
	}
}
