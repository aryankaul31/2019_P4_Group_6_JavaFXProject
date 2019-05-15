import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView {

	public abstract void act();

	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}

	// get height
	public double getWidth() {
		return getBoundsInParent().getWidth();
	}

	public double getHeight() {
		return getBoundsInParent().getHeight();
	}

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

	public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
		List<A> objects = getIntersectingObjects(cls);

		if (objects.size() == 0) {
			return null;
		} else {
			return objects.get(0);
		}

	}

	public World getWorld() {
		return (World) getParent();
	}
}
