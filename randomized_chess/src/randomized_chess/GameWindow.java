package randomized_chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame{
	private static final long serialVersionUID = 1L; //I just did this to get rid of the warning, it has no real use for us
	
	private GameOverWindow gameOverWindow;
	
	private JPanel panel;
	
	private TileButton selectedButton;
	
	private Colour playerTurn;
	
	{playerTurn = Colour.White;}//always the white player moves first
	
	private ChessBoard chessBoard;
	
	private XMLHandler handler;
	
	public GameWindow(ChessBoard chessBoard) {
		super("Randomized Chess");
		
		handler = new XMLHandler();
		
		try {
			handler.resetXML();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.chessBoard = chessBoard;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setPreferredSize(new Dimension(800,800));
		
		this.setResizable(false);
		
		panel = new JPanel();
		
		panel.setLayout(new GridLayout(8, 8));
		
		//creating the buttons
		for(int i = 8; i > 0; i--) {
			for(int j = 1; j <= 8; j++) {
				if((i + j) % 2 == 0) {
					TileButton button = new TileButton(new Color(255,251,161), this, chessBoard.board.get(new Coordinate(j, i)));
					panel.add(button);
				}else {
					TileButton button = new TileButton(new Color(167,113,31), this, chessBoard.board.get(new Coordinate(j, i)));
					panel.add(button);
				}
				
			}
			

		}
		
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		this.add(panel);
		
		this.pack();
		
		this.setLocationRelativeTo(null);
	}

	public ChessBoard getBoard() {
		return chessBoard;
	}

	public TileButton getSelectedButton() {
		return selectedButton;
	}

	public void setSelectedButton(TileButton selectedButton) {
		this.selectedButton = selectedButton;
	}

	public Colour getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(Colour playerTurn) {
		this.playerTurn = playerTurn;
	}

	public XMLHandler getHandler() {
		return handler;
	}

	public GameOverWindow getGameOverWindow() {
		return gameOverWindow;
	}

	public void setGameOverWindow(GameOverWindow gameOverWindow) {
		this.gameOverWindow = gameOverWindow;
	}


	
	
}
