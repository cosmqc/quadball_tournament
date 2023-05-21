package gui;

import base.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BaseWindow {
	private GUI gui;
	private BaseWindow selfRef;
	private JFrame frmMainScreen;
	public GameEnvironment game;

	/**
	 * Launch the application.
	 */
	public BaseWindow(GUI gui) {
		this.gui = gui;
		this.selfRef = this;
		this.game = gui.game;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frmMainScreen.setVisible(true);
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
		frmMainScreen = new JFrame();
		frmMainScreen.setTitle("Main Screen");
		frmMainScreen.setResizable(false);
		frmMainScreen.setBounds(gui.x, gui.y, 900, 650);
		frmMainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainScreen.getContentPane().setLayout(null);
		
		JLabel lblWeek = new JLabel("Week: " + String.valueOf(game.getCurrentWeek()) + " of " + game.getTotalWeeks());
		lblWeek.setHorizontalAlignment(SwingConstants.LEFT);
		lblWeek.setFont(new Font("Dialog", Font.BOLD, 18));
		lblWeek.setBounds(12, 0, 534, 44);
		frmMainScreen.getContentPane().add(lblWeek);
		
		JButton btnGoToShop = new JButton("Go To Shop");
		btnGoToShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchShopWindow(selfRef);
			}
		});
		btnGoToShop.setBounds(12, 552, 176, 44);
		frmMainScreen.getContentPane().add(btnGoToShop);
		
		JButton btnGoToClub = new JButton("Go To Club");
		btnGoToClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchClubWindow(selfRef);
			}
		});
		btnGoToClub.setBounds(200, 552, 176, 44);
		frmMainScreen.getContentPane().add(btnGoToClub);
		
		JButton btnGoToStadium = new JButton("Go To Stadium");
		btnGoToStadium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchStadiumWindow(selfRef);
			}
		});
		btnGoToStadium.setBounds(388, 552, 176, 44);
		frmMainScreen.getContentPane().add(btnGoToStadium);
		
		JButton btnTakeABye = new JButton("Take A Bye");
		btnTakeABye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchRestWeekWindow(selfRef);
			}
		});
		btnTakeABye.setBounds(576, 552, 176, 44);
		frmMainScreen.getContentPane().add(btnTakeABye);
	}
	
	public void closeWindow() {
		frmMainScreen.dispose();
	}
	
}
