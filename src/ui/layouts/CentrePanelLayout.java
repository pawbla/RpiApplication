package ui.layouts;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CentrePanelLayout {

	private JPanel panel;
	
	public CentrePanelLayout(JPanel panel) {
		this.panel = panel;
		this.panel.setLayout(new GridBagLayout());
	}
	
	public CentrePanelLayout addTitle(JLabel label, int posx, int posy, int cellx, int celly) {
		this.panel.add(label, new GridBagHandler(posx, posy, cellx, celly)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(100, 0));
		return this;
	}
	
	public CentrePanelLayout addWeatherIcon(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy, 4, 1)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(100, 0));
		return this;
	}	
	
	public CentrePanelLayout addWeatherDescription(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy, 4, 1)
				.setAnchor(GridBagConstraints.PAGE_START)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(20, 7));
		return this;
	}	
	
	public CentrePanelLayout addIndicator(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(70, 5));
		return this;
	}	
	
	public CentrePanelLayout addIndicator(JLabel label, int posx, int posy, int cellx, int celly) {
		this.panel.add(label, new GridBagHandler(posx, posy, cellx, celly)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(70, 5));
		return this;
	}
	
	public CentrePanelLayout addUnit(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.LAST_LINE_START)
				.setFill(GridBagConstraints.BOTH));
		return this;
	}
	
}
