package randomized_chess;

import java.util.Objects;

import javax.swing.Icon;

abstract public class ChessPiece {
	//There are 32 chess pieces on the table at the start of the game and each one has to be unique
	//color can be black or white
	
	private Colour colour;

	//type can be: King/Queen/Knight/Rook/Bishop/Pawn
	
	private Type type;

	private Id id;
	
	//example: the white Queen would have - color: white, type: Queen, id: WhiteQueen1
	//example: the 4th black pawn would have - color: black, type: Pawn, id: BlackPawn4
	
	//the current Tile the piece stands on
	private Tile tile;
	
	private ChessBoard chessBoard;
	
	//abstract constructor
	public ChessPiece(Colour colour, Type type, int id) {
		this.colour = colour;
		this.type = type;
		this.id = new Id(colour, type, id); //ex. WhitePawn5
	}
	
	//the private variables have their own getter methods
	//setter methods are not needed since chess pieces don't change, except for the alive variable and the coordinate
	public Colour getColour(){
		return colour;
	}
	
	public void setColour(Colour colour) {
		this.colour = colour;
	}
	
	public Type getType() {
		return type;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(colour, id, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChessPiece other = (ChessPiece) obj;
		return colour == other.colour && Objects.equals(id, other.id) && type == other.type;
	}

	public Id getId() {
		return id;
	}
	
	//the next two methods help with readability
	public int getCoordinateX() {
		return tile.getCoordinateX();
	}
	
	public int getCoordinateY() {
		return tile.getCoordinateY();
	}
	
	public Tile getTile() {
		return tile;
	}
	
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	public ChessBoard getChessBoard() {
		return chessBoard;
	}

	public void setChessBoard(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}
	
	//this method checks if the piece is able to move to the specified Tile
	//the boolean parameter is only true, when the method is called in the isChecked method
	//it tells us if we are checking where a King can move
	public abstract boolean canMove(Tile target, boolean justChecking);
	
	public abstract Icon getIcon();
	
	public boolean canMoveAnywhere() { 
		
		for(Tile t : this.chessBoard.board.values()) {
			if(this.canMove(t, false)) {
				return true;
			}
		}
		
		return false;
	}
	

	
}
