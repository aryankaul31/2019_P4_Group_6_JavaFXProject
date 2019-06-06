import java.util.Random;

import javafx.scene.image.Image;
/**
 * This class is the asteroids class that extends actor. It is used
 * to control an asteroid and describe its movements.
 *
 */
public class Asteroid extends Actor{
	/**
	 * Keeps track of asteroid health
	 */
	int health;
	/**
	 * Keeps track of asteroid size
	 */
	int size;
	/**
	 * Keeps track of change in asteroid position with respect to x
	 */
	double dx;
	/**
	 * Keeps track of change in asteroid position with respect to y
	 */
	double dy;
	
	/**
	 * This constructor is used to create an asteroid of a certain size
	 * @param size The initial size of the asteroid
	 */
	public Asteroid(int size) {
		Image image = new Image("file:Images/asteroid.png");
		setImage(image);
		setPreserveRatio(true);

		Random rand = new Random();
		
		while (dx == 0 && dy == 0) {
			dx = rand.nextInt(5) - 2;
			dy = rand.nextInt(5) - 2;
		}
		
		
		this.health = (4 / size + 1) * 100;
		/*
		 * Size starts out at 1, then 2, then 3. 3 is the smallest, dies after 3.
		 * 
		 */
		this.size = size;
		
		setFitWidth(50 / size);
		
	}

	/**
	 * The act method is called extremely frequently and rotates the asteroid
	 * in order to create a spiraling effect. It also handles collisions.
	 * */
	@Override
	public void act() {
		move(dx, dy);
		
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
		
		setRotate(getRotate() + 5);
		
		handleCollisions();

	}
	
	/**
	 * Setter method to set health of the asteroid and also creates 2 smaller asteroids
	 * @param health The health you would like to set the asteroid to
	 */
	public void setHealth(int health) {
		this.health = health;
		if (health <= 1) {
			if (size != 3) {
				Asteroid one = new Asteroid(size + 1);
				Asteroid two = new Asteroid(size + 1);
				one.setX(getX() + 15);
				one.setY(getY() - 15);
				two.setX(getX() - 15);
				two.setY(getY() + 15);
				getWorld().add(one);
				getWorld().add(two);
			}
			getWorld().remove(this);
		}
	}
	
	/**
	 * Get method to get health of any given asteroid
	 * @return The asteroid health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Handles collisions for asteroid objects and lowers health/sets score
	 * based on a player hit. Also handles adding explosions.
	 */
	public void handleCollisions() {
		if(getOneIntersectingObject(Laser.class) != null) {
			Laser x = getOneIntersectingObject(Laser.class);
			if(x.isFromPlayer()) {
				setHealth(getHealth()-100);
				GameWorld.score.setScore(GameWorld.score.getScore() + 25);

				addExplosion();
			}
			try {
				getWorld().remove(x);
			} catch(Exception e) {
				
			}
		}
	}
	
	/**
	 * Adds an explosion to a given x or y location
	 */
	public void addExplosion() {
		Explosions x = new Explosions();
		x.setX(getX());
		x.setY(getY());
		try {
			getWorld().add(x);
		} catch(Exception e) {
			
		}
	}

}