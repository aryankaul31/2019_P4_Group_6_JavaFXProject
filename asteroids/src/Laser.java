
public class Laser extends Actor {

	double y;
	double x;
	double rotation;
	double totalDistance;
	
	public Laser(double d, double e, double r) {
		this.y = e;
		setX(d);
		this.x = d;
		setY(e);
		rotation = r;
		setRotate(r);
	}
	@Override
	public void act() {
		// move it in the direction of rotation
		// by dx and dy
		// add to total distance.
		// if total distance > a threshhold, then delete this
		
		double dx = Math.cos(Math.toRadians(getRotate() + 90)) * 6;
		double dy = Math.sin(Math.toRadians(getRotate() + 90)) * 6;
		
		setY(getY() + dy);
		setX(getX() + dx);
		
		totalDistance ++;
		if (totalDistance > 10) {
			getWorld().remove(this);
		}
		
	}


}
