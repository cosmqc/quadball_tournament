package gui;

import base.*;
import exceptions.NotEnoughMoneyException;
import exceptions.TeamFullException;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class ShopWindow {

	Purchasable selectedPurchase;
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
		lblAthletesForSale.setBounds(20, 12, 250, 44);
		buyPanel.add(lblAthletesForSale);

		JLabel lblItemsForSale = new JLabel("Items For Sale");
		lblItemsForSale.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemsForSale.setFont(new Font("Dialog", Font.BOLD, 18));
		lblItemsForSale.setBounds(280, 12, 250, 44);
		buyPanel.add(lblItemsForSale);

		DefaultListModel<Athlete> buyAthleteListModel = new DefaultListModel<>();
		buyAthleteListModel.addAll(game.shopManager.athletesInShop);
		JList<Athlete> athleteBuyList = new JList<Athlete>(buyAthleteListModel);
		athleteBuyList.setBounds(20, 55, 250, 355);
		buyPanel.add(athleteBuyList);

		DefaultListModel<Item> buyItemListModel = new DefaultListModel<>();
		buyItemListModel.addAll(game.shopManager.itemsInShop);
		JList<Item> itemBuyList = new JList<Item>(buyItemListModel);
		itemBuyList.setBounds(280, 55, 250, 355);
		buyPanel.add(itemBuyList);

		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (itemBuyList.getSelectedValue() == null && athleteBuyList.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frmShop, "Please select an athlete or item to buy", "Nothing Selected",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int choice = JOptionPane.showConfirmDialog(frmShop, "Are You Sure?", "Confirm Purchase",
							JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						try {
							if (itemBuyList.getSelectedValue() != null) {
								itemBuyList.getSelectedValue().buy();
								refreshBuyItem(buyItemListModel);
							} else {
								athleteBuyList.getSelectedValue().buy();
								refreshBuyAthlete(buyAthleteListModel);
							}
						} catch (NotEnoughMoneyException error) {
							JOptionPane.showMessageDialog(frmShop, error.getMessage(), "Not enough money",
									JOptionPane.ERROR_MESSAGE);
						} catch (TeamFullException error) {
							JOptionPane.showMessageDialog(frmShop, error.getMessage(), "Team Full",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		btnPurchase.setBounds(551, 366, 176, 44);
		buyPanel.add(btnPurchase);

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
		lblAthletesOwned.setBounds(10, 10, 250, 44);
		sellPanel.add(lblAthletesOwned);

		DefaultListModel<Athlete> sellAthleteListModel = new DefaultListModel<>();
		sellAthleteListModel.addAll(game.playerTeam.getAllPlayers());
		JList<Athlete> athleteSellList = new JList<Athlete>(sellAthleteListModel);
		athleteSellList.setBounds(20, 53, 250, 355);
		sellPanel.add(athleteSellList);

		DefaultListModel<Item> sellItemListModel = new DefaultListModel<>();
		sellItemListModel.addAll(game.itemsInInventory);
		JList<Item> itemSellList = new JList<Item>(sellItemListModel);
		itemSellList.setBounds(280, 53, 250, 355);
		sellPanel.add(itemSellList);

		JLabel lblItemsOwned = new JLabel("Items Owned");
		lblItemsOwned.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemsOwned.setFont(new Font("Dialog", Font.BOLD, 18));
		lblItemsOwned.setBounds(280, 10, 250, 44);
		sellPanel.add(lblItemsOwned);
		
		JTextPane purchaseInfoBox = new JTextPane();
		purchaseInfoBox.setText("Select an athlete or item to view info.");
		purchaseInfoBox.setFont(new Font("Dialog", Font.PLAIN, 18));
		purchaseInfoBox.setEditable(false);
		purchaseInfoBox.setBounds(551, 55, 300, 281);
		buyPanel.add(purchaseInfoBox);
		
		athleteBuyList.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent selection) {
				selectPurchasable(selection, athleteBuyList, itemBuyList, purchaseInfoBox);
			}
		});
		
		itemBuyList.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent selection) {
				selectPurchasable(selection, itemBuyList, athleteBuyList, purchaseInfoBox);
			}
		});

		lblSellMoneyDisplay = new JLabel(String.format("Money: $%d", game.playerMoney));
		lblSellMoneyDisplay.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSellMoneyDisplay.setBounds(20, 509, 161, 38);
		sellPanel.add(lblSellMoneyDisplay);
		
		JTextPane sellInfoBox = new JTextPane();
		sellInfoBox.setText("Select an athlete or item to view info.");
		sellInfoBox.setFont(new Font("Dialog", Font.PLAIN, 18));
		sellInfoBox.setEditable(false);
		sellInfoBox.setBounds(551, 53, 300, 281);
		sellPanel.add(sellInfoBox);
		
		athleteSellList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent selection) {
				selectPurchasable(selection, athleteSellList, itemSellList, sellInfoBox);
			}
		});
		
		itemSellList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent selection) {
				selectPurchasable(selection, itemSellList, athleteSellList, sellInfoBox);
			}
		});

		JButton btnSell = new JButton("Sell");
		btnSell.setBounds(551, 364, 176, 44);
		sellPanel.add(btnSell);
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (itemSellList.getSelectedValue() == null && athleteSellList.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frmShop, "Please select an athlete or item to sell", "Nothing Selected",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int choice = JOptionPane.showConfirmDialog(frmShop, "Are You Sure?", "Confirm Sale",
							JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						if (itemSellList.getSelectedValue() != null) {
							itemSellList.getSelectedValue().sell();
							refreshSellItem(sellItemListModel);
						} else {
							athleteSellList.getSelectedValue().sell();
							refreshSellAthlete(sellAthleteListModel);
						}
					}
				}
			}
		});
		

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

	public void refreshSellItem(DefaultListModel<Item> 	list) {
		refreshMoneyLabels();
		list.removeAllElements();
		list.addAll(game.itemsInInventory);
		frmShop.repaint();
	}

	public void refreshMoneyLabels() {
		lblBuyMoneyDisplay.setText(String.format("Money: $%d", game.playerMoney));
		lblSellMoneyDisplay.setText(String.format("Money: $%d", game.playerMoney));
	}
	
	public void selectPurchasable(ListSelectionEvent event, JList<? extends Purchasable> clickedList, JList<? extends Purchasable> otherList, JTextPane purchaseInfoBox) {
		// removes selection from the other list
		if (clickedList.getSelectedValue() != null) {
			otherList.clearSelection();
			selectedPurchase = clickedList.getSelectedValue();
			purchaseInfoBox.setText(selectedPurchase.toShopString());
		}
	}
}
