package ui.panels.mainPanels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import ui.panels.AbstractPanel;

public class MainPanels extends AbstractPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7991844691580177592L;
	/**
	 * Constants
	 */
	private final static Color BORDER_COLOR = Color.GREEN;
	private final static int TICKNES = 1;
	private final static boolean IS_ROUNDED = true;
	private final static int MARGIN = 3;
	
	public MainPanels (int width, int height) {
		super();
		this.setPreferredSize(new Dimension(width, height));
		this.setSize(new Dimension(width, height));
		this.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, TICKNES, IS_ROUNDED));
		this.setMargin(MARGIN);	
	}
	
	private void setMargin(int margin) {
		Border current = this.getBorder();
		Border empty = new EmptyBorder(margin, margin, margin, margin);
		if (current == null)
		{
		    this.setBorder(empty);
		}
		else
		{
		    this.setBorder(new CompoundBorder(empty, current));
		}		
	}

}
