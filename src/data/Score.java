package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Score implements Comparable<Score> {
	//private int score;
	//private String highScore;
	//private LocalDateTime timeStamp;
	
	//temp
	private int score;
	private LocalDateTime timeStamp;
	
	
	
	public int getScore() {
		return score;
	}
	
	@Override
	public String toString() {
		return score + ", " + timeStamp + "\n";		
	}
	
	public Score() {
		this.score = 0;
		
	}
	
	public LocalDateTime getTimeStamp1() {
		return timeStamp;
	}
	
	public String getFormattedTimeStamp() {
		return timeStamp.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"));
	}
	
	public void increaseScore(int scoreIncreased) {
		this.score += scoreIncreased;
	}
	
	public void resetScore() {
		this.score = 0;
	}

	
	@Override
	public int compareTo(Score o) {
		int scoreRank = score - o.getScore() ;
		return scoreRank;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	/*
	public String getHighScore() { // format: Alf:560

		FileReader readFile = null;
		BufferedReader reader = null;

		try {
			readFile = new FileReader("highscore.dat");
			reader = new BufferedReader(readFile);
			return reader.readLine();
		}

		catch (Exception e) {
			return "0"; // if we cannot create the dat file
		}

		finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	

	public void checkBreakHighScore() {

		if (highScore.equals("")) {
			return;
		} 

		else if (score > Integer.parseInt(highScore.split(" : ")[0])) {
			//String name = JOptionPane.showInputDialog("What is your name?");
			//highScore = name + ":" + score;
		//if (score > Integer.parseInt(highScore)) {
			
			//highScore = String.valueOf(score);
			
			timeStamp = LocalDateTime.now();
			
			highScore = score + " : " + this.getFormattedTimeStamp();

			File highScoreFile = new File("highscore.dat");
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
				writeFile = new FileWriter(highScoreFile);
				writer = new BufferedWriter(writeFile);
				writer.write(this.highScore);
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
	
	public int getScore() {
		return this.score;
	}
	*/
	
	
	

}
