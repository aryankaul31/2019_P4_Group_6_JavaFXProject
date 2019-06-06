
public class GameWorld extends World {

	private Score score;
	
	public GameWorld() {
		score = new Score();
		score.setScore(0);
		score.setX(20);
		score.setY(20);
		getChildren().add(score);
	}
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
	
	public Score getScore() {
		return score;
	}
	
}
