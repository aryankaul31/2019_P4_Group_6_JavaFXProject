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
		
		Button b = new Button();
		b.setText("Help");
		b.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Help");
				alert.setContentText("Press the back arrow to go back, with deacceleration, in the opposite way spaceship is pointing\n" + 
						"space to shoot lasers\n" + 
						"c to clear your immunity\n" + 
						"i to get immunity for a few minutes, for testing purposes\n" + 
						"Asteroids spawn asteroid fragments when destroyed, and each fragment spawns 2 more\n" + 
						"Enemy ships fly around fast, shoot at the player, avoid them\n" + 
						"You have five lives, try and maximize your score\n" + 
						"You get immunity by either giving yourself it, by completing a level, or by dying - you get immunity for like 2 seconds or so\n" + 
						"Asteroids move randomly, so be careful\n" + 
						"");
				
				alert.setOnHidden(new EventHandler<DialogEvent>() {
					public void handle(DialogEvent evt) {
						alert.close();
					}
				});
				
			    alert.show(); 

			}
			
		});
		hb.getChildren().add(b);
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
