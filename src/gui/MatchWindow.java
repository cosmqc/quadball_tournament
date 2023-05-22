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


//		JComboBox comboBox = new JComboBox(gui.game.playerTeam.getAthletes().toArray());
//		comboBox.setBounds(225, 127, 450, 44);
//		frmStadium.getContentPane().add(comboBox);
//		
//		JComboBox comboBox_1 = new JComboBox(gui.game.playerTeam.getAthletes().toArray());
//		comboBox_1.setBounds(225, 200, 450, 44);
//		frmStadium.getContentPane().add(comboBox_1);
//		
//		JComboBox comboBox_2 = new JComboBox(gui.game.playerTeam.getAthletes().toArray());
//		comboBox_2.setBounds(225, 308, 450, 44);
//		frmStadium.getContentPane().add(comboBox_2);
//		
//		JComboBox comboBox_3 = new JComboBox(gui.game.playerTeam.getAthletes().toArray());
//		comboBox_3.setBounds(225, 416, 450, 44);
//		frmStadium.getContentPane().add(comboBox_3);

		
		JButton btnBegin = new JButton("Return Home");
		btnBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeMatchWindow(selfRef);
			}
		});
		btnBegin.setBounds(695, 559, 176, 44);
		frmStadium.getContentPane().add(btnBegin);
		
		JLabel lblChasers = new JLabel("Chasers:");
		lblChasers.setHorizontalAlignment(SwingConstants.CENTER);
		lblChasers.setFont(new Font("Dialog", Font.BOLD, 18));
		lblChasers.setBounds(350, 56, 200, 44);
		frmStadium.getContentPane().add(lblChasers);
		
		JLabel lblChaser = new JLabel(gui.game.playerTeam.getAthleteAtIndex(0).getName());
		lblChaser.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser.setFont(new Font("Dialog", Font.BOLD, 18));
		lblChaser.setBounds(10, 112, 440, 44);
		frmStadium.getContentPane().add(lblChaser);
		
		JLabel lblChaser_1 = new JLabel("AAA");
		lblChaser_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblChaser_1.setBounds(460, 112, 418, 44);
		frmStadium.getContentPane().add(lblChaser_1);
		
		JLabel lblChaser_1_1 = new JLabel("AAA");
		lblChaser_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_1_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblChaser_1_1.setBounds(462, 224, 418, 44);
		frmStadium.getContentPane().add(lblChaser_1_1);
		
		JLabel lblBeaters = new JLabel("Beaters:");
		lblBeaters.setHorizontalAlignment(SwingConstants.CENTER);
		lblBeaters.setFont(new Font("Dialog", Font.BOLD, 18));
		lblBeaters.setBounds(352, 168, 200, 44);
		frmStadium.getContentPane().add(lblBeaters);
		
		JLabel lblChaser_2 = new JLabel(gui.game.playerTeam.getAthleteAtIndex(1).getName());
		lblChaser_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_2.setFont(new Font("Dialog", Font.BOLD, 18));
		lblChaser_2.setBounds(12, 224, 440, 44);
		frmStadium.getContentPane().add(lblChaser_2);
		
		JLabel lblChaser_1_2 = new JLabel("AAA");
		lblChaser_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_1_2.setFont(new Font("Dialog", Font.BOLD, 18));
		lblChaser_1_2.setBounds(462, 336, 418, 44);
		frmStadium.getContentPane().add(lblChaser_1_2);
		
		JLabel lblKeepers = new JLabel("Keepers:");
		lblKeepers.setHorizontalAlignment(SwingConstants.CENTER);
		lblKeepers.setFont(new Font("Dialog", Font.BOLD, 18));
		lblKeepers.setBounds(352, 280, 200, 44);
		frmStadium.getContentPane().add(lblKeepers);
		
		JLabel lblChaser_3 = new JLabel(gui.game.playerTeam.getAthleteAtIndex(2).getName());
		lblChaser_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_3.setFont(new Font("Dialog", Font.BOLD, 18));
		lblChaser_3.setBounds(12, 336, 440, 44);
		frmStadium.getContentPane().add(lblChaser_3);
		
		JLabel lblChaser_1_3 = new JLabel("AAA");
		lblChaser_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_1_3.setFont(new Font("Dialog", Font.BOLD, 18));
		lblChaser_1_3.setBounds(460, 448, 418, 44);
		frmStadium.getContentPane().add(lblChaser_1_3);
		
		JLabel lblSeekers = new JLabel("Seekers:");
		lblSeekers.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeekers.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSeekers.setBounds(350, 392, 200, 44);
		frmStadium.getContentPane().add(lblSeekers);
		
		JLabel lblChaser_4 = new JLabel(gui.game.playerTeam.getAthleteAtIndex(3).getName());
		lblChaser_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser_4.setFont(new Font("Dialog", Font.BOLD, 18));
		lblChaser_4.setBounds(10, 448, 440, 44);
		frmStadium.getContentPane().add(lblChaser_4);
		
	}
	
	public void closeWindow() {
		frmStadium.dispose();
	}
}
