package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.wall.Wall;
import view.DebugConsolePanel;

/**
 * This class represents the controller between the debug consolea and the wall.
 * 
 * @author Shih Alf Slee
 * @category Software Maintenance
 * @version 2.0
 * @since 0.1
 *
 */
public class DebugConsolePanelController {
	
	// view
	DebugConsolePanel debugConsolePanel;
	
	// model
	Wall wall;
	
	/**
	 * This is a constructor class of class DebugConsoleController, it adds the action listeners to the button of the debug console.
	 *  
	 * @param debugPanel  this is the view
	 * @param wall this is the model
	 */
	public DebugConsolePanelController(DebugConsolePanel debugConsolePanel, Wall wall) {
		
		this.debugConsolePanel = debugConsolePanel;
		this.wall = wall;
		
		this.debugConsolePanel.addSkipLevelListener(new SkipLevelListener());
		
		this.debugConsolePanel.addResetBallsListener(new ResetBallsListener());
		
		this.debugConsolePanel.addBallXSpeedListener(new BallXSpeedListener());
		
		this.debugConsolePanel.addBallYSpeedListener(new BallYSpeedListener());
		
	}
	
	/**
	 * This class implements the action listener for the 'skip level' button of the debug console.
	 * 
	 */
	class SkipLevelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(wall.hasLevel()) {
				wall.nextLevel();
				
			}
			
			else {
				JOptionPane.showMessageDialog(null, "Hang in there, this is the last level", null, JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
	}
	
	/**
	 * This class implements the action listener for the 'reset balls' button of the debug console.
	 * 
	 */
	class ResetBallsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			wall.resetBallCount();
			
		}
		
	}
	
	/**
	 * This class implements the change listener for the slider of the horizontal movement speed of the ball.
	 * 
	 */
	class BallXSpeedListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			wall.setBallXSpeed(debugConsolePanel.getBallXSpeedSlider().getValue());
			
		}
		
	}
	
	/**
	 * This class implements the change listener for the slider of the vertical movement speed of the ball.
	 * 
	 */
	class BallYSpeedListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			wall.setBallYSpeed(debugConsolePanel.getBallYSpeedSlider().getValue());
			
		}
		
	}
	
	

}
