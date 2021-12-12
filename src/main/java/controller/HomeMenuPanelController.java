package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GameFrame;
import view.HighScoresBoard;
import view.HomeMenuPanel;
import view.InstructionsBoard;

/**
 * This class represents the controller between the home menu and the game board.
 * 
 * @author Shih Alf Slee
 * @version 2.0
 * @since 0.1
 *
 */
public class HomeMenuPanelController {

	// view
	private HomeMenuPanel homeMenuPanel;
	
	// model
	private GameFrame gameFrame;
	
	/**
	 * This is a constructor class of class HomeMenuController, it adds the action listeners to the buttons of the home menu.
	 * 
	 * @param homeMenuPanel
	 * @param gameFrame
	 */
	public HomeMenuPanelController(HomeMenuPanel homeMenuPanel, GameFrame gameFrame) {
		
		this.homeMenuPanel = homeMenuPanel;
		this.gameFrame = gameFrame;
		
		this.homeMenuPanel.addStartButtonListener(new StartButtonListener());
		
		this.homeMenuPanel.addExitButtonListener(new ExitButtonListener());
		
		this.homeMenuPanel.addInstructionButtonListener(new InstructionButtonListener());
		
		this.homeMenuPanel.addHighScoresButtonListener(new HighScoresButtonListener());
		
	}
	
	/**
	 * This class implements the action listener for the start button of the home menu.
	 * 
	 */
	class StartButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			gameFrame.enableGameBoard();
			
		}
		
	}
	
	/**
	 * This class implements the action listener for the exit button of the home menu.
	 * 
	 */
	class ExitButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Goodbye " + System.getProperty("user.name"));
			System.exit(0);
			
		}
		
	}
	
	/**
	 * This class implements the action listener for the 'instructions' button of the home menu.
	 * 
	 */
	class InstructionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new InstructionsBoard();
			
		}
		
	}
	
	/**
	 * This class implements the action listener for the 'high scores' button of the home menu.
	 * 
	 */
	class HighScoresButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new HighScoresBoard();
			
		}
		
	}
	
	
	
}
