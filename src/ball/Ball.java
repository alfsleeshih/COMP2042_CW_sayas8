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
     * @param center  the center point of the ball
     * @param radiusWidth  the radius from the width of the ball
     * @param radiusHeight  the radius from the height of the ball
     * @param inner  the inner color of the ball
     * @param border  the border color of the ball
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
     * @param center  the center point of the ball
     * @param radiusWidth  the radius from the width of the ball
     * @param radiusHeight  the radius from the height of the ball
     * @return  the border shape of the ball
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
     * @param p  the destination center point of the ball
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() - (w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * This method sets the surrounding points of the ball.
     * 
     * @param width  the width of the ball.
     * @param height  the height of the ball
     */
    private void setPoints(double width,double height){
    	
    	double radiusWidth = width / 2;
    	double radiusHeight = height / 2;
    	
        up.setLocation(center.getX(),center.getY()-(radiusHeight));
        down.setLocation(center.getX(),center.getY()+(radiusHeight));

        left.setLocation(center.getX()-(radiusWidth),center.getY());
        right.setLocation(center.getX()+(radiusWidth),center.getY());
    }

    /**
     * This method sets the movement speed of the ball.
     * 
     * @param x  the horizontal movement speed of the ball
     * @param y  the vertical movement speed of the ball
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * This method sets the horizontal movement speed of the ball.
     * 
     * @param s  the horizontal movement speed of the ball
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * This method sets the vertical movement speed of the ball.
     * 
     * @param s  the vertical movement speed of the ball
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    /**
     * This method reverses the horizontal movement speed of the ball.
     */
    public void reverseX(){
        speedX = -speedX; 
    }

    /**
     * This method reverses the vertical movement speed of the ball.
     */
    public void reverseY(){
        speedY = -speedY; 
    }

    /**
     * This method gets the border color of the ball.
     * @return  the border color of the ball
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * This method gets the inner color of the ball.
     * @return  the inner color of the ball
     */
    public Color getInnerColor(){
        return inner;
    }
    
    /**
     * This method gets the position of the ball by getting the center point of the ball.
     * @return  the center point of the ball
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     * This method gets the border shape of the ball.
     * @return  the border shape of the ball
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * This method gets the horizontal movement speed of the ball.
     * @return  the horizontal movement speed of the ball
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * This method gets the vertical movement speed of the ball.
     * @return  the vertical movement speed of the ball
     */
    public int getSpeedY(){
        return speedY;
    }
    
    /**
     * This method gets the top point of the ball.
     * @return  the top point of the ball
     */
    public Point2D getUp() {
    	return up;
    }
    
    /**
     * This method gets the bottom point of the ball.
     * @return  the bottom point of the ball
     */
    public Point2D getDown() {
    	return down;
    }
    
    /**
     * This method gets the leftmost point of the ball.
     * @return  the leftmost point of the ball
     */
    public Point2D getLeft() {
    	return left;
    }
    
    /**
     * This method gets the rightmost point of the ball.
     * @return  the rightmost point of the ball
     */
    public Point2D getRight() {
    	return right;
    }
    
    /**
     * This method increases the movement speed of the ball by increasing the horizontal and vertical movement speed of the ball.
     */
    public void increaseSpeed() {
    	speedX += 1;
    	speedY -= 1;
    }


}
