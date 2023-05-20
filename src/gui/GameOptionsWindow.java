package gui;

import base.*;
import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameOptionsWindow {
	private GUI gui;
	private GameOptionsWindow selfRef;
	private int athleteSlotsFilled;
	private ArrayList<Athlete> selectedAthletes;

	JFrame frmSportsManager;
	JTextField teamNameField;
	JTextPane athleteInfoBox;
	
	HashMap<JButton, Athlete> buttonAthleteMap = new HashMap<JButton, Athlete>();
	ArrayList<JButton> selectedButtons = new ArrayList<JButton>();
	
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the application.
	 */
	public GameOptionsWindow(GUI gui) {
		this.gui = gui;
		this.selfRef = this;
		this.athleteSlotsFilled = 0;
		this.selectedAthletes = new ArrayList<Athlete>();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frmSportsManager.setVisible(true);
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
		frmSportsManager = new JFrame();
		frmSportsManager.setTitle("Sports Manager");
		frmSportsManager.setResizable(false);
		frmSportsManager.setBounds(gui.x, gui.y, 900, 650);
		frmSportsManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSportsManager.getContentPane().setLayout(null);

		JLabel lblWhatIsYour = new JLabel("What is your team name?");
		lblWhatIsYour.setVerticalAlignment(SwingConstants.TOP);
		lblWhatIsYour.setHorizontalAlignment(SwingConstants.LEFT);
		lblWhatIsYour.setFont(new Font("Dialog", Font.BOLD, 16));
		lblWhatIsYour.setBounds(10, 60, 448, 44);
		frmSportsManager.getContentPane().add(lblWhatIsYour);

		teamNameField = new JTextField();
		teamNameField.setFont(new Font("Dialog", Font.PLAIN, 16));
		teamNameField.setBounds(247, 56, 595, 26);
		frmSportsManager.getContentPane().add(teamNameField);
		teamNameField.setColumns(10);

		JLabel lblWelcomeToSports = new JLabel("Welcome to Sports Manager!");
		lblWelcomeToSports.setFont(new Font("Dialog", Font.BOLD, 18));
		lblWelcomeToSports.setBounds(0, 0, 900, 44);
		lblWelcomeToSports.setHorizontalAlignment(SwingConstants.CENTER);
		frmSportsManager.getContentPane().add(lblWelcomeToSports);

		JLabel lbltheLengthMust = new JLabel(
				"(The length must be between 3 and 15 characters and must not include special characters)");
		lbltheLengthMust.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbltheLengthMust.setBounds(20, 82, 595, 15);
		frmSportsManager.getContentPane().add(lbltheLengthMust);

		JLabel lblHowLongWould = new JLabel("How long would you like the season to last?");
		lblHowLongWould.setVerticalAlignment(SwingConstants.TOP);
		lblHowLongWould.setHorizontalAlignment(SwingConstants.LEFT);
		lblHowLongWould.setFont(new Font("Dialog", Font.BOLD, 16));
		lblHowLongWould.setBounds(10, 121, 448, 44);
		frmSportsManager.getContentPane().add(lblHowLongWould);

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
		frmSportsManager.getContentPane().add(slider);

		JLabel lblPleaseChooseA = new JLabel("Please choose a difficulty:");
		lblPleaseChooseA.setVerticalAlignment(SwingConstants.TOP);
		lblPleaseChooseA.setHorizontalAlignment(SwingConstants.LEFT);
		lblPleaseChooseA.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPleaseChooseA.setBounds(10, 177, 448, 26);
		frmSportsManager.getContentPane().add(lblPleaseChooseA);

		JRadioButton rdbtnEasy = new JRadioButton("Easy");
		rdbtnEasy.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonGroup.add(rdbtnEasy);
		rdbtnEasy.setBounds(20, 199, 149, 23);
		frmSportsManager.getContentPane().add(rdbtnEasy);

		JRadioButton rdbtnHard = new JRadioButton("Hard");
		rdbtnHard.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonGroup.add(rdbtnHard);
		rdbtnHard.setBounds(20, 229, 149, 23);
		frmSportsManager.getContentPane().add(rdbtnHard);

		JButton btnNewButton = new JButton("LETS GO!!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.game.setTotalWeeks(slider.getValue());
				String teamName = teamNameField.getText();
				if (!teamName.matches("[a-zA-Z]{3,15}")) {
					JOptionPane.showMessageDialog(frmSportsManager, "Your team name is invalid", "Invalid Team Name",
							JOptionPane.ERROR_MESSAGE);
				} else if (selectedButtons.size() != 4) { 
					JOptionPane.showMessageDialog(frmSportsManager, "Please select four athletes", "Not Enough Athletes",
							JOptionPane.ERROR_MESSAGE);
				} else {
					gui.closeGameOptions(selfRef, lblWhatIsYour.getText());
				}
				//TODO add chosen athletes to game info
				//TODO require exactly four athletes before beginning
			}
		});
		btnNewButton.setBounds(699, 559, 176, 44);
		frmSportsManager.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Debug ( im lazy :) )");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.closeGameOptions(selfRef);
			}
		});
		btnNewButton_1.setBounds(491, 559, 198, 44);
		frmSportsManager.getContentPane().add(btnNewButton_1);
		
		JLabel lblPleaseChooseFour = new JLabel("Please choose FOUR athletes to start with:");
		lblPleaseChooseFour.setVerticalAlignment(SwingConstants.TOP);
		lblPleaseChooseFour.setHorizontalAlignment(SwingConstants.LEFT);
		lblPleaseChooseFour.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPleaseChooseFour.setBounds(10, 258, 448, 26);
		frmSportsManager.getContentPane().add(lblPleaseChooseFour);
		
		athleteInfoBox = new JTextPane();
		athleteInfoBox.setText("No Athlete Selected");
		athleteInfoBox.setFont(new Font("Dialog", Font.PLAIN, 18));
		athleteInfoBox.setEditable(false);
		athleteInfoBox.setBounds(575, 275, 300, 250);
		frmSportsManager.getContentPane().add(athleteInfoBox);
		
		JLabel lblAthleteInfo = new JLabel("Athlete Info");
		lblAthleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAthleteInfo.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAthleteInfo.setBounds(575, 234, 301, 44);
		frmSportsManager.getContentPane().add(lblAthleteInfo);
		
		// Add athlete buttons
		JButton btnAthlete1 = new JButton();
		setupAthleteButton(btnAthlete1);
		btnAthlete1.setBounds(10, 294, 150, 50);
		frmSportsManager.getContentPane().add(btnAthlete1);
		
		JButton btnAthlete2 = new JButton();
		setupAthleteButton(btnAthlete2);
		btnAthlete2.setBounds(170, 294, 150, 50);
		frmSportsManager.getContentPane().add(btnAthlete2);
		
		JButton btnAthlete3 = new JButton();
		setupAthleteButton(btnAthlete3);
		btnAthlete3.setBounds(330, 294, 150, 50);
		frmSportsManager.getContentPane().add(btnAthlete3);
		
		JButton btnAthlete4 = new JButton();
		setupAthleteButton(btnAthlete4);
		btnAthlete4.setBounds(10, 354, 150, 50);
		frmSportsManager.getContentPane().add(btnAthlete4);
		
		JButton btnAthlete5 = new JButton();
		setupAthleteButton(btnAthlete5);
		btnAthlete5.setBounds(170, 354, 150, 50);
		frmSportsManager.getContentPane().add(btnAthlete5);
		
		JButton btnAthlete6 = new JButton();
		setupAthleteButton(btnAthlete6);
		btnAthlete6.setBounds(330, 354, 150, 50);
		frmSportsManager.getContentPane().add(btnAthlete6);
	}

	public void closeWindow() {
		frmSportsManager.dispose();
	}
	
	void setupAthleteButton(JButton button) {
		Athlete athlete = new Athlete(gui.game);
		buttonAthleteMap.put(button, athlete);
		button.setText(athlete.getName());
		button.setFocusPainted(false);
		button.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		// hover listener
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				athleteInfoBox.setText(buttonAthleteMap.get(button).toClubString());
			}
		});
		
		// click listener
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!selectedButtons.contains(button)) {
					if (selectedButtons.size() >= 4) {
						JOptionPane.showMessageDialog(frmSportsManager, 
								"You may only select four athletes", 
								"Too Many Athletes",
								JOptionPane.ERROR_MESSAGE);
					} else {
						button.setBorder(new BevelBorder(BevelBorder.LOWERED));
						selectedButtons.add(button);
					}

				} else {
					button.setBorder(new BevelBorder(BevelBorder.RAISED));
					selectedButtons.remove(button);
				}
			}
		});
	}
}
