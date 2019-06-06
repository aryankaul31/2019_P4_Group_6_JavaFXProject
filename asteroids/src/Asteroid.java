import java.util.Random;

import javafx.scene.image.Image;

public class Asteroid extends Actor{
	
	int health;
	int dx;
	int dy;
	
	public Asteroid(int x, int y) {
		Image image = new Image("file:Images/asteroid.png");
		setImage(image);
		setFitWidth(50);
		setPreserveRatio(true);
	
		dx = x;
		dy = y;
		
	}

	@Override
	public void act() {
		move(dx, dy);
		
		if (getY() < 0) {
			setY(getWorld().getHeight());
		}
		if (getX() < 0) {
			setX(getWorld().getWidth());
		}
		if (getX() + getWidth() > getWorld().getWidth()) {
			setX(0.0);
		}
		if (getY() + getHeight() > getWorld().getHeight()) {
			setY(0.0);
		}
		
		setRotate(getRotate() + 5);

		if(health == 0) {
			getWorld().remove(this);
		}
	}
	
	public void setHealth(int health) {
		this.health = health;
		if (health == 0) {
			getWorld().remove(this);
		}
	}
	
	public int getHealth() {
		return health;
	}
	
	public void handleCollisions() {
		
		if(getOneIntersectingObject(Laser.class) != null) {
			if(getOneIntersectingObject(Laser.class).fromPlayer) {
				this.setHealth(getHealth()-100);
				getWorld().remove(getOneIntersectingObject(Laser.class));
			}
		}
		
		
	}

}