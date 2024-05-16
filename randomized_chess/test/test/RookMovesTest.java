package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import randomized_chess.ChessBoard;
import randomized_chess.Colour;
import randomized_chess.Coordinate;
import randomized_chess.King;
import randomized_chess.Player;
import randomized_chess.Rook;
import randomized_chess.Tile;

public class RookMovesTest {

	private Rook rook;
	
	@Before
	public void ctor() {
		this.rook = new Rook(Colour.White, 3);
		rook.setChessBoard(new ChessBoard(new Player(Colour.White), new Player(Colour.Black), null));
		rook.setTile(rook.getChessBoard().getBoard().get(new Coordinate(4, 4)));
	}
	
	@Test
	public void invalidTarget() {
		assertFalse(rook.canMove(null, false));
		
		assertFalse(rook.canMove(new Tile(new Coordinate(0, 0), null), false));
		
		assertFalse(rook.canMove(new Tile(new Coordinate(9, 12), null), false));
	}
	
	@Test
	public void targetHasFriendly() {
		assertFalse(rook.canMove(rook.getChessBoard().getBoard().get(new Coordinate(4, 2)), false));
	}
	
	@Test
	public void targetUnreachable() {
		assertFalse(rook.canMove(rook.getChessBoard().getBoard().get(new Coordinate(7, 6)), false));
	}
	
	@Test
	public void emptyTarget() {
		assertTrue(rook.canMove(rook.getChessBoard().getBoard().get(new Coordinate(4, 3)), false));
		assertTrue(rook.canMove(rook.getChessBoard().getBoard().get(new Coordinate(1, 4)), false));
		assertTrue(rook.canMove(rook.getChessBoard().getBoard().get(new Coordinate(4, 6)), false));
		assertTrue(rook.canMove(rook.getChessBoard().getBoard().get(new Coordinate(8, 4)), false));
	}
	
	@Test
	public void skipsKingIfJustChecking() {
		
		King king = new King(Colour.Black, 3);
		king.setTile(rook.getChessBoard().getBoard().get(new Coordinate(7, 4)));
		king.getTile().setPiece(king);
		
		assertTrue(rook.canMove(rook.getChessBoard().getBoard().get(new Coordinate(8, 4)), true));
		
		king.setTile(rook.getChessBoard().getBoard().get(new Coordinate(4, 5)));
		king.getTile().setPiece(king);
		
		assertTrue(rook.canMove(rook.getChessBoard().getBoard().get(new Coordinate(4, 6)), true));
		
		king.setTile(rook.getChessBoard().getBoard().get(new Coordinate(2, 4)));
		king.getTile().setPiece(king);
		
		assertTrue(rook.canMove(rook.getChessBoard().getBoard().get(new Coordinate(1, 4)), true));
		
		rook.setTile(rook.getChessBoard().getBoard().get(new Coordinate(4, 6)));
		king.setTile(rook.getChessBoard().getBoard().get(new Coordinate(4, 4)));
		king.getTile().setPiece(king);
		
		assertTrue(rook.canMove(rook.getChessBoard().getBoard().get(new Coordinate(4, 3)), true));
	}
	
	@Test
	public void targetObstructed() {
		King king = new King(Colour.Black, 3);
		king.setTile(rook.getChessBoard().getBoard().get(new Coordinate(7, 4)));
		king.getTile().setPiece(king);
		
		assertFalse(rook.canMove(rook.getChessBoard().getBoard().get(new Coordinate(8, 4)), false));
		
		king.setTile(rook.getChessBoard().getBoard().get(new Coordinate(4, 5)));
		king.getTile().setPiece(king);
		
		assertFalse(rook.canMove(rook.getChessBoard().getBoard().get(new Coordinate(4, 6)), false));
		
		king.setTile(rook.getChessBoard().getBoard().get(new Coordinate(2, 4)));
		king.getTile().setPiece(king);
		
		assertFalse(rook.canMove(rook.getChessBoard().getBoard().get(new Coordinate(1, 4)), false));
		
		rook.setTile(rook.getChessBoard().getBoard().get(new Coordinate(4, 6)));
		king.setTile(rook.getChessBoard().getBoard().get(new Coordinate(4, 4)));
		king.getTile().setPiece(king);
		
		assertFalse(rook.canMove(rook.getChessBoard().getBoard().get(new Coordinate(4, 3)), false));
	}
	
	@Test
	public void targetHasEnemy() {
		assertTrue(rook.canMove(rook.getChessBoard().getBoard().get(new Coordinate(4, 7)), false));
	}

}
