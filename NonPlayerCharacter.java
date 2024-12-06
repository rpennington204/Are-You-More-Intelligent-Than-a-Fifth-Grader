import java.util.Random;

public class NonPlayerCharacter {
	private int npcScore;
	private boolean botTurn;
	
	/*public void selectQuestion() {
		boolean selecting = true;
		Display display = new Display();
		while (selecting) {
			Random rand = new Random();
			int selectedQuestion = rand.nextInt(5);
			if (display.getSSQuestionsButtons(selectedQuestion).isVisible()) {
				display.getSSQuestionsButtons(selectedQuestion).setVisible(false);
				selecting = false;
			}
			
		}
	}*/
	
	public NonPlayerCharacter() {
		npcScore = 0;
		botTurn = false;
	}

	public boolean isBotTurn() {
		return botTurn;
	}

	public void setBotTurn(boolean botTurn) {
		this.botTurn = botTurn;
	}
	
	
	
}
