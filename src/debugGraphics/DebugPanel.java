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
package debugGraphics;

import javax.swing.*;
import javax.swing.event.ChangeListener;

import wall.Wall;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DebugPanel extends JPanel {

	private static final Color DEF_BKG = Color.black;

	private JButton skipLevelButton;
	private JButton resetBallsButton;

	private JSlider ballXSpeedSlider;
	private JSlider ballYSpeedSlider;

	private Wall wall;

	public DebugPanel(Wall wall) {

		this.wall = wall;

		initialize();

		// skipLevel = makeButton("Skip Level",e -> wall.nextLevel());
		// resetBalls = makeButton("Reset Balls",e -> wall.resetBallCount());

		// ballXSpeed = makeSlider(-4,4,e -> wall.setBallXSpeed(ballXSpeed.getValue()));
		// ballYSpeed = makeSlider(-4,4,e -> wall.setBallYSpeed(ballYSpeed.getValue()));

		skipLevelButton = new JButton("SKIP LEVEL");
		resetBallsButton = new JButton("RESET BALLS");

		ballXSpeedSlider = initSlider(-4, 4);
		ballYSpeedSlider = initSlider(-4, 4);

		this.add(skipLevelButton);
		this.add(resetBallsButton);

		this.add(ballXSpeedSlider);
		this.add(ballYSpeedSlider);

	}

	private void initialize() {
		this.setBackground(DEF_BKG);
		this.setLayout(new GridLayout(2, 2));
	}

	/*
	private JButton makeButton(String title, ActionListener e) {
		JButton out = new JButton(title);
		out.addActionListener(e);
		return out;
	}

	private JSlider makeSlider(int min, int max, ChangeListener e) {
		JSlider out = new JSlider(min, max);
		out.setMajorTickSpacing(1);
		out.setSnapToTicks(true);
		out.setPaintTicks(true);
		out.addChangeListener(e);
		return out;
	}
	*/

	private JSlider initSlider(int min, int max) {
		JSlider out = new JSlider(min, max);
		out.setMajorTickSpacing(1);
		out.setSnapToTicks(true);
		out.setPaintTicks(true);
		return out;
	}

	public void setValues(int x, int y) {
		ballXSpeedSlider.setValue(x);
		ballYSpeedSlider.setValue(y);
	}

	public void addSkipLevelListener(ActionListener listenForSkipLevelButton) {

		skipLevelButton.addActionListener(listenForSkipLevelButton);

	}

	public void addResetBallsListener(ActionListener listenForResetBallsButton) {

		resetBallsButton.addActionListener(listenForResetBallsButton);

	}

	public void addBallXSpeedListener(ChangeListener listenForBallXSpeedSlider) {

		ballXSpeedSlider.addChangeListener(listenForBallXSpeedSlider);

	}

	public void addBallYSpeedListener(ChangeListener listenForBallYSpeedSlider) {

		ballYSpeedSlider.addChangeListener(listenForBallYSpeedSlider);


	}
	
	public JSlider getBallXSpeedSlider() {
		return this.ballXSpeedSlider;
	}
	
	public JSlider getBallYSpeedSlider() {
		return this.ballYSpeedSlider;
	}

}
