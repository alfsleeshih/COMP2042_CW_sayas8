package controllers;

import debugGraphics.DebugConsole;
import gameGraphics.GameBoard;
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
		
	}
	
	class GameBoardListener implements KeyListener, MouseListener{

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
			// TODO Auto-generated method stub
			
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
		
	}
	

}
