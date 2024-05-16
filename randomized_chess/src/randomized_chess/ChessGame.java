package randomized_chess;

public class ChessGame {
	private Player playerWhite;
	private Player playerBlack;
	
	private ChessBoard chessBoard;
	
	public ChessGame() {
		playerWhite = new Player(Colour.White);
		playerBlack = new Player(Colour.Black);
		
		chessBoard = new ChessBoard(playerWhite, playerBlack, this);
		
	}
	
	public void start() {
		GameWindow window = new GameWindow(chessBoard);
		window.setVisible(true);
	}

	public ChessBoard getChessBoard() {
		return chessBoard;
	}
}
