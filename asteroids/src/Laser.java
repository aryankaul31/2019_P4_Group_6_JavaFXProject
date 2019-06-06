import javafx.scene.image.Image;
/**
 * 
 * The laser class is used to display the lasers once the enemy spaceship or the player fires the 
 * lasers. 
 *
 *
 */
public class Laser extends Actor {
	/**The initial y position of the laser */
	double y;
	/**The initial x position of the laser */
	double x;
	/**Keeps track of where the laser is facing */
	double rotation;
	/**The variable to track how far the laser has gone, once it reaches a certain distance it is removed */
	double totalDistance;
	/**The variable to track if the laser was fired by the player or not */
	boolean fromPlayer;
	/**
	 * This constructor is used to create a laser at the location of the player or enemy spaceship.
	 * @param d The initial x position of the laser
	 * @param e The initial y position of the laser
	 * @param r initial rotation for where the laser moves
	 * @param fromPlayer Keeps track if the player fired the laser or not
	 */
	public Laser(double d, double e, double r, boolean fromPlayer) {
		setImage(new Image("file:Images/laser.png"));
		setFitWidth(15);
		setPreserveRatio(true);
		
		setX(d);
		setY(e);
		setRotate(r);

		this.y = e;
		this.x = d;
		rotation = r;
		this.fromPlayer = fromPlayer;
	}
	/**
	 * The act method is called extremely frequently and updates the position of the laser to give it the 
	 * shooting effect. It also keeps track of the distance and when that variable reaches 60, the laser 
	 * is removed from the world. 
	 * */
	
	@Override
	public void act() {
		// move it in the direction of rotation
		// by dx and dy
		// add to total distance.
		// if total distance > a threshhold, then delete this
		
		double dx = Math.cos(Math.toRadians(getRotate() + 90)) * 5;
		double dy = Math.sin(Math.toRadians(getRotate() + 90)) * 5;
		
		setY(getY() + dy);
		setX(getX() + dx);
		
		totalDistance ++;
		if (totalDistance > 60 ) {
			getWorld().remove(this);
		}
		
	}
	/**
	 * Get method to determine if the laser was from the player 
	 * @return The from player variable for different classes to check if the laser was from the player
	 */
	public boolean isFromPlayer() {
		return fromPlayer;
	}


}
