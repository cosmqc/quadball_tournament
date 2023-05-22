package gui;

import base.*;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class StadiumWindow {
	private GUI gui;
	private StadiumWindow selfRef;
	private JFrame frmStadium;
	
	JLabel lblOpposingTeam;
	JButton selectedMatch = null;
	int currNumMatches = 0;
	DefaultListModel<Athlete> matchInfoModel;
	HashMap<JButton,Team> buttonTeamMap = new HashMap<JButton, Team>();
	
	/**
	 * Launch the application.
	 */
	public StadiumWindow(GUI gui) {
		this.gui = gui;
		this.selfRef = this;
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
		athleteTeamModel.addAll(Arrays.asList(gui.game.playerTeam.getAthletes()));
		
		JList<Athlete> athleteTeamList = new JList<Athlete>(athleteTeamModel);
		athleteTeamList.setBounds(10, 228, 360, 285);
		athleteTeamList.setCellRenderer(new StadiumListCellRenderer());
		frmStadium.getContentPane().add(athleteTeamList);
		
		JLabel lblAthleteTeam = new JLabel(gui.game.playerTeam.getName());
		lblAthleteTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblAthleteTeam.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAthleteTeam.setBounds(10, 174, 357, 44);
		frmStadium.getContentPane().add(lblAthleteTeam);
		
		JLabel lblVs = new JLabel("VS");
		lblVs.setHorizontalAlignment(SwingConstants.CENTER);
		lblVs.setFont(new Font("Dialog", Font.BOLD, 40));
		lblVs.setBounds(380, 354, 124, 44);
		frmStadium.getContentPane().add(lblVs);
		
		lblOpposingTeam = new JLabel("No opponent selected");
		lblOpposingTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpposingTeam.setFont(new Font("Dialog", Font.BOLD, 18));
		lblOpposingTeam.setBounds(514, 174, 357, 44);
		frmStadium.getContentPane().add(lblOpposingTeam);
		
		matchInfoModel = new DefaultListModel<Athlete>();
		
		JList<Athlete> opposingTeamList = new JList<Athlete>(matchInfoModel);
		opposingTeamList.setBounds(514, 228, 360, 285);
		opposingTeamList.setCellRenderer(new StadiumListCellRenderer());
		frmStadium.getContentPane().add(opposingTeamList);
		
		JButton btnMatch1 = new JButton();
		btnMatch1.setBounds(55, 54, 150, 110);
		setupMatchButton(btnMatch1);
		frmStadium.getContentPane().add(btnMatch1);
		
		JButton btnMatch2 = new JButton();
		btnMatch2.setBounds(215, 54, 150, 110);
		setupMatchButton(btnMatch2);
		frmStadium.getContentPane().add(btnMatch2);
		
		JButton btnMatch3 = new JButton();
		btnMatch3.setBounds(375, 54, 150, 110);
		setupMatchButton(btnMatch3);
		frmStadium.getContentPane().add(btnMatch3);

		JButton btnMatch4 = new JButton();
		btnMatch4.setBounds(535, 54, 150, 110);
		setupMatchButton(btnMatch4);
		frmStadium.getContentPane().add(btnMatch4);
		
		JButton btnMatch5 = new JButton();
		btnMatch5.setBounds(695, 54, 150, 110);
		setupMatchButton(btnMatch5);
		frmStadium.getContentPane().add(btnMatch5);
		
		JButton btnBegin = new JButton("Begin!");
		btnBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(frmStadium, "Are You Sure?\nYou can't turn back after this", "Confirm Match",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					gui.game.enemyTeam = buttonTeamMap.get(selectedMatch);
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
	
	public void setupMatchButton(JButton button) {
		int matchId = currNumMatches + 1;
		currNumMatches++;
		
		button.setText(gui.game.matchManager.matchOptions.get(matchId).getName());
		button.setFocusPainted(false);
		button.setBorder(new BevelBorder(BevelBorder.RAISED));
		buttonTeamMap.put(button, gui.game.matchManager.matchOptions.get(matchId));
		
		// onhover listener
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				matchInfoModel.clear();
				lblOpposingTeam.setText(buttonTeamMap.get(button).getName());
				matchInfoModel.addAll(buttonTeamMap.get(button).getAthletesList());
			}
		});
		
		// offhover listener
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				matchInfoModel.clear();
				lblOpposingTeam.setText("No opponent selected");
				if (selectedMatch != null) {
					lblOpposingTeam.setText(buttonTeamMap.get(selectedMatch).getName());
					matchInfoModel.addAll(buttonTeamMap.get(selectedMatch).getAthletesList());
				}
			}
		});
				
		// click listener
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedMatch != null) {
					selectedMatch.setBorder(new BevelBorder(BevelBorder.RAISED));
				}
				matchInfoModel.clear();
				lblOpposingTeam.setText(buttonTeamMap.get(button).getName());
				matchInfoModel.addAll(gui.game.matchManager.matchOptions.get(matchId).getAthletesList());
				button.setBorder(new BevelBorder(BevelBorder.LOWERED));
				selectedMatch = button;
			}
		});
	}
	
	public void closeWindow() {
		frmStadium.dispose();
	}
}

@SuppressWarnings("serial")
class StadiumListCellRenderer extends DefaultListCellRenderer {
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel c = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		c.setText(((Athlete) value).toExtendedString());
		return c;
	}
}
