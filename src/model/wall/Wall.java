/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistrinstructionButtonute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distrinstructionButtonuted in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package model.wall;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.JOptionPane;

import model.ball.Ball;
import model.ball.RubberBall;
import model.brick.Brick;
import model.brick.CementBrick;
import model.brick.ClayBrick;
import model.brick.Crack;
import model.brick.SteelBrick;
import model.player.Player;
import score.Score;
import score.ScoreList;

/**
 * This class represents a combination of class Brick, class Ball, and class Player. It is the main model class.
 * 
 * @author Shih Alf Slee
 * @category Software Maintenance
 * @version 2.0
 * @since 0.1
 *
 */
public class Wall {

    private static final int LEVELS_COUNT = 4;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;

    private Rectangle area;

    private Brick[] bricks;
    private Ball ball;
    private Player player;
    ScoreList scoreList;

    private Brick[][] levels;
    private int level;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    /**
     * This is a constructor method of class Wall, it initializes the score and the ball count, the starting position of the ball and the player.
     * 
     * @param drawArea  the dimension of the interface of the game
     * @param brickCount  the number of bricks
     * @param lineCount  the number of rows for the wall
     * @param brickDimensionRatio  the ratio of the dimension of a single brick
     * @param ballPos  the coordination of the ball
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);

        initBall(ballPos);
        
        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;
        
        player = new Player((Point) ballPos.clone(),150,10, drawArea);
        
        area = drawArea;
        
        scoreList = new ScoreList();
        
    }
    
    /**
     * This method initializes the movement speed of the ball.
     * 
     * @param ballPos  the coordination of the ball
     */
    private void initBall(Point2D ballPos) {
    	
    	int speedX = 3;
    	int speedY = -3;
    	
    	ball = new RubberBall(ballPos);
    	ball.setSpeed(speedX, speedY);
    }

    /**
     * This method creates the wall in the game with a single type of brick.
     * 
     * @param drawArea  the dimension of the interface of the game
     * @param brickCnt  the number of the bricks
     * @param lineCnt  the number of rows of the wall
     * @param brickSizeRatio  the ratio of the dimension of a single brick
     * @param type  the type of the brick
     * @return  the wall
     */
    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        /*
          if brickCount is not divisinstructionButtonle by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt; 

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2; //second line has 11bricks

        Brick[] tmp  = new Brick[brickCnt]; //brickCnt = 31

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine; // 30/10=3(lineCnt)
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,type);
        }
        
        
        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrick(p,brickSize);
        }
        return tmp;

    }

    /**
     * This method creates the wall in the game with two types of brick.
     * 
     * @param drawArea  the dimension of the interface of the game
     * @param brickCnt  the number of the bricks
     * @param lineCnt  the number of the rows of the wall
     * @param brickSizeRatio  the dimension of the size of a single brick
     * @param typeA  the first type of the brick
     * @param typeB  the second type of the brick
     * @return
     */
    private Brick[] makeChessBoardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        
    	
    	/*
          if brickCount is not divisinstructionButtonle by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

      //make the 5th and 6th brick on the second line different
        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine; // 0 1 2
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine; // 0 1 2 3 4 5 6 7 8 9
            double x = posX * brickLen; // total length
            x =(line % 2 == 0) ? x : (x - (brickLen / 2)); // there is 2 half bricks on the second line
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

      //make the last brick on the second line different
        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    /**
     * This method creates a rubber ball.
     * 
     * @param ballPos  the coordination of the ball
     */
    private void makeBall(Point2D ballPos){
        ball = new RubberBall(ballPos);
    }

    /**
     * this method creates the levels of the game.
     * 
     * @param drawArea  the dimension of the interface of the game
     * @param brickCount  the number of the bricks
     * @param lineCount  the number of rows of the wall
     * @param brickDimensionRatio  the ratio of the dimension of a single brick
     * @return  the levels
     * 
     * @see Brick[] wall.Wall.makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type)
     * @see Brick[] wall.Wall.makeChessBoardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type)
     */
    private Brick[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeChessBoardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = makeChessBoardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = makeChessBoardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        return tmp;
    }

