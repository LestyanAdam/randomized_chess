package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import randomized_chess.ChessBoard;
import randomized_chess.Colour;
import randomized_chess.Id;
import randomized_chess.Player;
import randomized_chess.Type;

public class CanMoveAnywhereTest {

	private ChessBoard board;
	
	@Before
	public void ctor() {
		this.board = new ChessBoard(new Player(Colour.White), new Player(Colour.Black), null);
	}
	
	@Test
	public void cannotMove() {
		assertFalse(board.getPlayerWhite().getChessPieces().get(new Id(Colour.White, Type.King, 1)).canMoveAnywhere());
		assertFalse(board.getPlayerWhite().getChessPieces().get(new Id(Colour.White, Type.Queen, 1)).canMoveAnywhere());
		assertFalse(board.getPlayerWhite().getChessPieces().get(new Id(Colour.White, Type.Rook, 1)).canMoveAnywhere());
		assertFalse(board.getPlayerWhite().getChessPieces().get(new Id(Colour.White, Type.Bishop, 1)).canMoveAnywhere());
		
		assertFalse(board.getPlayerBlack().getChessPieces().get(new Id(Colour.Black, Type.King, 1)).canMoveAnywhere());
		assertFalse(board.getPlayerBlack().getChessPieces().get(new Id(Colour.Black, Type.Queen, 1)).canMoveAnywhere());
		assertFalse(board.getPlayerBlack().getChessPieces().get(new Id(Colour.Black, Type.Rook, 1)).canMoveAnywhere());
		assertFalse(board.getPlayerBlack().getChessPieces().get(new Id(Colour.Black, Type.Bishop, 1)).canMoveAnywhere());
	}
	
	@Test
	public void canMove() {
		assertTrue(board.getPlayerWhite().getChessPieces().get(new Id(Colour.White, Type.Knight, 1)).canMoveAnywhere());
		assertTrue(board.getPlayerBlack().getChessPieces().get(new Id(Colour.Black, Type.Knight, 1)).canMoveAnywhere());
		
		assertTrue(board.getPlayerWhite().getChessPieces().get(new Id(Colour.White, Type.Pawn, 1)).canMoveAnywhere());
		assertTrue(board.getPlayerBlack().getChessPieces().get(new Id(Colour.Black, Type.Pawn, 1)).canMoveAnywhere());
	}
}
