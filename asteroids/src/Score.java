import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class Score extends Text{
	private int score;
		
		public Score() {
			score = 0;
			this.setFont(Font.font (this.getFont().getSize()+10));
			updateDisplay();
		}
		
		public void updateDisplay() {
			this.setText(Integer.toString(score));
		}
		
		public int getScore() {
			return score;
		}
		
		public void setScore(int input) {
			score = input;
			updateDisplay();
		}
}
