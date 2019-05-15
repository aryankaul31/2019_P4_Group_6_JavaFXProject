import java.util.ArrayList;
import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class World extends Pane {
	private AnimationTimer timer;
	static HashSet<KeyCode> keyCodes = new HashSet<KeyCode>();

	public World() {
		super();
		timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				act(now); // call act on the world
				for (int i = 0; i < getChildren().size(); i++) {
					// call act on each Actor in it
					if (Actor.class.isInstance(getChildren().get(i))) {

						((Actor) getChildren().get(i)).act();
					}
				}
			}

		};
	}

	public abstract void act(long now);

	public void add(Actor actor) {
		getChildren().add(actor);
	}

	public void remove(Actor actor) {
		getChildren().remove(actor);
	}

	public void start() {
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

	public <A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> cls) {
		ArrayList<A> matching = new ArrayList<A>();

		for (Node a : getChildren()) {
			// for each actor, if matching class
			if (cls.isInstance(a)) {
				// typecast and add to matching
				matching.add(cls.cast(a));
			}
		}

		return matching;
	}

	public void addKeyCode(KeyCode k) {
		keyCodes.add(k);

	}

	public void removeKeyCode(KeyCode k) {
		keyCodes.remove(k);

	}

	// determine whether a given KeyCode is in the list of keys that are currently
	// down
	public boolean isKeyDown(KeyCode k) {
		return keyCodes.contains(k);

	}
}
