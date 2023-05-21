package gui;

import base.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.JComboBox;

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
		frmStadium.setTitle("Team Setup");
		frmStadium.setResizable(false);
		frmStadium.setBounds(gui.x, gui.y, 900, 650);
		frmStadium.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStadium.getContentPane().setLayout(null);
		
		JLabel lblWeek = new JLabel("How will you organise your team?");
		lblWeek.setHorizontalAlignment(SwingConstants.LEFT);
		lblWeek.setFont(new Font("Dialog", Font.BOLD, 18));
		lblWeek.setBounds(12, 0, 534, 44);
		frmStadium.getContentPane().add(lblWeek);
		
		JLabel lblBeater = new JLabel("Beater");
		lblBeater.setHorizontalAlignment(SwingConstants.CENTER);
		lblBeater.setFont(new Font("Dialog", Font.BOLD, 18));
		lblBeater.setBounds(12, 200, 203, 44);
		frmStadium.getContentPane().add(lblBeater);
		
		JLabel lblKeeper = new JLabel("Keeper");
		lblKeeper.setHorizontalAlignment(SwingConstants.CENTER);
		lblKeeper.setFont(new Font("Dialog", Font.BOLD, 18));
		lblKeeper.setBounds(12, 308, 203, 44);
		frmStadium.getContentPane().add(lblKeeper);
		
		JLabel lblSeeker = new JLabel("Seeker");
		lblSeeker.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeeker.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSeeker.setBounds(12, 416, 203, 44);
		frmStadium.getContentPane().add(lblSeeker);
		
		JLabel lblOffensive = new JLabel("OFFENSIVE:");
		lblOffensive.setHorizontalAlignment(SwingConstants.CENTER);
		lblOffensive.setFont(new Font("Dialog", Font.BOLD, 18));
		lblOffensive.setBounds(350, 70, 200, 44);
		frmStadium.getContentPane().add(lblOffensive);
		
		JLabel lblDefens = new JLabel("DEFENSIVE:");
		lblDefens.setHorizontalAlignment(SwingConstants.CENTER);
		lblDefens.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDefens.setBounds(350, 254, 200, 44);
		frmStadium.getContentPane().add(lblDefens);
		
		JLabel lblSpeed = new JLabel("SPEED:");
		lblSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeed.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSpeed.setBounds(350, 362, 200, 44);
		frmStadium.getContentPane().add(lblSpeed);
		
		
		JComboBox comboBox = new JComboBox(gui.game.playerTeam.getAthletes().toArray());
		comboBox.setBounds(225, 127, 450, 44);
		frmStadium.getContentPane().add(comboBox);
		
		JLabel lblChaser = new JLabel("Chaser");
		lblChaser.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaser.setFont(new Font("Dialog", Font.BOLD, 18));
		lblChaser.setBounds(12, 124, 203, 44);
		frmStadium.getContentPane().add(lblChaser);
		
		JComboBox comboBox_1 = new JComboBox(gui.game.playerTeam.getAthletes().toArray());
		comboBox_1.setBounds(225, 200, 450, 44);
		frmStadium.getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox(gui.game.playerTeam.getAthletes().toArray());
		comboBox_2.setBounds(225, 308, 450, 44);
		frmStadium.getContentPane().add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox(gui.game.playerTeam.getAthletes().toArray());
		comboBox_3.setBounds(225, 416, 450, 44);
		frmStadium.getContentPane().add(comboBox_3);
		
		JButton btnBegin = new JButton("Begin!");
		btnBegin.setBounds(695, 559, 176, 44);
		frmStadium.getContentPane().add(btnBegin);
		
	}
	
	public void closeWindow() {
		frmStadium.dispose();
	}
}
