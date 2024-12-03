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
	
	
	/**
	 * The constructor uses the scanner tool to read a txt file and assign the questions and
	 * answers to the array objects
	 */
	public SocialStudiesQuestion() {
		//attempted = false;
		super("ssquestions.txt");
	}


	
}
