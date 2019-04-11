package ui.panels.mainPanels;

import java.awt.Color;

import ui.indicators.airpolution.CAQiIndicator;
import ui.indicators.airpolution.PM10Percent_Indicator;
import ui.indicators.airpolution.PM10_Indicator;
import ui.indicators.airpolution.PM1_Indicator;
import ui.indicators.airpolution.PM25Percent_Indicator;
import ui.indicators.airpolution.PM25_Indicator;
import ui.indicators.sun.DayLengthIndicator;
import ui.indicators.sun.SunRiseIndicator;
import ui.indicators.sun.SunSetIndicator;
import ui.labels.TitleLabel;
import ui.labels.UnitLabel;
import ui.layouts.NorthPanelLayout;

public class NorthPanel extends  MainPanels  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3697365703039000L;
	/**
	 * Constants
	 */
	private static final int WIDTH = 480;
	private static final int HEIGHT = 95;
	private static final int UNIT_LABEL_WIDTH = 18;
	private static final int UNIT_LABEL_HEIGHT = 18;
	private static final int SUN_WIDTH = 20;
	private static final int SUN_HEIGHT = 25;
	private static final int UNIT_WIDTH = 17;
	private static final int UNIT_HEIGHT = 14;
	private static final String CAQI_LABEL_TITLE = "CAQI";
	private static final String PM1_LABEL_TITLE = "PM1";
	private static final String PM10_LABEL_TITLE = "PM10";
	private static final String PM25_LABEL_TITLE = "PM25";
	private static final String SUN_LABEL_TITLE = "SUN";
	private final static String COLOR_HEX = "#FFFF00";
	private final static Color COLOR_LABEL = Color.GREEN;
	private final static String PM_UNIT = "<html><body><sup>&#181g/m³&#8663</sup><sub>%&#8680</sub></body></html>"; 
	private final static String SUN_RISE = "<html><body>&#8599</body></html>";
	private final static String SUN_SET = "<html><body>&#8600</body></html>";

	public NorthPanel() {
		super(WIDTH, HEIGHT);
		
		final String pmUnit = new StringBuilder(PM_UNIT).toString();
		final String sunRise = new StringBuilder(SUN_RISE).toString();
		final String sunSet = new StringBuilder(SUN_SET).toString();
		
		new NorthPanelLayout(this)
			.addCaqiTitle(new TitleLabel(CAQI_LABEL_TITLE))
			.addCaqiIndicator(CAQiIndicator.getInstance())
			.addPmTitle(new UnitLabel(PM1_LABEL_TITLE, UNIT_LABEL_WIDTH, UNIT_LABEL_HEIGHT, COLOR_LABEL), 1, 0)
			.addPMIndicator(PM1_Indicator.getInstance(), 1, 1)
			.addUnit(new UnitLabel(pmUnit, UNIT_WIDTH, UNIT_HEIGHT, Color.decode(COLOR_HEX)), 1, 2)
			.addPmTitle(new UnitLabel(PM10_LABEL_TITLE, UNIT_LABEL_WIDTH, UNIT_LABEL_HEIGHT, COLOR_LABEL), 2, 0)
			.addPMIndicator(PM10_Indicator.getInstance(), 2, 1)
			.addPMIndicator(PM10Percent_Indicator.getInstance(), 2, 2)
			.addPmTitle(new UnitLabel(PM25_LABEL_TITLE, UNIT_LABEL_WIDTH, UNIT_LABEL_HEIGHT, COLOR_LABEL), 3, 0)
			.addPMIndicator(PM25_Indicator.getInstance(), 3, 1)
			.addPMIndicator(PM25Percent_Indicator.getInstance(), 3, 2)
			.addSunTitle(new TitleLabel(SUN_LABEL_TITLE), 4, 0)
			.addSunIndicator(SunRiseIndicator.getInstance(), 4, 1)
			.addSunIndicator(SunSetIndicator.getInstance(), 6, 1)
			.addUnit(new UnitLabel(sunRise, SUN_WIDTH, SUN_HEIGHT, Color.GREEN), 5, 1)
			.addUnit(new UnitLabel(sunSet, SUN_WIDTH, SUN_HEIGHT, Color.GREEN), 7, 1)
			.addDayLengthIndicator(DayLengthIndicator.getInstance(), 4, 2);
			
	}
}
