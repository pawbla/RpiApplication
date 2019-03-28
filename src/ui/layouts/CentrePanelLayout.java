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
	
	public  CentrePanelLayout addCaqiTitle(JLabel label) {
		this.panel.add(label, new GridBagHandler(0, 0, 4, 1)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setIpad(50, 2));
		return this;
	}
	
	public CentrePanelLayout addCaqiIndicator(JLabel indicator) {
		this.panel.add(indicator, new GridBagHandler(0, 1, 4, 1)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(100, 0));
		return this;
	}
	
	public  CentrePanelLayout addPmTitle(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setIpad(0,  2)
				.setWeight(34, 0));
		return this;
	}
	
	public CentrePanelLayout addPMIndicator(JLabel indicator, int posx, int posy) {
		this.panel.add(indicator, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setIpad(0, 2)
				.setWeight(34, 100));
		return this;
	}
	
	public CentrePanelLayout addUnit(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setIpad(2,  4));
		return this;
	}
	
	
}
