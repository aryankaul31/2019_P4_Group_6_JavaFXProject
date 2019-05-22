import java.util.Random;

import javafx.scene.image.Image;

public class EnemyShip extends Spaceship {

	int total = 0;
	double dx;
	double dy;
	public EnemyShip(int h, int max) {
		super(h, max);
		
		Image image = new Image("file:spaceship.jpg");
		setImage(image);
		setFitWidth(20);
		setPreserveRatio(true);

		Random rand = new Random();
		dx = rand.nextDouble() * 10 - 5;
		dy = rand.nextDouble() * 10 - 5;
	}
	
	@Override
	public void act() {
		handleMovement();
		handleCollisions();
	}
	
	@Override
	public void handleMovement() {
		total ++;
		// randomize the movement
		Random rand = new Random();
		double d = rand.nextDouble() * 2 - 1;
		setX(getX() + dx + d);
		setY(getY() + dy + d);
		
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
		
		if (total % 25 == 0) {
			shoot();
		}
	}
	
	private void shoot() {
		// shoot towards the player
		Spaceship x = (Spaceship) getWorld().getObjects(Spaceship.class).get(0);
		double theta = Math.atan2(getY() - x.getY(), getX() - x.getX());
		Laser l = new Laser(getX(), getY(), Math.toDegrees(theta) + 90);
		System.out.println("Theta : " + Math.toDegrees(theta));
		getWorld().add(l);
	}
	
	@Override
	public void handleCollisions() {
		
	}


}
