package ui.layouts;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NorthPanelLayout {

	private JPanel panel;
	
	public NorthPanelLayout(JPanel panel) {
		this.panel = panel;
		this.panel.setLayout(new GridBagLayout());
	}
	
	public NorthPanelLayout addCaqiTitle(JLabel label) {
		this.panel.add(label, new GridBagHandler(0, 0, 1, 1)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(13, 0));
		return this;
	}
	
	public NorthPanelLayout addCaqiIndicator(JLabel indicator) {
		this.panel.add(indicator, new GridBagHandler(0, 1, 1, 2)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(13, 30));
		return this;
	}
	
	public NorthPanelLayout addPmTitle(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(20, 0));
		return this;
	}
	
	public NorthPanelLayout addPMIndicator(JLabel indicator, int posx, int posy) {
		this.panel.add(indicator, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(15, 40));
		return this;
	}
	
	public NorthPanelLayout addUnit(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.LAST_LINE_START)
				.setFill(GridBagConstraints.BOTH));
		return this;
	}
	
	public NorthPanelLayout addSunIndicator(JLabel indicator, int posx, int posy) {
		this.panel.add(indicator, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(10, 20));
		return this;	
	}
	
	public NorthPanelLayout addDayLengthIndicator(JLabel indicator, int posx, int posy) {
		this.panel.add(indicator, new GridBagHandler(posx, posy, 4, 1)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(10, 50));
		return this;	
	}
	
	public NorthPanelLayout addSunTitle(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy, 4, 1)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(7, 0));
		return this;	
	}
	
	public NorthPanelLayout addEmpty(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(15, 0));
		return this;	
	}
}
