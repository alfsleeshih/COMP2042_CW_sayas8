package player;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

import model.ball.RubberBall;
import model.player.Player;

class PlayerTest {

	@Test
	void testPlayer() {
		
		Rectangle drawArea = new Rectangle(0,0,600,450);
		Point ballPosition = new Point(300,430);
		int playerWidth = 150;
		int playerHeight = 10;
		 
		Player player = new Player(ballPosition, playerWidth, playerHeight, drawArea);
		
		assertEquals(player.getMinX(),75);
		assertEquals(player.getMaxX(),525);
	}
	
	@Test
	void testImpact() {
		
		Rectangle drawArea = new Rectangle(0,0,600,450);
		Point ballPosition = new Point(300,430);
		int playerWidth = 150;
		int playerHeight = 10;
		 
		Player player = new Player(ballPosition, playerWidth, playerHeight, drawArea);
		
		RubberBall rb1 = new RubberBall(ballPosition);
		
		Point2D p2 = new Point2D.Double(20,20);
		RubberBall rb2 = new RubberBall(p2);
		
		assertTrue(player.impact(rb1));
		assertFalse(player.impact(rb2));
	}
	
	@Test
	void testMoveLeft() {
		Rectangle drawArea = new Rectangle(0,0,600,450);
		int ballXPosition = 300;
		int ballYPosition = 430;
		Point ballPosition = new Point(ballXPosition,ballYPosition);
		int playerWidth = 150;
		int playerHeight = 10;
		 
		Player player = new Player(ballPosition, playerWidth, playerHeight, drawArea);
		
		player.moveLeft();
		player.move();
		
		assertTrue(player.getPlayerFace().contains(ballXPosition-5-(playerWidth/2),ballYPosition));
	}
	
	@Test
	void testMoveRight() {
		Rectangle drawArea = new Rectangle(0,0,600,450);
		int ballXPosition = 300;
		int ballYPosition = 430;
		Point ballPosition = new Point(ballXPosition,ballYPosition);
		int playerWidth = 150;
		int playerHeight = 10;
		 
		Player player = new Player(ballPosition, playerWidth, playerHeight, drawArea);
		
		player.moveRight();
		player.move();
		
		assertTrue(player.getPlayerFace().contains(ballXPosition+5-(playerWidth/2),ballYPosition));
	}

	@Test
	void testAdvancePlayer() {
		Rectangle drawArea = new Rectangle(0,0,600,450);
		Point ballPosition = new Point(300,430);
		int playerWidth = 150;
		int playerHeight = 10;

		Player player = new Player(ballPosition, playerWidth, playerHeight, drawArea);

		player.advancePlayer();

		assertEquals(player.getPlayerWidth(),100);

	}

}
