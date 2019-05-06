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
	
	public SouthPanelLayout addTitle(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(20, 100));
		return this;
	}
	
	public SouthPanelLayout addElement(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(20, 100));
		return this;
	}	
	
	public SouthPanelLayout addShortElement(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(5, 100));
		return this;
	}
	
	public SouthPanelLayout addLongElement(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(30, 100));
		return this;
	}
	
	public SouthPanelLayout addElementUnit(JLabel label, int posx, int posy) {
		this.panel.add(label, new GridBagHandler(posx, posy)
				.setAnchor(GridBagConstraints.CENTER)
				.setFill(GridBagConstraints.BOTH)
				.setWeight(10, 100));
		return this;
	}
}
