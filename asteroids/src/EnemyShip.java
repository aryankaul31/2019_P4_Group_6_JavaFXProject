import java.util.Random;

import javafx.scene.image.Image;
/**
 * This class is the enemy ship class that extends actor. It is used to create
 * an enemy spaceship capable of attacking the player
 *
 */
public class EnemyShip extends Spaceship {

	int total = 0;
	/**
	 * Keeps track of change in enemy ship position with respect to y
	 */
	double dx;
	/**
	 * Keeps track of change in enemy ship position with respect to y
	 */
	double dy;
	public EnemyShip(int h, int max) {
		super(h, max);
		
		Image image = new Image("file:Images/enemyspace.png");
		setImage(image);
		setFitWidth(20);
		setPreserveRatio(true);

		Random rand = new Random();
		dx = rand.nextDouble() * 7 - 3;
		dy = rand.nextDouble() * 7 - 3;
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
		
		if (total % 50 == 0) {
			shoot();
		}
	}
	
	private void shoot() {
		// shoot towards the player
		Spaceship x = (Spaceship) getWorld().getObjects(Spaceship.class).get(0);
		double theta = Math.atan2(getY() - x.getY(), getX() - x.getX());
		Laser l = new Laser(getX(), getY(), Math.toDegrees(theta) + 90, false);
		System.out.println("Theta : " + Math.toDegrees(theta));
		getWorld().add(l);
	}
//	 if (total % 25 == 0 then getX) {
//		 
//	 }
	public void handleCollisions() {
		if (getOneIntersectingObject(Laser.class) != null) {
			Laser x = getOneIntersectingObject(Laser.class);
			if (x.isFromPlayer()) { 
				getWorld().remove(x);
				GameWorld.score.setScore(GameWorld.score.getScore() + 50);
				getWorld().remove(this);
				
			}
		}
	}
	
	


}
