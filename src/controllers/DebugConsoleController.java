package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.wall.Wall;
import views.DebugPanel;

/**
 * This class represents the controller between the debug consolea and the wall.
 * 
 * @author Shih Alf Slee
 * @category Software Maintenance
 * @version 2.0
 * @since 0.1
 *
 */
public class DebugConsoleController {
	
	// view
	DebugPanel debugPanel;
	
	// model
	Wall wall;
	
	/**
	 * This is a constructor class of class DebugConsoleController, it adds the action listeners to the button of the debug console.
	 *  
	 * @param debugPanel  this is the view
	 * @param wall this is the model
	 */
	public DebugConsoleController(DebugPanel debugPanel, Wall wall) {
		
		this.debugPanel = debugPanel;
		this.wall = wall;
		
		this.debugPanel.addSkipLevelListener(new SkipLevelListener());
		
		this.debugPanel.addResetBallsListener(new ResetBallsListener());
		
		this.debugPanel.addBallXSpeedListener(new BallXSpeedListener());
		
		this.debugPanel.addBallYSpeedListener(new BallYSpeedListener());
		
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
			wall.setBallXSpeed(debugPanel.getBallXSpeedSlider().getValue());
			
		}
		
	}
	
	/**
	 * This class implements the change listener for the slider of the vertical movement speed of the ball.
	 * 
	 */
	class BallYSpeedListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			wall.setBallYSpeed(debugPanel.getBallYSpeedSlider().getValue());
			
		}
		
	}
	
	

}
