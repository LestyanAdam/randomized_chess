package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import randomized_chess.ChessBoard;
import randomized_chess.Colour;
import randomized_chess.Coordinate;
import randomized_chess.Knight;
import randomized_chess.Player;
import randomized_chess.Tile;

public class KnightMovesTest {

	private Knight knight;
	
	@Before
	public void ctor() {
		this.knight = new Knight(Colour.White, 3);
		knight.setChessBoard(new ChessBoard(new Player(Colour.White), new Player(Colour.Black), null));
		knight.setTile(knight.getChessBoard().getBoard().get(new Coordinate(4, 4)));
	}
	
	@Test
	public void invalidTarget() {
		assertFalse(knight.canMove(null, false));
		
		assertFalse(knight.canMove(new Tile(new Coordinate(0, 0), null), false));
		
		assertFalse(knight.canMove(new Tile(new Coordinate(9, 12), null), false));
	}
	
	@Test
	public void targetHasFriendly() {
		assertFalse(knight.canMove(knight.getChessBoard().getBoard().get(new Coordinate(3, 2)), false));
		assertFalse(knight.canMove(knight.getChessBoard().getBoard().get(new Coordinate(5, 2)), false));
	}
	
	@Test
	public void targetOutofReach() {
		assertFalse(knight.canMove(knight.getChessBoard().getBoard().get(new Coordinate(8, 8)), false));
		assertFalse(knight.canMove(knight.getChessBoard().getBoard().get(new Coordinate(3, 4)), false));
	}
	
	@Test
	public void emptyTarget() {
		assertTrue(knight.canMove(knight.getChessBoard().getBoard().get(new Coordinate(2, 3)), false));
		assertTrue(knight.canMove(knight.getChessBoard().getBoard().get(new Coordinate(5, 6)), false));
		assertTrue(knight.canMove(knight.getChessBoard().getBoard().get(new Coordinate(6, 5)), false));
		assertTrue(knight.canMove(knight.getChessBoard().getBoard().get(new Coordinate(6, 3)), false));
	}
	
	@Test
	public void targetHasEnemy() {
		knight.setTile(knight.getChessBoard().getBoard().get(new Coordinate(4, 6)));
		assertTrue(knight.canMove(knight.getChessBoard().getBoard().get(new Coordinate(3, 8)), false));
		assertTrue(knight.canMove(knight.getChessBoard().getBoard().get(new Coordinate(2, 7)), false));
		assertTrue(knight.canMove(knight.getChessBoard().getBoard().get(new Coordinate(5, 4)), false));
		assertTrue(knight.canMove(knight.getChessBoard().getBoard().get(new Coordinate(3, 4)), false));
	}

}
