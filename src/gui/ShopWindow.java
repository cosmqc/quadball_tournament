package gui;

import base.*;
import java.util.ArrayList;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

public class ShopWindow {
	private GUI gui;
	private ShopWindow selfRef;
	private JFrame frame;
	public GameEnvironment game;
	private ArrayList<Athlete> athletes = new ArrayList<>();
	private ArrayList<Item> items = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public ShopWindow(GUI gui) {
		this.gui = gui;
		this.selfRef = this;
		this.game = gui.game;
		athletes.add(new Athlete(game, "Test", 1,2,3,4,5));
		athletes.add(new Athlete(game, "Testing", 4,2,5,7,5));
		athletes.add(new Athlete(game, "Testeroo", 5,3,8,9,6));
		items.add(new Item("Rocket Shoes", new int[]{1, 0, 1, 0}, 3, "Zoom into the sky!"));
		items.add(new Item("Energy Drink", new int[]{0, 0, 2, -1}, 2, "Boost your stamina!"));
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
				gui.returnFromShop(selfRef);
			}
		});
		btnBack.setBounds(682, 552, 176, 44);
		frame.getContentPane().add(btnBack);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 12, 866, 596);
		tabbedPane.setFont( new Font( "Dialog", Font.BOLD, 18 ) );
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Buy", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblAthletesForSale = new JLabel("Athletes For Sale");
		lblAthletesForSale.setHorizontalAlignment(SwingConstants.CENTER);
		lblAthletesForSale.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAthletesForSale.setBounds(10, 12, 357, 44);
		panel.add(lblAthletesForSale);
		
		JLabel lblItemsForSale = new JLabel("Items For Sale");
		lblItemsForSale.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemsForSale.setFont(new Font("Dialog", Font.BOLD, 18));
		lblItemsForSale.setBounds(377, 12, 357, 44);
		panel.add(lblItemsForSale);
		
		
		
		DefaultListModel<Athlete> buyAthleteListModel = new DefaultListModel<>();
		buyAthleteListModel.addAll(athletes);
		JList<Athlete> athleteBuyList = new JList<Athlete>(buyAthleteListModel);
		athleteBuyList.setBounds(20, 55, 357, 355);
		panel.add(athleteBuyList);
		
		JButton btnBuyAthlete = new JButton("Buy Athlete");
		btnBuyAthlete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (athleteBuyList.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Please select an athlete to buy", "No Athlete Selected", JOptionPane.ERROR_MESSAGE);
				} else {
					int choice = JOptionPane.showConfirmDialog(frame, "Are You Sure?",  "Buy Athlete", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						System.out.println("Athlete Bought!");
					}
				}
			}
		});
		btnBuyAthlete.setBounds(95, 420, 176, 44);
		panel.add(btnBuyAthlete);
		
		DefaultListModel<Item> buyItemListModel = new DefaultListModel<>();
		buyItemListModel.addAll(items);
		JList<Item> itemBuyList = new JList<Item>(buyItemListModel);
		itemBuyList.setBounds(387, 55, 357, 355);
		panel.add(itemBuyList);
		
		JButton btnBuyItem = new JButton("Buy Item");
		btnBuyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (itemBuyList.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Please select an item to buy", "No Item Selected", JOptionPane.ERROR_MESSAGE);
				} else {
					int choice = JOptionPane.showConfirmDialog(frame, "Are You Sure?",  "Buy Item", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						System.out.println("Item Bought!");
					}
				}
			}
		});
		btnBuyItem.setBounds(496, 420, 176, 44);
		panel.add(btnBuyItem);
		
		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Sell", null, panel2, null);
		panel2.setLayout(null);
		
		JLabel lblAthletesOwned = new JLabel("Athletes Owned");
		lblAthletesOwned.setHorizontalAlignment(SwingConstants.CENTER);
		lblAthletesOwned.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAthletesOwned.setBounds(10, 10, 357, 44);
		panel2.add(lblAthletesOwned);
		
		DefaultListModel<Athlete> sellAthleteListModel = new DefaultListModel<>();
		sellAthleteListModel.addAll(athletes);
		JList<Athlete> athleteSellList = new JList<Athlete>(sellAthleteListModel);
		athleteSellList.setBounds(20, 53, 357, 355);
		panel2.add(athleteSellList);
		
		DefaultListModel<Item> sellItemListModel = new DefaultListModel<>();
		sellItemListModel.addAll(items);
		JList<Item> itemSellList = new JList<Item>(sellItemListModel);
		itemSellList.setBounds(387, 53, 357, 355);
		panel2.add(itemSellList);
		
		JLabel lblItemsOwned = new JLabel("Items Owned");
		lblItemsOwned.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemsOwned.setFont(new Font("Dialog", Font.BOLD, 18));
		lblItemsOwned.setBounds(377, 10, 357, 44);
		panel2.add(lblItemsOwned);
		
		JButton btnSellAthlete = new JButton("Sell Athlete");
		btnSellAthlete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (athleteSellList.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Please select an athlete to sell", "No Athlete Selected", JOptionPane.ERROR_MESSAGE);
				} else {
					int choice = JOptionPane.showConfirmDialog(frame, "Are You Sure?",  "Sell Athlete", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						System.out.println("Athlete Sold!");
					}
				}
			}
		});
		btnSellAthlete.setBounds(95, 418, 176, 44);
		panel2.add(btnSellAthlete);
		
		JButton btnSellItem = new JButton("Sell Item");
		btnSellItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (itemSellList.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Please select an item to sell", "No Item Selected", JOptionPane.ERROR_MESSAGE);
				} else {
					int choice = JOptionPane.showConfirmDialog(frame, "Are You Sure?",  "Sell Item", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						System.out.println("Item Sold!");
					}
				}
			}
		});
		btnSellItem.setBounds(496, 418, 176, 44);
		panel2.add(btnSellItem);
	}
	public void closeWindow() {
		frame.dispose();
	}
}
