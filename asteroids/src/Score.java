import javafx.scene.text.Text;
import javafx.scene.text.Font;
/**
 * Score class that extends text. This class is used  to keep track of the score for the player.
 *
 */
public class Score extends Text{
	/**Variable to keep track of the score */
	private int score;
	/**Constructor with no parameters, this constructor sets a font and calls update display to show the text to the game */
	public Score() {
		score = 0;
		this.setFont(Font.font (this.getFont().getSize() + 5));
		updateDisplay();
	}
	/**Updates the display for the player to see */
	public void updateDisplay() {
		this.setText("Score : " + Integer.toString(score));
	}
	/**
	 * Getter method for the score of the player
	 * @return The current score of the player
	 */
	public int getScore() {
		return score;
	}
	/**
	 * This method is used for other classes to update the score for the player
	 * @param input the new score for the player
	 */
	public void setScore(int input) {
		score = input;
		updateDisplay();
	}
}
