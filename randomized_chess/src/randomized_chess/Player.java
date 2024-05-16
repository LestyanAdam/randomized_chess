package randomized_chess;

import java.util.HashMap;

public class Player {
	private Colour colour;
	
	HashMap<Id , ChessPiece> chessPieces = new HashMap<>(16, 1.f);
	
	//creates the chess pieces and adds them to the hashmap
	public Player(Colour colour) {
		this.colour = colour;
		
		//create pawns
		for(int i = 1; i <= 8; i++) {
			Pawn p = new Pawn(colour, i);
			getChessPieces().put(new Id(colour, Type.Pawn, i), p);
		}
		
		//create rooks 
		for(int i = 1; i <= 2; i++) {
			Rook r = new Rook(colour, i);
			getChessPieces().put(new Id(colour, Type.Rook, i), r);
		}
		
		//create knights
		for(int i = 1; i <= 2; i++) {
			Knight k = new Knight(colour, i);
			getChessPieces().put(new Id(colour, Type.Knight, i), k);
		}
		
		//create bishops
		for(int i = 1; i <= 2; i++) {
			Bishop b = new Bishop(colour, i);
			getChessPieces().put(new Id(colour, Type.Bishop, i), b);
		}
		
		//create queen
		Queen queen = new Queen(colour, 1);
		getChessPieces().put(new Id(colour, Type.Queen, 1), queen);
		
		//create king
		King king = new King(colour, 1);
		getChessPieces().put(new Id(colour, Type.King, 1), king);
		
	}
	
	public Colour getColor() {
		return colour;
	}
	
	public King getKing() {
		return (King) getChessPieces().get(new Id(this.getColor(), Type.King, 1));
	}

	public HashMap<Id , ChessPiece> getChessPieces() {
		return chessPieces;
	}
	
}
