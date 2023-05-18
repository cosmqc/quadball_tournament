package base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import gui.GUI;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class Shop {
	private GUI gui;
	private Shop selfRef;
	private JFrame frame;
	public GameEnvironment game;

	/**
	 * Launch the application.
	 */
	public Shop(GUI gui) {
		this.gui = gui;
		this.selfRef = this;
		this.game = gui.game;
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
		frame.setResizable(false);
		frame.setBounds(gui.x, gui.y, 900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.returnFromShop(selfRef);
			}
		});
		btnBack.setBounds(682, 552, 176, 44);
		frame.getContentPane().add(btnBack);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 12, 866, 596);
		tabbedPane.setFont( new Font( "Dialog", Font.BOLD, 18 ) );
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Buy", null, panel, null);
		panel.setLayout(null);
		
		JButton btnAthlete = new JButton("Athlete 1");
		btnAthlete.setBounds(12, 12, 249, 300);
		panel.add(btnAthlete);
		
		JLabel lblInfo = new JLabel("Info");
		lblInfo.setVerticalAlignment(SwingConstants.TOP);
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(673, 12, 176, 209);
		panel.add(lblInfo);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Sell", null, panel_1, null);
	}
	public void closeWindow() {
		frame.dispose();
	}
}
