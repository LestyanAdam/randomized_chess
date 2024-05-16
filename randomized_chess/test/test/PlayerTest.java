package test;

import randomized_chess.Colour;
import randomized_chess.Type;
import randomized_chess.Id;
import randomized_chess.Player;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	private Player testPlayer;
	
	@Before
	public void ctor() {
		this.testPlayer = new Player(Colour.White);
	}
	
	@Test
	public void hasPawns() {
		for(int i = 1; i <= 8; i++) {
			assertNotNull(testPlayer.getChessPieces().get(new Id(testPlayer.getColor(), Type.Pawn, i)));
		}
	}
	
	@Test
	public void hasRooks() {
		for(int i = 1; i <= 2; i++) {
			assertNotNull(testPlayer.getChessPieces().get(new Id(testPlayer.getColor(), Type.Rook, i)));
		}
	}
	
	@Test
	public void hasBishops() {
		for(int i = 1; i <= 2; i++) {
			assertNotNull(testPlayer.getChessPieces().get(new Id(testPlayer.getColor(), Type.Bishop, i)));
		}
	}	
	
	@Test
	public void hasKnights() {
		for(int i = 1; i <= 2; i++) {
			assertNotNull(testPlayer.getChessPieces().get(new Id(testPlayer.getColor(), Type.Knight, i)));
		}
	}	
	
	@Test
	public void hasKing() {	
		assertNotNull(testPlayer.getChessPieces().get(new Id(testPlayer.getColor(), Type.King, 1)));
	}

	@Test
	public void hasQueen() {
		assertNotNull(testPlayer.getChessPieces().get(new Id(testPlayer.getColor(), Type.Queen, 1)));
	}
}
