package view;

import javax.swing.*;

import controller.GamePanelController;
import model.ball.Ball;
import model.brick.Brick;
import model.player.Player;
import model.wall.Wall;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.io.FileNotFoundException;

/**
 * This class represents the game board.
 * 
 * @author Shih Alf Slee
 * @version 2.0
 * @since 0.1
 * 
 */
public class GamePanel extends JComponent /*implements KeyListener,MouseListener,MouseMotionListener*/ {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private Timer gameTimer;

    private Wall wall;
    private GamePanelController gamePanelController;

    private String message;

    private boolean showPauseMenu;

    private Font menuFont;
    private Font inGameFont;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private int strLen;

    /**
     * This is the constructor method of class GameBoard, it initializes the game board.
     * 
     * @param owner  the frame of the game
     */
    public GamePanel(JFrame owner){
        super();
        

        strLen = 0;
        showPauseMenu = false;



        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);
        inGameFont = new Font("Monospaced",Font.BOLD,15);


        this.initialize();
        
        
        message = "";
        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(300,430));

        gamePanelController = new GamePanelController(owner, this, wall);
        //initialize the first level
        wall.nextLevel();

        gameTimer = new Timer(10,e ->{
        	
            wall.move();
            wall.findImpacts();

            //message = String.format("Bricks: %d \nBalls: %d  \nScore: %d \nHigh Score: %s",wall.getBrickCount(),wall.getBallCount(),wall.getScore(),wall.getScoreList().getHighScore());
            message = String.format("Bricks: %d \nBalls: %d  \nScore: %d \nHigh Score: %d, %s",
            		wall.getBrickCount(),
            		wall.getBallCount(),
            		wall.getScoreList().getLiveScore().getScore(),
            		checkHighScoreAvailable(),
            		checkTimeStampAvailable());
            
            if(wall.isBallLost()){
            	
            	
                if(wall.ballEnd()){
                	
                    wall.wallReset();
                    wall.getScoreList().checkBreakHighScore();
                    wall.getScoreList().breakHighScoreNotify();
                    try {
						wall.getScoreList().sortData();
					} catch (FileNotFoundException e1) {

                        e1.printStackTrace();
                    }
                    wall.getScoreList().getLiveScore().resetScore();
                    message = "Game over";
                    
                }
                wall.ballReset();
                gameTimer.stop();
            }
            else if(wall.isDone()){
                if(wall.hasLevel()){
                    message = "Go to Next Level";
                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    wall.nextLevel();
                }
                else{
                    wall.getScoreList().checkBreakHighScore();
                    wall.getScoreList().breakHighScoreNotify();
                    try {
                        wall.getScoreList().sortData();
                    } catch (FileNotFoundException e1) {

                        e1.printStackTrace();
                    }
                    wall.getScoreList().getLiveScore().resetScore();
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                }
            }

            repaint();
        });
        

    }
    
    /**
     * This method checks if there is at least one high score available in the high scores list.
     * 
     * @return  0, if there is no high scores in the high scores list, else, return the highest score in the high scores list
     */
    private int checkHighScoreAvailable() {
    	if (wall.getScoreList().getHighScore().getScore() == 0) {
    		return 0;
    	}
    	
    	else {
    		return wall.getScoreList().getHighScore().getScore();
    	}
    }
    
    /**
     * This method checks if there is at least one high score available in the high scores list.
     * 
     * @return  "N/A", if there is no high scores in the high scores list, else, return the time stamp of the highest score in the high scores list
     */
    private String checkTimeStampAvailable() {
    	
    	String na = "N/A";
    	
    	if (wall.getScoreList().getHighScore().getFormattedTimeStamp().equals("21-01-01 00:00")) {
    		return "N/A";
    	}
    	
    	else {
    		return wall.getScoreList().getHighScore().getFormattedTimeStamp();
    	}
    }

    /**
     * This method initializes the frame of the game board.
     */
    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        
    }

    /**
     * This method splits the string into lines, and draw the string.
     * 
     * @param g2d  the graphics of the text
     * @param text  the string
     * @param x  the x coordination of the text
     * @param y  the y coordination of the text
     */
    private void drawString(Graphics2D g2d, String text, int x, int y) {
        for (String line : text.split("\n"))
            g2d.drawString(line, x, y += g2d.getFontMetrics().getHeight());
    }

    public void paintComponent(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.blue);
        
        g2d.setFont(inGameFont);
        drawString(g2d, message, 260, 150);
        

        drawBall(wall.getBall(),g2d);

        for(Brick b : wall.getBricks())
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(wall.getPlayer(),g2d);

        if(showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }
    
    /**
     * This method clears the interface of the game board.
     * 
     * @param g2d  the graphics of the background
     */
    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    /**
     * this method draws the brick.
     * 
     * @param brick  the brick
     * @param g2d  the graphics of the brick
     */
    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    /**
     * This method draws the ball.
     * 
     * @param ball  the ball
     * @param g2d  the graphics of the ball
     */
    private void drawBall(Ball ball,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * This method draws the player.
     * 
     * @param p  the player
     * @param g2d  the graphics of the player
     */
    private void drawPlayer(Player p,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * This method opens up the pause menu of the game board.
     * 
     * @param g2d  the graphics of the game board
     */
    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * This method obscures the game board.
     * 
     * @param g2d  the graphics of the game board
     */
    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * This method draws the pause menu of the game board.
     * 
     * @param g2d  the graphics of the pause menu
     */
    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        
        
        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }
    
    /**
     * This method adds key listeners to class GameBoard.
     * 
     * @param keyListener  the class that implements the key listener
     */
    public void addKeyL(KeyListener keyListener) {
   
    	this.addKeyListener(keyListener);
    	 
    }

    /**
     * This method adds mouse listener to class GameBoard.
     * 
     * @param mouseListener  the class that implements the mouse listener
     */
    public void addMouseL(MouseListener mouseListener) {
    	
    	this.addMouseListener(mouseListener);
    	
    }
    
    /**
     * This method adds mouse motion listener to class GameBoard.
     * 
     * @param mouseMotionListener  the class that implements the mouse motion listener
     */
    public void addMouseMotionL (MouseMotionListener mouseMotionListener) {
    	
    	this.addMouseMotionListener(mouseMotionListener);
    }

    /**
     * This method stops the running of the game, and displays 'Focus Lost'.
     */
    public void onLostFocus(){
        gameTimer.stop();
        message = "Focus Lost";
        repaint();
    }
    
    /**
     * This method checks if the pause menu is showed.
     * 
     * @return  true if the pause menu is showed, false otherwise
     */
    public boolean getShowPauseMenu() {
    	return this.showPauseMenu;
    }
    
    /**
     * This method shows the pause menu or hides the pause menu.
     */
    public void setShowPauseMenu() {
    	this.showPauseMenu = !this.showPauseMenu;
    }
    
    /**
     * This method hides the pause menu of the game board.
     */
    public void quitPauseMenu() {
    	this.showPauseMenu = false;
    }
    
    /**
     * This method performs repaint to class GameBoard.
     */
    public void doRepaint() {
    	this.repaint();
    }
    
    /**
     * This method stops the running of the game.
     */
    public void stopGameTimer() {
    	this.gameTimer.stop();
    }
    
    /**
     * This method starts the running of the game.
     */
    public void startGameTimer() {
    	this.gameTimer.start();
    }
    
    /**
     * This method checks the running of the game.
     * 
     * @return  true if the game is running, false otherwise
     */
    public Boolean isGameRunning() {
    	return this.gameTimer.isRunning();
    }
    
    /**
     * This method returns the 'continue' button.
     * 
     * @return  the 'continue' button
     */
    public Rectangle getContinueButtonRect() {
    	return this.continueButtonRect;
    }
    
    /**
     * This method returns the 'restart' button.
     * 
     * @return  the 'restart' button
     */
    public Rectangle getRestartButtonRect() {
    	return this.restartButtonRect;
    }
    
    /**
     * This method returns the 'exit' button.
     * 
     * @return  the 'exit' button
     */
    public Rectangle getExitButtonRect() {
    	return this.exitButtonRect;
    }
    
    /**
     * This method sets the restarting message.
     */
    public void setRestartMessage() {
    	this.message = "Restarting Game...";
    }

}
