package ui.panels.mainPanels;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.springframework.stereotype.Component;

import ui.indicators.airpolution.CAQiIndicator;
import ui.indicators.airpolution.PM10Percent_Indicator;
import ui.indicators.airpolution.PM10_Indicator;
import ui.indicators.airpolution.PM1_Indicator;
import ui.indicators.airpolution.PM25Percent_Indicator;
import ui.indicators.airpolution.PM25_Indicator;
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
	private static final int HEIGHT = 180;
	private static final int UNIT_LABEL_WIDTH = 20;
	private static final int UNIT_LABEL_HEIGHT = 18;
	private static final int UNIT_WIDTH = 17;
	private static final int UNIT_HEIGHT = 14;
	private static final String CAQI_LABEL_TITLE = "CAQI";
	private static final String PM1_LABEL_TITLE = "PM1";
	private static final String PM10_LABEL_TITLE = "PM10";
	private static final String PM25_LABEL_TITLE = "PM25";
	private final static String COLOR_HEX = "#FFFF00";
	private final static Color COLOR_LABEL = Color.GREEN;
	private final static String PM_UNIT = "<html><body>&#181g<br/>m³</body></html>";

	public CentrePanel() {
		super(WIDTH, HEIGHT);
		final String pmUnit = new StringBuilder(PM_UNIT).toString();
		new CentrePanelLayout(this)
			.addCaqiTitle(new TitleLabel(CAQI_LABEL_TITLE))
			.addCaqiIndicator(CAQiIndicator.getInstance())
			.addPmTitle(new UnitLabel(PM1_LABEL_TITLE, UNIT_LABEL_WIDTH, UNIT_LABEL_HEIGHT, COLOR_LABEL), 0, 2)
			.addPmTitle(new UnitLabel(PM10_LABEL_TITLE, UNIT_LABEL_WIDTH, UNIT_LABEL_HEIGHT, COLOR_LABEL), 1, 2)
			.addPmTitle(new UnitLabel(PM25_LABEL_TITLE, UNIT_LABEL_WIDTH, UNIT_LABEL_HEIGHT, COLOR_LABEL), 2, 2)
			.addPMIndicator(PM1_Indicator.getInstance(), 0, 3)
			.addPMIndicator(PM10_Indicator.getInstance(), 1, 3)
			.addPMIndicator(PM25_Indicator.getInstance(), 2, 3)
			.addPMIndicator(PM10Percent_Indicator.getInstance(), 1, 4)
			.addPMIndicator(PM25Percent_Indicator.getInstance(), 2, 4)
			.addUnit(new UnitLabel(pmUnit, UNIT_WIDTH, UNIT_HEIGHT, Color.decode(COLOR_HEX)), 3, 3)
			.addUnit(new UnitLabel("%", UNIT_WIDTH, UNIT_HEIGHT, Color.decode(COLOR_HEX)), 3, 4);
	}
}
