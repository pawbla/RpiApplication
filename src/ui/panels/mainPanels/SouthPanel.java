package ui.panels.mainPanels;

import java.awt.Color;

import ui.indicators.accuWeather.CeilingIndicator;
import ui.indicators.accuWeather.CloudCoverIndicator;
import ui.indicators.accuWeather.UVIndexIndicator;
import ui.indicators.accuWeather.UVTextIndicator;
import ui.labels.TitleLabel;
import ui.labels.UnitLabel;
import ui.layouts.SouthPanelLayout;

public class SouthPanel extends  MainPanels {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1053168072712383835L;
	/**
	 * Constants
	 */
	private static final int WIDTH = 480;
	private static final int HEIGHT = 40;
	
	private static final int UNIT_WIDTH = 1;
	private static final int UNIT_HEIGHT = 20;
	
	private static final String CLOUD_TITLE = "Zachmurzenie";
	private static final String UV_TITLE = "UV Index";
	private static final String CLOUD_COVER_UNIT = "%";
	private static final String CEILING_UNIT = "m";
	
	public SouthPanel() {
		super(WIDTH, HEIGHT);
		/*new SouthPanelLayout(this)
		.addTitle(new TitleLabel(CLOUD_TITLE), 0, 0, 4, 1)
		.addTitle(new TitleLabel(UV_TITLE), 4, 0, 2, 1)
		.addFillEmpty(new TitleLabel(" "), 6, 0)
		.addElement(CloudCoverIndicator.getInstance(), 0, 1)
		.addElementUnit(new UnitLabel(CLOUD_COVER_UNIT, UNIT_WIDTH, UNIT_HEIGHT, Color.YELLOW), 1, 1)
		.addElement(CeilingIndicator.getInstance(), 2, 1)
		.addElementUnit(new UnitLabel(CEILING_UNIT, UNIT_WIDTH, UNIT_HEIGHT, Color.YELLOW), 3, 1)
		.addElement(UVIndexIndicator.getInstance(), 4, 1)
		.addElement(UVTextIndicator.getInstance(), 5, 1);*/
		
	}
}
