import javafx.scene.image.Image;

public class PowerUp extends Actor{
	public static final int NEGATIVE_HEALTH_POWERUP = 0;
	public static final int HEALTH_POWERUP = 1;
	public static final int SPEED_POWERUP = 2;
	
	int state;
	
	public PowerUp(int state) {
		this.state = state;
		
		/*
		 * Here, pick one of the four saved images.
		 * They should all be downloaded and saved as 
		 * powerup0, powerup1.png, etc in the file.
		 * Pick the proper image and set the image.
		 */
		Image[] y = new Image[3];
		y[0] = new Image("file:Images/negativeHealth.jpg");
		y[1] = new Image("file:Images/healthPowerup.png");
		y[2] = new Image("file:Images/speedUpSprite.png");
		
		setImage(y[state]);
		
	}

	@Override
	public void act() {
		// do nothing but slowly rotate
		setRotate(getRotate() + 2);
	}
	
	public int getState() {
		return state;
	}
}
