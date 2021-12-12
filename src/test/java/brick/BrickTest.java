package brick;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

import model.ball.RubberBall;
import model.brick.CementBrick;

class BrickTest {

	@Test
	void testSetImpact() {
		CementBrick cb = new CementBrick(new Point(20,20), new Dimension(100,20));
		
		assertFalse(cb.isBroken());
		
		cb.repair();
		
		cb.setImpact(new Point2D.Double(20,20), 0);
		
		assertFalse(cb.isBroken());
		
	}
	
	@Test
	void testFindImpact() {
		Point2D p1 = new Point2D.Double(25,15);
		RubberBall rb1 = new RubberBall(p1);
		
		Point2D p2 = new Point2D.Double(30,44.9);
		RubberBall rb2 = new RubberBall(p2);
		
		Point2D p3 = new Point2D.Double(15,30);
		RubberBall rb3 = new RubberBall(p3);
		
		Point2D p4 = new Point2D.Double(124.9,30);
		RubberBall rb4 = new RubberBall(p4);
		
		CementBrick cb = new CementBrick(new Point(20,20), new Dimension(100,20));
		
		//UP_IMPACT = 100
		//DOWN_IMPACT = 200
		//LEFT_IMPACT = 300
		//RIGHT_IMPACT = 400
		
		assertEquals(cb.findImpact(rb1), 100);
		assertEquals(cb.findImpact(rb2), 200);
		assertEquals(cb.findImpact(rb3), 300);
		assertEquals(cb.findImpact(rb4), 400);
		
	}

}