    /**
     * This method creates the movement of the player and the ball.
     */
    public void move(){
        player.move();
        ball.move();
    }

    /**
     * This method reverses the movement of the ball if it interacts with other components except wall in the game, increases the score and discount a brick if the ball interacts with the wall, and discount the number of balls of the player if the ball falls against the ground.
     */
    public void findImpacts(){
        if(player.impact(ball)){
            ball.reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
            * because for every brick program checks for horizontal and vertical impacts
            */
            brickCount--;
            scoreList.getLiveScore().increaseScore(5);
        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    /**
     * This method checks the interaction between the ball and the wall, and reverses the movement of the ball according to which side of the brick the ball has interacted, and cracks the if the brick is cement brick.
     * 
     * @return  true if the ball interacts the wall, false otherwise.
     */
    private boolean impactWall(){
        for(Brick b : bricks){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.getDown(), Crack.UP);
                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.getUp(), Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.getRight(), Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.getLeft(), Crack.LEFT);
            }
        }
        return false;
    }

    /**
     * This method checks the interaction of the ball and the horizontal border of the game frame.
     * 
     * @return  true if the ball interacts with the horizontal border of the game frame, false otherwise
     */
    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * This method returns the number of the bricks.
     * 
     * @return  the number of the bricks
     */
    public int getBrickCount(){
        return brickCount;
    }

    /**
     * This method returns the number of the balls.
     * 
     * @return  the number of the balls
     */
    public int getBallCount(){
        return ballCount;
    }

    /**
     * This method checks if the ball is out of boundary of the game frame.
     * 
     * @return  true if the ball is out of the boundary of the game frame, false otherwise
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * this method resets the starting coordination and the starting movement speed of the ball.
     * 
     */
    public void ballReset(){
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        
        
        int speedX = 3;
        int speedY = -3;
        
        ball.setSpeed(speedX,speedY);
        
        //initBall();
        ballLost = false;
    }

    /**
     * This method resets the wall, the number of the bricks, and the number of the balls.
     */
    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    /**
     * This method checks if the number of balls reaches zero, and therefore the player loses the game.
     * 
     * @return  true if the number of balls is zero, false otherwise
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * This method checks if the number of bricks reaches zero, and therefore the player completes a level.
     * 
     * @return  true if the number of bricks is zero
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * This method moves the wall to the next level, and resets the number of bricks.
     */
    public void nextLevel(){
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    /**
     * This method checks if the game is in the last level.
     * 
     * @return  true if the game is not in the last level, false otherwise
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * This method sets the horizontal movement speed of the ball.
     * 
     * @param s  horizontal movement speed of the ball
     */
    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    /**
     * This method sets the vertical movement speed of the ball.
     * 
     * @param s  vertical movement speed of the ball
     */
    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    /**
     * This method resets the number of the balls.
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * This method creates the brick according to the passed in size and type parameter.
     * 
     * @param point  The coordination of the brick
     * @param size  the dimension of the brick
     * @param type  the type of the brick
     * @return  the brick according to the passed in size and type parameter
     */
    private Brick makeBrick(Point point, Dimension size, int type){
        Brick out;
        switch(type){
            case CLAY:
                out = new ClayBrick(point,size);
                break;
            case STEEL:
                out = new SteelBrick(point,size);
                break;
            case CEMENT:
                out = new CementBrick(point, size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }
    
    /**
     * This method returns the ball.
     * 
     * @return  the ball
     */
    public Ball getBall() {
    	return this.ball;
    }
    
    /**
     * This method returns the array of brick.
     * 
     * @return  the array of brick
     */
    public Brick[] getBricks() {
    	return this.bricks;
    	
    }
    
    /**
     * This method returns the player.
     * 
     * @return  the player
     */
    public Player getPlayer() {
    	return this.player;
    }
    
    /**
     * This method returns the score list.
     * 
     * @return  the score list
     */
    public ScoreList getScoreList() {
    	return scoreList;
    }
    
    
}
