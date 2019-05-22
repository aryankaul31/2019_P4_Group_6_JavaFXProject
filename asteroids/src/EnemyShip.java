import java.util.Random;

import javafx.scene.image.Image;

public class EnemyShip extends Spaceship {

	public EnemyShip(int h, int max, boolean isPlayer) {
		super(h, max);
		Image image = new Image("file:spaceship.png");
		
		setFitWidth(25);
		setPreserveRatio(true);
		
		speed = 2;
	}
	
	@Override
	public void act() {
		handleMovement();
		shoot();
		handleCollisions();
	}
	
	@Override
	public void handleMovement() {
		// randomize the movement
		Random rand = new Random();
		double dx = rand.nextDouble() * 5 + 1;
		double dy = rand.nextDouble() * 5 + 1;
		setX(getX() + dx);
		setY(getY() + dy);
		
		// edge loop!!
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
		
		shoot();
	}
	
	private void shoot() {
		// shoot towards the player
		Spaceship x = (Spaceship) getWorld().getObjects(Spaceship.class);
		double slope = (x.getY() - getY()) / (x.getX() - getX());
		double theta = Math.atan(slope) + 90;
		Laser l = new Laser(getX(), getY(), theta);
		getWorld().add(l);
	}
	
	@Override
	public void handleCollisions() {
		
	}


}
