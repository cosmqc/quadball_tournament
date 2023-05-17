package jda178_lbl40_SportsTournament;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class BaseWindow {
	private GUI gui;
	private BaseWindow selfRef;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public BaseWindow(GUI gui) {
		this.gui = gui;
		this.selfRef = this;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(gui.x, gui.y, gui.w, gui.h);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
