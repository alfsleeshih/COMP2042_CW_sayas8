package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the frame of the 'instructions' pop up.
 * 
 * @author Shih Alf Slee
 * @version 2.0
 * @since 0.1
 *
 */
public class InstructionsBoard extends JFrame implements ActionListener {
	
	final int frameWidth = 300;
	final int frameHeight = 200;
	
	final int buttonWidth = 80;
	final int buttonHeight = 20;
	
	JPanel instructionPanel;
	JButton okButton;
	JScrollPane jps;
	
	/**
	 * This is the constructor method of class InstructionsFrame, it initializes the frame and the panel of the 'instructions' pop up, and creates an 'OK' button.
	 */
	public InstructionsBoard() {
		
		createExitButtonutton();
		
		initPanel();
		
		initFrame();
		
		
	}
	
	/**
	 * This method initializes the frame of the 'instructions' pop up.
	 */
	public void initFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(frameWidth,frameHeight);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.add(instructionPanel);
		this.setVisible(true);
		
	}
	
	/**
	 * This method initializes the panel of the 'instructions' pop up, and creates a table containing the information of 'instructions'.
	 */
	public void initPanel() {
		instructionPanel = new JPanel();
		
		instructionPanel.setLayout(new BorderLayout());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		instructionPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		instructionPanel.setBorder(BorderFactory.createTitledBorder(
		BorderFactory.createEtchedBorder(), "INTRUCTIONS", TitledBorder.CENTER, TitledBorder.TOP));
		
		
		String[][] data = {
		        { "Esc", "Enter/Exit Pause Menu" },
		        { "Space", "Start/Pause Game" },
		        { "A", "Move Leftwards" },
		        { "D", "Move Rightwards" },
		        { "Alt + Shift + F1", "Open Up Debug Console" }
		     };
		String[] header = { "Key", "Action Performed" };
		JTable table = new JTable(data, header);
		
		table.setPreferredScrollableViewportSize(new Dimension(250,100));
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Monospaced",Font.BOLD,10));
		
		
		JScrollPane jps = new JScrollPane(table);
		
		
		
		instructionPanel.add(jps);
		
	}
	
	/**
	 * This method creates an 'OK' button.
	 */
	public void createExitButtonutton() {
		
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
