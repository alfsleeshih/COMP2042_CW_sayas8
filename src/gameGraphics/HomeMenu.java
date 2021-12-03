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
package gameGraphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class HomeMenu extends JComponent implements /*MouseListener, MouseMotionListener,*/ ActionListener {

    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "BRICK DESTROY";
    private static final String CREDITS = "VERSION 1.1";
    private static final String START_TEXT = "START";
    private static final String EXIT_TEXT = "EXIT";
    private static final String INSTRUCTIONS_TEXT = "INSTRUCTIONS";
    private static final String HIGHSCORES_TEXT = "HIGHSCORES";

    //private static final Color BG_COLOR = Color.GREEN.darker();
    private static final Color BG_COLOR = Color.pink.darker();
    private static final Color BORDER_COLOR = new Color(200,8,21); //Venetian Red
    private static final Color DASH_BORDER_COLOR = new  Color(255, 216, 0);//school bus yellow
    //private static final Color TEXT_COLOR = new Color(16, 52, 166);//egyptian blue
    private static final Color TEXT_COLOR = Color.white;
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    private Rectangle menuFace;
    //private Rectangle startButton;
    //private Rectangle menuButton;
    //private Rectangle instructionButton;

    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private GameFrame owner;

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


    public HomeMenu(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        //this.addMouseListener(this);
        //this.addMouseMotionListener(this);

        this.owner = owner;
        
        //JButton btn = new JButton();
        //this.add(btn);


        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        //Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        //startButton = new Rectangle(btnDim);
        //menuButton = new Rectangle(btnDim);
        //instructionButton = new Rectangle(btnDim);

        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        //buttonFont = new Font("Monospaced",Font.PLAIN,startButton.height-2);

        //image = new ImageIcon("background.jpg").getImage();
        
        /*
        JLabel background = new JLabel(new ImageIcon("bg.jpg"));
		background.setBounds(0,0,450,250);
		this.add(background);
		*/
        
        this.containerHeight = area.height;
        this.containerWidth = area.width;
        
        this.initButton();
		
		
    }
    
    public void initButton() {
    	//int cW = 450;
		//int cH = 250;
		
		int buttonWidth = 150;
		int buttonHeight = 30;
		
        startButton = new JButton(START_TEXT);
        startButton.setBounds((containerWidth-buttonWidth)/2,90,buttonWidth,buttonHeight);
        startButton.addActionListener(this);
        this.add(startButton);
        
        exitButton = new JButton(EXIT_TEXT);
        exitButton.setBounds((containerWidth-buttonWidth)/2,130,buttonWidth,buttonHeight);
        exitButton.addActionListener(this);
        this.add(exitButton);
        
        instructionButton = new JButton(INSTRUCTIONS_TEXT);
        instructionButton.setBounds((containerWidth-buttonWidth)/2,170,buttonWidth,buttonHeight);
        instructionButton.addActionListener(this);
        this.add(instructionButton);
        
        highScoresButton = new JButton(HIGHSCORES_TEXT);
        highScoresButton.setBounds((containerWidth-buttonWidth)/2,210,buttonWidth,buttonHeight);
        highScoresButton.addActionListener(this);
        this.add(highScoresButton);
        
        
        }

    
    public void paintComponent(Graphics g){
    	
    	//super.paintComponent(g);
    	
    	//g.drawImage(image,0,0,null);
    	
    	//Graphics2D g2d = (Graphics2D)g;
    	
    	//g2d.drawImage(image,0,0,null);
    	
        drawMenu((Graphics2D)g);
    }

    
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

        //methods calls
        drawText(g2d);
        //drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
        
        
    }

    private void drawContainer(Graphics2D g2d){
    	
    	//Image picture = Toolkit.getDefaultToolkit().getImage("background.jpg"); 
    	Image picture = new ImageIcon(this.getClass().getResource("/bg.jpg")).getImage();
    	g2d.drawImage(picture,0,0,this);
    	
    	/*
        Color prev = g2d.getColor();
        
        
        g2d.setColor(BG_COLOR);
        g2d.fill(menuFace);

        Stroke tmp = g2d.getStroke();
        
        
        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(borderStoke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(menuFace);
        
        
        g2d.setStroke(tmp);

        g2d.setColor(prev);
        */
        
        
    }

    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);

        int sX,sY;

        //sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        //sY = (int)(menuFace.getHeight() / 4);

        //g2d.setFont(greetingsFont);
        //g2d.drawString(GREETINGS,sX,sY);

        //sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        //sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        //g2d.setFont(gameTitleFont);
        //g2d.drawString(GAME_TITLE,sX,sY);

        //sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        //sY += (int) creditsRect.getHeight() * 1.1;

        //g2d.setFont(creditsFont);
        //g2d.drawString(CREDITS,sX,sY);
        
        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 4);
        
        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);
        
        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;
        
        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);
        

    }
    
    
    /*
     * 
        */
    


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==startButton) {
			//System.out.println("Hello");
			owner.enableGamexitButtonoard();
		}
		
		else if(e.getSource()==exitButton) {
			System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
		}
		
		else if(e.getSource()==instructionButton) {
			new InstructionFrame();
		}
		
		else if(e.getSource()==highScoresButton) {
			new HighScoresFrame();
		}
		
	}
    
    
}
