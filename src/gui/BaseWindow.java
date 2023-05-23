package gui;

import base.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
		lblWeek.setBounds(12, 42, 534, 44);
		frmMainScreen.getContentPane().add(lblWeek);
		
		JButton btnGoToShop = new JButton("Go To Shop");
		btnGoToShop.setFont(new Font("Dialog", Font.BOLD, 30));
		btnGoToShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchShopWindow(selfRef);
			}
		});
		btnGoToShop.setBounds(12, 144, 400, 200);
		frmMainScreen.getContentPane().add(btnGoToShop);
		
		JButton btnGoToClub = new JButton("Go To Club");
		btnGoToClub.setFont(new Font("Dialog", Font.BOLD, 30));
		btnGoToClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.launchClubWindow(selfRef);
			}
		});
		btnGoToClub.setBounds(478, 144, 400, 200);
		frmMainScreen.getContentPane().add(btnGoToClub);
		
		JButton btnGoToStadium = new JButton("Go To Stadium");
		btnGoToStadium.setFont(new Font("Dialog", Font.BOLD, 30));
		btnGoToStadium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gui.game.playerTeam.getNumActive() < 4) {
					JOptionPane.showMessageDialog(frmMainScreen, "You must have four active athletes in your team to enter the stadium", "Not Enough Athletes",
							JOptionPane.ERROR_MESSAGE);
				} else {
					gui.launchStadiumWindow(selfRef);
				}
			}
		});
		btnGoToStadium.setBounds(12, 358, 400, 200);
		frmMainScreen.getContentPane().add(btnGoToStadium);
		
		JButton btnTakeABye = new JButton("Take A Bye");
		btnTakeABye.setFont(new Font("Dialog", Font.BOLD, 30));
		btnTakeABye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gui.game.playerTeam.getNumActive() < 4) {
					JOptionPane.showMessageDialog(frmMainScreen, "You must have four active athletes in your team to take a bye", "Not Enough Athletes",
							JOptionPane.ERROR_MESSAGE);
				} else {
					gui.launchRestWeekWindow(selfRef);
				}
			}
		});
		btnTakeABye.setBounds(478, 356, 400, 200);
		frmMainScreen.getContentPane().add(btnTakeABye);
		
		JLabel lblMoney = new JLabel("Money: $%s".formatted(gui.game.playerMoney));
		lblMoney.setHorizontalAlignment(SwingConstants.LEFT);
		lblMoney.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMoney.setBounds(12, 72, 534, 44);
		frmMainScreen.getContentPane().add(lblMoney);
		
		JLabel lblPoints = new JLabel("Points: %s".formatted(gui.game.playerPoints));
		lblPoints.setHorizontalAlignment(SwingConstants.LEFT);
		lblPoints.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPoints.setBounds(12, 102, 534, 44);
		frmMainScreen.getContentPane().add(lblPoints);
		
		JLabel lblWelcomeToQuadball = new JLabel("Welcome To QuadBall, %s!".formatted(gui.game.playerTeam.getName()));
		lblWelcomeToQuadball.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToQuadball.setFont(new Font("Dialog", Font.BOLD, 30));
		lblWelcomeToQuadball.setBounds(12, 3, 876, 44);
		frmMainScreen.getContentPane().add(lblWelcomeToQuadball);
		
		JLabel lblOutOfMoney = new JLabel("Out of money or athletes?");
		lblOutOfMoney.setHorizontalAlignment(SwingConstants.LEFT);
		lblOutOfMoney.setFont(new Font("Dialog", Font.BOLD, 18));
		lblOutOfMoney.setBounds(426, 578, 272, 26);
		frmMainScreen.getContentPane().add(lblOutOfMoney);
		
		JButton btnBack = new JButton("Declare Bankruptcy");
		btnBack.setBounds(702, 568, 176, 44);
		frmMainScreen.getContentPane().add(btnBack);
	}
	
	public void closeWindow() {
		frmMainScreen.dispose();
	}
}
