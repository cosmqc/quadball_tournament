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
		
		DefaultListModel<Athlete> athleteTeamModel = new DefaultListModel<>();
		athleteTeamModel.addAll(game.athletesInTeam);
		
		JPanel viewPanel = new JPanel();
		tabbedPane.addTab("View Team", null, viewPanel, null);
		viewPanel.setLayout(null);
		
		JLabel lblAthletesInTeam = new JLabel("Athletes In Team");
		lblAthletesInTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblAthletesInTeam.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAthletesInTeam.setBounds(10, 10, 300, 44);
		viewPanel.add(lblAthletesInTeam);
		
		JList<Athlete> athleteTeamList = new JList<Athlete>(athleteTeamModel);
		athleteTeamList.setBounds(10, 53, 300, 250);
		viewPanel.add(athleteTeamList);
		
		JLabel lblAthletesInReserve = new JLabel("Athletes In Reserve");
		lblAthletesInReserve.setHorizontalAlignment(SwingConstants.CENTER);
		lblAthletesInReserve.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAthletesInReserve.setBounds(10, 313, 300, 44);
		viewPanel.add(lblAthletesInReserve);
		
		JList<Athlete> athleteReserveList = new JList<Athlete>();
		athleteReserveList.setBounds(10, 356, 300, 150);
		viewPanel.add(athleteReserveList);
		
		JTextPane txtpnInfo = new JTextPane();
		txtpnInfo.setEditable(false);
		txtpnInfo.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtpnInfo.setBounds(550, 53, 300, 250);
		viewPanel.add(txtpnInfo);
		txtpnInfo.setText("No Athlete Selected");
		
		athleteTeamList.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent selectedAthlete) {
                if (!selectedAthlete.getValueIsAdjusting()) {
                	Athlete athleteInfo = athleteTeamList.getSelectedValue();
                	//TODO: list equipped item??
                	//TODO: Athlete.getEquipped() will return the item they're holding or null if its empty
                	txtpnInfo.setText(String.format("Name: %s \nOffence: %s \nDefence: %s \nSpeed: %s \nStamina: %s/%s",
                			athleteInfo.getName(),athleteInfo.getOffence(),athleteInfo.getDefence(),athleteInfo.getSpeed(),athleteInfo.getStamina(),athleteInfo.getRawStamina()));
                }
            }
        });
		
		
		JLabel lblAthleteInfo = new JLabel("Athlete Info");
		lblAthleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAthleteInfo.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAthleteInfo.setBounds(550, 12, 301, 44);
		viewPanel.add(lblAthleteInfo);
		
		JButton btnSubOut = new JButton("Sub Out");
		btnSubOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (athleteTeamList.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Please select an athlete to sub out", "No Athlete Selected",
							JOptionPane.ERROR_MESSAGE);
				} else {
					//TODO: Sub out athlete. Is this a Team.java?
				}
			}
		});
		btnSubOut.setBounds(320, 258, 97, 44);
		viewPanel.add(btnSubOut);
		
		JButton btnSubIn = new JButton("Sub In");
		btnSubIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (athleteReserveList.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Please select an athlete to sub in", "No Athlete Selected",
							JOptionPane.ERROR_MESSAGE);
				} else {
					//TODO: Sub in athlete. Is this a Team.java?
				}
			}
		});
		btnSubIn.setBounds(320, 462, 97, 44);
		viewPanel.add(btnSubIn);
		
		JPanel inventoryPanel = new JPanel();
		tabbedPane.addTab("Inventory", null, inventoryPanel, null);
		inventoryPanel.setLayout(null);
		
		JLabel lblItemsOwned = new JLabel("Items Owned");
		lblItemsOwned.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemsOwned.setFont(new Font("Dialog", Font.BOLD, 18));
		lblItemsOwned.setBounds(10, 10, 300, 44);
		inventoryPanel.add(lblItemsOwned);
		
		DefaultListModel<Item> inventoryModel = new DefaultListModel<>();
		inventoryModel.addAll(game.itemsInInventory);
		
		JList<Item> itemList = new JList<Item>(inventoryModel);
		itemList.setBounds(10, 51, 300, 250);
		inventoryPanel.add(itemList);
		
		JTextPane txtpnItemInfo = new JTextPane();
		txtpnItemInfo.setText("No Item Selected");
		txtpnItemInfo.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtpnItemInfo.setEditable(false);
		txtpnItemInfo.setBounds(550, 53, 300, 250);
		inventoryPanel.add(txtpnItemInfo);
		
		itemList.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent selectedItem) {
                if (!selectedItem.getValueIsAdjusting()) {
                	Item itemInfo = itemList.getSelectedValue();
                	txtpnItemInfo.setText(String.format("Name: %s \n%s \nEffects: \nOffence: %s\nDefence: %s\nSpeed: %s\nStamina: %s",
                			itemInfo.getName(),itemInfo.getDescription(),itemInfo.getEffect()[0],itemInfo.getEffect()[1],itemInfo.getEffect()[2],itemInfo.getEffect()[3]));
                }
            }
        });
		
		JLabel lblItemInfo = new JLabel("Item Info");
		lblItemInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemInfo.setFont(new Font("Dialog", Font.BOLD, 18));
		lblItemInfo.setBounds(549, 10, 301, 44);
		inventoryPanel.add(lblItemInfo);
		
		JButton btnUseItem = new JButton("Use Item On...");
		btnUseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (itemList.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Please select an item to use", "No Item Selected",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Athlete athleteChosen = (Athlete) JOptionPane.showInputDialog(frame, "Which athlete would you like to give the item to?", "Use Item", JOptionPane.PLAIN_MESSAGE,
		                    null, athleteTeamModel.toArray(), null);
					if(athleteChosen != null) {
						athleteChosen.equipItem(itemList.getSelectedValue());
					}
				}
			}
		});
		btnUseItem.setBounds(320, 257, 97, 44);
		inventoryPanel.add(btnUseItem);
		
		
		
		
	}
	
	public void closeWindow() {
		frame.dispose();
	}
}
