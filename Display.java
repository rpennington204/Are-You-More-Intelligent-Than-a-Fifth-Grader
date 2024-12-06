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
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class handles functions related to display and menus.
 */

public class Display implements ActionListener{
	private JFrame displayFrame;
	private JButton startButton;
	//first dimension is category, second is specific question
	//order goes social studies, science, grammar, art, then math
	private JButton[][] questionsButtons;
	private JPanel startingMenu;
	private JPanel questionsMenu;
	private JPanel answerMenu;
	private Font questionButtonFont = new Font("Arial", Font.PLAIN, 40);
	private JButton correctAnswerButton;
	private JButton incorrectAnswerButton1;
	private JButton incorrectAnswerButton2;
	private JButton incorrectAnswerButton3;
	private JPanel resultScreen;
	private Question question;
	private int player1Points;
	private int player2Points;
	private JLabel player1ScoreLabel;
	private JLabel player2ScoreLabel;
	private int playerTurn = 0;

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
		questionsButtons = new JButton[5][5];
		questionsMenu = new JPanel();
		displayFrame.add(questionsMenu);
		questionsMenu.setVisible(true);
		
		questionsMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 30, 50, 30);
		
		for (int i = 0; i < 5; i++) {
			int pointValue = 100;
			for (int j = 0; j < 5; j++) {
				gbc.gridx = i;
				gbc.gridy = j + 1;
				questionsButtons[i][j] = new JButton("" + pointValue);
				questionsButtons[i][j].addActionListener(this);
				questionsButtons[i][j].setFont(questionButtonFont);
				questionsMenu.add(questionsButtons[i][j], gbc);
				pointValue = pointValue + 100;
			}
		}
		
		gbc.insets = new Insets(0, 5, 40, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel ssCategoryText = new JLabel("Social Studies");
		questionsMenu.add(ssCategoryText, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		JLabel scienceCategoryText = new JLabel("Science");
		questionsMenu.add(scienceCategoryText, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		JLabel grammarCategoryText = new JLabel("Grammar");
		questionsMenu.add(grammarCategoryText, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		JLabel artCategoryText = new JLabel("Art");
		questionsMenu.add(artCategoryText, gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 0;
		JLabel mathCategoryText = new JLabel("Math");
		questionsMenu.add(mathCategoryText, gbc);
		
		player1ScoreLabel = new JLabel("Player One Score: " + player1Points);
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.insets = new Insets(5,5, 5, 0);
		questionsMenu.add(player1ScoreLabel , gbc);
		
		player2ScoreLabel = new JLabel("Player Two Score: " + player2Points);
		gbc.gridx = 4;
		gbc.gridy = 6;
		gbc.insets = new Insets(5, 0, 5, 5);
		questionsMenu.add(player2ScoreLabel, gbc);	
	}
	
	public void answerMenu(Question question) {
		answerMenu = new JPanel();
		displayFrame.add(answerMenu);
		answerMenu.setVisible(true);
		answerMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Random rand = new Random();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(80,80,80,80);
		answerMenu.add(new JLabel(question.getSelectedQuestion()), gbc);
		int xPosition = rand.nextInt(2);
		int yPosition = rand.nextInt(2) + 1;
		
		//reset gridbag width
		gbc.gridwidth = 1;
		
		//if the correct answer is at the 0 x position
		if (xPosition == 0) {
			//incorrect answer at (1,1)
			gbc.gridx = 1;
			gbc.gridy = 1;
			//gbc.insets = new Insets(10,5,10,5);
			incorrectAnswerButton1 = new JButton(question.getWrongAnswer1());
			incorrectAnswerButton1.addActionListener(this);
			answerMenu.add(incorrectAnswerButton1, gbc);
			
			//incorrect answer at (1,2)
			gbc.gridx = 1;
			gbc.gridy = 2;
			//gbc.insets = new Insets(10,5,10,10);
			incorrectAnswerButton2 = new JButton(question.getWrongAnswer2());
			incorrectAnswerButton2.addActionListener(this);
			answerMenu.add(incorrectAnswerButton2, gbc);
			
			//if the correct answer y position is at 1
			if (yPosition == 1) {
				//correct answer at (0,1)
				gbc.gridx = 0;
				gbc.gridy = 1;
				//gbc.insets = new Insets(10,5,10,5);
				correctAnswerButton = new JButton(question.getSelectedAnswer());
				correctAnswerButton.addActionListener(this);
				answerMenu.add(correctAnswerButton, gbc);
				
				//incorrect answer at (0,2)
				gbc.gridx = 0;
				gbc.gridy = 2;
				//gbc.insets = new Insets(10,5,10,10);
				incorrectAnswerButton3 = new JButton(question.getWrongAnswer3());
				incorrectAnswerButton3.addActionListener(this);
				answerMenu.add(incorrectAnswerButton3, gbc);
			}
			
			//if correct answer y position is at 2
			else {
				//correct answer at (0,2)
				gbc.gridx = 0;
				gbc.gridy = 2;
				//gbc.insets = new Insets(10,5,10,10);
				correctAnswerButton = new JButton(question.getSelectedAnswer());
				correctAnswerButton.addActionListener(this);
				answerMenu.add(correctAnswerButton, gbc);
				
				//incorrect answer at (0,1)
				gbc.gridx = 0;
				gbc.gridy = 1;
				//gbc.insets = new Insets(10,5,10,10);
				incorrectAnswerButton3 = new JButton(question.getWrongAnswer3());
				incorrectAnswerButton3.addActionListener(this);
				answerMenu.add(incorrectAnswerButton3, gbc);
			}
		}
		
		//if the correct answer x position is at 1
		else {
			//incorrect answer at (0,1)
			gbc.gridx = 0;
			gbc.gridy = 1;
			//gbc.insets = new Insets(10,5,10,5);
			incorrectAnswerButton1 = new JButton(question.getWrongAnswer1());
			incorrectAnswerButton1.addActionListener(this);
			answerMenu.add(incorrectAnswerButton1, gbc);
			
			//incorrect answer at (0,2)
			gbc.gridx = 0;
			gbc.gridy = 2;
			//gbc.insets = new Insets(10,5,10,10);
			incorrectAnswerButton2 = new JButton(question.getWrongAnswer2());
			incorrectAnswerButton2.addActionListener(this);
			answerMenu.add(incorrectAnswerButton2, gbc);
			
			//if correct answer y position is at 1
			if (yPosition == 1) {
				//correct answer at (1,1)
				gbc.gridx = 1;
				gbc.gridy = 1;
				//gbc.insets = new Insets(10,5,10,5);
				correctAnswerButton = new JButton(question.getSelectedAnswer());
				correctAnswerButton.addActionListener(this);
				answerMenu.add(correctAnswerButton, gbc);
				
				//incorrect answer at (1,2)
				gbc.gridx = 1;
				gbc.gridy = 2;
				//gbc.insets = new Insets(10,5,10,10);
				incorrectAnswerButton3 = new JButton(question.getWrongAnswer3());
				incorrectAnswerButton3.addActionListener(this);
				answerMenu.add(incorrectAnswerButton3, gbc);
			}
			
			//if correct answer y is at 2
			else {
				//correct answer at (1,2)
				gbc.gridx = 1;
				gbc.gridy = 2;
				//gbc.insets = new Insets(10,5,10,5);
				correctAnswerButton = new JButton(question.getSelectedAnswer());
				correctAnswerButton.addActionListener(this);
				answerMenu.add(correctAnswerButton, gbc);
				
				//incorrect answer at (1,1)
				gbc.gridx = 1;
				gbc.gridy = 1;
				//gbc.insets = new Insets(10,5,10,10);
				incorrectAnswerButton3 = new JButton(question.getWrongAnswer3());
				incorrectAnswerButton3.addActionListener(this);
				answerMenu.add(incorrectAnswerButton3, gbc);
			}
			
		}
		
	}
	
	public void answerResultScreen(boolean correct) {
		resultScreen = new JPanel();
		displayFrame.add(resultScreen);
		resultScreen.setVisible(true);
		resultScreen.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		if (correct) {
			resultScreen.add(new JLabel("Correct!"), gbc);
		}
		else {
			resultScreen.add(new JLabel("Incorrect :("), gbc);
		}
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				resultScreen.setVisible(false);
				questionsMenu.setVisible(true);			
			}
		};
		timer.schedule(task, 2000);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			startingMenu.setVisible(false);
			questionsMenu();
		}
		if (e.getSource() == questionsButtons[0][0]) {
			questionsMenu.setVisible(false);
			questionsButtons[0][0].setVisible(false);
			question = new SocialStudiesQuestion();
			question.selectQuestion(0);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[0][1]) {
			questionsMenu.setVisible(false);
			questionsButtons[0][1].setVisible(false);
			question = new SocialStudiesQuestion();
			question.selectQuestion(1);
			answerMenu(question);
			
		}
		
		if (e.getSource() == questionsButtons[0][2]) {
			questionsMenu.setVisible(false);
			questionsButtons[0][2].setVisible(false);
			question = new SocialStudiesQuestion();
			question.selectQuestion(2);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[0][3]) {
			questionsMenu.setVisible(false);
			questionsButtons[0][3].setVisible(false);
			question = new SocialStudiesQuestion();
			question.selectQuestion(3);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[0][4]) {
			questionsMenu.setVisible(false);
			questionsButtons[0][4].setVisible(false);
			question = new SocialStudiesQuestion();
			question.selectQuestion(4);
			answerMenu(question);
			
		}
		
		if (e.getSource() == incorrectAnswerButton1 || e.getSource() == incorrectAnswerButton2 
				|| e.getSource() == incorrectAnswerButton3) {
			answerMenu.setVisible(false);
			answerResultScreen(false);
		}
		if (e.getSource() == correctAnswerButton) {
			answerMenu.setVisible(false);
			answerResultScreen(true);
			if (playerTurn%2 == 0) {
				player1Points = player1Points + question.getPointValue();
				player1ScoreLabel.setText("Player One Score: " + player1Points);
			}
			else {
				player2Points = player2Points + question.getPointValue();
				player2ScoreLabel.setText("Player Two Score: " + player2Points);
			}
			playerTurn ++;
			
			if (playerTurn == 25) {
				//TODO: make ending menu
			}
		}
			
		
	}

	

	
	

}
