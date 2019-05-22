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
	public void handleMovement() {
		// randomize the movement
	}

	@Override
	public void handlePowerup() {
		// nothing, it should just pass right through it.
	}

}
