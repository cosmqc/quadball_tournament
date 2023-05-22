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
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MatchWindow {
	private GUI gui;
	private MatchWindow selfRef;
	private JFrame frmStadium;
	public GameEnvironment game;
	private int moneyGained;

	/**
	 * Launch the application.
	 */
	public MatchWindow(GUI gui) {
		this.gui = gui;
		this.selfRef = this;
		this.game = gui.game;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frmStadium.setVisible(true);
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
		frmStadium = new JFrame();
		frmStadium.setTitle("Match Results");
		frmStadium.setResizable(false);
		frmStadium.setBounds(gui.x, gui.y, 900, 650);
		frmStadium.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStadium.getContentPane().setLayout(null);
		
		JLabel lblWeek = new JLabel("Match Results");
		lblWeek.setHorizontalAlignment(SwingConstants.LEFT);
		lblWeek.setFont(new Font("Dialog", Font.BOLD, 18));
		lblWeek.setBounds(12, 0, 534, 44);
		frmStadium.getContentPane().add(lblWeek);
		
		JLabel lblChasers = new JLabel("");
		displayMatchInfo(lblChasers);
		
		JButton btnBegin = new JButton("Return Home");
		btnBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.game.nextWeek(moneyGained);
				gui.closeMatchWindow(selfRef);
			}
		});
		btnBegin.setBounds(695, 559, 176, 44);
		frmStadium.getContentPane().add(btnBegin);
		
		lblChasers.setHorizontalAlignment(SwingConstants.CENTER);
		lblChasers.setFont(new Font("Dialog", Font.BOLD, 40));
		lblChasers.setBounds(12, 40, 876, 44);
		frmStadium.getContentPane().add(lblChasers);
		
		JLabel lblChaser = new JLabel(gui.game.playerTeam.getAthleteAtIndex(0).getName());
		lblChaser.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser.setBounds(12, 267, 440, 32);
		frmStadium.getContentPane().add(lblChaser);
		
		JLabel lblChaser_2 = new JLabel(gui.game.playerTeam.getAthleteAtIndex(1).getName());
		lblChaser_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_2.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_2.setBounds(12, 337, 440, 32);
		frmStadium.getContentPane().add(lblChaser_2);
		
		JLabel lblChaser_3 = new JLabel(gui.game.playerTeam.getAthleteAtIndex(2).getName());
		lblChaser_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_3.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_3.setBounds(12, 407, 440, 32);
		frmStadium.getContentPane().add(lblChaser_3);
		
		JLabel lblChaser_4 = new JLabel(gui.game.playerTeam.getAthleteAtIndex(3).getName());
		lblChaser_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_4.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_4.setBounds(12, 477, 440, 44);
		frmStadium.getContentPane().add(lblChaser_4);
		
		JLabel lblChaser_1 = new JLabel(gui.game.enemyTeam.getAthleteAtIndex(0).getName());
		lblChaser_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_1.setBounds(448, 261, 440, 44);
		frmStadium.getContentPane().add(lblChaser_1);
		
		JLabel lblChaser_2_1 = new JLabel(gui.game.enemyTeam.getAthleteAtIndex(1).getName());
		lblChaser_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_2_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_2_1.setBounds(448, 331, 440, 44);
		frmStadium.getContentPane().add(lblChaser_2_1);
		
		JLabel lblChaser_3_1 = new JLabel(gui.game.enemyTeam.getAthleteAtIndex(2).getName());
		lblChaser_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_3_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_3_1.setBounds(448, 477, 440, 44);
		frmStadium.getContentPane().add(lblChaser_3_1);
		
		JLabel lblChaser_4_1 = new JLabel(gui.game.enemyTeam.getAthleteAtIndex(3).getName());
		lblChaser_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_4_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_4_1.setBounds(448, 401, 440, 44);
		frmStadium.getContentPane().add(lblChaser_4_1);
		
		JLabel lblEnemyTeam = new JLabel("Enemy Team");
		lblEnemyTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnemyTeam.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEnemyTeam.setBounds(448, 201, 440, 44);
		frmStadium.getContentPane().add(lblEnemyTeam);
		
		JLabel lblYourTeam = new JLabel("Your Team");
		lblYourTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourTeam.setFont(new Font("Dialog", Font.BOLD, 18));
		lblYourTeam.setBounds(12, 201, 440, 44);
		frmStadium.getContentPane().add(lblYourTeam);
		
		JLabel lblStamina1 = new JLabel(displayStaminaInfo(0));
		lblStamina1.setHorizontalAlignment(SwingConstants.CENTER);
		lblStamina1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblStamina1.setBounds(12, 287, 440, 44);
		frmStadium.getContentPane().add(lblStamina1);
		
		JLabel lblStamina2 = new JLabel(displayStaminaInfo(1));
		lblStamina2.setHorizontalAlignment(SwingConstants.CENTER);
		lblStamina2.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblStamina2.setBounds(12, 357, 440, 44);
		frmStadium.getContentPane().add(lblStamina2);
		
		JLabel lblStamina3 = new JLabel(displayStaminaInfo(2));
		lblStamina3.setHorizontalAlignment(SwingConstants.CENTER);
		lblStamina3.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblStamina3.setBounds(12, 427, 440, 44);
		frmStadium.getContentPane().add(lblStamina3);
		
		JLabel lblStamina4 = new JLabel(displayStaminaInfo(3));
		lblStamina4.setHorizontalAlignment(SwingConstants.CENTER);
		lblStamina4.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblStamina4.setBounds(12, 497, 440, 44);
		frmStadium.getContentPane().add(lblStamina4);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(448, 201, 3, 350);
		frmStadium.getContentPane().add(panel);
		
		JLabel lblMoneyGained = new JLabel("Money Gained: $%s".formatted(moneyGained));
		lblMoneyGained.setHorizontalAlignment(SwingConstants.LEFT);
		lblMoneyGained.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMoneyGained.setBounds(12, 94, 534, 44);
		frmStadium.getContentPane().add(lblMoneyGained);
		
		JLabel lblMoneyGained_1 = new JLabel("Points Gained: ");
		lblMoneyGained_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblMoneyGained_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMoneyGained_1.setBounds(12, 148, 534, 44);
		frmStadium.getContentPane().add(lblMoneyGained_1);
		
		
		
	}
	
	void displayMatchInfo(JLabel label) {
		if (gui.game.matchManager.matchWon(gui.game.playerTeam,gui.game.enemyTeam)) {
			label.setText("MATCH WON!");
			label.setForeground(new Color(38, 162, 105));
			moneyGained = gui.game.matchManager.winMoney();
		} else {
			label.setText("Match lost...");
			label.setForeground(new Color(255, 0, 0));
			moneyGained = gui.game.matchManager.lossMoney();
		}
	}
	
	String displayStaminaInfo(int athleteIndex) {
		int staminaValue = gui.game.playerTeam.getAthleteAtIndex(athleteIndex).getStamina();
		int maxStaminaValue = gui.game.playerTeam.getAthleteAtIndex(athleteIndex).getMaxStamina();
		if (staminaValue > 0) {
			return "Stamina: %s/%s".formatted(staminaValue,maxStaminaValue);
		} else {
			return "Stamina: %s/%s - INJURED".formatted(staminaValue,maxStaminaValue);
		}
	}
	
	public void closeWindow() {
		frmStadium.dispose();
	}
}
