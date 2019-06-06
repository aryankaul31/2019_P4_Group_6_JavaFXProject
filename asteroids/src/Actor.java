import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView {

	/**
	 * abstract act method, meant to be overridden by all subclasses
	 */
	public abstract void act();
	/**
	 * Moves actor
	 * @param dx - amount to move x
	 * @param dy - amount to move y
	 */
	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}

	/**
	 * Getter for width of actor
	 * @return width
	 */
	public double getWidth() {
		return getBoundsInParent().getWidth();
	}
	/**
	 * Getter for height of actor
	 * @return height
	 */
	public double getHeight() {
		return getBoundsInParent().getHeight();
	}

	/**
	 * Gets all intersecting objects of a certain class
	 * @param <A> A class type
	 * @param cls
	 * @return A list of all intersecting objects of a certain class
	 */
	public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls) {
		ArrayList<A> objects = new ArrayList<A>();

		for (Actor x : getWorld().getObjects(cls)) {
			if (!this.equals(x)) {
				if (intersects(x.getBoundsInParent())) {
					objects.add((A) x);
				}
			}
		}

		return objects;
	}
	
	/**
	 * Gets all intersecting objects
	 * @param <A> Class type
	 * @param cls
	 * @return List of all objects in world of a particular type
	 */

	public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
		List<A> objects = getIntersectingObjects(cls);

		if (objects.size() == 0) {
			return null;
		} else {
			return objects.get(0);
		}

	}
	
	/**
	 * Getter for world
	 * @return World
	 */

	public World getWorld() {
		return (World) getParent();
	}
}
