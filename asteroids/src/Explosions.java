import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * This class is the explosions class that extends actor. It is used to display an explosion when an asteroid 
 * or the player or the enemy spaceship is killed.
 *
 */
public class Explosions extends Actor{
	
	/** * ArrayList to keep track of the spaceships */
	ArrayList<Image> explosions = new ArrayList<Image>();
	/**Checking which state it is in to change the arrayList picture accordingly */
	int state;
	/**keeping track of the steps that are called every act */
	int steps;
	/**
	 * Constructor for the explosions class, this puts the explosions into the arrayList and initializes the other 2 variables
	 */
	public Explosions() {
		state = 0;
		steps = 0;
		for (int i = 1 ; i < 17 ; i ++) {
			explosions.add(new Image("file:Images/exp" + i + ".png"));
		}
		setState(0);
	}
	/**
	 * Set method for the stat variable
	 * @param n The new parameter for the  state
	 */
	
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
/**
 * Increases the state every 10 act methods to change the pictures accordingly.
 */
	@Override
	public void act() {
		steps ++;
		if (steps%10 == 0) {
			setState(state + 1);
		}
	}
}
