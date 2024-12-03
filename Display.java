import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Random;

/**
 * This class handles functions related to display and menus.
 */

public class Display implements ActionListener{
	private JFrame displayFrame;
	private JButton startButton;
	private JButton[] ssQuestionsButtons;
	private JPanel startingMenu;
	private JPanel questionsMenu;
	private JPanel answerMenu;
	private Font questionButtonFont = new Font("Arial", Font.PLAIN, 40);

	/**
	 * This method creates the frame for the program.
	 */
	public Display() {
		displayFrame = new JFrame();
		displayFrame.setSize(1280, 720);
		displayFrame.setTitle("Are You More Intelligent Than a Fifth Grader?");
		displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		displayFrame.setVisible(true);
	}
	/**
	 * This method creates the panel for the starting menu.
	 */
	public void startingMenu() {
		startingMenu = new JPanel();
		displayFrame.add(startingMenu);
		startingMenu.setVisible(true);
		startingMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(100, 100, 100, 100);
		startingMenu.add(new JLabel("Welcome to Are You More Intelligent Than a Fifth Grader!"), gbc);
		startButton = new JButton("Click to Start");
		startButton.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(100, 100, 100, 100);
		startingMenu.add(startButton, gbc);
	}
	/**
	 * This method creates the panel for the questions menu. 
	 */
	public void questionsMenu() {
		ssQuestionsButtons = new JButton[5];
		questionsMenu = new JPanel();
		displayFrame.add(questionsMenu);
		questionsMenu.setVisible(true);
		questionsMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		int pointValue = 100;
		for (int i = 0; i < 5; i++) {
			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.insets = new Insets(50, 50, 4, 4);
			ssQuestionsButtons[i] = new JButton("" + pointValue);
			ssQuestionsButtons[i].addActionListener(this);
			ssQuestionsButtons[i].setFont(questionButtonFont);
			questionsMenu.add(ssQuestionsButtons[i], gbc);
			pointValue = pointValue + 100;
		}
	}
	
	public void answerMenu(int question) {
		//TODO: set up the fucking answer menu 
		answerMenu = new JPanel();
		displayFrame.add(answerMenu);
		answerMenu.setVisible(true);
		answerMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		if (question == 0) {
			answerMenu.add(new JLabel("fortnite battle pass fortnite"));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			startingMenu.setVisible(false);
			questionsMenu();
		}
		if (e.getSource() == ssQuestionsButtons[0]) {
			questionsMenu.setVisible(false);
			ssQuestionsButtons[0].setVisible(false);
			answerMenu(0);
		}
		
	}

}
