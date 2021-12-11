package model.brick;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * This class represents the drawing of crack on the brick.
 * 
 * @author Shih Alf Slee
 * @category Software Maintenance
 * @version 2.0
 * @since 0.1
 *
 */
public class Crack {
	
	private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;



    private GeneralPath crack;

    private Brick brick;
    private int crackDepth;
    private int steps;
    private static Random rnd;

    /**
     * This is a constructor method of class Crack.
     * 
     * @param brick  the brick object
     * @param crackDepth  the depth of the crack
     * @param steps  the steps
     */
    public Crack(Brick brick, int crackDepth, int steps){ // crackDepth = 1, steps = 35, by default

    	this.brick = brick;
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;
        rnd = new Random();

    }


    /**
     * This method draw the crack on the brick.
     * 
     * @return  the path of the crack
     */
    public GeneralPath draw(){

        return crack;
    }

    /**
     * This method resets the path of the crack.
     */
    public void reset(){
        crack.reset();
    }

    /**
     * This method sets the drawing of the crack on the brick.
     * 
     * @param point  the impacted point of the brick
     * @param direction  the side of the brick that is impacted
     */
    protected void makeCrack(Point2D point, int direction){ 
        Rectangle bounds = this.brick.getBrickFace().getBounds();
        
        Point impact = new Point((int)point.getX(),(int)point.getY()); //impact point of the brick
        Point start = new Point();
        Point end = new Point();


        switch(direction){
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start,end,VERTICAL); //create random point within the touched edge of the brick
                makeCrack(impact,tmp);

                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);

                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);

                break;

        }
    }

    /**
     * This method draws the crack on the brick.
     * 
     * @param start  the staring coordination of the crack drawing
     * @param end  the ending coordination of the crack drawing
     */
    protected void makeCrack(Point start, Point end){ 
    	// start is the touched point of the brick
    	// end is a new random direction for the ball to go

        GeneralPath path = new GeneralPath();


        path.moveTo(start.x,start.y); //initialize the starting point of the ball

        double w = (end.x - start.x) / (double)steps; 
        double h = (end.y - start.y) / (double)steps;

        int bound = crackDepth; // 1
        int jump  = bound * 5; // 5

        double x,y;

        for(int i = 1; i < steps;i++){

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);
            
            //System.out.println(inMiddle(i,3,35));

            //System.out.println(inMiddle(i,CRACK_SECTIONS,steps));
            if(inMiddle(i,CRACK_SECTIONS,steps)) // CRACK_SECTIONS = 3, by default
                y += jumps(jump,JUMP_PROBABILITY); //JUMP_PROBABILITY = 0.7, by default

            path.lineTo(x,y);

        }

        path.lineTo(end.x,end.y);
        crack.append(path,true);
    }

    /**
     * This method creates a random point between the dimension of the brick.
     * 
     * @param bound  the dimension of the brick
     * @return  random number between number between the dimension of the brick
     */
    private int randomInBounds(int bound){
        int n = (bound * 2) + 1;
        return rnd.nextInt(n) - bound; // -1 0 1
    }

    /**
     * This method sets a limitation to create distinct crack drawing
     * 
     * @param i  the number from 1 to the steps
     * @param steps  the steps
     * @param divisions  the divisions
     * @return  true if i is in between of the boundary limitation
     */
    private boolean inMiddle(int i,int steps,int divisions){
    	
    	double stepsClone = steps;
    	double divisionsClone = divisions;
    	
        double low = (stepsClone/divisionsClone); // 0
        double up = low * (divisionsClone - 1); // 2
        
        return  (i > (int)low) && (i < (int)up);
    }

    /**
     * This method sets a probability to the crack drawing on the brick, and crate distinct crack drawing on the brick.
     * 
     * @param bound  the dimension of the brick
     * @param probability  the probability to succeed
     * @return  random number if succeed, 0 otherwise
     */
    private int jumps(int bound,double probability){

        if(rnd.nextDouble() > probability)
            return randomInBounds(bound);
        return  0;

    }

    /**
     * This method creates a random point.
     * 
     * @param from  the starting coordination
     * @param to  the destination
     * @param direction  the direction to create random point
     * @return  a random point
     */
    private Point makeRandomPoint(Point from,Point to, int direction){

        Point out = new Point();
        int pos;

        switch(direction){
            case HORIZONTAL:
                pos = rnd.nextInt(to.x - from.x) + from.x;
                out.setLocation(pos,to.y);
                break;
            case VERTICAL:
                pos = rnd.nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x,pos);
                break;
        }
        return out;
    }

}
