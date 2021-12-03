package data;

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

import gameGraphics.HighScoresFrame;

public class ScoreList { // list of data

	ArrayList<Score> data;
	
	Score liveScore;

	
	public ScoreList() {

		data = new ArrayList<Score>();
		
		liveScore = new Score();

		try {
			sortData();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	public void sortData() throws FileNotFoundException {

		File file = new File("highscore.txt");
		
		if (!file.exists()) {
			try {
				file.createNewFile();
						
			} catch (IOException e) {
				//System.out.println("hi");
				e.printStackTrace();
			}
			
			//FileWriter writeFile = null;
			//BufferedWriter writer = null;
			
			String defaultHighScore = "0, 21-01-01 00:00" + "\n" ;
			/*
			try {
				FileWriter writer = new FileWriter(file);
				writer.write(defaultHighScore);
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
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
		
		

		Scanner scanner;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] scoreAndDateTime = line.split(", ");

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");

				// data.add(new Score(Integer.valueOf(scoreAndDateTime[0]),
						// LocalDateTime.parse(scoreAndDateTime[1], formatter)));
				
				Score score = new Score();
				
				score.setScore(Integer.valueOf(scoreAndDateTime[0]));
				score.setTimeStamp(LocalDateTime.parse(scoreAndDateTime[1], formatter));
				
				data.add(score);
				
				

			}
			
			Collections.sort(data, Collections.reverseOrder());

			// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
			//System.out.println(data);
			// System.out.println(data.get(0).getTimeStamp1().format(formatter));
			// System.out.println(((ChronoLocalDateTime<LocalDate>)
			// data).format(formatter));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void checkBreakHighScore() {

		/*
		if (highScore.equals("")) {
			return;
		}
		*/

		//if (this.getLiveScore().getScore() > this.getHighScore().getScore()) {
			
			//System.out.println(checkBreakHighScores());
		
		if(checkBreakHighScores()) {
			
			//timeStamp = LocalDateTime.now();
			
			//highScore = score + " : " + this.getFormattedTimeStamp();
			
			liveScore.setTimeStamp(LocalDateTime.now());
			
			String newHighScore = liveScore.getScore() + ", " + liveScore.getFormattedTimeStamp() + "\n";

			File highScoreFile = new File("highscore.txt");
			if (!highScoreFile.exists()) {
				try {
					highScoreFile.createNewFile();
				} catch (IOException e) {
					//System.out.println("New file created.");
					e.printStackTrace();
				}
			}

			FileWriter writeFile = null;
			BufferedWriter writer = null;

			try {
				writeFile = new FileWriter(highScoreFile, true);
				writer = new BufferedWriter(writeFile);
				//writer.write(newHighScore);
				/*
				if (highScoreFile.exists()) {
					writer.append(newHighScore);
				}
				else {
					writer.write(newHighScore);
				}
				*/
				
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

	public ArrayList<Score> getData() {
		return this.data;
	}

	public Score getHighScore() {
		return this.data.get(0);
	}
	
	public Score getLiveScore() {
		return liveScore;
	}
	
	public void printRank() {
		System.out.println(data);
	}
	
	public boolean checkBreakHighScores() {
		//if (this.liveScore > this.data.getScores())
		
		//System.out.println(this.data.get(0).getScore());
		//System.out.println(this.liveScore.getScore() > this.data.get(0).getScore());
		
		/*
		if (this.liveScore.getScore() > this.data.get(0).getScore()) { // higher than first
			return true;
		}
		*/
		
		//return (this.liveScore.getScore() > this.data.get(0).getScore());
			
		
		for (int i=0; i<this.data.size()-1; i++) {
			
			//System.out.println("check");
			
			if ((this.liveScore.getScore() > this.data.get(i+1).getScore() &&  // eliminate the default
					this.liveScore.getScore() < this.data.get(0).getScore()) && // lower than first
						checkNotEqualHighScores())
						//checkEqualHighScores(this.liveScore.getScore(), this.data.get)) 
				return true;
			}
		
		return (this.liveScore.getScore() > this.data.get(0).getScore());
		
		
	}
			
		
		
		
		//else return false;
		//return false;
	
	
	public boolean checkNotEqualHighScores() {
		
		boolean flag = false;
		
		for (int i=0; i<this.data.size(); i++) {
			if (this.liveScore.getScore() != this.data.get(i).getScore())
				flag = true;
		}
		
		//System.out.println(flag);
		return flag;
	}
	
	public void breakHighScoreNotify() {
		if (this.liveScore.getScore() > this.data.get(0).getScore()) {
			new HighScoresFrame();
			JOptionPane.showMessageDialog(null, "Congluatulations! You just break a new highscore.", null, JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	

}
