package controllers;

import gameGraphics.GameFrame;
import gameGraphics.HighScoresFrame;
import gameGraphics.HomeMenu;
import gameGraphics.InstructionFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeMenuController {

	// view
	HomeMenu homeMenu;
	
	// model
	GameFrame gameFrame;
	
	
	public HomeMenuController(HomeMenu homeMenu, GameFrame gameFrame) {
		
		this.homeMenu = homeMenu;
		this.gameFrame = gameFrame;
		
		this.homeMenu.addStartButtonListener(new StartButtonListener());
		
		this.homeMenu.addExitButtonListener(new ExitButtonListener());
		
		this.homeMenu.addInstructionButtonListener(new InstructionButtonListener());
		
		this.homeMenu.addHighScoresButtonListener(new HighScoresButtonListener());
		
	}
	
	class StartButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			gameFrame.enableGameBoard();
			
		}
		
	}
	
	class ExitButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Goodbye " + System.getProperty("user.name"));
			System.exit(0);
			
		}
		
	}
	
	class InstructionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new InstructionFrame();
			
		}
		
	}
	
	class HighScoresButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new HighScoresFrame();
			
		}
		
	}
	
	
	
}
