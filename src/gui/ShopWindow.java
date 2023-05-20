package gui;

import base.*;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ShopWindow {

	// TODO: Clear selection when switching lists, there shouldn't be a selection in
	// both Item and Athlete lists

	JFrame frmShop;
	GUI gui;
	ShopWindow selfRef;
	GameEnvironment game;

	JLabel lblBuyMoneyDisplay;
	JLabel lblSellMoneyDisplay;

	/**
	 * Launch the application.
	 */
	public ShopWindow(GUI gui) {
		this.gui = gui;
		this.selfRef = this;
		this.game = gui.game;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frmShop.setVisible(true);
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
		frmShop = new JFrame();
		frmShop.setTitle("Shop");
		frmShop.setResizable(false);
		frmShop.setBounds(gui.x, gui.y, 900, 650);
		frmShop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShop.getContentPane().setLayout(null);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeShopWindow(selfRef);
			}
		});
		btnBack.setBounds(682, 552, 176, 44);
		frmShop.getContentPane().add(btnBack);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 12, 866, 596);
		tabbedPane.setFont(new Font("Dialog", Font.BOLD, 18));
		frmShop.getContentPane().add(tabbedPane);

		JPanel buyPanel = new JPanel();
		tabbedPane.addTab("Buy", null, buyPanel, null);
		buyPanel.setLayout(null);

		JLabel lblAthletesForSale = new JLabel("Athletes For Sale");
		lblAthletesForSale.setHorizontalAlignment(SwingConstants.CENTER);
		lblAthletesForSale.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAthletesForSale.setBounds(10, 12, 357, 44);
		buyPanel.add(lblAthletesForSale);

		JLabel lblItemsForSale = new JLabel("Items For Sale");
		lblItemsForSale.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemsForSale.setFont(new Font("Dialog", Font.BOLD, 18));
		lblItemsForSale.setBounds(377, 12, 357, 44);
		buyPanel.add(lblItemsForSale);

		DefaultListModel<Athlete> buyAthleteListModel = new DefaultListModel<>();
		buyAthleteListModel.addAll(game.shopManager.athletesInShop);
		JList<Athlete> athleteBuyList = new JList<Athlete>(buyAthleteListModel);
		athleteBuyList.setBounds(20, 55, 357, 355);
		buyPanel.add(athleteBuyList);

		// TODO: Condense buy athlete / buy item into "BUY"

		JButton btnBuyAthlete = new JButton("Buy Athlete");
		btnBuyAthlete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (athleteBuyList.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frmShop, "Please select an athlete to buy", "No Athlete Selected",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int choice = JOptionPane.showConfirmDialog(frmShop, "Are You Sure?", "Buy Athlete",
							JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						// TODO: add cost implementation to purchasables
						athleteBuyList.getSelectedValue().buy();
						refreshBuyAthlete(buyAthleteListModel);

					}
				}
			}
		});
		btnBuyAthlete.setBounds(95, 420, 176, 44);
		buyPanel.add(btnBuyAthlete);

		DefaultListModel<Item> buyItemListModel = new DefaultListModel<>();
		buyItemListModel.addAll(game.shopManager.itemsInShop);
		JList<Item> itemBuyList = new JList<Item>(buyItemListModel);
		itemBuyList.setBounds(387, 55, 357, 355);
		buyPanel.add(itemBuyList);

		JButton btnBuyItem = new JButton("Buy Item");
		btnBuyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (itemBuyList.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frmShop, "Please select an item to buy", "No Item Selected",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int choice = JOptionPane.showConfirmDialog(frmShop, "Are You Sure?", "Buy Item",
							JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						itemBuyList.getSelectedValue().buy();
						refreshBuyItem(buyItemListModel);
					}
				}
			}
		});
		btnBuyItem.setBounds(496, 420, 176, 44);
		buyPanel.add(btnBuyItem);

		lblBuyMoneyDisplay = new JLabel(String.format("Money: $%d", game.playerMoney));
		lblBuyMoneyDisplay.setFont(new Font("Dialog", Font.BOLD, 18));
		lblBuyMoneyDisplay.setBounds(20, 509, 161, 38);
		buyPanel.add(lblBuyMoneyDisplay);

		JPanel sellPanel = new JPanel();
		tabbedPane.addTab("Sell", null, sellPanel, null);
		sellPanel.setLayout(null);

		JLabel lblAthletesOwned = new JLabel("Athletes Owned");
		lblAthletesOwned.setHorizontalAlignment(SwingConstants.CENTER);
		lblAthletesOwned.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAthletesOwned.setBounds(10, 10, 357, 44);
		sellPanel.add(lblAthletesOwned);

		DefaultListModel<Athlete> sellAthleteListModel = new DefaultListModel<>();
		sellAthleteListModel.addAll(game.playerTeam.getAllPlayers());
		JList<Athlete> athleteSellList = new JList<Athlete>(sellAthleteListModel);
		athleteSellList.setBounds(20, 53, 357, 355);
		sellPanel.add(athleteSellList);

		DefaultListModel<Item> sellItemListModel = new DefaultListModel<>();
		sellItemListModel.addAll(game.itemsInInventory);
		JList<Item> itemSellList = new JList<Item>(sellItemListModel);
		itemSellList.setBounds(387, 53, 357, 355);
		sellPanel.add(itemSellList);

		JLabel lblItemsOwned = new JLabel("Items Owned");
		lblItemsOwned.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemsOwned.setFont(new Font("Dialog", Font.BOLD, 18));
		lblItemsOwned.setBounds(377, 10, 357, 44);
		sellPanel.add(lblItemsOwned);

		// TODO: Condense sell athlete / sell item into "SELL"

		JButton btnSellAthlete = new JButton("Sell Athlete");
		btnSellAthlete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (athleteSellList.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frmShop, "Please select an athlete to sell", "No Athlete Selected",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int choice = JOptionPane.showConfirmDialog(frmShop, "Are You Sure?", "Sell Athlete",
							JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						athleteSellList.getSelectedValue().sell();
						refreshSellAthlete(sellAthleteListModel);
					}
				}
			}
		});
		btnSellAthlete.setBounds(95, 418, 176, 44);
		sellPanel.add(btnSellAthlete);

		JButton btnSellItem = new JButton("Sell Item");
		btnSellItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (itemSellList.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frmShop, "Please select an item to sell", "No Item Selected",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int choice = JOptionPane.showConfirmDialog(frmShop, "Are You Sure?", "Sell Item",
							JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						itemSellList.getSelectedValue().sell();
						refreshSellItem(sellItemListModel);
					}
				}
			}
		});
		btnSellItem.setBounds(496, 418, 176, 44);
		sellPanel.add(btnSellItem);

		lblSellMoneyDisplay = new JLabel(String.format("Money: $%d", game.playerMoney));
		lblSellMoneyDisplay.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSellMoneyDisplay.setBounds(20, 509, 161, 38);
		sellPanel.add(lblSellMoneyDisplay);

		// refreshes all lists and selections on tab change.
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				refreshBuyAthlete(buyAthleteListModel);
				refreshBuyItem(buyItemListModel);
				refreshSellAthlete(sellAthleteListModel);
				refreshSellItem(sellItemListModel);

			}
		});
	}

	public void closeWindow() {
		frmShop.dispose();
	}

	public void refreshBuyAthlete(DefaultListModel<Athlete> list) {
		refreshMoneyLabels();
		list.removeAllElements();
		list.addAll(game.shopManager.athletesInShop);
		frmShop.repaint();
	}

	public void refreshBuyItem(DefaultListModel<Item> list) {
		refreshMoneyLabels();
		list.removeAllElements();
		list.addAll(game.shopManager.itemsInShop);
		frmShop.repaint();
	}

	public void refreshSellAthlete(DefaultListModel<Athlete> list) {
		refreshMoneyLabels();
		list.removeAllElements();
		list.addAll(game.playerTeam.getAllPlayers());
		frmShop.repaint();
	}

	public void refreshSellItem(DefaultListModel<Item> list) {
		refreshMoneyLabels();
		list.removeAllElements();
		list.addAll(game.itemsInInventory);
		frmShop.repaint();
	}

	public void refreshMoneyLabels() {
		lblBuyMoneyDisplay.setText(String.format("Money: $%d", game.playerMoney));
		lblSellMoneyDisplay.setText(String.format("Money: $%d", game.playerMoney));
	}
}
