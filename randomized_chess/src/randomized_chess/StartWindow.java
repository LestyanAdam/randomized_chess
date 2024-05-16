package randomized_chess;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//This is the game's start menu
//here we can start a new game, see previous games or exit the game

//TODO implement functional buttons

public class StartWindow extends JFrame{
	private static final long serialVersionUID = 1L; //I just did this to get rid of the warning, it has no real use for us
	
	private static ChessGame game;
	private static PreviousGameWindow previousGameWindow;
	
	private JPanel panel;
	private JLabel welcomeText, emptySpace1, descriptionText, emptySpace2;
	private JButton newGameButton, previousGamesButton, exitButton;
	
	public StartWindow() {
		super("Randomized Chess");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setPreferredSize(new Dimension(700, 225));
		
		this.setResizable(false);
		
		panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		welcomeText = new JLabel("Welcome to Randomized Chess!");
		emptySpace1 = new JLabel(" ");
		descriptionText = new JLabel("Click New Game to start a new chess match or click Previous Games to view the previously played games!");
		emptySpace2 = new JLabel(" ");
		
		welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);
		emptySpace1.setAlignmentX(Component.CENTER_ALIGNMENT);
		descriptionText.setAlignmentX(Component.CENTER_ALIGNMENT);
		emptySpace2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		newGameButton = new JButton("New Game");
		previousGamesButton = new JButton("Previous Games");
		exitButton = new JButton("Exit");
		
		newGameButton.addActionListener(new NewGameButtonListener());
		previousGamesButton.addActionListener(new PreviousGameButtonListener());
		exitButton.addActionListener(new ExitButtonListener());
		
		newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		newGameButton.setPreferredSize(new Dimension(100, 100));
		newGameButton.setMargin(new Insets(1,31,2,30));
		
		previousGamesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		previousGamesButton.setPreferredSize(new Dimension(100, 100));
		
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitButton.setPreferredSize(new Dimension(100, 100));
		exitButton.setMargin(new Insets(2,50,1,50));
		
		panel.add(Box.createRigidArea(new Dimension(0,7)));
		
		panel.add(welcomeText);
		panel.add(emptySpace1);
		panel.add(descriptionText);
		panel.add(emptySpace2);
		panel.add(newGameButton);
		panel.add(previousGamesButton);
		panel.add(exitButton);
		
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		this.add(panel);
		
		this.pack();
		
		this.setLocationRelativeTo(null);
	}
	
	private class NewGameButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			game = new ChessGame();		
			game.start();
		}
	}
	
	private class PreviousGameButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			previousGameWindow = new PreviousGameWindow();
			previousGameWindow.setVisible(true);
		}
	}
	
	private class ExitButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
