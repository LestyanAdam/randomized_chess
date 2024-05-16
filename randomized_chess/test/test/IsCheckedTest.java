package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import randomized_chess.ChessBoard;
import randomized_chess.Colour;
import randomized_chess.Coordinate;
import randomized_chess.Player;

public class IsCheckedTest {

	private ChessBoard board;
	
	@Before
	public void ctor() {
		this.board = new ChessBoard(new Player(Colour.White), new Player(Colour.Black), null);
	}
	
	@Test
	public void isChecked() {
		for( int i = 1; i <= 8; i++) {
			assertTrue(board.getBoard().get(new Coordinate(i, 6)).isChecked(Colour.White));
			assertTrue(board.getBoard().get(new Coordinate(i, 3)).isChecked(Colour.Black));
		}
	}
	
	@Test
	public void notChecked() {
		for(int i = 1; i <= 8; i++) {
			assertFalse(board.getBoard().get(new Coordinate(i, 3)).isChecked(Colour.White));
			assertFalse(board.getBoard().get(new Coordinate(i, 6)).isChecked(Colour.Black));
			
			assertFalse(board.getBoard().get(new Coordinate(i, 4)).isChecked(Colour.White));
			assertFalse(board.getBoard().get(new Coordinate(i, 4)).isChecked(Colour.Black));
		}
	}

}
