package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import randomized_chess.Bishop;
import randomized_chess.ChessBoard;
import randomized_chess.Colour;
import randomized_chess.Coordinate;
import randomized_chess.King;
import randomized_chess.Player;
import randomized_chess.Tile;

public class BishopMovesTest {

	private Bishop bishop;
	
	@Before
	public void ctor() {
		this.bishop = new Bishop(Colour.White, 3);
		bishop.setChessBoard(new ChessBoard(new Player(Colour.White), new Player(Colour.Black), null));
		bishop.setTile(bishop.getChessBoard().getBoard().get(new Coordinate(4, 4)));
	}
	
	@Test
	public void invalidTarget() {
		assertFalse(bishop.canMove(null, false));
		
		assertFalse(bishop.canMove(new Tile(new Coordinate(0, 0), null), false));
		
		assertFalse(bishop.canMove(new Tile(new Coordinate(9, 12), null), false));
	}
	
	@Test
	public void targetHasFriendly() {
		assertFalse(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(2, 2)), false));
	}
	
	@Test
	public void targetUnreachable() {
		assertFalse(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(4, 6)), false));
		assertFalse(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(8, 3)), false));
		assertFalse(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(8, 8)), false));
		assertFalse(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(3, 4)), false));
	}
	
	@Test
	public void emptyTarget() {
		assertTrue(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(6, 6)), false));
		assertTrue(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(2, 6)), false));
		assertTrue(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(3, 3)), false));
		assertTrue(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(5, 3)), false));
	}
	
	@Test
	public void skipsKingIfJustChecking() {
		King king = new King(Colour.Black, 3);
		king.setTile(bishop.getChessBoard().getBoard().get(new Coordinate(3, 5)));
		king.getTile().setPiece(king);
		
		assertTrue(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(2, 6)), true));
		
		king.setTile(bishop.getChessBoard().getBoard().get(new Coordinate(5, 5)));
		king.getTile().setPiece(king);
		
		assertTrue(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(6, 6)), true));
		
		bishop.setTile(bishop.getChessBoard().getBoard().get(new Coordinate(4, 6)));
		king.setTile(bishop.getChessBoard().getBoard().get(new Coordinate(2, 4)));
		king.getTile().setPiece(king);
		
		assertTrue(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(1, 3)), true));
		
		king.setTile(bishop.getChessBoard().getBoard().get(new Coordinate(6, 4)));
		king.getTile().setPiece(king);
		
		assertTrue(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(7, 3)), true));
	}
	
	@Test
	public void targetObstructed() {
		King king = new King(Colour.Black, 3);
		king.setTile(bishop.getChessBoard().getBoard().get(new Coordinate(3, 5)));
		king.getTile().setPiece(king);
		
		assertFalse(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(2, 6)), false));
		
		king.setTile(bishop.getChessBoard().getBoard().get(new Coordinate(5, 5)));
		king.getTile().setPiece(king);
		
		assertFalse(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(6, 6)), false));
		
		bishop.setTile(bishop.getChessBoard().getBoard().get(new Coordinate(4, 6)));
		king.setTile(bishop.getChessBoard().getBoard().get(new Coordinate(2, 4)));
		king.getTile().setPiece(king);
		
		assertFalse(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(1, 3)), false));
		
		king.setTile(bishop.getChessBoard().getBoard().get(new Coordinate(6, 4)));
		king.getTile().setPiece(king);
		
		assertFalse(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(7, 3)), false));
	}
	
	@Test
	public void targetHasEnemy() {
		assertTrue(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(1, 7)), false));
		assertTrue(bishop.canMove(bishop.getChessBoard().getBoard().get(new Coordinate(7, 7)), false));
	}
}
