import java.util.Random;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Spaceship extends Actor {

	int lives;
	int maxSpeed;
	double speed;
	boolean isPowerUp;
	int duration;
	int steps;
	boolean canShoot;
	int immunity;
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
		
		steps = 0;
		
		canShoot = true;
		
		immunity = 100;
	}
	

	@Override
	public void act() {
		//edit the way the game ends, add an explosion if you can
		
		handleMovement();
		
		// handling all types of collisions
		handlePowerup();
		handleAsteroidCollision();
		handleSpaceshipCollision();
		handleLaserCollision();
		updatePosition();
		Game.lives.setText("Lives Left: " + lives);
		
		if (immunity > 0) {
			immunity --;
		}
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
		
		if (!canShoot) {
			steps ++;
			if (steps == 10) {
				canShoot = true;
				steps = 0;
			}
		}
		
		if (getWorld().isKeyDown(KeyCode.SPACE) && canShoot) {
			Laser l = new Laser(getX(), getY(), getRotate() + 180, true);
			getWorld().add(l);
			canShoot = false;
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
			
			//numbers can be changed, I just did what I thought
			
			isPowerUp = true;
			PowerUp x = getOneIntersectingObject(PowerUp.class);
			if(x.getState() == 0) {
				
			} else if(x.getState() == 1) {
				duration = 0;
				lives++;
			} else if(x.getState() == 2) {
				duration = 200;
				speed += 10;
			}
		}

	}

	public void handleAsteroidCollision() {
		/* if spaceship runs into an asteroid, loses a life */
		if(getOneIntersectingObject(Asteroid.class) != null) {
			addExplosion();
			if (immunity == 0) {
				decrementLives();
			}
			getWorld().remove(getOneIntersectingObject(Asteroid.class));
		}
	}

	private void addExplosion() {
		Explosions x = new Explosions();
		getWorld().add(x);
		x.setX(getX());
		x.setY(getY());
	}


	public void handleSpaceshipCollision() {
		// TODO Auto-generated method stub
		if(getOneIntersectingObject(EnemyShip.class) != null) {
			addExplosion();
			if (immunity == 0) {
				decrementLives();
			}
			getWorld().remove(getOneIntersectingObject(EnemyShip.class));
		}
	}
	
	public void handleLaserCollision() {
		if (getOneIntersectingObject(Laser.class) != null) {
			Laser x = getOneIntersectingObject(Laser.class);
			if (!x.isFromPlayer()) {
				getWorld().remove(x);
				addExplosion();
				if (immunity == 0) {
					decrementLives();
				}
			}
		}
	}
	
	public void decrementLives() {
		if(lives == 1) {
			// display error message of some sort here
			System.exit(0);
		}
		
		lives --;
		setX(250);
		setY(400);
		immunity = 20;
		
		// wipe the field of asteroids and enemies
		for (Actor x : getWorld().getObjects(Asteroid.class)) {
			getWorld().remove(x);
		}
		for (Actor x : getWorld().getObjects(EnemyShip.class)) {
			getWorld().remove(x);
		}
		
		// reset with new thingies
//		for (int i = 0 ; i < 1 ; i ++) {
//			EnemyShip x = new EnemyShip(1, 4);
//			x.setX(100 + i*10);
//			x.setY(100 + i*10);
//			getWorld().add(x);
//			
//		}
		for(int i = 0; i < 2; i++) {
			Random rand = new Random();
			Asteroid asteroid = new Asteroid(rand.nextInt(2) + 1, rand.nextInt(2) + 1, 1);
			asteroid.setHealth(100);
			asteroid.setX(Math.random() * 500);
			
			asteroid.setY(Math.random() * 500);
			getWorld().add(asteroid);
		}
	}
}
