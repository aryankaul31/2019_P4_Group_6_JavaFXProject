import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Spaceship extends Actor {

	int lives;
	int maxSpeed;
	double speed;
	boolean isPowerUp;
	int duration;

	/*
	 * This should be subclassed, once for - Player - Enemy Spaceship
	 */

	public Spaceship(int h, int max) {
		Image image = new Image("file:Images/player.png");

		setImage(image);
		setFitWidth(25);
		setPreserveRatio(true);
		
		lives = h;
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
			Laser l = new Laser(getX(), getY(), getRotate() + 180);
			getWorld().add(l);
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
		if (getY() + getWidth() < 0) {
			setY(getWorld().getHeight() - getWidth() - 1);
		}
		if (getX() + getWidth()< 0) {
			setX(getWorld().getWidth() - getWidth() - 1);
		}
		if (getX() > getWorld().getWidth()) {
			setX(1.0);
		}
		if (getY()  > getWorld().getHeight()) {
			setY(1.0);
		}

	}

	public void handlePowerup() {
		/*
		 * if spaceship runs into a powerup, set the isPowerUp boolean to true and then
		 * set a duration and apply the effect, based on static variables in Powerup, to
		 * the player for that duration.
		 */
		if(getOneIntersectingObject(PowerUp.class) != null) {
			isPowerUp = true;
			duration = 200;
			//not done
		}

	}

	public void handleAsteroidCollision() {
		/* if spaceship runs into an asteroid, loses a life */
		if(getOneIntersectingObject(Asteroid.class) != null) {
			lives--;
		}
	}

	public void handleSpaceshipCollision() {
		// TODO Auto-generated method stub
		if(getOneIntersectingObject(Asteroid.class) != null) {
			lives--;
		}
	}
}
