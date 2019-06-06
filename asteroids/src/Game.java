import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
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
	public void start(final Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Asteroids");
		final BorderPane root = new BorderPane();
		Image png = new Image("file:Images/space.jpg");
		BackgroundImage image = new BackgroundImage(png, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background ground = new Background(image);
		root.setBackground(ground);
		
		world = new GameWorld();
		world.setPrefSize(500, 500);
		root.setCenter(world);
		final Scene scene = new Scene(root);
		
		world.start();
		primaryStage.setScene(scene);
		primaryStage.show();
		world.requestFocus();
		
		// help stuff
		
		final Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help page");
		alert.setContentText("Press the forward key to move forward, with acceleration, in the direction of the spaceship. \n"
				+ "Press the back arrow to go back, with deacceleration, in the opposite way spaceship is pointing. \n" + 
				"Press the left and right arrows to rotate your spaceship by 7 degrees in either direction.\n" +
				"Press space to shoot lasers. \n" + 
				"Press \'i\' to get immunity for a few minutes, for testing purposes. \n" + 
				"Press \'c\' to clear your immunity. \n\n" + 
				"Asteroids spawn asteroid fragments when destroyed, and each fragment spawns 2 more\n" + 
				"Enemy ships fly around fast and shoot at the player. Avoid them! \n" + 
				"You have five lives, try and maximize your score. \n" + 
				"You get temporary immunity when you die or when you kill all the asteroids and enemy spaceships on the screen, which proceeds to the next level.\n" + 
				"Asteroids move randomly, so be careful.\n\n" + 
				"Good luck! \n");
	    alert.show();
	    
	    alert.setOnHidden(new EventHandler<DialogEvent>() {
			public void handle(DialogEvent evt) {
				alert.close();
				HBox hb = new HBox();
				
				lives = new Label("Lives: ");
				
				lives.setTextFill(Color.web("#000000"));
			    lives.setFont(new Font("Arial", 20));

				hb.getChildren().addAll(lives, world.getScore(), world.returnImmunity());
				root.setTop(hb);
				Spaceship ship = new Spaceship(5, 3);
				ship.setX(250);
				ship.setY(250);
				world.add(ship);
				hb.setSpacing(50);
				hb.setStyle("-fx-background-color: #343d46;");
				
				
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

			}
		});
		
	}
	

}
