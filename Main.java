import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main{

	public static void main(String[] args) {
		SocialStudiesQuestion question = new SocialStudiesQuestion();
		question.selectQuestion(0);
		System.out.println(question.getSelectedQuestion());
		System.out.println(question.getSelectedAnswer());
		System.out.println(question.getWrongAnswer1());
		System.out.println(question.getWrongAnswer2());
		System.out.println(question.getWrongAnswer3());
		
		Display display = new Display();
		display.startingMenu();
		
		
		
	}
}
