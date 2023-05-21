package gui;

import base.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

public class StadiumWindow {
	private GUI gui;
	private StadiumWindow selfRef;
	private JFrame frmStadium;
	public GameEnvironment game;

	/**
	 * Launch the application.
	 */
	public StadiumWindow(GUI gui) {
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
		frmStadium.setTitle("Stadium");
		frmStadium.setResizable(false);
		frmStadium.setBounds(gui.x, gui.y, 900, 650);
		frmStadium.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStadium.getContentPane().setLayout(null);
		
		JLabel lblWeek = new JLabel("Matches Available:");
		lblWeek.setHorizontalAlignment(SwingConstants.LEFT);
		lblWeek.setFont(new Font("Dialog", Font.BOLD, 18));
		lblWeek.setBounds(12, 0, 534, 44);
		frmStadium.getContentPane().add(lblWeek);
		
		DefaultListModel<Athlete> athleteTeamModel = new DefaultListModel<>();
		athleteTeamModel.addAll(gui.game.playerTeam.getAthletes());
		
		JList<Athlete> athleteTeamList = new JList<Athlete>(athleteTeamModel);
		athleteTeamList.setBounds(10, 228, 360, 285);
		frmStadium.getContentPane().add(athleteTeamList);
		
		JLabel lblAthleteTeam = new JLabel("Current Team");
		lblAthleteTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblAthleteTeam.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAthleteTeam.setBounds(10, 174, 357, 44);
		frmStadium.getContentPane().add(lblAthleteTeam);
		
		JLabel lblVs = new JLabel("VS");
		lblVs.setHorizontalAlignment(SwingConstants.CENTER);
		lblVs.setFont(new Font("Dialog", Font.BOLD, 40));
		lblVs.setBounds(380, 354, 124, 44);
		frmStadium.getContentPane().add(lblVs);
		
		JLabel lblOpposingTeam = new JLabel("Opposing Team");
		lblOpposingTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpposingTeam.setFont(new Font("Dialog", Font.BOLD, 18));
		lblOpposingTeam.setBounds(514, 174, 357, 44);
		frmStadium.getContentPane().add(lblOpposingTeam);
		
		DefaultListModel<Athlete> opposingTeamModel = new DefaultListModel<>();
		opposingTeamModel.addAll(gui.game.playerTeam.getSubs());
		
		JList<Athlete> opposingTeamList = new JList<Athlete>(opposingTeamModel);
		opposingTeamList.setBounds(514, 228, 360, 285);
		frmStadium.getContentPane().add(opposingTeamList);
		
		JButton btnAthlete3 = new JButton();
		btnAthlete3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opposingTeamModel.clear();
				Athlete test = new Athlete(game);
				opposingTeamModel.addElement(test);
			}
		});
		btnAthlete3.setBounds(55, 54, 150, 110);
		btnAthlete3.setText("Match 1");
		frmStadium.getContentPane().add(btnAthlete3);
		
		JButton btnAthlete3_1 = new JButton();
		btnAthlete3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opposingTeamModel.clear();
				Athlete test = new Athlete(game);
				opposingTeamModel.addElement(test);
			}
		});
		btnAthlete3_1.setText("Match 2");
		btnAthlete3_1.setBounds(215, 54, 150, 110);
		frmStadium.getContentPane().add(btnAthlete3_1);
		
		JButton btnAthlete3_2 = new JButton();
		btnAthlete3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opposingTeamModel.clear();
				Athlete test = new Athlete(game);
				opposingTeamModel.addElement(test);
			}
		});
		btnAthlete3_2.setText("Match 3");
		btnAthlete3_2.setBounds(375, 54, 150, 110);
		frmStadium.getContentPane().add(btnAthlete3_2);
		
		JButton btnAthlete3_3 = new JButton();
		btnAthlete3_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opposingTeamModel.clear();
				Athlete test = new Athlete(game);
				opposingTeamModel.addElement(test);
			}
		});
		btnAthlete3_3.setText("Match 4");
		btnAthlete3_3.setBounds(535, 54, 150, 110);
		frmStadium.getContentPane().add(btnAthlete3_3);
		
		JButton btnAthlete3_4 = new JButton();
		btnAthlete3_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opposingTeamModel.clear();
				Athlete test = new Athlete(game);
				opposingTeamModel.addElement(test);
			}
		});
		btnAthlete3_4.setText("Match 5");
		btnAthlete3_4.setBounds(695, 54, 150, 110);
		frmStadium.getContentPane().add(btnAthlete3_4);
		
		JButton btnBegin = new JButton("Prepare For Battle!");
		btnBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(frmStadium, "Are You Sure?\nYou can't turn back after this", "Confirm Match",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					gui.launchMatchWindow(selfRef);
				}
			}
		});
		btnBegin.setBounds(695, 552, 176, 44);
		frmStadium.getContentPane().add(btnBegin);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeStadiumWindow(selfRef);
			}
		});
		btnBack.setBounds(509, 552, 176, 44);
		frmStadium.getContentPane().add(btnBack);
		
	}
	
	public void closeWindow() {
		frmStadium.dispose();
	}
}
