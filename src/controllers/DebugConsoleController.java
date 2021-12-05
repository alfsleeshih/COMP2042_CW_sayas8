package controllers;

import wall.Wall;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import views.DebugPanel;

public class DebugConsoleController {
	
	// view
	DebugPanel debugPanel;
	
	// model
	Wall wall;
	
	public DebugConsoleController(DebugPanel view, Wall model) {
		
		this.debugPanel = view;
		this.wall = model;
		
		this.debugPanel.addSkipLevelListener(new SkipLevelListener());
		
		this.debugPanel.addResetBallsListener(new ResetBallsListener());
		
		this.debugPanel.addBallXSpeedListener(new BallXSpeedListener());
		
		this.debugPanel.addBallYSpeedListener(new BallYSpeedListener());
		
	}
	
	class SkipLevelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			wall.nextLevel();
			
		}
	}
	
	class ResetBallsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			wall.resetBallCount();
			
		}
		
	}
	
	class BallXSpeedListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			wall.setBallXSpeed(debugPanel.getBallXSpeedSlider().getValue());
			
		}
		
	}
	
	class BallYSpeedListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			wall.setBallYSpeed(debugPanel.getBallYSpeedSlider().getValue());
			
		}
		
	}
	
	

}
