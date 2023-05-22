package gui;

import base.*;
import exceptions.InvalidSwapException;

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
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

public class ClubWindow {
	GUI gui;
	ClubWindow selfRef;
	JFrame frmClub;
	Athlete oldSelectedAthlete;
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

		DefaultListModel<Athlete> athleteTeamModel = new DefaultListModel<Athlete>();
		athleteTeamModel.addAll(Arrays.asList(gui.game.playerTeam.getAthletes()));

		JPanel viewPanel = new JPanel();
		tabbedPane.addTab("View Team", null, viewPanel, null);
		viewPanel.setLayout(null);

		JLabel lblAthletesInTeam = new JLabel("Athletes In Team");
		lblAthletesInTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblAthletesInTeam.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAthletesInTeam.setBounds(10, 10, 300, 44);
		viewPanel.add(lblAthletesInTeam);

		JList<Athlete> athleteTeamList = new JList<Athlete>(athleteTeamModel);
		athleteTeamList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		athleteTeamList.setBounds(10, 53, 300, 250);
		viewPanel.add(athleteTeamList);

		DefaultListModel<Athlete> athleteReserveModel = new DefaultListModel<Athlete>();
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

		JButton btnSwapUp = new JButton("↑");
		btnSwapUp.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnSwapUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedAthlete != null) {
					try {
						gui.game.playerTeam.swapUp(selectedAthlete);
						refreshAthleteList(athleteTeamModel);
						refreshSubList(athleteReserveModel);
					} catch (IllegalArgumentException error) {
						System.out.println(error);
						JOptionPane.showMessageDialog(frmClub, error.getMessage(), "No Athlete Selected",
								JOptionPane.ERROR_MESSAGE);
					} catch (InvalidSwapException error) {
						JOptionPane.showMessageDialog(frmClub, error.getMessage(), "Cannot move Athlete",
								JOptionPane.ERROR_MESSAGE);
					}
					txtpnInfo.setText("No Athlete selected");
				}
			}
		});
		btnSwapUp.setBounds(320, 279, 97, 44);
		viewPanel.add(btnSwapUp);

		JButton btnSwapDown = new JButton("↓");
		btnSwapDown.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnSwapDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedAthlete != null) {
					try {
						gui.game.playerTeam.swapDown(selectedAthlete);
						refreshAthleteList(athleteTeamModel);
						refreshSubList(athleteReserveModel);
					} catch (IllegalArgumentException error) {
						System.out.println(error);
						JOptionPane.showMessageDialog(frmClub, error.getMessage(), "No Athlete Selected",
								JOptionPane.ERROR_MESSAGE);
					} catch (InvalidSwapException error) {
						JOptionPane.showMessageDialog(frmClub, error.getMessage(), "Cannot move Athlete",
								JOptionPane.ERROR_MESSAGE);
					}
					txtpnInfo.setText("No Athlete selected");
				}
			}
		});
		btnSwapDown.setBounds(320, 334, 97, 44);
		viewPanel.add(btnSwapDown);

		itemButton = new JButton("Unequip Item");
		itemButton.setBounds(705, 313, 140, 37);
		itemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = selectedAthlete.unequipItem();
				Athlete temp = selectedAthlete;
				gui.game.itemsInInventory.add(item);
				refreshItemButton();
				reselectSelected(temp, athleteTeamList, athleteReserveList);
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
					Athlete temp = selectedAthlete;
					refreshNickButton();
					refreshAthleteList(athleteTeamModel);
					refreshSubList(athleteReserveModel);
					reselectSelected(temp, athleteTeamList, athleteReserveList);
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
							null, gui.game.playerTeam.getAthletes(), null);
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

		// refresh all on tab change
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				selectedAthlete = null;
				refreshNickButton();
				refreshAthleteList(athleteTeamModel);
				refreshSubList(athleteReserveModel);

				selectedItem = null;
				txtpnItemInfo.setText("No item selected");
				refreshItemButton();
				refreshItemList(inventoryModel);
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
		list.addAll(Arrays.asList(gui.game.playerTeam.getAthletes()));
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
	
	public void reselectSelected(Athlete athlete, JList<Athlete> teamList, JList<Athlete> subList) {
		selectedAthlete = athlete;
		if (Arrays.asList(gui.game.playerTeam.getAthletes()).contains(athlete)) {
			DefaultListModel<Athlete> model = (DefaultListModel<Athlete>) teamList.getModel();
			int athleteIndex = model.indexOf(athlete);
			teamList.setSelectedIndex(athleteIndex);
		} else if (gui.game.playerTeam.getSubs().contains(athlete)) {
			DefaultListModel<Athlete> model = (DefaultListModel<Athlete>) subList.getModel();
			int subIndex = model.indexOf(athlete);
			subList.setSelectedIndex(subIndex);
		} else {
			throw new IllegalArgumentException("Player not in any team");
			
		}
	}
	
	public void closeWindow() {
		frmClub.dispose();
	}
}
