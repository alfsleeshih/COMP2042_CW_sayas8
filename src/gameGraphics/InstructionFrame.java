package gameGraphics;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructionFrame extends JFrame implements ActionListener {
	
	final int frameWidth = 300;
	final int frameHeight = 200;
	
	final int buttonWidth = 80;
	final int buttonHeight = 20;
	
	JPanel instructionPanel;
	JButton okButton;
	JScrollPane jps;
	
	public InstructionFrame() {
		
		createxitButtonutton();
		
		initPanel();
		
		initFrame();
		
		
	}
	
	public void initFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(frameWidth,frameHeight);
		//this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setVisible(true);
		this.add(instructionPanel);
		
	}
	
	public void initPanel() {
		instructionPanel = new JPanel();
		
		instructionPanel.setLayout(new BorderLayout());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		instructionPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		instructionPanel.setBorder(BorderFactory.createTitledBorder(
		BorderFactory.createEtchedBorder(), "INTRUCTION", TitledBorder.CENTER, TitledBorder.TOP));
		
		
		String[][] data = {
		        { "Esc", "Enter/Exit Pause Menu" },
		        { "Space", "Start/Pause Game" },
		        { "A", "Move Leftwards" },
		        { "D", "Move Rightwards" },
		        { "Alt + Shift + F1", "Open Console" }
		     };
		String[] header = { "Key", "Action Performed" };
		JTable table = new JTable(data, header);
		
		table.setPreferredScrollableViewportSize(new Dimension(250,100));
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Monospaced",Font.BOLD,10));
		
		
		JScrollPane jps = new JScrollPane(table);
		
		
		
		instructionPanel.add(jps);
		
	}
	
	
	
	
	public void createxitButtonutton() {
		
		okButton = new JButton("OK");
		
		okButton.setPreferredSize(new Dimension(buttonWidth,buttonHeight));
		
		okButton.addActionListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==okButton) {
			dispose();
		}
		
	}

}
