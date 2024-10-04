package controller;

import view.MainFrame;
import view.MainPanel;

public class Controller {

	private MainFrame mainframe;
	private MainPanel mainpanel;
	
	public Controller() {
		mainpanel = new MainPanel();
		mainframe = new MainFrame();
		
		// Add MainPanel to MainFrame
		mainframe.add(mainpanel);

		// Optional: make the frame visible (if not already done in MainFrame)
		mainframe.setVisible(true);
	}
}
