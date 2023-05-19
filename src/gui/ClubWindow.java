package gui;

import base.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.JTextPane;

public class ClubWindow {
	private GUI gui;
	private ClubWindow selfRef;
	private JFrame frame;
	public GameEnvironment game;

	/**
	 * Launch the application.
	 */
	public ClubWindow(GUI gui) {
		this.gui = gui;
		this.selfRef = this;
		this.game = gui.game;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frame.setVisible(true);
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
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(gui.x, gui.y, 900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeClubWindow(selfRef);
			}
		});
		btnBack.setBounds(682, 552, 176, 44);
		frame.getContentPane().add(btnBack);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Dialog", Font.BOLD, 18));
		tabbedPane.setBounds(10, 10, 866, 593);
		frame.getContentPane().add(tabbedPane);
		
		JPanel viewPanel = new JPanel();
		tabbedPane.addTab("View Team", null, viewPanel, null);
		viewPanel.setLayout(null);
		
		JLabel lblAthletesInTeam = new JLabel("Athletes In Team");
		lblAthletesInTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblAthletesInTeam.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAthletesInTeam.setBounds(10, 12, 357, 44);
		viewPanel.add(lblAthletesInTeam);
		
		DefaultListModel<Athlete> athleteTeamModel = new DefaultListModel<>();
		athleteTeamModel.addAll(game.athletesInTeam);
		JList<Athlete> athleteTeamList = new JList<Athlete>(athleteTeamModel);
		athleteTeamList.setBounds(20, 53, 357, 250);
		viewPanel.add(athleteTeamList);
		
		JLabel lblAthletesInReserve = new JLabel("Athletes In Reserve");
		lblAthletesInReserve.setHorizontalAlignment(SwingConstants.CENTER);
		lblAthletesInReserve.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAthletesInReserve.setBounds(10, 313, 357, 44);
		viewPanel.add(lblAthletesInReserve);
		
		JList<Athlete> athleteReserveList = new JList<Athlete>();
		athleteReserveList.setBounds(20, 356, 357, 150);
		viewPanel.add(athleteReserveList);
		
		JTextPane txtpnInfo = new JTextPane();
		txtpnInfo.setEditable(false);
		txtpnInfo.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtpnInfo.setBounds(494, 53, 357, 250);
		viewPanel.add(txtpnInfo);
		txtpnInfo.setText("No Athlete Selected");
		athleteTeamList.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent selectedItem) {
                if (!selectedItem.getValueIsAdjusting()) {
                	Athlete athleteInfo = athleteTeamList.getSelectedValue();
                	txtpnInfo.setText(String.format("Name: %s \nOffence: %s \nDefence: %s \nSpeed: %s \nStamina: %s/%s"
                			,athleteInfo.getName(),athleteInfo.getOffence(),athleteInfo.getDefence(),athleteInfo.getSpeed(),athleteInfo.getStamina(),athleteInfo.getRawStamina()));
                }
            }
        });
		
		
		JLabel lblAthleteInfo = new JLabel("Athlete Info");
		lblAthleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAthleteInfo.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAthleteInfo.setBounds(494, 12, 357, 44);
		viewPanel.add(lblAthleteInfo);
		
		JPanel inventoryPanel = new JPanel();
		tabbedPane.addTab("Inventory", null, inventoryPanel, null);
		inventoryPanel.setLayout(null);
		
		
		
	}
	
	public void closeWindow() {
		frame.dispose();
	}
}
