package ui.panels.mainPanels;

import java.awt.Color;

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
	
	private static final String CLOUD_TITLE = "ZACHMURZENIE";
	private static final String UV_TITLE = "UV INDEX";
	private static final String CLOUD_COVER_UNIT = "%";
	
	public SouthPanel() {
		super(WIDTH, HEIGHT);
		new SouthPanelLayout(this)
			.addTitle(new TitleLabel(CLOUD_TITLE), 0, 0)
			.addElement(CloudCoverIndicator.getInstance(), 1, 0)
			.addElementUnit(new UnitLabel(CLOUD_COVER_UNIT, UNIT_WIDTH, UNIT_HEIGHT, Color.YELLOW), 2, 0)
			.addTitle(new TitleLabel(UV_TITLE), 3, 0)
			.addShortElement(UVIndexIndicator.getInstance(), 4, 0)
			.addLongElement(UVTextIndicator.getInstance(), 5, 0);
	}
}
