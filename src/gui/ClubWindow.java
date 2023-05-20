package gui;

import base.*;
import exceptions.TeamFullException;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class ClubWindow {
	GUI gui;
	ClubWindow selfRef;
	JFrame frmClub;
	Athlete selectedAthlete;
	Item selectedItem;

	JButton nickButton;
	JButton itemButton;

	/**
	 * Launch the application.
	 */
	public ClubWindow(GUI gui) {
		this.gui = gui;
		this.selfRef = this;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frmClub.setVisible(true);
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
		frmClub = new JFrame();
		frmClub.setTitle("Club");
		frmClub.setResizable(false);
		frmClub.setBounds(gui.x, gui.y, 900, 650);
		frmClub.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClub.getContentPane().setLayout(null);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeClubWindow(selfRef);
			}
		});
		btnBack.setBounds(682, 552, 176, 44);
		frmClub.getContentPane().add(btnBack);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Dialog", Font.BOLD, 18));
		tabbedPane.setBounds(10, 10, 866, 593);
		frmClub.getContentPane().add(tabbedPane);

		DefaultListModel<Athlete> athleteTeamModel = new DefaultListModel<>();
		athleteTeamModel.addAll(gui.game.playerTeam.getAthletes());

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

		DefaultListModel<Athlete> athleteReserveModel = new DefaultListModel<>();
		athleteReserveModel.addAll(gui.game.playerTeam.getSubs());

		JLabel lblAthletesInReserve = new JLabel("Athletes In Reserve");
		lblAthletesInReserve.setHorizontalAlignment(SwingConstants.CENTER);
		lblAthletesInReserve.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAthletesInReserve.setBounds(10, 313, 300, 44);
		viewPanel.add(lblAthletesInReserve);

		JList<Athlete> athleteReserveList = new JList<Athlete>(athleteReserveModel);
		athleteReserveList.setBounds(10, 356, 300, 150);
		viewPanel.add(athleteReserveList);

		JTextPane txtpnInfo = new JTextPane();
		txtpnInfo.setEditable(false);
		txtpnInfo.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtpnInfo.setBounds(550, 53, 300, 250);
		viewPanel.add(txtpnInfo);
		txtpnInfo.setText("No Athlete Selected");

		athleteTeamList.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent selection) {
				// removes selection from the other list
				athleteReserveList.clearSelection();
				if (!selection.getValueIsAdjusting()) {
					selectedAthlete = athleteTeamList.getSelectedValue();
					if (selectedAthlete != null) {
						txtpnInfo.setText(selectedAthlete.toClubString());
					}
				}
				refreshNickButton();
				refreshItemButton();
			}
		});

		athleteReserveList.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent selection) {
				// removes selection from the other list
				athleteTeamList.clearSelection();
				if (!selection.getValueIsAdjusting()) {
					selectedAthlete = athleteReserveList.getSelectedValue();
					if (selectedAthlete != null) {
						txtpnInfo.setText(selectedAthlete.toClubString());
					}
				}
				refreshNickButton();
				refreshItemButton();
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
					JOptionPane.showMessageDialog(frmClub, "Please select an athlete to sub out", "No Athlete Selected",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Athlete athlete = athleteTeamList.getSelectedValue();
					try {
						gui.game.playerTeam.bench(athlete);
						refreshAthleteList(athleteTeamModel);
						refreshSubList(athleteReserveModel);
					} catch (IllegalArgumentException error) {
						System.out.println(error);
						// TODO: display error. backend sub will not have gone through but that needs to
						// be shown to the player
					}
				}
			}
		});
		btnSubOut.setBounds(320, 258, 97, 44);
		viewPanel.add(btnSubOut);

		JButton btnSubIn = new JButton("Sub In");
		btnSubIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (athleteReserveList.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frmClub, "Please select an athlete to sub in", "No Athlete Selected",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Athlete athlete = athleteReserveList.getSelectedValue();
					try {
						gui.game.playerTeam.promote(athlete);
						refreshAthleteList(athleteTeamModel);
						refreshSubList(athleteReserveModel);
					} catch (TeamFullException error) {
						JOptionPane.showMessageDialog(frmClub, error.getMessage(), "Team Full!",
								JOptionPane.ERROR_MESSAGE);
					} catch (IllegalArgumentException error) {
						System.out.println(error);
						System.exit(1);
						// TODO: display error. backend sub will not have gone through but that needs to
						// be shown to the player
					}
				}
			}
		});
		btnSubIn.setBounds(320, 462, 97, 44);
		viewPanel.add(btnSubIn);

		itemButton = new JButton("Unequip Item");
		itemButton.setBounds(705, 313, 140, 37);
		itemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = selectedAthlete.unequipItem();
				gui.game.itemsInInventory.add(item);
			}
		});
		viewPanel.add(itemButton);
		refreshItemButton();

		nickButton = new JButton("Add Nickname");
		nickButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nick = (String) JOptionPane.showInputDialog(frmClub,
						"What do you want to nickname " + selectedAthlete.getRawName() + "?", "");
				if (nick != null) {
					if (nick.isBlank()) {
						nick = null;
					}
					selectedAthlete.setNickname(nick);
					refreshNickButton();
					refreshAthleteList(athleteTeamModel);
					refreshSubList(athleteReserveModel);
					txtpnInfo.setText("No Athlete Selected");
				}
			}
		});
		nickButton.setBounds(555, 313, 140, 37);
		viewPanel.add(nickButton);
		refreshNickButton();

		JPanel inventoryPanel = new JPanel();
		tabbedPane.addTab("Inventory", null, inventoryPanel, null);
		inventoryPanel.setLayout(null);

		JLabel lblItemsOwned = new JLabel("Items Owned");
		lblItemsOwned.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemsOwned.setFont(new Font("Dialog", Font.BOLD, 18));
		lblItemsOwned.setBounds(10, 10, 300, 44);
		inventoryPanel.add(lblItemsOwned);

		DefaultListModel<Item> inventoryModel = new DefaultListModel<Item>();
		inventoryModel.addAll(gui.game.itemsInInventory);

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

			public void valueChanged(ListSelectionEvent clickedItem) {
				if (!clickedItem.getValueIsAdjusting()) {
					selectedItem = itemList.getSelectedValue();
					if (selectedItem != null) {
						txtpnItemInfo.setText(selectedItem.getClubString());
					}
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
					JOptionPane.showMessageDialog(frmClub, "Please select an item to use", "No Item Selected",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Athlete athleteChosen = (Athlete) JOptionPane.showInputDialog(frmClub,
							"Which athlete would you like to give the item to?", "Use Item", JOptionPane.PLAIN_MESSAGE,
							null, gui.game.playerTeam.getAllPlayers().toArray(), null);
					if (athleteChosen != null) {
						Item oldItem = athleteChosen.equipItem(itemList.getSelectedValue());
						if (oldItem != null) {
							gui.game.itemsInInventory.add(oldItem);
						}
						gui.game.itemsInInventory.remove(itemList.getSelectedValue());
						txtpnItemInfo.setText("No item selected");
						refreshItemList(inventoryModel);
						refreshItemButton();
					}
				}
			}
		});
		btnUseItem.setBounds(320, 257, 126, 44);
		inventoryPanel.add(btnUseItem);

		// makes sure something can't be selected on another tab, reduces edge cases
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				selectedAthlete = null;
				selectedItem = null;
				refreshItemButton();
			}
		});

	}

	public void refreshNickButton() {
		if (selectedAthlete != null) {
			if (selectedAthlete.getNickname() == null) {
				nickButton.setText("Add Nickname");
			} else {
				nickButton.setText("Edit Nickname");
			}
			nickButton.setVisible(true);
		} else {
			nickButton.setVisible(false);
		}
		frmClub.repaint();
	}

	public void refreshItemButton() {
		if (selectedAthlete != null) {
			itemButton.setVisible(selectedAthlete.getEquippedItem() != null);
		} else {
			itemButton.setVisible(false);
		}
	}

	public void refreshAthleteList(DefaultListModel<Athlete> list) {
		list.removeAllElements();
		list.addAll(gui.game.playerTeam.getAthletes());
		frmClub.repaint();
	}

	public void refreshSubList(DefaultListModel<Athlete> list) {
		list.removeAllElements();
		list.addAll(gui.game.playerTeam.getSubs());
		frmClub.repaint();
	}

	public void refreshItemList(DefaultListModel<Item> list) {
		list.removeAllElements();
		list.addAll(gui.game.itemsInInventory);
		frmClub.repaint();
	}

	public void closeWindow() {
		frmClub.dispose();
	}
}
