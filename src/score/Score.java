package score;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;


/**
 * This class represents the score in the game.
 * 
 * @author Shih Alf Slee
 * @category Software Maintenance
 * @version 2.0
 * @since 0.1
 *
 */
public class Score implements Comparable<Score> {
	
	private int score;
	private LocalDateTime timeStamp;
	
	/**
	 * This is the constructor method of class Score, it initializes the score of the player.
	 */
	public Score() {
		this.score = 0;
		
	}
	
	/**
	 * This method returns the score of the player.
	 * 
	 * @return  the score of the player
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * This method returns the time stamp of the score played by the player.
	 * 
	 * @return  the time stamp of the score played by the player
	 */
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * This method format the time stamp of the score played by the player to a pattern of "yy-MM-dd HH:mm".
	 * 
	 * @return  the formatted time stamp of then score played by the player.
	 */
	public String getFormattedTimeStamp() {
		return timeStamp.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"));
	}
	
	/**
	 * This method increases the score.
	 * 
	 * @param scoreIncreased  the value to be increased to the score
	 */
	public void increaseScore(int scoreIncreased) {
		this.score += scoreIncreased;
	}
	
	/**
	 * This method resets the score.
	 */
	public void resetScore() {
		this.score = 0;
	}

	/**
	 * This method sets the score.
	 * 
	 * @param score  the value to be set to the score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * This method sets the time stamp of the score played by the player.
	 * 
	 * @param timeStamp  the time stamp value to be set to the time stamp of the score played by the player
	 */
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	@Override
	public String toString() {
		return score + ", " + timeStamp + "\n";		
	}
	
	@Override
	public int compareTo(Score o) {
		int scoreRank = score - o.getScore() ;
		return scoreRank;
	}
	

}
