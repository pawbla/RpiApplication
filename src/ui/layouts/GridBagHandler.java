package ui.layouts;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GridBagHandler extends GridBagConstraints {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 444573143270653761L;

	public GridBagHandler(int gridx, int gridy) {
		this.gridx = gridx;
		this.gridy = gridy;
	}
	
	public GridBagHandler(int gridx, int gridy, int gridwidth, int gridheight) {
		this(gridx, gridy);
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}
	
	public GridBagHandler setAnchor(int anchor) {
		this.anchor = anchor;
		return this;
	}
	
	public GridBagHandler setFill (int fill) {
		this.fill = fill;
		return this;
	}
	
	public GridBagHandler setWeight (double weightx, double weighty) {
		this.weightx = weightx;
		this.weighty = weighty;
		return this;
	}
	
	public GridBagHandler setInsets (int top, int left, int bottom, int right) {
		this.insets =  new Insets(top, left, bottom, right);
		return this;
	}
	
	public GridBagHandler setInsets (int distance) {
		this.insets =  new Insets(distance, distance, distance, distance);
		return this;
	}
	
	public GridBagHandler setIpad(int ipadx, int ipady) {
		this.ipadx = ipadx;
		this.ipady = ipady;
		return this;
	}
	
}
