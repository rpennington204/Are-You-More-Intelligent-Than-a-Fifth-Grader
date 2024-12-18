
import java.util.Random;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
/**
 * Parent class that stores and has functions related to the questions in the quiz show
 */
public class Question {
	protected String question[][]; 				
								   				
	/*There will be 100 point, 200 point, up to 500 point questions.
	The first dimension in the array stores location for point
	values (0=100 questions), and the second dimension stores specific questions.*/
	protected String answer[][];   				
	protected String incorrectAnswers[][][];   
											  

	protected String selectedQuestion;
	protected String selectedAnswer;
	protected String wrongAnswer1;
	protected String wrongAnswer2;
	protected String wrongAnswer3;
	protected int pointValue;
	
	
	/**
	 * This method will be used by child classes to randomly select a string for its question,
	 * and also assign the appropriate answers
	 */
	public void selectQuestion(int pValue) {
		Random rand = new Random();
		int randQuestion = rand.nextInt(10);
		
		selectedQuestion = question[pValue][randQuestion];
		selectedAnswer = answer[pValue][randQuestion];
		wrongAnswer1 = incorrectAnswers[pValue][randQuestion][0];
		wrongAnswer2 = incorrectAnswers[pValue][randQuestion][1];
		wrongAnswer3 = incorrectAnswers[pValue][randQuestion][2];
		pointValue = (pValue + 1) * 100;
	}
	
	/**
	 * The constructor uses the scanner tool to read a txt file and assign the questions and
	 * answers to the array objects
	 */
	public Question(String fileName) {
		question = new String[5][10];
		answer = new String[5][10];
		incorrectAnswers = new String[5][10][3];
		
		try {
			File ssFile = new File(fileName);
			FileInputStream fis = new FileInputStream(ssFile);
			Scanner scnr = new Scanner(fis);
			for (int i = 0; i < 5; i++) {																						
				for (int j = 0; j < 10; j++) {                          
					question[i][j] = scnr.nextLine();
				}
			}
			for (int i =0; i < 5; i++) {
				for (int j = 0; j < 10; j++) {
					answer[i][j] = scnr.nextLine();
				}
			}
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 10; j++) {
					for (int k = 0; k < 3; k++) {
						incorrectAnswers[i][j][k] = scnr.nextLine();
					}
				}
			}
			scnr.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}
	
	
	/**
	 * getters and setters for Question
	 */
	public int getPointValue() {
		return pointValue;
	}

	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	public String getSelectedQuestion() {
		return selectedQuestion;
	}

	public void setSelectedQuestion(String selectedQuestion) {
		this.selectedQuestion = selectedQuestion;
	}

	public String getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public String getWrongAnswer1() {
		return wrongAnswer1;
	}

	public void setWrongAnswer1(String wrongAnswer1) {
		this.wrongAnswer1 = wrongAnswer1;
	}

	public String getWrongAnswer2() {
		return wrongAnswer2;
	}

	public void setWrongAnswer2(String wrongAnswer2) {
		this.wrongAnswer2 = wrongAnswer2;
	}

	public String getWrongAnswer3() {
		return wrongAnswer3;
	}

	public void setWrongAnswer3(String wrongAnswer3) {
		this.wrongAnswer3 = wrongAnswer3;
	}	
}
