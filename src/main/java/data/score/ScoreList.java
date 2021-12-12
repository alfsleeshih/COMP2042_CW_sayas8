package data.score;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JOptionPane;

import view.HighScoresBoard;

/**
 * This class represents a collection of scores in the game.
 * 
 * @author Shih Alf Slee
 * @version 2.0
 * @since 0.1
 *
 */
public class ScoreList { // list of data

	ArrayList<Score> highScoresList;
	
	Score liveScore;

	/**
	 * This is the constructor method of class ScoreList, it initializes a live score, a high scores list by reading 'highscore.txt', and sort the high scores list in descending order.
	 */
	public ScoreList() {

		highScoresList = new ArrayList<Score>();
		
		liveScore = new Score();

		try {
			sortData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * This method checks the existence of 'highscore.txt', collects the data from the text file, and inserts it into the high score list in ascending order.
	 * 
	 * @throws FileNotFoundException  the exception if the 'highscore.txt' cannot be found
	 */
	public void sortData() throws FileNotFoundException {

		File file = new File("highscore.txt");
		
		this.checkAndInitializeHighScoreFile(file);
		
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] scoreAndDateTime = line.split(", ");

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");

				Score score = new Score();
				
				score.setScore(Integer.valueOf(scoreAndDateTime[0]));
				score.setTimeStamp(LocalDateTime.parse(scoreAndDateTime[1], formatter));
				
				highScoresList.add(score);
				
			}
			
			Collections.sort(highScoresList, Collections.reverseOrder());

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * This method check if 'highscore.txt' exists, if it does not exists, create a new 'highscore.txt' and write a default high score to the new text file.
	 * 
	 * @param file  the 'highscore.txt' file
	 */
	public void checkAndInitializeHighScoreFile(File file) {
				
		if (!file.exists()) {
			try {
				file.createNewFile();
						
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String defaultHighScore = "0, 21-01-01 00:00" + "\n" ;
			
			FileWriter writeFile = null;
			BufferedWriter writer = null;
			
			try {
				writeFile = new FileWriter(file, true);
				writer = new BufferedWriter(writeFile);
				writer.write(defaultHighScore);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method checks if the live score is supposed to be added into the high scores list, if yes, writes the live score and the time stamp of the live score into the high scores list.
	 *
	 */
	public void checkBreakHighScore() {
		
		if(checkBreakHighScores()) {
			
			liveScore.setTimeStamp(LocalDateTime.now());
			
			String newHighScore = liveScore.getScore() + ", " + liveScore.getFormattedTimeStamp() + "\n";

			File highScoreFile = new File("highscore.txt");
			if (!highScoreFile.exists()) {
				try {
					highScoreFile.createNewFile();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}

			FileWriter writeFile = null;
			BufferedWriter writer = null;
			
			try {
				writeFile = new FileWriter(highScoreFile, true);
				writer = new BufferedWriter(writeFile);
				
				writer.append(newHighScore);
			}

			catch (Exception e) {

			}

			finally {
				try {
					if (writer != null)
						writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * This method checks if the live score is supposed to be added into the high score list.
	 * 
	 * @return  true if live score is larger than the highest score in the high scores list or larger and not equal to any high scores in the high scores list
	 */
	public boolean checkBreakHighScores() {
		
		for (int i=0; i<this.highScoresList.size()-1; i++) {
			
			if ((this.liveScore.getScore() > this.highScoresList.get(i+1).getScore() &&  // eliminate the default
					this.liveScore.getScore() < this.highScoresList.get(0).getScore()) && // lower than first
						checkNotEqualHighScores()) // not equal to any of the high score
				return true;
			}
		
		return (this.liveScore.getScore() > this.highScoresList.get(0).getScore());
		
		
	}
	
	/**
	 * This method returns true if the live score is not equal to any high scores in the high scores list.
	 * 
	 * @return  true if the live score is not equal to any high scores in the high scores list.
	 */
	public boolean checkNotEqualHighScores() {
		
		boolean flag = false;
		
		for (int i=0; i<this.highScoresList.size(); i++) {
			if (this.liveScore.getScore() != this.highScoresList.get(i).getScore())
				flag = true;
		}
		
		return flag;
	}
	
	/**
	 * This method checks if a new high score is created, if so, create a JDialog to congratulate the player.
	 */
	public void breakHighScoreNotify() {
		if (this.liveScore.getScore() > this.highScoresList.get(0).getScore()) {
			new HighScoresBoard();
			JOptionPane.showMessageDialog(null, "Congratulations! You just break a new highscore.", null, JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * This method returns the high scores list.
	 * 
	 * @return  the high scores list
	 */
	public ArrayList<Score> getHighScoresList() {
		return this.highScoresList;
	}

	/**
	 * This method returns the highest score in the high scores list.
	 * 
	 * @return  the highest score in the high scores list
	 */
	public Score getHighScore() {
		return this.highScoresList.get(0);
	}
	
	/**
	 * This method returns the live score of the player.
	 * 
	 * @return  the live score of the player
	 */
	public Score getLiveScore() {
		return liveScore;
	}
	
	/**
	 * This method prints the high score list
	 */
	public void printRank() {
		System.out.println(highScoresList);
	}
	
	

}
