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
		
		JButton btnBegin = new JButton("Return Home");
		btnBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.game.nextWeek();
				gui.closeMatchWindow(selfRef);
			}
		});
		btnBegin.setBounds(695, 559, 176, 44);
		frmStadium.getContentPane().add(btnBegin);
		
		JLabel lblChasers = new JLabel("");
		displayMatchInfo(lblChasers);
		
		lblChasers.setHorizontalAlignment(SwingConstants.CENTER);
		lblChasers.setFont(new Font("Dialog", Font.BOLD, 40));
		lblChasers.setBounds(12, 56, 876, 44);
		frmStadium.getContentPane().add(lblChasers);
		
		JLabel lblChaser = new JLabel(gui.game.playerTeam.getAthleteAtIndex(0).getName());
		lblChaser.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser.setBounds(12, 168, 440, 32);
		frmStadium.getContentPane().add(lblChaser);
		
		JLabel lblChaser_2 = new JLabel(gui.game.playerTeam.getAthleteAtIndex(1).getName());
		lblChaser_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_2.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_2.setBounds(12, 254, 440, 32);
		frmStadium.getContentPane().add(lblChaser_2);
		
		JLabel lblChaser_3 = new JLabel(gui.game.playerTeam.getAthleteAtIndex(2).getName());
		lblChaser_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_3.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_3.setBounds(12, 336, 440, 32);
		frmStadium.getContentPane().add(lblChaser_3);
		
		JLabel lblChaser_4 = new JLabel(gui.game.playerTeam.getAthleteAtIndex(3).getName());
		lblChaser_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_4.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_4.setBounds(12, 405, 440, 44);
		frmStadium.getContentPane().add(lblChaser_4);
		
		JLabel lblChaser_1 = new JLabel(gui.game.enemyTeam.getAthleteAtIndex(0).getName());
		lblChaser_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_1.setBounds(448, 162, 440, 44);
		frmStadium.getContentPane().add(lblChaser_1);
		
		JLabel lblChaser_2_1 = new JLabel(gui.game.enemyTeam.getAthleteAtIndex(1).getName());
		lblChaser_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_2_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_2_1.setBounds(448, 248, 440, 44);
		frmStadium.getContentPane().add(lblChaser_2_1);
		
		JLabel lblChaser_3_1 = new JLabel(gui.game.enemyTeam.getAthleteAtIndex(2).getName());
		lblChaser_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_3_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_3_1.setBounds(448, 405, 440, 44);
		frmStadium.getContentPane().add(lblChaser_3_1);
		
		JLabel lblChaser_4_1 = new JLabel(gui.game.enemyTeam.getAthleteAtIndex(3).getName());
		lblChaser_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_4_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_4_1.setBounds(448, 330, 440, 44);
		frmStadium.getContentPane().add(lblChaser_4_1);
		
		JLabel lblEnemyTeam = new JLabel("Enemy Team");
		lblEnemyTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnemyTeam.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEnemyTeam.setBounds(448, 112, 440, 44);
		frmStadium.getContentPane().add(lblEnemyTeam);
		
		JLabel lblYourTeam = new JLabel("Your Team");
		lblYourTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourTeam.setFont(new Font("Dialog", Font.BOLD, 18));
		lblYourTeam.setBounds(12, 112, 440, 44);
		frmStadium.getContentPane().add(lblYourTeam);
		
		JLabel lblStamina = new JLabel("Stamina - INJURED");
		lblStamina.setHorizontalAlignment(SwingConstants.CENTER);
		lblStamina.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblStamina.setBounds(12, 198, 440, 44);
		frmStadium.getContentPane().add(lblStamina);
		
		JLabel lblStamina_1 = new JLabel("Stamina");
		lblStamina_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblStamina_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblStamina_1.setBounds(12, 280, 440, 44);
		frmStadium.getContentPane().add(lblStamina_1);
		
		JLabel lblStamina_1_1 = new JLabel("Stamina");
		lblStamina_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblStamina_1_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblStamina_1_1.setBounds(12, 362, 440, 44);
		frmStadium.getContentPane().add(lblStamina_1_1);
		
		JLabel lblStamina_1_2 = new JLabel("Stamina");
		lblStamina_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblStamina_1_2.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblStamina_1_2.setBounds(12, 440, 440, 44);
		frmStadium.getContentPane().add(lblStamina_1_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(448, 112, 3, 410);
		frmStadium.getContentPane().add(panel);
		
		
		
	}
	
	void displayMatchInfo(JLabel label) {
		if (gui.game.matchManager.matchWon(gui.game.playerTeam,gui.game.enemyTeam)) {
			label.setText("MATCH WON!");
			System.out.println("HIII");
			label.setForeground(new Color(38, 162, 105));
		} else {
			label.setText("Match lost...");
			label.setForeground(new Color(255, 0, 0));
		}
	}
	
	public void closeWindow() {
		frmStadium.dispose();
	}
}
