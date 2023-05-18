package jda178_lbl40_SportsTournament;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.print.Printable;
import java.awt.event.ActionEvent;
import java.util.Random;

public class DebugWindow {

	public GUI gui;
	private JFrame frame;
	private DebugWindow selfRef;
	
	
	public DebugWindow(GUI gui) {
		this.gui = gui;
		this.selfRef = this;
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

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton btnNewButton = new JButton("Create Athlete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Athlete athlete = new Athlete(gui.game);
				System.out.println(athlete.toString());
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Show Used Names");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(gui.game.namesInUse);
			}
		});
		
		JButton btnNewButton_2 = new JButton("Create Item");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = new Random().nextInt(gui.game.itemList.items.length-1);
				System.out.println(n);
				System.out.println(gui.game.itemList.items[n]);
			}
		});
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Go back ̿̿'̿'\\̵͇̿̿\\з=( ͠° ͟ʖ ͡°)=ε/̵͇̿̿/'̿̿ ̿ ̿ ̿ ̿ ̿");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeDebugWindow(selfRef);
			}
		});
		frame.getContentPane().add(btnNewButton_3);
	}
	
	public void closeWindow() {
		frame.dispose();
	}

}
