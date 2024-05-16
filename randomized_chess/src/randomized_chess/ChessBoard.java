package randomized_chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class ChessBoard {
	
	private ChessGame game;
	
	private Player playerWhite;
	private Player playerBlack;
	
	HashMap<Coordinate, Tile> board = new HashMap<>(64, 1.f);
	
	//this constructor sets up the chess board
	public ChessBoard(Player white, Player black, ChessGame game) {
		
		this.game = game;
		
		this.playerWhite = white;
		this.playerBlack = black;
		
		this.setUpTiles();
		
		if(playerWhite != null) {
			this.setUpPieces(playerWhite);
		}
		
		if(playerBlack != null) {
			this.setUpPieces(playerBlack);
		}
	}
	
	public void setUpTiles() {

		for(int i = 1; i <= 8; i++) {
			for(int j = 1; j <= 8; j++) {
				Coordinate newCoordinate = new Coordinate(j, i);
				Tile newTile = new Tile(newCoordinate, this);
				board.put(newCoordinate, newTile);
			}
		}
	}
	
	public void setUpPieces(Player player) {

		int row1 = -1;
		int row2 = -1;
		
		if(player.getColor() == Colour.White) {
			row1 = 2;
			row2 = 1;
		}else if(player.getColor() == Colour.Black) {
			row1 = 7;
			row2 = 8;
		}		
		
		//now we place the chess pieces on the board
		//we can easily place the pawns since those will always start in the same places		
		
		for(int i = 1; i <= 8; i++) {
			board.get(new Coordinate(i, row1)).setPiece(player.getChessPieces().get(new Id(player.getColor(), Type.Pawn, i)));
			player.getChessPieces().get(new Id(player.getColor(), Type.Pawn, i)).setTile(board.get(new Coordinate(i, row1)));
			player.getChessPieces().get(new Id(player.getColor(), Type.Pawn, i)).setChessBoard(this);
			
		}
		
		//now we have to randomly place the rest of the pieces, the rooks, the knights, the bishops, the queens and the kings
		//our version of randomized chess doesn't have many constraints on how the pieces have to be placed
		//the only constraint is that the two bishops have to be on opposing colors
		
		//generate a random even number
		// 2, 4, 6, 8
		//place the first bishop on the ranEven,row2 coordinate
		
		int ranEven = new Random().nextInt(1, 5) * 2;
		board.get(new Coordinate(ranEven, row2)).setPiece(player.getChessPieces().get(new Id(player.getColor(), Type.Bishop, 1)));
		player.getChessPieces().get(new Id(player.getColor(), Type.Bishop, 1)).setTile(board.get(new Coordinate(ranEven, row2)));
		player.getChessPieces().get(new Id(player.getColor(), Type.Bishop, 1)).setChessBoard(this);
		
		//generate a random odd number
		// 1, 3, 5, 7
		//place the second bishop on the ranOdd,row2 coordinate
		
		int ranOdd = new Random().nextInt(0, 4) * 2 + 1;
		board.get(new Coordinate(ranOdd, row2)).setPiece(player.getChessPieces().get(new Id(player.getColor(), Type.Bishop, 2)));
		player.getChessPieces().get(new Id(player.getColor(), Type.Bishop, 2)).setTile(board.get(new Coordinate(ranOdd, row2)));
		player.getChessPieces().get(new Id(player.getColor(), Type.Bishop, 2)).setChessBoard(this);
		
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		for(int i = 1; i <= 8; i++) {
			
			if(i != ranEven && i != ranOdd) {
				numbers.add(i);
			}
		}
		
		Collections.shuffle(numbers);
		
		//we place the remaining chess pieces at their randomized Tiles
		for(int j = 1; j <=2; j++) {
			board.get(new Coordinate(numbers.get(j - 1), row2)).setPiece(player.getChessPieces().get(new Id(player.getColor(), Type.Rook, j)));
			player.getChessPieces().get(new Id(player.getColor(), Type.Rook, j)).setTile(board.get(new Coordinate(numbers.get(j - 1), row2)));
			player.getChessPieces().get(new Id(player.getColor(), Type.Rook, j)).setChessBoard(this);
		}
		
		for(int j = 1; j <=2; j++) {
			board.get(new Coordinate(numbers.get(j + 1), row2)).setPiece(player.getChessPieces().get(new Id(player.getColor(), Type.Knight, j)));
			player.getChessPieces().get(new Id(player.getColor(), Type.Knight, j)).setTile(board.get(new Coordinate(numbers.get(j + 1), row2)));
			player.getChessPieces().get(new Id(player.getColor(), Type.Knight, j)).setChessBoard(this);
		}
		
		board.get(new Coordinate(numbers.get(4), row2)).setPiece(player.getChessPieces().get(new Id(player.getColor(), Type.Queen, 1)));
		player.getChessPieces().get(new Id(player.getColor(), Type.Queen, 1)).setTile(board.get(new Coordinate(numbers.get(4), row2)));
		player.getChessPieces().get(new Id(player.getColor(), Type.Queen, 1)).setChessBoard(this);
		
		board.get(new Coordinate(numbers.get(5), row2)).setPiece(player.getChessPieces().get(new Id(player.getColor(), Type.King, 1)));
		player.getChessPieces().get(new Id(player.getColor(), Type.King, 1)).setTile(board.get(new Coordinate(numbers.get(5), row2)));
		player.getChessPieces().get(new Id(player.getColor(), Type.King, 1)).setChessBoard(this);
	}

	public Player getPlayerWhite() {
		return playerWhite;
	}

	public Player getPlayerBlack() {
		return playerBlack;
	}

	public ChessGame getGame() {
		return game;
	}
	
	public HashMap<Coordinate, Tile> getBoard(){
		return board;
	}
}
