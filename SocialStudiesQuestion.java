import java.util.Random;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;

/**
 * Child class to Question that works with social studies questions
 */
public class SocialStudiesQuestion extends Question{
	
	//TODO: begin to store questions into the arrays
	
	
	/**
	 * The constructor uses the scanner tool to read a txt file and assign the questions and
	 * answers to the array objects
	 */
	public SocialStudiesQuestion() {
		attempted = false;
		question = new String[5][10];
		answer = new String[5][10];
		incorrectAnswers = new String[5][10][3];
		
		try {
			File ssFile = new File("ssquestions.txt");
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


	
}
