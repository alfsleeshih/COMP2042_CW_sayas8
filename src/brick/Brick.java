package brick;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

import ball.Ball;

/**
 * This class is an abstract parent class for the types of bricks in the game.
 * 
 * @author Shih Alf Slee
 * @category Software Maintenance
 * @version 2.0
 * @since 0.1
 *
 */


abstract public class Brick  {

    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    //private static Random rnd;

    private String name;
    private Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;

    /**
     * This is a constructor method of class Brick, it initializes the type, state, strength, appearance and the size of the brick.
     * 
     * @param name  the type of the brick
     * @param pos  the top left corner coordinate of the brick
     * @param size  the dimension of the brick
     * @param border  the border color of the brick
     * @param inner  the inner color of the brick
     * @param strength  the number of the times require to touch the brick with the ball, in order to disappear the brick
     */
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        //rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    /**
     * This method creates the border shape of the brick.
     * 
     * @param pos  the top left corner coordinate of the brick
     * @param size  the dimension of the brick
     * @return  the border shape of the brick
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size); 

    /**
     * This method weaken the strength of the brick, and the brick is set broken once the strength value reaches 0. 
     * 
     * @param point  the impacted point of the brick
     * @param dir  the side（top/bottom/left/right) of the brick impacted
     * @return  the updated state of the brick
     * 
     * @see makeCrack(Point2D point, int direction)
     * @see makeCrack(Point start, Point end)
     * @see impact()
     */
    public  boolean setImpact(Point2D point , int dir){ // the parameters are used in makeCrack1
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /**
     * This method gets the border shape of the brick.
     * 
     * @return  the border shape of the brick
     */
    public abstract Shape getBrick();

    /**
     * This method gets the border color of the brick.
     * 
     * @return  the border color of the brick
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     * This method gets the inner color of the brick.
     * 
     * @return  the border color of the brick
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * This method checks which side of the brick is impacted.
     * 
     * @param b  the ball
     * @return  the side of the brick impacted
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.getRight()))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.getUp()))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.getDown()))
            out = UP_IMPACT;
        return out;
    }

    /**
     * This method checks if the brick is broken.
     * 
     * @return  the state of the brick
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * This method resets the state and the strength of the brick.
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * This method weaken the strength of the brick, and update the state of the brick according to its state.
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }
    
    /**
     * This method returns the border shape of the brick.
     * 
     * @return  border shape of the brick
     */
    public Shape getBrickFace() {
    	return this.brickFace;
    }



}





