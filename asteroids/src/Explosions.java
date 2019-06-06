import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Explosions extends Actor{
	ArrayList<Image> explosions = new ArrayList<Image>();
	int state;
	int steps;
	
	public Explosions() {
		state = 0;
		steps = 0;
		for (int i = 1 ; i < 17 ; i ++) {
			explosions.add(new Image("file:Images/exp" + i + ".png"));
		}
		setState(0);
	}
	
	public void setState(int n) {
		state = n;
		if (state >= explosions.size()) {
			getWorld().remove(this);
		} else {
			setImage(explosions.get(state));
			setPreserveRatio(true);
			setFitWidth(30);
		}
	}

	@Override
	public void act() {
		steps ++;
		if (steps%10 == 0) {
			setState(state + 1);
		}
	}
}
