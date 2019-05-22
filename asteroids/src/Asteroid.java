import javafx.scene.image.Image;

public class Asteroid extends Actor{
	
	int health;
	int dx;
	int dy;
	
	public Asteroid(int x, int y) {
		Image image = new Image("file:asteroid.png");
		setImage(image);
	
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