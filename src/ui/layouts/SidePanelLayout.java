package ui.layouts;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SidePanelLayout {
	
	private JPanel panel;

	public SidePanelLayout(JPanel panel) {
		this.panel = panel;
		this.panel.setLayout(new GridBagLayout());
	}
	
	public SidePanelLayout addTitle(JLabel label) {
		this.panel.add(label, new GridBagHandler(0, 0, 2, 1)
				.setAnchor(GridBagConstraints.PAGE_START)
				.setFill(GridBagConstraints.BOTH)
				.setIpad(10, 2));
		return this;
	}
	
	public SidePanelLayout addTemperatureIndicator(JLabel indicator) {
		this.panel.add(indicator, new GridBagHandler(0, 1)
				.setAnchor(GridBagConstraints.FIRST_LINE_END)
				.setFill(GridBagConstraints.BOTH)
				.setIpad(10, 30));
		return this;
	}
	
	public SidePanelLayout addTemperatureUnit (JLabel label) {
		this.panel.add(label, new GridBagHandler(1, 1)
				.setAnchor(GridBagConstraints.PAGE_START)
				.setFill(GridBagConstraints.BOTH)
				.setIpad(2, 2));
		return this;
	}
	
	public SidePanelLayout addHumidityIndicator(JLabel indicator) {
		this.panel.add(indicator, new GridBagHandler(0, 2)
				.setAnchor(GridBagConstraints.FIRST_LINE_END)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(100, 100));
		return this;
	}
	
	public SidePanelLayout addHumidityUnit(JLabel label) {
		this.panel.add(label, new GridBagHandler(1, 2)
				.setAnchor(GridBagConstraints.FIRST_LINE_START)
				.setFill(GridBagConstraints.BOTH)
				.setIpad(5, 5));
		return this;
	}
	
}
