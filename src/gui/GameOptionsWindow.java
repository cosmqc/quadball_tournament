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

	JFrame frmSportsManager;
	JTextPane athleteInfoBox;

	HashMap<JButton, Athlete> buttonAthleteMap = new HashMap<JButton, Athlete>();
	ArrayList<JButton> selectedButtons = new ArrayList<JButton>();

	public GameOptionsWindow(GUI gui) {
		this.gui = gui;
		this.selfRef = this;

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

		JTextField teamNameField = new JTextField();
		teamNameField.setFont(new Font("Dialog", Font.PLAIN, 16));
		teamNameField.setBounds(247, 56, 595, 26);
		frmSportsManager.getContentPane().add(teamNameField);
		teamNameField.setColumns(10);

		JLabel lblWelcomeToSports = new JLabel("Welcome to Quadball Quest!");
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

		JSlider weekSlider = new JSlider();
		weekSlider.setSnapToTicks(true);
		weekSlider.setValue(1);
		weekSlider.setPaintLabels(true);
		weekSlider.setPaintTicks(true);
		weekSlider.setMinorTickSpacing(1);
		weekSlider.setMajorTickSpacing(1);
		weekSlider.setMaximum(15);
		weekSlider.setMinimum(5);
		weekSlider.setBounds(420, 115, 411, 50);
		frmSportsManager.getContentPane().add(weekSlider);

		JLabel lblDifficulty = new JLabel("Please choose a difficulty:");
		lblDifficulty.setVerticalAlignment(SwingConstants.TOP);
		lblDifficulty.setHorizontalAlignment(SwingConstants.LEFT);
		lblDifficulty.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDifficulty.setBounds(10, 177, 448, 26);
		frmSportsManager.getContentPane().add(lblDifficulty);

		ButtonGroup difficultyButtonGroup = new ButtonGroup();

		JRadioButton rdbtnEasy = new JRadioButton("Easy");
		rdbtnEasy.setFont(new Font("Dialog", Font.BOLD, 12));
		rdbtnEasy.setActionCommand("easy");
		difficultyButtonGroup.add(rdbtnEasy);
		rdbtnEasy.setBounds(20, 199, 69, 23);
		frmSportsManager.getContentPane().add(rdbtnEasy);

		JRadioButton rdbtnHard = new JRadioButton("Hard");
		rdbtnHard.setFont(new Font("Dialog", Font.BOLD, 12));
		rdbtnHard.setActionCommand("hard");
		difficultyButtonGroup.add(rdbtnHard);
		rdbtnHard.setBounds(90, 199, 149, 23);
		frmSportsManager.getContentPane().add(rdbtnHard);

		JButton submitButton = new JButton("LETS GO!!");
		submitButton.setBounds(699, 559, 176, 44);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validateOptions(teamNameField, weekSlider, difficultyButtonGroup);
			}
		});
		frmSportsManager.getContentPane().add(submitButton);

		JLabel lblPleaseChooseFour = new JLabel("Please choose FOUR athletes to start with:");
		lblPleaseChooseFour.setVerticalAlignment(SwingConstants.TOP);
		lblPleaseChooseFour.setHorizontalAlignment(SwingConstants.LEFT);
		lblPleaseChooseFour.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPleaseChooseFour.setBounds(10, 240, 448, 26);
		frmSportsManager.getContentPane().add(lblPleaseChooseFour);

		athleteInfoBox = new JTextPane();
		athleteInfoBox.setText("Hover to view statistics.");
		athleteInfoBox.setFont(new Font("Dialog", Font.PLAIN, 18));
		athleteInfoBox.setEditable(false);
		athleteInfoBox.setBounds(554, 244, 300, 281);
		frmSportsManager.getContentPane().add(athleteInfoBox);

		JButton btnAthlete1 = new JButton();
		setupAthleteButton(btnAthlete1);
		btnAthlete1.setBounds(10, 284, 150, 110);
		frmSportsManager.getContentPane().add(btnAthlete1);

		JButton btnAthlete2 = new JButton();
		setupAthleteButton(btnAthlete2);
		btnAthlete2.setBounds(170, 284, 150, 110);
		frmSportsManager.getContentPane().add(btnAthlete2);

		JButton btnAthlete3 = new JButton();
		setupAthleteButton(btnAthlete3);
		btnAthlete3.setBounds(330, 284, 150, 110);
		frmSportsManager.getContentPane().add(btnAthlete3);

		JButton btnAthlete4 = new JButton();
		setupAthleteButton(btnAthlete4);
		btnAthlete4.setBounds(10, 405, 150, 110);
		frmSportsManager.getContentPane().add(btnAthlete4);

		JButton btnAthlete5 = new JButton();
		setupAthleteButton(btnAthlete5);
		btnAthlete5.setBounds(170, 405, 150, 110);
		frmSportsManager.getContentPane().add(btnAthlete5);

		JButton btnAthlete6 = new JButton();
		setupAthleteButton(btnAthlete6);
		btnAthlete6.setBounds(330, 405, 150, 110);
		frmSportsManager.getContentPane().add(btnAthlete6);
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
					button.setBorder(new BevelBorder(BevelBorder.LOWERED));
					selectedButtons.add(button);
				} else {
					button.setBorder(new BevelBorder(BevelBorder.RAISED));
					selectedButtons.remove(button);
				}
			}
		});
	}

	void validateOptions(JTextField teamNameField, JSlider weekSlider, ButtonGroup difficultyButtons) {
		String teamName = teamNameField.getText();
		if (!teamName.matches("[a-zA-Z0-9]{3,15}")) {
			JOptionPane.showMessageDialog(frmSportsManager, "Your team name is invalid", "Invalid Team Name",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (difficultyButtons.getSelection() == null) {
			JOptionPane.showMessageDialog(frmSportsManager, "Please pick a difficulty", "Invalid Difficulty",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (selectedButtons.size() != 4) {
			JOptionPane.showMessageDialog(frmSportsManager, "Please select exactly four athletes",
					"Not Enough Athletes", JOptionPane.ERROR_MESSAGE);
			return;
		}

		gui.game.setTotalWeeks(weekSlider.getValue());
		gui.game.setDifficulty(difficultyButtons.getSelection().getActionCommand());
		Athlete[] selectedAthletes = new Athlete[gui.game.numPlayers];
		int i = 0;
		for (JButton button : selectedButtons) {
			selectedAthletes[i] = buttonAthleteMap.get(button);
			i++;
		}
		gui.game.playerTeam = new Team(gui.game, teamNameField.getText(), selectedAthletes, new ArrayList<Athlete>());
		gui.closeGameOptions(selfRef);
	}

	public void closeWindow() {
		frmSportsManager.dispose();
	}

}