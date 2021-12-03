package ball;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * This class is an abstract parent class for the types of balls in the game.
 * 
 * @author Shih Alf Slee
 * @category Software Maintenance
 * @version 2.0
 * @since 0.1
 *
 */

abstract public class Ball {

    private Shape ballFace;

    private Point2D center;

    //edges of ball
    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    /**
     * This is a constructor method of class Ball, it initializes the speed, color, and surrounding points of the ball.
     * 
     * @param center This is the center point of the ball
     * @param radiusWidth This is the radius from the width of the ball
     * @param radiusHeight This is the radius from the height of the ball
     * @param inner This is the inner color of the ball
     * @param border This is the border color of the ball
     */
    public Ball(Point2D center, int radiusWidth, int radiusHeight, Color inner, Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusHeight/2));
        down.setLocation(center.getX(),center.getY()+(radiusHeight/2));

        left.setLocation(center.getX()-(radiusWidth/2),center.getY());
        right.setLocation(center.getX()+(radiusWidth/2),center.getY());


        ballFace = makeBall(center,radiusHeight,radiusWidth);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * This method creates a border shape of the ball.
     * 
     * @param center This is the center point of the ball
     * @param radiusWidth This is the radius from the width of the ball
     * @param radiusHeight This is the radius from the height of the ball
     * @return This returns the border shape of the ball
     */
    protected abstract Shape makeBall(Point2D center,int radiusWidth,int radiusHeight);

    /**
     * 	This method moves the ball by changing the center point of the ball according to the speed.
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() - (w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }
    
    /**
     * This method moves the ball by changing the center point of the ball.
     * 
     * @param p This is the destination center point of the ball
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() - (w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    
    private void setPoints(double width,double height){
    	
    	double radiusWidth = width / 2;
    	double radiusHeight = height / 2;
    	
        up.setLocation(center.getX(),center.getY()-(radiusHeight));
        down.setLocation(center.getX(),center.getY()+(radiusHeight));

        left.setLocation(center.getX()-(radiusWidth),center.getY());
        right.setLocation(center.getX()+(radiusWidth),center.getY());
    }

    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    public void setXSpeed(int s){
        speedX = s;
    }

    public void setYSpeed(int s){
        speedY = s;
    }

    public void reverseX(){
        speedX = -speedX; //original was speed *= -speed
    }

    public void reverseY(){
        speedY = -speedY; 
    }

    public Color getBorderColor(){
        return border;
    }

    public Color getInnerColor(){
        return inner;
    }

    public Point2D getPosition(){
        return center;
    }

    public Shape getBallFace(){
        return ballFace;
    }

    public int getSpeedX(){
        return speedX;
    }

    public int getSpeedY(){
        return speedY;
    }
    
    public Point2D getUp() {
    	return up;
    }
    
    public Point2D getDown() {
    	return down;
    }
    
    public Point2D getLeft() {
    	return left;
    }
    
    public Point2D getRight() {
    	return right;
    }
    
    public void increaseSpeed() {
    	speedX += 1;
    	speedY -= 1;
    }


}
