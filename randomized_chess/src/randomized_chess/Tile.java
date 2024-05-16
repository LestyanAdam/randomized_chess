package randomized_chess;

public class Tile {
	
	//each Tile can have a chess piece on it
	private ChessPiece piece;
	
	//every Tile has a coordinate on the chess board
	//x and y are between 1 and 8
	//0,0 is the default
	private Coordinate coordinate;
	
	
	private ChessBoard chessBoard;
	
	//constructor
	public Tile(Coordinate coordinate, ChessBoard chessBoard) {
		this.coordinate = coordinate;
		this.chessBoard = chessBoard;
	}
	
	//getter and setter methods
	public ChessPiece getPiece() {
		return piece;
	}

	public void setPiece(ChessPiece piece) {
		this.piece = piece;
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	//the next two methods help with readability
	public int getCoordinateX() {
		return coordinate.getX();
	}
	
	public int getCoordinateY() {
		return coordinate.getY();
	}
	
	//checks if the Tile is valid
	//helps with readability
	public boolean isValid() {
		return this.coordinate.isValid();
	}
	
	//returns the color of the piece that is on the Tile
	//helps with readability
	public Colour getPieceColour() {
		if(this.getPiece() == null) {
			return null;
		}
		return this.getPiece().getColour();
	}
	 
	//returns true if the Tile is being attacked by an enemy piece
	public boolean isChecked(Colour colour) {
		//this method receives the color of the chess piece that asks if the Tile is checked
		//that means we have to check if any pieces of the enemy player (the opposite color) can move to that Tile	
		
		if(colour == Colour.White) {
			for(ChessPiece p : this.chessBoard.getPlayerBlack().getChessPieces().values()) {

				if(p.canMove(this, true)) {
					return true;
				}
			}
			
		}else if(colour == Colour.Black) {
			
			for(ChessPiece p : this.chessBoard.getPlayerWhite().getChessPieces().values()) {

				if(p.canMove(this, true)) {
					return true;
				}
			}
		}

		return false;
	}

	public ChessBoard getChessBoard() {
		return chessBoard;
	}
}
