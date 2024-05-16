package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import randomized_chess.ChessBoard;
import randomized_chess.Colour;
import randomized_chess.Coordinate;
import randomized_chess.King;
import randomized_chess.Pawn;
import randomized_chess.Player;
import randomized_chess.Tile;

public class KingMovesTest {

	private King king;
	
	@Before
	public void ctor() {
		king = new King(Colour.White, 3);
		king.setChessBoard(new ChessBoard(new Player(Colour.White), new Player(Colour.Black), null));
		king.setTile(king.getChessBoard().getBoard().get(new Coordinate(4, 4)));
	}

	@Test
	public void invalidTarget() {
		assertFalse(king.canMove(null, false));
		
		assertFalse(king.canMove(new Tile(new Coordinate(0, 0), null), false));
		
		assertFalse(king.canMove(new Tile(new Coordinate(9, 12), null), false));
	}
	
	@Test
	public void targetHasFriendly() {
		king.setTile(king.getChessBoard().getBoard().get(new Coordinate(4, 3)));
		
		assertFalse(king.canMove(king.getChessBoard().getBoard().get(new Coordinate(3, 2)), false));
	}
	
	@Test
	public void targetOutofReach() {
		assertFalse(king.canMove(king.getChessBoard().getBoard().get(new Coordinate(6, 4)), false));
	}
	
	@Test
	public void targetIsChecked() {
		king.setTile(king.getChessBoard().getBoard().get(new Coordinate(4, 5)));
		
		assertFalse(king.canMove(king.getChessBoard().getBoard().get(new Coordinate(5, 6)), false));
		assertFalse(king.canMove(king.getChessBoard().getBoard().get(new Coordinate(4, 6)), false));
		
		assertTrue(king.canMove(king.getChessBoard().getBoard().get(new Coordinate(5, 6)), true));
	}
	
	@Test
	public void goodTarget() {
		assertTrue(king.canMove(king.getChessBoard().getBoard().get(new Coordinate(3, 3)), false));
		assertTrue(king.canMove(king.getChessBoard().getBoard().get(new Coordinate(4, 3)), false));
		assertTrue(king.canMove(king.getChessBoard().getBoard().get(new Coordinate(5, 3)), false));
		assertTrue(king.canMove(king.getChessBoard().getBoard().get(new Coordinate(3, 4)), false));
		assertTrue(king.canMove(king.getChessBoard().getBoard().get(new Coordinate(5, 4)), false));
		assertTrue(king.canMove(king.getChessBoard().getBoard().get(new Coordinate(3, 5)), false));
		assertTrue(king.canMove(king.getChessBoard().getBoard().get(new Coordinate(4, 5)), false));
		assertTrue(king.canMove(king.getChessBoard().getBoard().get(new Coordinate(5, 5)), false));
	}
	
	@Test
	public void targetHasEnemy() {
		Pawn p = new Pawn(Colour.Black, 10);
		p.setTile(king.getChessBoard().getBoard().get(new Coordinate(4, 5)));
		p.getTile().setPiece(p);
		
		assertTrue(king.canMove(king.getChessBoard().getBoard().get(new Coordinate(4, 5)), false));
	}
}
