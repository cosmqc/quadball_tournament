package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class FinalWindow {

	GUI gui;
	FinalWindow selfRef;
	JFrame frmFinal;
	/**
	 * Launch the application.
	 */
	public FinalWindow(GUI gui) {
		this.gui = gui;
		this.selfRef = this;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frmFinal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize() {
		frmFinal = new JFrame();
		frmFinal.setTitle("Game Over");
		frmFinal.setResizable(false);
		frmFinal.setBounds(gui.x, gui.y, 900, 650);
		frmFinal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFinal.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GAME OVER");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(60, 25, 740, 231);
		frmFinal.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Money");
		lblNewLabel_1.setBounds(217, 154, 146, 102);
		frmFinal.getContentPane().add(lblNewLabel_1);
	}
}
