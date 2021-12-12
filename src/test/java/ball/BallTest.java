package ball;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

import model.ball.RubberBall;

//test up down lef right point and move

class BallTest {

	@Test
	void testBall() {
		Point2D p = new Point2D.Double(20,20);
		RubberBall rb = new RubberBall(p);
		
		assertEquals(rb.getUp(), new Point2D.Double(20,15));
		assertEquals(rb.getDown(), new Point2D.Double(20,25));
		assertEquals(rb.getLeft(), new Point2D.Double(15,20));
		assertEquals(rb.getRight(), new Point2D.Double(25,20));
	}
	
	@Test 
	void testMove() {
		Point2D p = new Point2D.Double(20,20);
		RubberBall rb = new RubberBall(p);
		
		rb.setSpeed(7, 4);
		rb.move();
		
		assertEquals(rb.getPosition(), new Point2D.Double(27,24));
	}
	
	@Test
	void testMoveTo() {
		Point2D p = new Point2D.Double(20,20);
		RubberBall rb = new RubberBall(p);
		
		rb.moveTo(new Point(100,100));
		
		assertEquals(rb.getPosition(), new Point2D.Double(100,100));
	}

}
