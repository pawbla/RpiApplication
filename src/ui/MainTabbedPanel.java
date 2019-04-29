package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTabbedPane;
import javax.swing.UIManager;


public class MainTabbedPanel extends JTabbedPane {

	/**
	 * Generated serial UID
	 */
	private static final long serialVersionUID = 4479114683469274660L;
	
	private static MainTabbedPanel instance = null;
	
	/** static block for configuration of UI view for JTabbedPane 
	 * values get from:
	 * https://www.rgagnon.com/javadetails/JavaUIDefaults.txt 
	 * */
	static {
	    UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new Insets(0,0,0,0));
	    UIManager.getDefaults().put("TabbedPane.tabsOverlapBorder", true);
	    UIManager.getDefaults().put("TabbedPane.font", new Font(Font.DIALOG, Font.PLAIN, 17));
	    
	    UIManager.getDefaults().put("TabbedPane.tabAreaBackground", Color.BLACK);
	    UIManager.getDefaults().put("TabbedPane.background", Color.BLACK);
	    UIManager.getDefaults().put("TabbedPane.foreground", Color.GREEN);
	    UIManager.getDefaults().put("TabbedPane.darkShadow", Color.GREEN);
	    UIManager.getDefaults().put("TabbedPane.focus", Color.GREEN);
	    
	    //selected tabs:
	    UIManager.getDefaults().put("TabbedPane.selected", Color.BLACK);
	    UIManager.getDefaults().put("TabbedPane.selectHighlight", Color.GREEN);
	    UIManager.getDefaults().put("TabbedPane.highlight", Color.GREEN);
	    UIManager.getDefaults().put("TabbedPane.shadow", Color.GREEN);
	    UIManager.getDefaults().put("TabbedPane.highlight", Color.GREEN);   
	      
	    UIManager.getDefaults().put("TabbedPane.tabInsets", new Insets(2, 10, 2, 10));
	}

	public MainTabbedPanel() {
		super(JTabbedPane.BOTTOM);
		this.setOpaque(true);
	}
	
	public static MainTabbedPanel getInstance() {
		if (instance == null) {
			instance = new MainTabbedPanel();
		}
		return instance;
	}
}
