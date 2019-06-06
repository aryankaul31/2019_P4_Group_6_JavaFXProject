import java.util.Random;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Game extends Application{
	
	GameWorld world;
	static Label lives; 
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Asteroids");
		BorderPane root = new BorderPane();
		Image png = new Image("file:Images/space.jpg");
		BackgroundImage image = new BackgroundImage(png, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background ground = new Background(image);
		root.setBackground(ground);
		
		world = new GameWorld();
		world.setPrefSize(500, 500);
		root.setCenter(world);
		Scene scene = new Scene(root);
		HBox hb = new HBox();
		Label label = new Label("Score: ");
	    label.setFont(new Font("Arial", 20));

		label.setTextFill(Color.web("#000000"));
		hb.getChildren().add(label);
		lives = new Label("Lives Left: ");
		
		lives.setTextFill(Color.web("#000000"));
	    lives.setFont(new Font("Arial", 20));

		hb.getChildren().add(lives);
		root.setTop(hb);
		Spaceship ship = new Spaceship(5, 3);
		ship.setX(250);
		ship.setY(400);
		world.add(ship);
		hb.setSpacing(100);
		
//		for (int i = 0 ; i < 1 ; i ++) {
//			EnemyShip x = new EnemyShip(1, 4);
//			x.setX(100 + i*10);
//			x.setY(100 + i*10);
//			world.add(x);
//			
//		}
		
		for(int i = 0; i < 2; i++) {
			Random rand = new Random();
			Asteroid asteroid = new Asteroid(rand.nextInt(2) + 1, rand.nextInt(2) + 1, 1);
			asteroid.setHealth(100);
			asteroid.setX(Math.random() * 500);
			
			asteroid.setY(Math.random() * 500);
			world.add(asteroid);
		}
		// TODO Auto-generated method stub
		world.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				world.addKeyCode(event.getCode());
			}

		});
		world.setOnKeyReleased(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				world.removeKeyCode(event.getCode());
			}
			
		});
		
		
		
		

		world.start();
		primaryStage.setScene(scene);
		primaryStage.show();
		world.requestFocus();
		
		
	}

}
