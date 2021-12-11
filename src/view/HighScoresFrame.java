package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import score.ScoreList;

/**
 * This class represents the frame of the high score list in the game.
 * 
 * @author Shih Alf Slee
 * @category Software Maintenance
 * @version 2.0
 * @since 0.1
 *
 */
public class HighScoresFrame extends JFrame implements ActionListener {
	final int frameWidth = 300;
	final int frameHeight = 200;

	final int buttonWidth = 80;
	final int buttonHeight = 20;

	ScoreList scoreList;
	ArrayList<Integer> scores;
	ArrayList<String> timeStamps;
	JPanel instructionPanel;
	JButton okButton;
	JScrollPane jps;

	/**
	 * This is the constructor method of class HighScoreFrame, it initializes the frame and the panel of the high scores frame, and creates a 'OK' button.
	 */
	public HighScoresFrame() {
		
		scoreList = new ScoreList();
		scores = new ArrayList<Integer>(5);
		timeStamps = new ArrayList<String>(5);
		
		createExitButtonutton();
		
		initPanel();
		
		initFrame();
		
	}

	/**
	 * This method initializes the frame of the high scores list.
	 */
	public void initFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(frameWidth, frameHeight);
		// this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setVisible(true);
		this.add(instructionPanel);

	}

	/**
	 * This method initializes the panel of the high scores list, and creates a high scores rank table.
	 */
	public void initPanel() {
		instructionPanel = new JPanel();

		instructionPanel.setLayout(new BorderLayout());

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		instructionPanel.add(buttonPanel, BorderLayout.SOUTH);

		instructionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "HIGHSCORES",
				TitledBorder.CENTER, TitledBorder.TOP));
		
		
		for ( int i=0 ;i<scoreList.getHighScoresList().size()-1 ;i++ ) {
		
			 this.scores.add(scoreList.getHighScoresList().get(i).getScore());
			 this.timeStamps.add(scoreList.getHighScoresList().get(i).getFormattedTimeStamp());
		}
		
		
		String[][] data = new String[5][2]; // 5 rows, 2 columns
		
		int flag = 0;
		
		
		for ( int i=0; i<2; i++ ) { // i == number of columns
			
		//while (flag != -1) {
			
			if (flag == 0) {
				flag = 1;
				for ( int j=0; j<scoreList.getHighScoresList().size()-1; j++) { // j == number of rows
					data[j][i] = String.valueOf(j+1);
				}
			}
			
			else if (flag == 1) {
				flag = -1;
				for ( int j=0; j<scoreList.getHighScoresList().size()-1; j++) { // j == number of rows
					data[j][i] = String.valueOf(scores.get(j)) + ", " + timeStamps.get(j);
				}
			}
		}
		
		String[] header = { "Rank", "Score" };
		JTable table = new JTable(data, header);

		table.setPreferredScrollableViewportSize(new Dimension(250, 100));
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Monospaced", Font.BOLD, 10));

		JScrollPane jps = new JScrollPane(table);

		instructionPanel.add(jps);

	}

	/**
	 * This method creates an 'OK' button.
	 */
	public void createExitButtonutton() {

		okButton = new JButton("OK");

		okButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

		okButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			dispose();
		}

	}
}
