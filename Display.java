
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	
	private JPanel startingMenu;
	private JPanel questionsMenu;
	private JPanel answerMenu;
	private JPanel endingMenu;
	private JPanel resultScreen;
	
	private Font questionButtonFont = new Font("Arial", Font.PLAIN, 40);
	private Font answerButtonFont = new Font("Arial", Font.PLAIN, 18);
	private Font questionFont = new Font("Arial", Font.PLAIN, 25);
	private Font startMenuFont = new Font("Arial", Font.PLAIN, 30);
	
	private JButton correctAnswerButton;
	private JButton incorrectAnswerButton1;
	private JButton incorrectAnswerButton2;
	private JButton incorrectAnswerButton3;
	private JButton startButton;
	private JButton exitButton;
	
	//first dimension is category, second is specific question
	//order goes social studies, science, grammar, art, then math
	private JButton[][] questionsButtons;
	
	private Question question;
	
	private JLabel player1ScoreLabel;
	private JLabel player2ScoreLabel;
	
	private int player1Points;
	private int player2Points;
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
		
		//Starting menu text
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(100, 100, 100, 100);
		JLabel openingText = new JLabel("Welcome to Are You More Intelligent Than a Fifth Grader!");
		openingText.setFont(startMenuFont);
		startingMenu.add(openingText, gbc);
		
		//Start button
		startButton = new JButton("Click to Start");
		startButton.setFont(answerButtonFont);
		startButton.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(20, 100, 20, 100);
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
		
		//Buttons that select the questions
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
		
		//Labels for the category headers
		gbc.insets = new Insets(0, 40, 40, 40);
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
		
		//Labels for each player's score
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
	
	/**
	 * @param question
	 * Sets up the panel for when you answer a question
	 */
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
		gbc.insets = new Insets(80,20,80,20);
		JLabel questionText = new JLabel(question.getSelectedQuestion());
		answerMenu.add(questionText, gbc);
		questionText.setFont(questionFont);
		
		int xPosition = rand.nextInt(2);
		int yPosition = rand.nextInt(2) + 1;
		
		incorrectAnswerButton1 = new JButton(question.getWrongAnswer1());
		incorrectAnswerButton2 = new JButton(question.getWrongAnswer2());
		incorrectAnswerButton3 = new JButton(question.getWrongAnswer3());
		correctAnswerButton = new JButton(question.getSelectedAnswer());
		
		incorrectAnswerButton1.setFont(answerButtonFont);
		incorrectAnswerButton2.setFont(answerButtonFont);
		incorrectAnswerButton3.setFont(answerButtonFont);
		correctAnswerButton.setFont(answerButtonFont);
		
		//reset gridbag width
		gbc.gridwidth = 1;
		
		//if the correct answer is at the 0 x position
		if (xPosition == 0) {
			//incorrect answer at (1,1)
			gbc.insets = new Insets(80,40,80,80);
			gbc.gridx = 1;
			gbc.gridy = 1;
			incorrectAnswerButton1.addActionListener(this);
			answerMenu.add(incorrectAnswerButton1, gbc);
			
			//incorrect answer at (1,2)
			gbc.gridx = 1;
			gbc.gridy = 2;
			incorrectAnswerButton2.addActionListener(this);
			answerMenu.add(incorrectAnswerButton2, gbc);
			
			//if the correct answer y position is at 1
			if (yPosition == 1) {
				//correct answer at (0,1)
				gbc.insets = new Insets(80,80,80,40);
				gbc.gridx = 0;
				gbc.gridy = 1;
				correctAnswerButton.addActionListener(this);
				answerMenu.add(correctAnswerButton, gbc);
				
				//incorrect answer at (0,2)
				gbc.gridx = 0;
				gbc.gridy = 2;
				incorrectAnswerButton3.addActionListener(this);
				answerMenu.add(incorrectAnswerButton3, gbc);
			}
			
			//if correct answer y position is at 2
			else {
				//correct answer at (0,2)
				gbc.insets = new Insets(80,80,80,40);
				gbc.gridx = 0;
				gbc.gridy = 2;
				correctAnswerButton.addActionListener(this);
				answerMenu.add(correctAnswerButton, gbc);
				
				//incorrect answer at (0,1)
				gbc.gridx = 0;
				gbc.gridy = 1;
				incorrectAnswerButton3.addActionListener(this);
				answerMenu.add(incorrectAnswerButton3, gbc);
			}
		}
		
		//if the correct answer x position is at 1
		else {
			//incorrect answer at (0,1)
			gbc.insets = new Insets(80,80,80,40);
			gbc.gridx = 0;
			gbc.gridy = 1;
			incorrectAnswerButton1.addActionListener(this);
			answerMenu.add(incorrectAnswerButton1, gbc);
			
			//incorrect answer at (0,2)
			gbc.gridx = 0;
			gbc.gridy = 2;
			incorrectAnswerButton2.addActionListener(this);
			answerMenu.add(incorrectAnswerButton2, gbc);
			
			//if correct answer y position is at 1
			if (yPosition == 1) {
				//correct answer at (1,1)
				gbc.insets = new Insets(80,40,80,80);
				gbc.gridx = 1;
				gbc.gridy = 1;
				correctAnswerButton.addActionListener(this);
				answerMenu.add(correctAnswerButton, gbc);
				
				//incorrect answer at (1,2)
				gbc.gridx = 1;
				gbc.gridy = 2;
				incorrectAnswerButton3.addActionListener(this);
				answerMenu.add(incorrectAnswerButton3, gbc);
			}
			
			//if correct answer y is at 2
			else {
				//correct answer at (1,2)
				gbc.insets = new Insets(80,40,80,80);
				gbc.gridx = 1;
				gbc.gridy = 2;
				correctAnswerButton.addActionListener(this);
				answerMenu.add(correctAnswerButton, gbc);
				
				//incorrect answer at (1,1)
				gbc.gridx = 1;
				gbc.gridy = 1;
				incorrectAnswerButton3.addActionListener(this);
				answerMenu.add(incorrectAnswerButton3, gbc);
			}
			
		}
		
	}
	
	/**
	 * @param correct
	 * Shows text for when you get a correct or incorrect answer
	 */
	public void answerResultScreen(boolean correct) {
		resultScreen = new JPanel();
		displayFrame.add(resultScreen);
		resultScreen.setVisible(true);
		resultScreen.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Correct or Incorrect text
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel incorrectText = new JLabel("Incorrect :(");
		incorrectText.setFont(questionFont);
		JLabel correctText = new JLabel("Correct!");
		correctText.setFont(questionFont);
		if (correct) {
			resultScreen.add(correctText, gbc);
		}
		else {
			resultScreen.add(incorrectText, gbc);
		}
		
		//Keeps the text up for two seconds before returning to the question menu or going to the ending screen
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				resultScreen.setVisible(false);
				if (playerTurn == 25) {
					endingScreen();
				}
				else {
					questionsMenu.setVisible(true);		
				}
			}
		};
		timer.schedule(task, 2000);
		
	}
	
	/**
	 * The ending screen that reveals who won and lost.
	 */
	public void endingScreen() {
		endingMenu = new JPanel();
		displayFrame.add(endingMenu);
		endingMenu.setVisible(true);
		endingMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(20,20,20,20);
		
		//Text for who won or lost (or a tie)
		JLabel playerOneWinsText = new JLabel("Game Over! Player One Wins");
		playerOneWinsText.setFont(questionFont);
		JLabel playerTwoWinsText = new JLabel("Game Over! Player Two Wins");
		playerTwoWinsText.setFont(questionFont);
		JLabel tieText = new JLabel("Game Over! It's a Tie!");
		tieText.setFont(questionFont);
		
		if (player1Points > player2Points) {
			endingMenu.add(playerOneWinsText, gbc);
			gbc.gridx = 0;
			gbc.gridy = 1;
			exitButton = new JButton("Exit");
			exitButton.addActionListener(this);
			endingMenu.add(exitButton, gbc);
			exitButton.setVisible(true);
		}
		else if (player2Points > player1Points){
			endingMenu.add(playerTwoWinsText, gbc);
			gbc.gridx = 0;
			gbc.gridy = 1;
			exitButton = new JButton("Exit");
			exitButton.addActionListener(this);
			endingMenu.add(exitButton, gbc);
			exitButton.setVisible(true);
		}
		else {
			endingMenu.add(tieText, gbc);
			gbc.gridx = 0;
			gbc.gridy = 1;
			exitButton = new JButton("Exit");
			exitButton.addActionListener(this);
			endingMenu.add(exitButton, gbc);
			exitButton.setVisible(true);
		}
	}
	

	@Override
	/**
	 * This method handles all button inputs.
	 */
	public void actionPerformed(ActionEvent e) {
		//Starts game
		if (e.getSource() == startButton) {
			startingMenu.setVisible(false);
			questionsMenu();
		}
		//Buttons that open question
		if (e.getSource() == questionsButtons[0][0]) {
			questionsMenu.setVisible(false);
			questionsButtons[0][0].setVisible(false);
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(0, 30, 100, 30);
			gbc.gridx = 0;
			gbc.gridy = 1;
			questionsMenu.add(new JLabel(""), gbc);
			
			question = new SocialStudiesQuestion();
			question.selectQuestion(0);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[0][1]) {
			questionsMenu.setVisible(false);
			questionsButtons[0][1].setVisible(false);
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(0, 30, 100, 30);
			gbc.gridx = 0;
			gbc.gridy = 2;
			questionsMenu.add(new JLabel(""), gbc);
			
			question = new SocialStudiesQuestion();
			question.selectQuestion(1);
			answerMenu(question);
			
		}
		
		if (e.getSource() == questionsButtons[0][2]) {
			questionsMenu.setVisible(false);
			questionsButtons[0][2].setVisible(false);
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(0, 30, 100, 30);
			gbc.gridx = 0;
			gbc.gridy = 3;
			questionsMenu.add(new JLabel(""), gbc);
			
			question = new SocialStudiesQuestion();
			question.selectQuestion(2);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[0][3]) {
			questionsMenu.setVisible(false);
			questionsButtons[0][3].setVisible(false);
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(0, 30, 100, 30);
			gbc.gridx = 0;
			gbc.gridy = 4;
			questionsMenu.add(new JLabel(""), gbc);
			
			question = new SocialStudiesQuestion();
			question.selectQuestion(3);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[0][4]) {
			questionsMenu.setVisible(false);
			questionsButtons[0][4].setVisible(false);
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(0, 30, 100, 30);
			gbc.gridx = 0;
			gbc.gridy = 5;
			questionsMenu.add(new JLabel(""), gbc);
			
			question = new SocialStudiesQuestion();
			question.selectQuestion(4);
			answerMenu(question);
			
		}
		
		if (e.getSource() == questionsButtons[1][0]) {
			questionsMenu.setVisible(false);
			questionsButtons[1][0].setVisible(false);
			question = new ScienceQuestion();
			question.selectQuestion(0);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[1][1]) {
			questionsMenu.setVisible(false);
			questionsButtons[1][1].setVisible(false);
			question = new ScienceQuestion();
			question.selectQuestion(1);
			answerMenu(question);
			
		}
		
		if (e.getSource() == questionsButtons[1][2]) {
			questionsMenu.setVisible(false);
			questionsButtons[1][2].setVisible(false);
			question = new ScienceQuestion();
			question.selectQuestion(2);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[1][3]) {
			questionsMenu.setVisible(false);
			questionsButtons[1][3].setVisible(false);
			question = new ScienceQuestion();
			question.selectQuestion(3);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[1][4]) {
			questionsMenu.setVisible(false);
			questionsButtons[1][4].setVisible(false);
			question = new ScienceQuestion();
			question.selectQuestion(4);
			answerMenu(question);
			
		}
		
		if (e.getSource() == questionsButtons[2][0]) {
			questionsMenu.setVisible(false);
			questionsButtons[2][0].setVisible(false);
			question = new VocabQuestion();
			question.selectQuestion(0);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[2][1]) {
			questionsMenu.setVisible(false);
			questionsButtons[2][1].setVisible(false);
			question = new VocabQuestion();
			question.selectQuestion(1);
			answerMenu(question);
			
		}
		
		if (e.getSource() == questionsButtons[2][2]) {
			questionsMenu.setVisible(false);
			questionsButtons[2][2].setVisible(false);
			question = new VocabQuestion();
			question.selectQuestion(2);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[2][3]) {
			questionsMenu.setVisible(false);
			questionsButtons[2][3].setVisible(false);
			question = new VocabQuestion();
			question.selectQuestion(3);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[2][4]) {
			questionsMenu.setVisible(false);
			questionsButtons[2][4].setVisible(false);
			question = new VocabQuestion();
			question.selectQuestion(4);
			answerMenu(question);
			
		}
		
		if (e.getSource() == questionsButtons[3][0]) {
			questionsMenu.setVisible(false);
			questionsButtons[3][0].setVisible(false);
			question = new ArtQuestion();
			question.selectQuestion(0);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[3][1]) {
			questionsMenu.setVisible(false);
			questionsButtons[3][1].setVisible(false);
			question = new ArtQuestion();
			question.selectQuestion(1);
			answerMenu(question);
			
		}
		
		if (e.getSource() == questionsButtons[3][2]) {
			questionsMenu.setVisible(false);
			questionsButtons[3][2].setVisible(false);
			question = new ArtQuestion();
			question.selectQuestion(2);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[3][3]) {
			questionsMenu.setVisible(false);
			questionsButtons[3][3].setVisible(false);
			question = new ArtQuestion();
			question.selectQuestion(3);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[3][4]) {
			questionsMenu.setVisible(false);
			questionsButtons[3][4].setVisible(false);
			question = new ArtQuestion();
			question.selectQuestion(4);
			answerMenu(question);
			
		}
		
		if (e.getSource() == questionsButtons[4][0]) {
			questionsMenu.setVisible(false);
			questionsButtons[4][0].setVisible(false);
			question = new MathQuestion();
			question.selectQuestion(0);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[4][1]) {
			questionsMenu.setVisible(false);
			questionsButtons[4][1].setVisible(false);
			question = new MathQuestion();
			question.selectQuestion(1);
			answerMenu(question);
			
		}
		
		if (e.getSource() == questionsButtons[4][2]) {
			questionsMenu.setVisible(false);
			questionsButtons[4][2].setVisible(false);
			question = new MathQuestion();
			question.selectQuestion(2);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[4][3]) {
			questionsMenu.setVisible(false);
			questionsButtons[4][3].setVisible(false);
			question = new MathQuestion();
			question.selectQuestion(3);
			answerMenu(question);
			
		}
		if (e.getSource() == questionsButtons[4][4]) {
			questionsMenu.setVisible(false);
			questionsButtons[4][4].setVisible(false);
			question = new MathQuestion();
			question.selectQuestion(4);
			answerMenu(question);
			
		}
		//Buttons for incorrect or correct answers
		if (e.getSource() == incorrectAnswerButton1 || e.getSource() == incorrectAnswerButton2 
				|| e.getSource() == incorrectAnswerButton3) {
			answerMenu.setVisible(false);
			answerResultScreen(false);
			playerTurn++;
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
			

		}
		//closes the program
		if (e.getSource() == exitButton) {
			System.exit(0);
		}
		
			
		
	}

	

	
	

}
