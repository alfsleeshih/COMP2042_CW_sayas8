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
package model.player;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import model.ball.Ball;

/**
 * This class represents the paddle in the game.
 * 
 * @author Shih Alf Slee
 * @version 2.0
 * @since 0.1
 *
 */

public class Player {


    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle drawArea;
    private Rectangle playerFace;
    private Point ballPoint; //detect the center of the player
    private int moveAmount;
    private int minX;
    private int maxX;

    /**
     * This is a constructor method of class Player, it initializes the border shape of the player, the leftmost and rightmost coordination it could moved. 
     * 
     * @param ballPoint  the coordination of the ball
     * @param width  the width of the player
     * @param height  the height of the player
     * @param container  the border shape of the container
     */
    public Player(Point ballPoint,int width,int height,Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        drawArea = container;
        minX = container.x + (width / 2);
        maxX = minX + container.width - width;
    }

    /**
     * This method decreases the width of the player.
     */
    public void advancePlayer() {

        int advancedPlayerWidth = ((int)playerFace.getWidth()) - 50;
        int advancedPlayerHeight = (int)playerFace.getHeight();

        playerFace = makeRectangle(advancedPlayerWidth,advancedPlayerHeight);
        minX = drawArea.x + (advancedPlayerWidth / 2);
        maxX = minX + drawArea.width - advancedPlayerWidth;
    }

    /**
     * This method creates a rectangular shape according to the passed in width parameter and height paramater.
     * 
     * @param width  the width of the rectangle
     * @param height  the height of the rectangle
     * @return  the rectangular shape according to the passed in width parameter and height paramater
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     * This method checks if the player has contacted with the ball.
     * 
     * @param b  the ball
     * @return  true if the player has contacted with the ball, else false
     */
    public boolean impact(Ball b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.getDown()) ;
    }

    /**
     * This method moves the player with a restriction of its leftmost coordination and rightmost coordination.
     */
    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < minX || x > maxX)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    /**
     * This method moves the player to its left.
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * This method moves the player to its right.
     */
    public void moveRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * This method stops the movement of the player.
     */
    public void stop(){
        moveAmount = 0;
    }

    /**
     * This method returns the border shape of the player.
     * 
     * @return  the border shape of the player
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }

    /**
     * This method moves the player to the specified coordination.
     * 
     * @param p  the specified coordination for the player to move to
     */
    public void moveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    
    /**
     * This method returns the minimum x coordination of the player.
     * 
     * @return  the minimum x coordination of the player
     */
    public int getMinX() {
    	return this.minX;
    }
    
    /**
     * This method returns the maximum x coordination of the player.
     * 
     * @return  the maximum x coordination of the player
     */
    public int getMaxX() {
    	return this.maxX;
    }

    /**
     * This method returns the width of the player.
     *
     * @return  the width of the player
     */
    public int getPlayerWidth() {
        return (int) this.playerFace.getWidth();
    }
}
