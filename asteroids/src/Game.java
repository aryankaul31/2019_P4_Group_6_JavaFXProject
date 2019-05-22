import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Game extends Application{
	
	GameWorld world;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Asteroids");
		BorderPane root = new BorderPane();
		
		world = new GameWorld();
		world.setPrefSize(500, 500);
		root.setCenter(world);
		Scene scene = new Scene(root);
		
		Spaceship ship = new Spaceship(5, 3);
		ship.setX(250);
		ship.setY(400);
		world.add(ship);
		
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
