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