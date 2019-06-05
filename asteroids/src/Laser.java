import javafx.scene.image.Image;

public class Laser extends Actor {

	double y;
	double x;
	double rotation;
	double totalDistance;
	boolean fromPlayer;
	
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
	@Override
	public void act() {
		// move it in the direction of rotation
		// by dx and dy
		// add to total distance.
		// if total distance > a threshhold, then delete this
		
		double dx = Math.cos(Math.toRadians(getRotate() + 90)) * 3;
		double dy = Math.sin(Math.toRadians(getRotate() + 90)) * 3;
		
		setY(getY() + dy);
		setX(getX() + dx);
		
		totalDistance ++;
		if (totalDistance > 50) {
			getWorld().remove(this);
		}
		
	}
	
	public boolean isFromPlayer() {
		return fromPlayer;
	}


}
