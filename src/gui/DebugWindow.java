package gui;

import base.*;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
				System.out.println(gui.game.itemManager.randomItem());
			}
		});

		JButton btnNewButton_4 = new JButton("<html>Add +R Item to +R Athlete<html>");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Athlete athlete = new Athlete(gui.game);

			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 30));
		frame.getContentPane().add(btnNewButton_4);
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("<html>Go back <br>̿̿'̿'\\̵͇̿̿\\з=( ͠° ͟ʖ ͡°)=ε/̵͇̿̿/'̿̿ ̿ ̿ ̿ </html>̿ ");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeDebugWindow(selfRef);
			}
		});
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_5 = new JButton("Random Team");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Team team = new Team(gui.game);
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		frame.getContentPane().add(btnNewButton_5);
	}

	public void closeWindow() {
		frame.dispose();
	}

}
