package ui.panels.mainPanels;

import java.awt.Color;

import javax.swing.SwingConstants;

import org.springframework.stereotype.Component;

import ui.indicators.WeatherIconIndicator;
import ui.indicators.accuWeather.WeatherTextIndicator;
import ui.indicators.accuWeather.WindDirectionIndicator;
import ui.indicators.accuWeather.WindSpeedIndicator;
import ui.indicators.weather.PressureIndicator;
import ui.labels.TitleLabel;
import ui.labels.UnitLabel;
import ui.layouts.CentrePanelLayout;

@Component
public class CentrePanel extends MainPanels  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8488813971922259532L;
	/**
	 * Constants
	 */
	private static final int WIDTH = 210;
	private static final int HEIGHT = 165;
	private static final String WEATHER_TITLE = "POGODA";
	private static final String WIND_TITLE = "WIATR";
	private static final String PRESSURE_TITLE = "CIŚNIENIE";
	private static final String WIND_SPEED_UNIT = "km/h";
	private static final String PRESSURE_UNIT = "hPa";
	private static final int UNIT_WIDTH = 25;
	private static final int UNIT_HEIGHT = 15;

	public CentrePanel() {
		super(WIDTH, HEIGHT);
		TitleLabel wind = new TitleLabel(WIND_TITLE);
		wind.setHorizontalAlignment(SwingConstants.LEFT);
		TitleLabel pressure = new TitleLabel(PRESSURE_TITLE);
		pressure.setHorizontalAlignment(SwingConstants.LEFT);
		new CentrePanelLayout(this)
			.addTitle(new TitleLabel(WEATHER_TITLE), 0, 0, 4 ,1)
			.addWeatherIcon(WeatherIconIndicator.getInstance().setIconByNumber(00), 0, 1)
			.addWeatherDescription(WeatherTextIndicator.getInstance(), 0, 2)
			.addTitle(wind, 0, 3, 1 ,1)
			.addIndicator(WindSpeedIndicator.getInstance(), 1, 3)
			.addUnit(new UnitLabel(WIND_SPEED_UNIT, UNIT_WIDTH, UNIT_HEIGHT, Color.YELLOW), 2, 3)
			.addIndicator(WindDirectionIndicator.getInstance(),3, 3)
			.addTitle(pressure, 0, 4, 1 ,1)
			.addIndicator(PressureIndicator.getInstance(), 1, 4, 2, 1)
			.addUnit(new UnitLabel(PRESSURE_UNIT, UNIT_WIDTH, UNIT_HEIGHT, Color.YELLOW), 3, 4);
	}
	
	
}
