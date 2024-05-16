package randomized_chess;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	
	private JLabel text;
	
	//the constructor receives the colour of the player who won
	public GameOverWindow(Colour colour) {
		super("Game Over!");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setPreferredSize(new Dimension(300,100));
		
		this.setResizable(false);
		
		panel = new JPanel();
		
		text = new JLabel("Player " + colour.toString() + " has won the game!");
		
		panel.add(text, BorderLayout.NORTH);
		
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		this.add(panel);
		
		this.pack();
		
		this.setLocationRelativeTo(null);
		
	}

}
