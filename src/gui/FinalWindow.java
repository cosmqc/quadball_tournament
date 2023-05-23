package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class FinalWindow {

	GUI gui;
	FinalWindow selfRef;
	JFrame frmFinal;
	/**
	 * Launch the application.
	 */
	public FinalWindow(GUI gui) {
		this.gui = gui;
		this.selfRef = this;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frmFinal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize() {
		frmFinal = new JFrame();
		frmFinal.setTitle("Game Over");
		frmFinal.setResizable(false);
		frmFinal.setBounds(gui.x, gui.y, 900, 650);
		frmFinal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFinal.getContentPane().setLayout(null);
		
		JLabel lblPoints = new JLabel("Points: %s".formatted(gui.game.playerPoints));
		lblPoints.setFont(new Font("Dialog", Font.BOLD, 30));
		lblPoints.setBounds(12, 264, 876, 102);
		frmFinal.getContentPane().add(lblPoints);
		
		JLabel lblGameOver = new JLabel("GAME OVER");
		lblGameOver.setFont(new Font("Dialog", Font.BOLD, 40));
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOver.setBounds(12, 12, 888, 150);
		frmFinal.getContentPane().add(lblGameOver);
		
		JLabel lblMoneyRemaining = new JLabel("Money Remaining: $%s".formatted(gui.game.playerMoney));
		lblMoneyRemaining.setFont(new Font("Dialog", Font.BOLD, 30));
		lblMoneyRemaining.setBounds(12, 378, 876, 102);
		frmFinal.getContentPane().add(lblMoneyRemaining);
		
		JLabel lblSeasonLength = new JLabel("Season Length: %s Weeks".formatted(gui.game.getTotalWeeks()));
		lblSeasonLength.setFont(new Font("Dialog", Font.BOLD, 30));
		lblSeasonLength.setBounds(12, 492, 876, 102);
		frmFinal.getContentPane().add(lblSeasonLength);
	}
}
