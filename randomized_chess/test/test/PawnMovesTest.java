package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import randomized_chess.ChessBoard;
import randomized_chess.Colour;
import randomized_chess.Coordinate;
import randomized_chess.Id;
import randomized_chess.Pawn;
import randomized_chess.Player;
import randomized_chess.Tile;
import randomized_chess.Type;

public class PawnMovesTest {

	private Pawn pawn;
	
	@Before
	public void ctor() {
		this.pawn = new Pawn(Colour.White, 9);
		pawn.setChessBoard(new ChessBoard(new Player(Colour.White), new Player(Colour.Black), null));
		pawn.setTile(pawn.getChessBoard().getBoard().get(new Coordinate(4, 4)));
	}
	
	@Test
	public void invalidTarget() {
		assertFalse(pawn.canMove(null, false));
		
		assertFalse(pawn.canMove(new Tile(new Coordinate(0, 0), null), false));
		
		assertFalse(pawn.canMove(new Tile(new Coordinate(9, 12), null), false));
	}
	
	@Test
	public void targetOutofReach() {
		assertFalse(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(4, 6)), false));
		assertFalse(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(3, 5)), false));
	}
	
	@Test
	public void targetHasFriendly() {
		Pawn p = new Pawn(Colour.White, 10);
		p.setTile(pawn.getChessBoard().getBoard().get(new Coordinate(4, 5)));
		p.getTile().setPiece(p);
		
		assertFalse(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(4, 5)), false));
	}
	
	@Test
	public void whiteStart() {
		pawn = (Pawn) pawn.getChessBoard().getPlayerWhite().getChessPieces().get(new Id(Colour.White, Type.Pawn, 2));
		
		assertTrue(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(2, 3)), false));
		assertTrue(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(2, 4)), false));
		
		assertTrue(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(1, 3)), true));
		assertTrue(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(3, 3)), true));
		
		assertFalse(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(1, 3)), false));
		assertFalse(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(3, 3)), false));
		
		assertFalse(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(2, 3)), true));
		assertFalse(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(2, 4)), true));
	}
	
	@Test
	public void blackStart() {
		pawn = (Pawn) pawn.getChessBoard().getPlayerBlack().getChessPieces().get(new Id(Colour.Black, Type.Pawn, 2));
		
		assertTrue(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(2, 6)), false));
		assertTrue(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(2, 5)), false));
		
		assertTrue(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(1, 6)), true));
		assertTrue(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(3, 6)), true));
		
		assertFalse(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(3, 6)), false));
		assertFalse(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(1, 6)), false));
		
		assertFalse(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(2, 6)), true));
		assertFalse(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(2, 5)), true));
	}
	
	@Test
	public void whiteAttack() {
		pawn.setTile(pawn.getChessBoard().getBoard().get(new Coordinate(4, 6)));
		
		assertTrue(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(3, 7)), false));
		assertTrue(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(5, 7)), false));
		
		assertFalse(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(4, 7)), false));
		assertFalse(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(4, 8)), false));
	}
	
	@Test
	public void blackAttack() {
		pawn.setColour(Colour.Black);
		pawn.setTile(pawn.getChessBoard().getBoard().get(new Coordinate(4, 3)));
		
		assertTrue(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(3, 2)), false));
		assertTrue(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(5, 2)), false));
		
		assertFalse(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(4, 1)), false));
		assertFalse(pawn.canMove(pawn.getChessBoard().getBoard().get(new Coordinate(4, 2)), false));
	}
}
