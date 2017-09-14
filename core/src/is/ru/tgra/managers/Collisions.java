package is.ru.tgra.managers;

import is.ru.tgra.objects.Ball;
import is.ru.tgra.objects.Block;
import is.ru.tgra.utils.Point2D;
import is.ru.tgra.utils.Settings;
import is.ru.tgra.utils.Utils;
import is.ru.tgra.utils.Vector2D;

public class Collisions {
	private static Point2D pHit;
	public static void checkCollisions(Ball ball, float timeRemaining) {
		float tHit = Float.MAX_VALUE;
		// Base case.
		
		if (timeRemaining <= 0) {
			
			return;
		}
		
		Point2D[] bounds = GameManager.bounds;
		for (int i = 0; i < bounds.length; i++) {
			int j = (i < bounds.length -1) ? i+1 : 0;
			tHit = checkBallCollisionWithLine(bounds[i], bounds[j], ball, timeRemaining);
			
			if (tHit > 0.0f && i == 3 && j == 0) {
				// We've hit the ground and we lose a life.
				GameManager.loseLife();
				ball.setTimeLeftToMove(0);
				
				// Recursively check if collision in rest of frame time.
				checkCollisions(ball, timeRemaining - tHit);
				return;
			}
		}
		
		Point2D[] paddlePoints = GameManager.paddle.getPoints();
		for (int i = 0; i < paddlePoints.length - 1; i++) {
			tHit = checkBallCollisionWithLine(paddlePoints[i], paddlePoints[i+1], ball, timeRemaining);
			
			if (tHit > 0.0f && i == 1) {
				// We've collided with the top of the paddle
				float ratio = GameManager.paddle.getRatioOfPoint(pHit);
				if (ratio < 0.35 || ratio > 0.65) {
					ball.setDirection(new Vector2D(ratio*2-1, 1));
				}
				SoundManager.BOING.play();
				
				// Recursively check if collision in rest of frame time.
				checkCollisions(ball, timeRemaining - tHit);
				return;
			}
		}
		
		for (Block block : GameManager.blocks) {
			if (!block.getExploded()) {
				Point2D[] blockPoints = block.getPoints();
				for (int i = 0; i < blockPoints.length; i++) {
					int j = (i < blockPoints.length - 1) ? i+1 : 0;
					tHit = checkBallCollisionWithLine(blockPoints[i], blockPoints[j], ball, timeRemaining);
					
					if (tHit > 0.0f) {
						ball.impact();
						block.explode();
						GameManager.blockCount--;
						ScreenShaker.shake(Settings.SHAKE_POWER, Settings.SHAKE_TIMER);
						GameManager.scoreboard.addToScore(500);
						SoundManager.POP.play();
						
						// Recursively check if collision in rest of frame time.
						checkCollisions(ball, timeRemaining - tHit);
						return;
					}
				}
			}
		}
	}
	
	private static float checkBallCollisionWithLine(Point2D p1, Point2D p2, Ball ball, float deltaTime) {
		
		Point2D[] points = ball.getPointsOnBall();
		
		Vector2D v = new Vector2D(p2.x-p1.x, p2.y-p1.y);
		Vector2D n = new Vector2D(v.y, -v.x);
				
		Vector2D c = ball.getVelocity();
		
		float tHit = Float.MAX_VALUE;
		
		boolean collision = false;
		
		for (int i = 0; i < points.length; i++) {
			Point2D A = points[i];
			
			float newtHit = Utils.tHit(A, p1, n, c);
			if (newtHit > 0 && newtHit < deltaTime) {
				Point2D newpHit = new Point2D(A.x + newtHit * c.x, A.y + newtHit * c.y);
				if (newpHit.isBetween(p1, p2)) {
					if (newtHit < tHit) {
						tHit = newtHit;
						pHit = newpHit;
						collision = true;
					}
				}
			}
		}
		
		if (collision) {
			//GameManager.pHits.clear();
			GameManager.pHits.add(pHit);
			Vector2D a = c;
			float x = a.x - (2*(a.dot(n) / n.dot(n)) * n.x);
			float y = a.y - (2*(a.dot(n) / n.dot(n)) * n.y);
			Vector2D newDirection = new Vector2D(x,y);
			
			ball.move(tHit*0.9f);
			ball.setDirection(newDirection.normalize());
			ball.subtractFromTimeLeftToMove(tHit);
			/*
			System.out.println("------------------------------------------");
			System.out.println("Thit:      " + tHit);
			System.out.println("DeltaTime: " + deltaTime);
			System.out.println("phit:      " + pHit);
			System.out.println("new direction: " + newDirection.normalize());
			*/
			return tHit;
		}
		
		return -1.0f;
	}
}
