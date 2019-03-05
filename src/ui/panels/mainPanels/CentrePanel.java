package ui.panels.mainPanels;

import java.awt.BorderLayout;

import org.springframework.stereotype.Component;

import ui.indicators.weather.PressureIndicator;

@Component
public class CentrePanel extends MainPanels  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8488813971922259532L;
	/**
	 * Constants
	 */
	private static final int WIDTH = 200;
	private static final int HEIGHT = 210;

	public CentrePanel() {
		super(WIDTH, HEIGHT);
		this.add(PressureIndicator.getInstance(), BorderLayout.PAGE_END);
	}
}
