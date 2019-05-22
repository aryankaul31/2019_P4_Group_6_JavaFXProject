import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Spaceship extends Actor {

	int health;
	int maxSpeed;
	double speed;
	boolean isPowerUp;
	int duration;

	/*
	 * This should be subclassed, once for - Player - Enemy Spaceship
	 */

	public Spaceship(int h, int max) {
		Image image = new Image("file:player.png");

		setImage(image);
		setFitWidth(25);
		setPreserveRatio(true);
		
		health = h;
		maxSpeed = max;
		speed = 0;
	}
	

	@Override
	public void act() {
		handleMovement();
		
		// handling all types of collisions
		handlePowerup();
		handleAsteroidCollision();
		handleSpaceshipCollision();
		
		updatePosition();
		
	}

	public void handleMovement() {
		// rotation
		if (getWorld().isKeyDown(KeyCode.LEFT)) {
			setRotate(getRotate() - 5);
		}
		if (getWorld().isKeyDown(KeyCode.RIGHT)) {
			setRotate(getRotate() + 5);
		}
		if (getRotate() < 0) {
			setRotate(360 + getRotate());
		} else if (getRotate() > 360) {
			setRotate(getRotate() - 360);
		}
		
		// speed changing
		if (getWorld().isKeyDown(KeyCode.UP)) {
			// Dynamically increasing speed
			speed += 0.1;
			//System.out.println(speed);
			if (speed > maxSpeed) speed = maxSpeed;
			
		} else if (getWorld().isKeyDown(KeyCode.DOWN)) {
			// Dynamically decreasing speed to 0
			speed -= 0.1;
			if (speed < 0) speed = 0;

		} else {
			// subtle returning to normal speed
			speed -= 0.05;
			if (speed < 0) speed = 0;
		}
		
		if (getWorld().isKeyDown(KeyCode.SPACE)) {
			// shoot laser things
		}


	}

	public void updatePosition() {
		
		double dx = Math.cos(Math.toRadians(getRotate() + 90)) * speed;
		double dy = Math.sin(Math.toRadians(getRotate() + 90)) * speed;
		
//		System.out.println("dx = " + dx);
//		System.out.println("dy = " + dy);
//		System.out.println("Rotation : " + (getRotate() + 90));
		
		setX(getX() - dx);
		setY(getY() - dy);
		
		// still have to do edgelooping
	}

	public void handlePowerup() {
		/*
		 * if spaceship runs into a powerup, set the isPowerUp boolean to true and then
		 * set a duration and apply the effect, based on static variables in Powerup, to
		 * the player for that duration.
		 */

	}

	public void handleAsteroidCollision() {
		/* if spaceship runs into an asteroid, loses a life */
	}

	public void handleSpaceshipCollision() {
		// TODO Auto-generated method stub
		
	}
}
