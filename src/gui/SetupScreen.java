package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JRadioButton;

public class SetupScreen {

	private JFrame frame;
	private JTextField textField;
	private JLabel lbltheLengthMust;
	private JLabel lblHowLongWould;
	private JLabel lblPleaseChooseA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupScreen window = new SetupScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SetupScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWhatIsYour = new JLabel("What is your team name?");
		lblWhatIsYour.setVerticalAlignment(SwingConstants.TOP);
		lblWhatIsYour.setHorizontalAlignment(SwingConstants.LEFT);
		lblWhatIsYour.setFont(new Font("Dialog", Font.BOLD, 16));
		lblWhatIsYour.setBounds(10, 60, 448, 44);
		frame.getContentPane().add(lblWhatIsYour);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField.setBounds(247, 56, 595, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblWelcomeToSports = new JLabel("Welcome to Sports Manager!");
		lblWelcomeToSports.setFont(new Font("Dialog", Font.BOLD, 18));
		lblWelcomeToSports.setBounds(0, 0, 900, 44);
		lblWelcomeToSports.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblWelcomeToSports);
		
		lbltheLengthMust = new JLabel("(The length must be between 3 and 15 characters and must not include special characters)");
		lbltheLengthMust.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbltheLengthMust.setBounds(20, 82, 595, 15);
		frame.getContentPane().add(lbltheLengthMust);
		
		lblHowLongWould = new JLabel("How long would you like the season to last?");
		lblHowLongWould.setVerticalAlignment(SwingConstants.TOP);
		lblHowLongWould.setHorizontalAlignment(SwingConstants.LEFT);
		lblHowLongWould.setFont(new Font("Dialog", Font.BOLD, 16));
		lblHowLongWould.setBounds(10, 121, 448, 44);
		frame.getContentPane().add(lblHowLongWould);
		
		JSlider slider = new JSlider();
		slider.setSnapToTicks(true);
		slider.setValue(1);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(1);
		slider.setMaximum(15);
		slider.setMinimum(5);
		slider.setBounds(431, 113, 411, 50);
		frame.getContentPane().add(slider);
		
		lblPleaseChooseA = new JLabel("Please choose a difficulty:");
		lblPleaseChooseA.setVerticalAlignment(SwingConstants.TOP);
		lblPleaseChooseA.setHorizontalAlignment(SwingConstants.LEFT);
		lblPleaseChooseA.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPleaseChooseA.setBounds(10, 177, 448, 44);
		frame.getContentPane().add(lblPleaseChooseA);
		
		JRadioButton rdbtnEasy = new JRadioButton("Easy");
		rdbtnEasy.setBounds(20, 199, 149, 23);
		frame.getContentPane().add(rdbtnEasy);
		
		JRadioButton rdbtnHard = new JRadioButton("Hard");
		rdbtnHard.setBounds(20, 229, 149, 23);
		frame.getContentPane().add(rdbtnHard);
	}
}