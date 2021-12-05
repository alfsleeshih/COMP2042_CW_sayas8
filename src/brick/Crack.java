package brick;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

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


    public Crack(Brick brick, int crackDepth, int steps){ // crackDepth = 1, steps = 35, by default

    	this.brick = brick;
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;
        rnd = new Random();

    }



    public GeneralPath draw(){

        return crack;
    }

    public void reset(){
        crack.reset();
    }

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

            if(inMiddle(i,CRACK_SECTIONS,steps)) // CRACK_SECTIONS = 3, by default
                y += jumps(jump,JUMP_PROBABILITY); //JUMP_PROBABILITY = 0.7, by default

            path.lineTo(x,y);

        }

        path.lineTo(end.x,end.y);
        crack.append(path,true);
    }

    private int randomInBounds(int bound){
        int n = (bound * 2) + 1;
        return rnd.nextInt(n) - bound; // -1 0 1
    }

    private boolean inMiddle(int i,int steps,int divisions){
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return  (i > low) && (i < up);
    }

    private int jumps(int bound,double probability){

        if(rnd.nextDouble() > probability)
            return randomInBounds(bound);
        return  0;

    }

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
