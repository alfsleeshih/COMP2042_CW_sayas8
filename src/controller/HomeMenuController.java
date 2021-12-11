package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GameFrame;
import view.HighScoresFrame;
import view.HomeMenuPanel;
import view.InstructionFrame;

/**
 * This class represents the controller between the home menu and the game board.
 * 
 * @author Shih Alf Slee
 * @category Software Maintenance
 * @version 2.0
 * @since 0.1
 *
 */
public class HomeMenuController {

	// view
	HomeMenuPanel homeMenu;
	
	// model
	GameFrame gameFrame;
	
	/**
	 * This is a constructor class of class HomeMenuController, it adds the action listeners to the buttons of the home menu.
	 * 
	 * @param homeMenu
	 * @param gameFrame
	 */
	public HomeMenuController(HomeMenuPanel homeMenu, GameFrame gameFrame) {
		
		this.homeMenu = homeMenu;
		this.gameFrame = gameFrame;
		
		this.homeMenu.addStartButtonListener(new StartButtonListener());
		
		this.homeMenu.addExitButtonListener(new ExitButtonListener());
		
		this.homeMenu.addInstructionButtonListener(new InstructionButtonListener());
		
		this.homeMenu.addHighScoresButtonListener(new HighScoresButtonListener());
		
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
			new InstructionFrame();
			
		}
		
	}
	
	/**
	 * This class implements the action listener for the 'high scores' button of the home menu.
	 * 
	 */
	class HighScoresButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new HighScoresFrame();
			
		}
		
	}
	
	
	
}
