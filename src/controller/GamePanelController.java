package controller;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

import model.wall.Wall;
import view.DebugConsoleFrame;
import view.GamePanel;

/**
 * This class represents the controller between the game board and the wall.
 * 
 * @author Shih Alf Slee
 * @category Software Maintenance
 * @version 2.0
 * @since 0.1
 *
 */
public class GamePanelController {
	
	// view
	GamePanel gamePanel;
	DebugConsoleFrame debugConsoleFrame;
	
	// model
	Wall wall;
	
	/**
	 * This is a constructor method of class GameBoardController, it adds the key listeners, mouse listeners and the mouse motion listeners to the game board.
	 * 
	 * @param gameFrame  this is the window frame of the view
	 * @param gameBoard  this is the view
	 * @param wall  this is the model
	 */
	public GamePanelController(JFrame gameFrame, GamePanel gamePanel, Wall wall) {
		
		this.gamePanel = gamePanel;
		this.debugConsoleFrame = new DebugConsoleFrame(gameFrame,wall,gamePanel);
		this.wall = wall;
		
		this.gamePanel.addKeyL(new GameBoardListener());
		this.gamePanel.addMouseL(new GameBoardListener());
		this.gamePanel.addMouseMotionL(new GameBoardListener());
		
	}
	
	/**
	 * This class implements the key listener, mouse listener, and the mouse motion listener for the game board.
	 * 
	 */
	class GameBoardListener implements KeyListener, MouseListener, MouseMotionListener{

		@Override
		public void keyTyped(KeyEvent e) {
			
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) {
			
			case KeyEvent.VK_A:
				wall.getPlayer().moveLeft();
				break;
				
			case KeyEvent.VK_D:
				wall.getPlayer().movRight();
				break;
				
			case KeyEvent.VK_ESCAPE:
				gamePanel.setShowPauseMenu();
				gamePanel.doRepaint();
				gamePanel.stopGameTimer();
				break;
				
			case KeyEvent.VK_SPACE:
				if(!gamePanel.getShowPauseMenu()) {
					if(gamePanel.isGameRunning()) {
						gamePanel.stopGameTimer();
					}
					
					else {
						gamePanel.startGameTimer();
					}
				}
				break;
				
			case KeyEvent.VK_F1:
				if (e.isAltDown() && e.isShiftDown()) {
					debugConsoleFrame.setVisible(true);
				}
				
			default:
				wall.getPlayer().stop();
					
			
			}
			
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			wall.getPlayer().stop();
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
			Point p = e.getPoint();
			
			if(!gamePanel.getShowPauseMenu()) {
				return;
			}
			
			else if(gamePanel.getContinueButtonRect().contains(p)) {
				gamePanel.quitPauseMenu();
				gamePanel.doRepaint();
			}
			
			else if(gamePanel.getRestartButtonRect().contains(p)) {
				gamePanel.setRestartMessage();
				wall.ballReset();
				wall.wallReset();
				gamePanel.quitPauseMenu();
				gamePanel.doRepaint();
			}
			
			else if(gamePanel.getExitButtonRect().contains(p)) {
				System.out.println("Goodbye " + System.getProperty("user.name"));
				System.exit(0);
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			
			Point p = e.getPoint();
			
			if(gamePanel.getExitButtonRect() != null && gamePanel.getShowPauseMenu() == true) {
				if (gamePanel.getExitButtonRect().contains(p) || gamePanel.getRestartButtonRect().contains(p) || gamePanel.getContinueButtonRect().contains(p)) {
					gamePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
				else {
					gamePanel.setCursor(Cursor.getDefaultCursor());
				}
			}
			
			
		}
		
	}
	

}
