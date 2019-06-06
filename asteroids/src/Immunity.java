import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Immunity extends Text {

	boolean immune;
	
	public Immunity() {
		this.setFont(Font.font (this.getFont().getSize()+5));
		updateDisplay();
	}
	
	public void updateDisplay() {
		if (immune) {
			setText("Immune");
			setFill(Color.color(Math.random(), Math.random(), Math.random()));
		} else {
			setText("Not Immune");
			setFill(Color.BLACK);
		}
	}
	
	public void setImmune(boolean input) {
		immune = input;
		updateDisplay();
	}
}