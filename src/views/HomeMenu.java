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
package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * This class represents the home menu of the game.
 * 
 * @author Shih Alf Slee
 * @category Software Maintenance
 * @version 2.0
 * @since 0.1
 *
 */
public class HomeMenu extends JComponent {

    //private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "BRICK DESTROY";
    private static final String CREDITS = "VERSION 2.0";
    private static final String START_TEXT = "START";
    private static final String EXIT_TEXT = "EXIT";
    private static final String INSTRUCTIONS_TEXT = "INSTRUCTIONS";
    private static final String HIGHSCORES_TEXT = "HIGHSCORES";

    //private static final Color BG_COLOR = Color.GREEN.darker();
    //private static final Color BG_COLOR = Color.pink.darker();
    //private static final Color BORDER_COLOR = new Color(200,8,21); //Venetian Red
    //private static final Color DASH_BORDER_COLOR = new  Color(255, 216, 0);//school bus yellow
    //private static final Color TEXT_COLOR = new Color(16, 52, 166);//egyptian blue
    private static final Color TEXT_COLOR = Color.white;
    //private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    //private static final Color CLICKED_TEXT = Color.WHITE;
    //private static final int BORDER_SIZE = 5;
    //private static final float[] DASHES = {12,6};

    private Rectangle menuFace;
    //private Rectangle startButton;
    //private Rectangle menuButton;
    //private Rectangle instructionButton;

    //private BasicStroke borderStoke;
    //private BasicStroke borderStoke_noDashes;

    //private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    //private Font buttonFont;

    //private GameFrame owner;

    //private boolean startClicked;
    //private boolean menuClicked;
    //private boolean instructionClicked;
    
    //private Image image;
    
    JButton startButton;
    JButton exitButton;
    JButton instructionButton;
    JButton highScoresButton;
    
    
    int containerWidth;
    int containerHeight;

    /**
     * This is the constructor method of class HomeMenu.
     * 
     * @param area  the dimension of the frame of the home menu
     */
    public HomeMenu(Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        
        this.containerHeight = area.height;
        this.containerWidth = area.width;
        
        this.initButton();
		
    }
    
    /**
     * This method creates the 'start', 'exit', 'instructions', and 'high scores' button of the home menu.
     */
    public void initButton() {
		
		int buttonWidth = 150;
		int buttonHeight = 30;
		
        startButton = new JButton(START_TEXT);
        startButton.setBounds((containerWidth-buttonWidth)/2,90,buttonWidth,buttonHeight);
        //startButton.addActionListener(this);
        this.add(startButton);
        
        exitButton = new JButton(EXIT_TEXT);
        exitButton.setBounds((containerWidth-buttonWidth)/2,130,buttonWidth,buttonHeight);
        //exitButton.addActionListener(this);
        this.add(exitButton);
        
        instructionButton = new JButton(INSTRUCTIONS_TEXT);
        instructionButton.setBounds((containerWidth-buttonWidth)/2,170,buttonWidth,buttonHeight);
        //instructionButton.addActionListener(this);
        this.add(instructionButton);
        
        highScoresButton = new JButton(HIGHSCORES_TEXT);
        highScoresButton.setBounds((containerWidth-buttonWidth)/2,210,buttonWidth,buttonHeight);
        //highScoresButton.addActionListener(this);
        this.add(highScoresButton);
        
        
        }

    
    public void paintComponent(Graphics g){
    	
        drawMenu((Graphics2D)g);
    }

    /**
     * this method draws the home menu.
     * 
     * @param g2d  the graphics of the home menu
     */
    public void drawMenu(Graphics2D g2d){	

        drawContainer(g2d);

        
        //all the following method calls need a relative
        //painting directly into the HomeMenu rectangle,
        //so the translation is made here so the other methods do not do that.
         
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        drawText(g2d);

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
        
        
    }

    /**
     * This method draws the container of the home menu.
     * 
     * @param g2d  the graphics of the container of the home menu
     */
    private void drawContainer(Graphics2D g2d){
    	
    	//Image picture = Toolkit.getDefaultToolkit().getImage("background.jpg"); 
    	Image picture = new ImageIcon(this.getClass().getResource("/res/bg.jpg")).getImage();
    	g2d.drawImage(picture,0,0,this);
    
        
    }

    /**
     * This method draws the text of the home menu.
     * 
     * @param g2d  the graphics of the text of the home menu
     */
    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);

        int sX,sY;

        
        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 4);
        
        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);
        
        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;
        
        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);
        

    }
    
    /**
     * This method adds action listener to the 'start' button of the home menu.
     * 
     * @param listenForStartButton  the class that implements action listener
     */
	public void addStartButtonListener (ActionListener listenForStartButton) {
		this.startButton.addActionListener(listenForStartButton);
	}
	
	/**
     * This method adds action listener to the 'exit' button of the home menu.
     * 
     * @param listenForStartButton  the class that implements action listener
     */
	public void addExitButtonListener (ActionListener listenForExitButton) {
		this.exitButton.addActionListener(listenForExitButton);
	}
	
	/**
     * This method adds action listener to the 'instructions' button of the home menu.
     * 
     * @param listenForStartButton  the class that implements action listener
     */
	public void addInstructionButtonListener (ActionListener listenForInstructionButton) {
		this.instructionButton.addActionListener(listenForInstructionButton);
	}
	
	/**
     * This method adds action listener to the 'high scores' button of the home menu.
     * 
     * @param listenForStartButton  the class that implements action listener
     */
	public void addHighScoresButtonListener (ActionListener listenForHighScoresButton) {
		this.highScoresButton.addActionListener(listenForHighScoresButton);
	}
    
    
}
