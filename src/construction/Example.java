package construction;

import javax.swing.*;

import gameGraphics.GameFrame;

import java.awt.*;

public class Example extends JPanel {
	
	private GameFrame owner;
	Image bg;
	
	public Example(GameFrame owner) {
		this.setLayout(null);
		this.owner = owner;
		bg = new ImageIcon("background.jpg").getImage();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(bg, 0, 0, null);
	}

}
