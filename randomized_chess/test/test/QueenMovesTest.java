package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import randomized_chess.ChessBoard;
import randomized_chess.Colour;
import randomized_chess.Coordinate;
import randomized_chess.King;
import randomized_chess.Player;
import randomized_chess.Queen;
import randomized_chess.Tile;

public class QueenMovesTest {

	private Queen queen;
	
	@Before
	public void ctor() {
		this.queen = new Queen(Colour.White, 3);
		queen.setChessBoard(new ChessBoard(new Player(Colour.White), new Player(Colour.Black), null));
		queen.setTile(queen.getChessBoard().getBoard().get(new Coordinate(4, 4)));
	}
	
	@Test
	public void invalidTarget() {
		assertFalse(queen.canMove(null, false));
		
		assertFalse(queen.canMove(new Tile(new Coordinate(0, 0), null), false));
		
		assertFalse(queen.canMove(new Tile(new Coordinate(9, 12), null), false));
	}
	
	@Test
	public void targetHasFriendly() {
		assertFalse(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(4, 2)), false));
	}
	
	@Test
	public void targetUnreachable() {
		assertFalse(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(7, 6)), false));
	}
	
	@Test
	public void emptyTarget() {
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(4, 3)), false));
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(1, 4)), false));
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(4, 6)), false));
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(8, 4)), false));
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(6, 6)), false));
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(2, 6)), false));
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(3, 3)), false));
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(5, 3)), false));
	}
	
	@Test
	public void skipsKingIfJustChecking() {
		
		King king = new King(Colour.Black, 3);
		king.setTile(queen.getChessBoard().getBoard().get(new Coordinate(7, 4)));
		king.getTile().setPiece(king);
		
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(8, 4)), true));
		
		king.setTile(queen.getChessBoard().getBoard().get(new Coordinate(4, 5)));
		king.getTile().setPiece(king);
		
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(4, 6)), true));
		
		king.setTile(queen.getChessBoard().getBoard().get(new Coordinate(2, 4)));
		king.getTile().setPiece(king);
		
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(1, 4)), true));
		
		queen.setTile(queen.getChessBoard().getBoard().get(new Coordinate(4, 6)));
		king.setTile(queen.getChessBoard().getBoard().get(new Coordinate(4, 4)));
		king.getTile().setPiece(king);
		
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(4, 3)), true));
		
		king.setTile(queen.getChessBoard().getBoard().get(new Coordinate(3, 5)));
		king.getTile().setPiece(king);
		
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(2, 6)), true));
		
		king.setTile(queen.getChessBoard().getBoard().get(new Coordinate(5, 5)));
		king.getTile().setPiece(king);
		
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(6, 6)), true));
		
		queen.setTile(queen.getChessBoard().getBoard().get(new Coordinate(4, 6)));
		king.setTile(queen.getChessBoard().getBoard().get(new Coordinate(2, 4)));
		king.getTile().setPiece(king);
		
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(1, 3)), true));
		
		king.setTile(queen.getChessBoard().getBoard().get(new Coordinate(6, 4)));
		king.getTile().setPiece(king);
		
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(7, 3)), true));
	}
	
	@Test
	public void targetObstructed() {
		King king = new King(Colour.Black, 3);
		king.setTile(queen.getChessBoard().getBoard().get(new Coordinate(7, 4)));
		king.getTile().setPiece(king);
		
		assertFalse(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(8, 4)), false));
		
		king.setTile(queen.getChessBoard().getBoard().get(new Coordinate(4, 5)));
		king.getTile().setPiece(king);
		
		assertFalse(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(4, 6)), false));
		
		king.setTile(queen.getChessBoard().getBoard().get(new Coordinate(2, 4)));
		king.getTile().setPiece(king);
		
		assertFalse(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(1, 4)), false));
		
		queen.setTile(queen.getChessBoard().getBoard().get(new Coordinate(4, 6)));
		king.setTile(queen.getChessBoard().getBoard().get(new Coordinate(4, 4)));
		king.getTile().setPiece(king);
		
		assertFalse(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(4, 3)), false));
		
		king.setTile(queen.getChessBoard().getBoard().get(new Coordinate(3, 5)));
		king.getTile().setPiece(king);
		
		assertFalse(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(2, 4)), false));
		
		king.setTile(queen.getChessBoard().getBoard().get(new Coordinate(5, 5)));
		king.getTile().setPiece(king);
		
		assertFalse(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(7, 3)), false));
		
		queen.setTile(queen.getChessBoard().getBoard().get(new Coordinate(4, 3)));
		king.setTile(queen.getChessBoard().getBoard().get(new Coordinate(2, 5)));
		king.getTile().setPiece(king);
		
		assertFalse(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(1, 6)), false));
		
		king.setTile(queen.getChessBoard().getBoard().get(new Coordinate(6, 5)));
		king.getTile().setPiece(king);
		
		assertFalse(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(7, 6)), false));
	}
	
	@Test
	public void targetHasEnemy() {
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(4, 7)), false));
		
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(1, 7)), false));
		assertTrue(queen.canMove(queen.getChessBoard().getBoard().get(new Coordinate(7, 7)), false));
	}
}
