package ui.indicators;

import java.io.File;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WeatherIconIndicator extends JLabel {
	
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());

	/**
	 * Generated Serial UID
	 */
	private static final long serialVersionUID = -708498479326343309L;
	
	/**
	 * Constants
	 */
	private static final String RESOURCE_PATH = "src/resources/weather_icons/";
	private static final String ALTERNATIVE_PATH = "/resources/weather_icons/";
	private static final String GIF_NAME = ".GIF";
	
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
		File file = new File(String.format("%s%02d%s", RESOURCE_PATH, fileName, GIF_NAME));
		String path;
		Icon icon;
		if(file.exists()) {
			path = file.getPath();
			icon = new ImageIcon(path);
		} else {
			path = String.format("%s%02d%s", ALTERNATIVE_PATH, fileName, GIF_NAME);
			URL url = getClass().getResource(String.format("%s%02d%s", ALTERNATIVE_PATH, fileName, GIF_NAME));
			icon = new ImageIcon(url);
		}
		
		logger.debug("Open weather icon located in:" + path);
		
		this.setIcon(icon);
		return this;
	}
}
