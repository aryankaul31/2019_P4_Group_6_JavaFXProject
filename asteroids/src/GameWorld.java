import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameWorld extends World {

	public static Score score;
	public static Immunity immune;
	public static int level;
	
	public GameWorld() {
		score = new Score();
		score.setScore(0);
		score.setX(20);
		score.setY(20);
		
		immune = new Immunity();
		immune.setImmune(false);
		immune.setX(20);
		immune.setY(20);
		
		level = 1;
		
		spawnRandomAsteroid();
	}
	@Override
	public void act(long now) {
		checkForLevelCompletion();
	}
	
	public void checkForLevelCompletion() {
		List<Asteroid> x = getObjects(Asteroid.class);
		List<EnemyShip> y = getObjects(EnemyShip.class);
		
		if (x.size() == 0 && y.size() == 0) {
			// level complete
			level ++;
			if (level > 2) {
				for (int i = 0 ; i < (level - 1) / 2 ; i ++) {
					spawnRandomEnemy();
				}
			}
			
			for (int i = 0 ; i < level + 1 ; i ++) {
				spawnRandomAsteroid();
			}
			
			Spaceship nn = (Spaceship) getObjects(Spaceship.class).get(0);
			nn.setX(250);
			nn.setY(250);
		}
	}
	
	public void spawnRandomEnemy() {
		EnemyShip x = new EnemyShip(1, 4);
		x.setX(100);
		x.setY(100);
		add(x);
	}
	
	public void spawnRandomAsteroid() {
		Asteroid asteroid = new Asteroid(1);
		asteroid.setHealth(100);
		asteroid.setX(Math.random() * 500);
		asteroid.setY(Math.random() * 300);
		add(asteroid);
	}
	
	public Score getScore() {
		return score;
	}
	
	public Immunity returnImmunity() {
		return immune;
	}
	
	public void setLevel(int x) {
		level = x;
	}
	
	public int getLevel() {
		return level;
	}

	public void gameOver() {
		
		Spaceship nn = (Spaceship) getObjects(Spaceship.class).get(0);
		remove(nn);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Over");
		alert.setContentText("Well Played! \nYour final score is " + score.getScore() + "\n");
		
		alert.setOnHidden(new EventHandler<DialogEvent>() {
			public void handle(DialogEvent evt) {
				Platform.exit();
			}
		});

	    alert.show(); 
	}
	
}
