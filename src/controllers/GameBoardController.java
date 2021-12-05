package controllers;

import views.DebugConsole;
import views.GameBoard;
import wall.Wall;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;


public class GameBoardController {
	
	// view
	GameBoard gameBoard;
	DebugConsole debugConsole;
	
	// model
	Wall wall;
	
	public GameBoardController(JFrame gameFrame, GameBoard gameBoard, Wall wall) {
		
		this.gameBoard = gameBoard;
		this.debugConsole = new DebugConsole(gameFrame,wall,gameBoard);
		this.wall = wall;
		
		this.gameBoard.addKeyL(new GameBoardListener());
		this.gameBoard.addMouseL(new GameBoardListener());
		this.gameBoard.addMouseMotionL(new GameBoardListener());
		
	}
	
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
				gameBoard.setShowPauseMenu();
				gameBoard.doRepaint();
				gameBoard.stopGameTimer();
				break;
				
			case KeyEvent.VK_SPACE:
				if(!gameBoard.getShowPauseMenu()) {
					if(gameBoard.isGameRunning()) {
						gameBoard.stopGameTimer();
					}
					
					else {
						gameBoard.startGameTimer();
					}
				}
				break;
				
			case KeyEvent.VK_F1:
				if (e.isAltDown() && e.isShiftDown()) {
					debugConsole.setVisible(true);
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
			
			if(!gameBoard.getShowPauseMenu()) {
				return;
			}
			
			else if(gameBoard.getContinueButtonRect().contains(p)) {
				gameBoard.quitPauseMenu();
				gameBoard.doRepaint();
			}
			
			else if(gameBoard.getRestartButtonRect().contains(p)) {
				gameBoard.setRestartMessage();
				wall.ballReset();
				wall.wallReset();
				gameBoard.quitPauseMenu();
				gameBoard.doRepaint();
			}
			
			else if(gameBoard.getExitButtonRect().contains(p)) {
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
			
			if(gameBoard.getExitButtonRect() != null && gameBoard.getShowPauseMenu() == true) {
				if (gameBoard.getExitButtonRect().contains(p) || gameBoard.getRestartButtonRect().contains(p) || gameBoard.getContinueButtonRect().contains(p)) {
					gameBoard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
				else {
					gameBoard.setCursor(Cursor.getDefaultCursor());
				}
			}
			
			/*
			Point p = mouseEvent.getPoint();
	        if(exitButtonRect != null && showPauseMenu) {
	            if (exitButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p))
	                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	            else
	                this.setCursor(Cursor.getDefaultCursor());
	        }
	        else{
	            this.setCursor(Cursor.getDefaultCursor());
	        }
	        */
			
		}
		
	}
	

}
