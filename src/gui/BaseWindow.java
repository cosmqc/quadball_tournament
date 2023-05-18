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
	private JFrame frame;
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
		
		JLabel lblWeek = new JLabel("Week: " + String.valueOf(game.getCurrentWeek()) + " of " + game.getTotalWeeks());
		lblWeek.setHorizontalAlignment(SwingConstants.LEFT);
		lblWeek.setFont(new Font("Dialog", Font.BOLD, 18));
		lblWeek.setBounds(12, 0, 534, 44);
		frame.getContentPane().add(lblWeek);
		
		JButton btnGoToShop = new JButton("Go To Shop");
		btnGoToShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.goToShop(selfRef);
			}
		});
		btnGoToShop.setBounds(12, 552, 176, 44);
		frame.getContentPane().add(btnGoToShop);
		
		JButton btnGoToClub = new JButton("Go To Club");
		btnGoToClub.setBounds(200, 552, 176, 44);
		frame.getContentPane().add(btnGoToClub);
		
		JButton btnGoToStadium = new JButton("Go To Stadium");
		btnGoToStadium.setBounds(388, 552, 176, 44);
		frame.getContentPane().add(btnGoToStadium);
		
		JButton btnTakeABye = new JButton("Take A Bye");
		btnTakeABye.setBounds(576, 552, 176, 44);
		frame.getContentPane().add(btnTakeABye);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
}
