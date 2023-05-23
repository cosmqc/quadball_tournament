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
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class MatchWindow.
 */
public class MatchWindow {
	
	/** The gui. */
	private GUI gui;
	
	/** The self ref. */
	private MatchWindow selfRef;
	
	/** The frm stadium. */
	private JFrame frmStadium;
	
	/** The game. */
	public GameEnvironment game;
	
	/** The money gained. */
	private int moneyGained;
	
	/** The points gained. */
	private int pointsGained;
	
	/**
	 * Launch the application.
	 *
	 * @param gui the gui
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
		lblWeek.setBounds(12, 12, 534, 44);
		frmStadium.getContentPane().add(lblWeek);
		
		JLabel lblChasers = new JLabel("");
		displayMatchInfo(lblChasers);
		
		JButton btnBegin = new JButton("Return Home");
		btnBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eventMessage = game.randomManager.getRandomEvent();
				if (eventMessage != "") {
					JOptionPane.showMessageDialog(frmStadium, eventMessage, "!! Random Event !!",
							JOptionPane.INFORMATION_MESSAGE);
				}
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
		lblChaser.setBounds(12, 218, 440, 32);
		frmStadium.getContentPane().add(lblChaser);
		
		JLabel lblChaser_2 = new JLabel(gui.game.playerTeam.getAthleteAtIndex(1).getName());
		lblChaser_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_2.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_2.setBounds(12, 288, 440, 32);
		frmStadium.getContentPane().add(lblChaser_2);
		
		JLabel lblChaser_3 = new JLabel(gui.game.playerTeam.getAthleteAtIndex(2).getName());
		lblChaser_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_3.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_3.setBounds(12, 358, 440, 32);
		frmStadium.getContentPane().add(lblChaser_3);
		
		JLabel lblChaser_4 = new JLabel(gui.game.playerTeam.getAthleteAtIndex(3).getName());
		lblChaser_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_4.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_4.setBounds(12, 428, 440, 44);
		frmStadium.getContentPane().add(lblChaser_4);
		
		JLabel lblChaser_1 = new JLabel(gui.game.enemyTeam.getAthleteAtIndex(0).getName());
		lblChaser_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_1.setBounds(448, 212, 440, 44);
		frmStadium.getContentPane().add(lblChaser_1);
		
		JLabel lblChaser_2_1 = new JLabel(gui.game.enemyTeam.getAthleteAtIndex(1).getName());
		lblChaser_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_2_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_2_1.setBounds(448, 282, 440, 44);
		frmStadium.getContentPane().add(lblChaser_2_1);
		
		JLabel lblChaser_3_1 = new JLabel(gui.game.enemyTeam.getAthleteAtIndex(2).getName());
		lblChaser_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_3_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_3_1.setBounds(448, 428, 440, 44);
		frmStadium.getContentPane().add(lblChaser_3_1);
		
		JLabel lblChaser_4_1 = new JLabel(gui.game.enemyTeam.getAthleteAtIndex(3).getName());
		lblChaser_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_4_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblChaser_4_1.setBounds(448, 352, 440, 44);
		frmStadium.getContentPane().add(lblChaser_4_1);
		
		JLabel lblEnemyTeam = new JLabel(gui.game.enemyTeam.getName());
		lblEnemyTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnemyTeam.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEnemyTeam.setBounds(448, 152, 440, 44);
		frmStadium.getContentPane().add(lblEnemyTeam);
		
		JLabel lblYourTeam = new JLabel(gui.game.playerTeam.getName());
		lblYourTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourTeam.setFont(new Font("Dialog", Font.BOLD, 18));
		lblYourTeam.setBounds(12, 152, 440, 44);
		frmStadium.getContentPane().add(lblYourTeam);
		
		JLabel lblStamina1 = new JLabel(displayStaminaInfo(0));
		lblStamina1.setHorizontalAlignment(SwingConstants.CENTER);
		lblStamina1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblStamina1.setBounds(12, 238, 440, 44);
		frmStadium.getContentPane().add(lblStamina1);
		
		JLabel lblStamina2 = new JLabel(displayStaminaInfo(1));
		lblStamina2.setHorizontalAlignment(SwingConstants.CENTER);
		lblStamina2.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblStamina2.setBounds(12, 308, 440, 44);
		frmStadium.getContentPane().add(lblStamina2);
		
		JLabel lblStamina3 = new JLabel(displayStaminaInfo(2));
		lblStamina3.setHorizontalAlignment(SwingConstants.CENTER);
		lblStamina3.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblStamina3.setBounds(12, 378, 440, 44);
		frmStadium.getContentPane().add(lblStamina3);
		
		JLabel lblStamina4 = new JLabel(displayStaminaInfo(3));
		lblStamina4.setHorizontalAlignment(SwingConstants.CENTER);
		lblStamina4.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblStamina4.setBounds(12, 448, 440, 44);
		frmStadium.getContentPane().add(lblStamina4);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(448, 152, 3, 350);
		frmStadium.getContentPane().add(panel);
		
		JLabel lblMoneyGained = new JLabel("Money Gained: $%s".formatted(moneyGained));
		lblMoneyGained.setHorizontalAlignment(SwingConstants.LEFT);
		lblMoneyGained.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMoneyGained.setBounds(12, 504, 534, 44);
		frmStadium.getContentPane().add(lblMoneyGained);
		
		JLabel lblMoneyGained_1 = new JLabel("Points Gained: %s".formatted(pointsGained));
		lblMoneyGained_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblMoneyGained_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMoneyGained_1.setBounds(12, 558, 534, 44);
		frmStadium.getContentPane().add(lblMoneyGained_1);
		
		
		
	}
	
	/**
	 * Display match info.
	 *
	 * @param label the label
	 */
	void displayMatchInfo(JLabel label) {
		int pointsBefore = game.playerPoints;
		int moneyBefore = game.playerMoney;
		if (gui.game.matchManager.matchWon(gui.game.enemyTeam)) {
			label.setText("MATCH WON!");
			label.setForeground(new Color(38, 162, 105));
			
		} else {
			label.setText("Match lost...");
			label.setForeground(new Color(255, 0, 0));
		}
		moneyGained = game.playerMoney - moneyBefore;
		pointsGained = game.playerPoints - pointsBefore;
	}
	
	/**
	 * Display stamina info.
	 *
	 * @param athleteIndex the athlete index
	 * @return the string
	 */
	String displayStaminaInfo(int athleteIndex) {
		int staminaValue = gui.game.playerTeam.getAthleteAtIndex(athleteIndex).getStamina();
		int maxStaminaValue = gui.game.playerTeam.getAthleteAtIndex(athleteIndex).getMaxStamina();
		if (staminaValue > 0) {
			return "Stamina: %s/%s".formatted(staminaValue,maxStaminaValue);
		} else {
			return "Stamina: %s/%s - INJURED".formatted(staminaValue,maxStaminaValue);
		}
	}
	
	/**
	 * Close window.
	 */
	public void closeWindow() {
		frmStadium.dispose();
	}
}
