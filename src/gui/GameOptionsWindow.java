package gui;

import base.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
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

	private JFrame frmSportsManager;
	private JTextField textField;
	private JLabel lbltheLengthMust;
	private JLabel lblHowLongWould;
	private JLabel lblPleaseChooseA;
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

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField.setBounds(247, 56, 595, 26);
		frmSportsManager.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblWelcomeToSports = new JLabel("Welcome to Sports Manager!");
		lblWelcomeToSports.setFont(new Font("Dialog", Font.BOLD, 18));
		lblWelcomeToSports.setBounds(0, 0, 900, 44);
		lblWelcomeToSports.setHorizontalAlignment(SwingConstants.CENTER);
		frmSportsManager.getContentPane().add(lblWelcomeToSports);

		lbltheLengthMust = new JLabel(
				"(The length must be between 3 and 15 characters and must not include special characters)");
		lbltheLengthMust.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbltheLengthMust.setBounds(20, 82, 595, 15);
		frmSportsManager.getContentPane().add(lbltheLengthMust);

		lblHowLongWould = new JLabel("How long would you like the season to last?");
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

		lblPleaseChooseA = new JLabel("Please choose a difficulty:");
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
				String teamName = textField.getText();
				if (!teamName.matches("[a-zA-Z]{3,15}")) {
					JOptionPane.showMessageDialog(frmSportsManager, "Your team name is invalid", "Invalid Team Name",
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
		
		JTextPane txtpnInfo = new JTextPane();
		txtpnInfo.setText("No Athlete Selected");
		txtpnInfo.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtpnInfo.setEditable(false);
		txtpnInfo.setBounds(575, 275, 300, 250);
		frmSportsManager.getContentPane().add(txtpnInfo);
		
		JLabel lblAthleteInfo = new JLabel("Athlete Info");
		lblAthleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAthleteInfo.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAthleteInfo.setBounds(575, 234, 301, 44);
		frmSportsManager.getContentPane().add(lblAthleteInfo);
		
		JLabel lblAthletesSelected = new JLabel("Athletes selected:");
		lblAthletesSelected.setVerticalAlignment(SwingConstants.TOP);
		lblAthletesSelected.setHorizontalAlignment(SwingConstants.LEFT);
		lblAthletesSelected.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAthletesSelected.setBounds(10, 420, 448, 26);
		frmSportsManager.getContentPane().add(lblAthletesSelected);
		
		Athlete option1 = new Athlete(gui.game);
		JButton btnAthlete1 = new JButton(option1.getName());
		btnAthlete1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtpnInfo.setText(option1.toClubString());
			}
		});
		btnAthlete1.setBounds(10, 294, 150, 50);
		frmSportsManager.getContentPane().add(btnAthlete1);
		
		Athlete option2 = new Athlete(gui.game);
		JButton btnAthlete2 = new JButton(option2.getName());
		btnAthlete2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtpnInfo.setText(option2.toClubString());
			}
		});
		btnAthlete2.setBounds(170, 294, 150, 50);
		frmSportsManager.getContentPane().add(btnAthlete2);
		
		Athlete option3 = new Athlete(gui.game);
		JButton btnAthlete3 = new JButton(option3.getName());
		btnAthlete3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtpnInfo.setText(option3.toClubString());
			}
		});
		btnAthlete3.setBounds(330, 294, 150, 50);
		frmSportsManager.getContentPane().add(btnAthlete3);
		
		Athlete option4 = new Athlete(gui.game);
		JButton btnAthlete4 = new JButton(option4.getName());
		btnAthlete4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtpnInfo.setText(option4.toClubString());
			}
		});
		btnAthlete4.setBounds(10, 354, 150, 50);
		frmSportsManager.getContentPane().add(btnAthlete4);
		
		Athlete option5 = new Athlete(gui.game);
		JButton btnAthlete5 = new JButton(option5.getName());
		btnAthlete5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtpnInfo.setText(option5.toClubString());
			}
		});
		btnAthlete5.setBounds(170, 354, 150, 50);
		frmSportsManager.getContentPane().add(btnAthlete5);
		
		Athlete option6 = new Athlete(gui.game);
		JButton btnAthlete6 = new JButton(option6.getName());
		btnAthlete6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtpnInfo.setText(option6.toClubString());
			}
		});
		btnAthlete6.setBounds(330, 354, 150, 50);
		frmSportsManager.getContentPane().add(btnAthlete6);
				
		
		JButton btnSelected1 = new JButton("");
		btnSelected1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (athleteSlotsFilled >= 1) {
					txtpnInfo.setText(selectedAthletes.get(0).toClubString());
				}
			}
		});
		btnSelected1.setBounds(10, 469, 150, 50);
		if (selectedAthletes.size() >= 1) {
			btnSelected1.setText(selectedAthletes.get(0).getName());
		}
		frmSportsManager.getContentPane().add(btnSelected1);
		
		JButton btnSelected2 = new JButton("");
		btnSelected2.setBounds(170, 469, 150, 50);
		btnSelected2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (athleteSlotsFilled >= 2) {
					txtpnInfo.setText(selectedAthletes.get(1).toClubString());
				}
			}
		});
		if (selectedAthletes.size() >= 2) {
			btnSelected2.setText(selectedAthletes.get(1).getName());
		}
		frmSportsManager.getContentPane().add(btnSelected2);
		
		JButton btnSelected3 = new JButton("");
		btnSelected3.setBounds(10, 529, 150, 50);
		btnSelected3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (athleteSlotsFilled >= 3) {
					txtpnInfo.setText(selectedAthletes.get(2).toClubString());
				}
			}
		});
		if (selectedAthletes.size() >= 3) {
			btnSelected3.setText(selectedAthletes.get(2).getName());
		}
		frmSportsManager.getContentPane().add(btnSelected3);
		
		JButton btnSelected4 = new JButton("");
		btnSelected4.setBounds(170, 529, 150, 50);
		btnSelected4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (athleteSlotsFilled == 4) {
					txtpnInfo.setText(selectedAthletes.get(3).toClubString());
				}
			}
		});
		if (selectedAthletes.size() >= 4) {
			btnSelected4.setText(selectedAthletes.get(3).getName());
		}
		frmSportsManager.getContentPane().add(btnSelected4);
		
		// Updates the UI to display the selected athletes
		btnAthlete1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (athleteSlotsFilled == 4) {
					JOptionPane.showMessageDialog(frmSportsManager, "You may only select four athletes", "Too Many Athletes",
							JOptionPane.ERROR_MESSAGE);
				} else if (!selectedAthletes.contains(option1)) {
					selectedAthletes.add(option1);
					athleteSlotsFilled += 1;
					if (athleteSlotsFilled == 1) {
						btnSelected1.setText(option1.getName());
					} else if (athleteSlotsFilled == 2) {
						btnSelected2.setText(option1.getName());
					} else if (athleteSlotsFilled == 3) {
						btnSelected3.setText(option1.getName());
					} else {
						btnSelected4.setText(option1.getName());
					}
				}
			}
		});
		
		btnAthlete2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (athleteSlotsFilled == 4) {
					JOptionPane.showMessageDialog(frmSportsManager, "You may only select four athletes", "Too Many Athletes",
							JOptionPane.ERROR_MESSAGE);
				} else if (!selectedAthletes.contains(option2)) {
					selectedAthletes.add(option2);
					athleteSlotsFilled += 1;
					if (athleteSlotsFilled == 1) {
						btnSelected1.setText(option2.getName());
					} else if (athleteSlotsFilled == 2) {
						btnSelected2.setText(option2.getName());
					} else if (athleteSlotsFilled == 3) {
						btnSelected3.setText(option2.getName());
					} else {
						btnSelected4.setText(option2.getName());
					}
				}
			}
		});
		
		btnAthlete3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (athleteSlotsFilled == 4) {
					JOptionPane.showMessageDialog(frmSportsManager, "You may only select four athletes", "Too Many Athletes",
							JOptionPane.ERROR_MESSAGE);
				} else if (!selectedAthletes.contains(option3)) {
					selectedAthletes.add(option3);
					athleteSlotsFilled += 1;
					if (athleteSlotsFilled == 1) {
						btnSelected1.setText(option3.getName());
					} else if (athleteSlotsFilled == 2) {
						btnSelected2.setText(option3.getName());
					} else if (athleteSlotsFilled == 3) {
						btnSelected3.setText(option3.getName());
					} else {
						btnSelected4.setText(option3.getName());
					}
				}
			}
		});
		
		btnAthlete4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (athleteSlotsFilled == 4) {
					JOptionPane.showMessageDialog(frmSportsManager, "You may only select four athletes", "Too Many Athletes",
							JOptionPane.ERROR_MESSAGE);
				} else if (!selectedAthletes.contains(option4)) {
					selectedAthletes.add(option4);
					athleteSlotsFilled += 1;
					if (athleteSlotsFilled == 1) {
						btnSelected1.setText(option4.getName());
					} else if (athleteSlotsFilled == 2) {
						btnSelected2.setText(option4.getName());
					} else if (athleteSlotsFilled == 3) {
						btnSelected3.setText(option4.getName());
					} else {
						btnSelected4.setText(option4.getName());
					}
				}
			}
		});
		
		btnAthlete5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (athleteSlotsFilled == 4) {
					JOptionPane.showMessageDialog(frmSportsManager, "You may only select four athletes", "Too Many Athletes",
							JOptionPane.ERROR_MESSAGE);
				} else if (!selectedAthletes.contains(option5)) {
					selectedAthletes.add(option5);
					athleteSlotsFilled += 1;
					if (athleteSlotsFilled == 1) {
						btnSelected1.setText(option5.getName());
					} else if (athleteSlotsFilled == 2) {
						btnSelected2.setText(option5.getName());
					} else if (athleteSlotsFilled == 3) {
						btnSelected3.setText(option5.getName());
					} else {
						btnSelected4.setText(option5.getName());
					}
				}
			}
		});
		
		btnAthlete6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (athleteSlotsFilled == 4) {
					JOptionPane.showMessageDialog(frmSportsManager, "You may only select four athletes", "Too Many Athletes",
							JOptionPane.ERROR_MESSAGE);
				} else if (!selectedAthletes.contains(option6)) {
					selectedAthletes.add(option6);
					athleteSlotsFilled += 1;
					if (athleteSlotsFilled == 1) {
						btnSelected1.setText(option6.getName());
					} else if (athleteSlotsFilled == 2) {
						btnSelected2.setText(option6.getName());
					} else if (athleteSlotsFilled == 3) {
						btnSelected3.setText(option6.getName());
					} else {
						btnSelected4.setText(option6.getName());
					}
				}
			}
		});
		
		btnSelected1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (athleteSlotsFilled >= 1) {
					selectedAthletes.remove(0);
					athleteSlotsFilled -= 1;
				}
				if (athleteSlotsFilled == 0) {
					btnSelected1.setText("");
				} else if (athleteSlotsFilled == 1) {
					btnSelected1.setText(selectedAthletes.get(0).getName());
					btnSelected2.setText("");
				} else if (athleteSlotsFilled == 2) {
					btnSelected1.setText(selectedAthletes.get(0).getName());
					btnSelected2.setText(selectedAthletes.get(1).getName());
					btnSelected3.setText("");
				} else if (athleteSlotsFilled == 3) {
					btnSelected1.setText(selectedAthletes.get(0).getName());
					btnSelected2.setText(selectedAthletes.get(1).getName());
					btnSelected3.setText(selectedAthletes.get(2).getName());
					btnSelected4.setText("");
				}
			}
		});
		
		btnSelected2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (athleteSlotsFilled >= 2) {
					selectedAthletes.remove(1);
					athleteSlotsFilled -= 1;
				}
				if (athleteSlotsFilled == 1) {
					btnSelected2.setText("");
				} else if (athleteSlotsFilled == 2) {
					btnSelected2.setText(selectedAthletes.get(1).getName());
					btnSelected3.setText("");
				} else if (athleteSlotsFilled == 3) {
					btnSelected2.setText(selectedAthletes.get(1).getName());
					btnSelected3.setText(selectedAthletes.get(2).getName());
					btnSelected4.setText("");
				}
			}
		});
		
		btnSelected3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (athleteSlotsFilled >= 3) {
					selectedAthletes.remove(2);
					athleteSlotsFilled -= 1;
				}
				if (athleteSlotsFilled == 2) {
					btnSelected3.setText("");
				} else if (athleteSlotsFilled == 3) {
					btnSelected3.setText(selectedAthletes.get(2).getName());
					btnSelected4.setText("");
				}
			}
		});
		
		btnSelected4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (athleteSlotsFilled == 4) {
					selectedAthletes.remove(3);
					athleteSlotsFilled -= 1;
				}
				btnSelected4.setText("");
			}
		});
		
		JLabel lblclickToDeselect = new JLabel("(Click on an athlete to deselect it)");
		lblclickToDeselect.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblclickToDeselect.setBounds(10, 444, 595, 15);
		frmSportsManager.getContentPane().add(lblclickToDeselect);

	}

	public void closeWindow() {
		frmSportsManager.dispose();
	}
}
