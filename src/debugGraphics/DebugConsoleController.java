package debugGraphics;

import wall.Wall;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DebugConsoleController {
	
	DebugPanel view;
	Wall model;
	
	public DebugConsoleController(DebugPanel view, Wall model) {
		
		this.view = view;
		this.model = model;
		
		this.view.addSkipLevelListener(new SkipLevelListener());
		
		this.view.addResetBallsListener(new ResetBallsListener());
		
		this.view.addBallXSpeedListener(new BallXSpeedListener());
		
		this.view.addBallYSpeedListener(new BallYSpeedListener());
		
	}
	
	class SkipLevelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			model.nextLevel();
			
		}
	}
	
	class ResetBallsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			model.resetBallCount();
			
		}
		
	}
	
	class BallXSpeedListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			model.setBallXSpeed(view.getBallXSpeedSlider().getValue());
			
		}
		
	}
	
	class BallYSpeedListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			model.setBallYSpeed(view.getBallYSpeedSlider().getValue());
			
		}
		
	}
	
	

}
