package ui.layouts;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SouthPanelLayout {

	private JPanel panel;
	
	public SouthPanelLayout(JPanel panel) {
		this.panel = panel;
		this.panel.setLayout(new GridBagLayout());
	}
	
	public SouthPanelLayout addTitle(JLabel label, int posx, int posy, int cellx, int celly) {
		this.panel.add(label, new GridBagHandler(posx, posy, cellx, celly)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(40, 0));
		return this;
	}
	
	public SouthPanelLayout addElement(JLabel label, int posx, int posy, int cellx, int celly) {
		this.panel.add(label, new GridBagHandler(posx, posy, cellx, celly)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(7, 50));
		return this;
	}
	
	public SouthPanelLayout addElement(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(7, 50));
		return this;
	}
	
	public SouthPanelLayout addElementUnit(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.FIRST_LINE_START)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(5, 50));
		return this;
	}
	
	public SouthPanelLayout addFillEmpty(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(20, 0));
		return this;
	}
}
