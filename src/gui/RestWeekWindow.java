package gui;

import base.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class RestWeekWindow {
	private GUI gui;
	private RestWeekWindow selfRef;
	private JFrame frmRestWeek;
	public GameEnvironment game;

	/**
	 * Launch the application.
	 */
	public RestWeekWindow(GUI gui) {
		this.gui = gui;
		this.selfRef = this;
		this.game = gui.game;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frmRestWeek.setVisible(true);
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
		frmRestWeek = new JFrame();
		frmRestWeek.setTitle("Rest Week");
		frmRestWeek.setResizable(false);
		frmRestWeek.setBounds(gui.x, gui.y, 900, 650);
		frmRestWeek.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRestWeek.getContentPane().setLayout(null);
		
		JLabel lblWeek = new JLabel("Move to the next week?");
		lblWeek.setHorizontalAlignment(SwingConstants.LEFT);
		lblWeek.setFont(new Font("Dialog", Font.BOLD, 18));
		lblWeek.setBounds(12, 0, 534, 44);
		frmRestWeek.getContentPane().add(lblWeek);
		
		DefaultListModel<Athlete> athleteTeamModel = new DefaultListModel<>();
		athleteTeamModel.addAll(gui.game.playerTeam.getAllPlayers());
		
		DefaultListModel<Athlete> opposingTeamModel = new DefaultListModel<>();
		opposingTeamModel.addAll(gui.game.playerTeam.getSubs());
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeRestWeekWindow(selfRef);
			}
		});
		btnBack.setBounds(509, 552, 176, 44);
		frmRestWeek.getContentPane().add(btnBack);
		
		JLabel lblEveryAthlete = new JLabel("- Every athlete will fully restore their stamina");
		lblEveryAthlete.setHorizontalAlignment(SwingConstants.LEFT);
		lblEveryAthlete.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblEveryAthlete.setBounds(12, 66, 534, 44);
		frmRestWeek.getContentPane().add(lblEveryAthlete);
		
		JLabel lblEveryAthlete_1 = new JLabel("- Items and athletes in the shop will be updated");
		lblEveryAthlete_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblEveryAthlete_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblEveryAthlete_1.setBounds(12, 120, 534, 44);
		frmRestWeek.getContentPane().add(lblEveryAthlete_1);
		
		JLabel lblEveryAthlete_1_1 = new JLabel("- Available matches in the stadium will be updated");
		lblEveryAthlete_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblEveryAthlete_1_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblEveryAthlete_1_1.setBounds(12, 174, 534, 44);
		frmRestWeek.getContentPane().add(lblEveryAthlete_1_1);
		
		JLabel lblEveryAthlete_1_1_1 = new JLabel("- You may also choose an athlete to train to improve their stats (optional)");
		lblEveryAthlete_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblEveryAthlete_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblEveryAthlete_1_1_1.setBounds(12, 228, 730, 44);
		frmRestWeek.getContentPane().add(lblEveryAthlete_1_1_1);
		
		JLabel lblWouldYouLike = new JLabel("Would you like to train an athlete?");
		lblWouldYouLike.setHorizontalAlignment(SwingConstants.LEFT);
		lblWouldYouLike.setFont(new Font("Dialog", Font.BOLD, 18));
		lblWouldYouLike.setBounds(12, 306, 534, 44);
		frmRestWeek.getContentPane().add(lblWouldYouLike);
		
		ArrayList<Object> trainingOptions = new ArrayList<Object>();
		trainingOptions.add("Skip Training");
		trainingOptions.addAll(gui.game.playerTeam.getAllPlayers());
		Object[] trainingOptionsDisplay = trainingOptions.toArray();
		JComboBox comboBox = new JComboBox(trainingOptionsDisplay);
		comboBox.setBounds(22, 360, 450, 44);
		frmRestWeek.getContentPane().add(comboBox);
		
		JButton btnTakeABye = new JButton("Take A Bye");
		btnTakeABye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(frmRestWeek, "Are You Sure?", "Skip Week",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					gui.game.nextWeek();
					gui.closeRestWeekWindow(selfRef);
				}
			}
		});
		btnTakeABye.setBounds(700, 552, 176, 44);
		btnTakeABye.setBounds(700, 552, 176, 44);
		frmRestWeek.getContentPane().add(btnTakeABye);
		
	}
	
	public void closeWindow() {
		frmRestWeek.dispose();
	}
}
