/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistrinstructionButtonute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distrinstructionButtonuted in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package view;

import javax.swing.*;
import javax.swing.event.ChangeListener;

import model.wall.Wall;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the panel of the debug console.
 * 
 * @author Shih Alf Slee
 * @version 2.0
 * @since 0.1
 *
 */
public class DebugConsolePanel extends JPanel {

	private static final Color DEF_BKG = Color.black;

	private JButton skipLevelButton;
	private JButton resetBallsButton;

	private JSlider ballXSpeedSlider;
	private JSlider ballYSpeedSlider;

	//private Wall wall;

	/**
	 * This is a constructor method of class DebugPanel, it creates the 'skip level' and 'reset ball' button, and sliders to set the movement speed of the ball.
	 */
	public DebugConsolePanel() {
		initialize();

		skipLevelButton = new JButton("SKIP LEVEL");
		resetBallsButton = new JButton("RESET BALLS");

		ballXSpeedSlider = initSlider(-6, 6);
		ballYSpeedSlider = initSlider(-6, 6);

		this.add(skipLevelButton);
		this.add(resetBallsButton);

		this.add(ballXSpeedSlider);
		this.add(ballYSpeedSlider);

	}

	/**
	 * This method initializes the debug panel by setting its background colour and layout.
	 */
	private void initialize() {
		this.setBackground(DEF_BKG);
		this.setLayout(new GridLayout(2, 2));
	}

	/**
	 * This method initializes JSlider.
	 * 
	 * @param min  the minimum value of the slider
	 * @param max  the maximum value of the slider
	 * @return  the JSlider
	 */
	private JSlider initSlider(int min, int max) {
		JSlider out = new JSlider(min, max);
		out.setMajorTickSpacing(1);
		out.setSnapToTicks(true);
		out.setPaintTicks(true);
		return out;
	}

	/**
	 * this method sets the movement speed of the ball via the slider.
	 * 
	 * @param x  the horizontal movement speed of the ball
	 * @param y  the vertical movement speed of the ball
	 */
	public void setValues(int x, int y) {
		ballXSpeedSlider.setValue(x);
		ballYSpeedSlider.setValue(y);
	}
	
	/**
	 * This method adds action listener to the 'skip level' button of the debug panel.
	 * 
	 * @param listenForSkipLevelButton  the class that implements the action listener
	 */
	public void addSkipLevelListener(ActionListener listenForSkipLevelButton) {

		skipLevelButton.addActionListener(listenForSkipLevelButton);

	}

	/**
	 * This method adds action listener to the 'reset balls' button of the debug panel.
	 * 
	 * @param listenForResetBallsButton  the class that implements the action listener
	 */
	public void addResetBallsListener(ActionListener listenForResetBallsButton) {

		resetBallsButton.addActionListener(listenForResetBallsButton);

	}

	/**
	 * This method adds change listener to the BallXSpeed slider.
	 * 
	 * @param listenForBallXSpeedSlider  the class that implements the change listener
	 */
	public void addBallXSpeedListener(ChangeListener listenForBallXSpeedSlider) {

		ballXSpeedSlider.addChangeListener(listenForBallXSpeedSlider);

	}

	/**
	 * This method adds change listener to the BallYSpeed slider.
	 * 
	 * @param listenForBallYSpeedSlider  the class that implements the change listener
	 */
	public void addBallYSpeedListener(ChangeListener listenForBallYSpeedSlider) {

		ballYSpeedSlider.addChangeListener(listenForBallYSpeedSlider);


	}
	
	/**
	 * This method returns the slider that controls the horizontal movement speed of the ball.
	 * 
	 * @return  the slider that controls the horizontal movement speed of the ball
	 */
	public JSlider getBallXSpeedSlider() {
		return this.ballXSpeedSlider;
	}
	
	/**
	 * This method returns the slider that controls the vertical movement speed of the ball.
	 * 
	 * @return  the slider that controls the vertical movement speed of the ball
	 */
	public JSlider getBallYSpeedSlider() {
		return this.ballYSpeedSlider;
	}

}
