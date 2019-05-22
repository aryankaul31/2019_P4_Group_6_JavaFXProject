import javafx.scene.image.Image;

public class Asteroid extends Actor{
	
	int health;
	int dx;
	int dy;
	
	public Asteroid(int x, int y) {
		Image image = new Image("file:asteroid.png");
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
			dy = -dy;
		}
		if (getX() < 0) {
			dx = -dx;
		}
		if (getX() + getWidth() > getWorld().getWidth()) {
			dx = -dx;
		}
		if (getY() + getHeight() > getWorld().getHeight()) {
			dy = -dy;
		}
		
		setRotate(getRotate() + 5);
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

}