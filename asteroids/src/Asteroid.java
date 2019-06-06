import java.util.Random;

import javafx.scene.image.Image;

public class Asteroid extends Actor{
	
	int health;
	int size;
	double dx;
	double dy;
	
	public Asteroid(int size) {
		Image image = new Image("file:Images/asteroid.png");
		setImage(image);
		setPreserveRatio(true);
		setPickOnBounds(false);

		Random rand = new Random();
		dx = rand.nextInt(5) - 2;
		dy = rand.nextInt(5) - 2;
		
		this.health = (4 / size + 1) * 100;
		/*
		 * Size starts out at 1, then 2, then 3. 3 is the smallest, dies after 3.
		 * 
		 */
		this.size = size;
		
		setFitWidth(75 / size);
		
	}

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
	
	public int getHealth() {
		return health;
	}
	
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