package is.ru.tgra.managers;

import is.ru.tgra.Breakout;
import is.ru.tgra.objects.Ball;
import is.ru.tgra.objects.Block;
import is.ru.tgra.utils.Point2D;
import is.ru.tgra.utils.Settings;
import is.ru.tgra.utils.Vector2D;

public class Collisions {
	
	private static boolean collision = false;
	
	public static void checkCollisions(float deltaTime) {
		collision = false;
		checkCollisionsWithBall(GameManager.ball, deltaTime);
	}
	
	private static void checkCollisionsWithBall(Ball ball, float timeRemaining) {
		
		// Base case.
		if (timeRemaining <= 0) {
			return;
		}
		
		Point2D[] bounds = GameManager.bounds;
		for (int i = 0; i < bounds.length; i++) {
			int j = (i < bounds.length -1) ? i+1 : 0;
			if (ball.checkCollisionWithLine(bounds[i], bounds[j], timeRemaining) && i == 3 && j == 0) {
				// We've hit the ground and we lose a life.
				GameManager.loseLife();
				collision = true;
				break;
			}
		}
		
		Point2D[] paddlePoints = GameManager.paddle.getPoints();
		for (int i = 0; i < paddlePoints.length - 1; i++) {
			if (ball.checkCollisionWithLine(paddlePoints[i], paddlePoints[i+1], timeRemaining) && i == 1) {
				// We've collided with the top of the paddle
				float ratio = GameManager.paddle.getRatioOfPoint(ball.getPHit());
				
				if (ratio < 0.35 || ratio > 0.65) {
					ball.setDirection(new Vector2D(ratio*2-1, 1));
				}
				
				break;
			}
		}
		
		for (Block block : GameManager.blocks) {
			if (!block.getExploded()) {
				Point2D[] blockPoints = block.getPoints();
				
				for (int i = 0; i < blockPoints.length; i++) {
					int j = (i < blockPoints.length - 1) ? i+1 : 0;
					boolean collision = ball.checkCollisionWithLine(blockPoints[i], blockPoints[j], timeRemaining);
					if (collision) {
						ball.impact();
						block.explode();
						GameManager.blockCount--;
						ScreenShaker.shake(Settings.SHAKE_POWER, Settings.SHAKE_TIMER);
						GameManager.scoreboard.addToScore(500);
						//SoundManager.POP.play();
						break;
					}
				}
			}
		}
	}
}
