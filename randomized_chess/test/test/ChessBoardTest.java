package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import randomized_chess.ChessBoard;
import randomized_chess.ChessPiece;
import randomized_chess.Colour;
import randomized_chess.Coordinate;
import randomized_chess.Player;
import randomized_chess.Type;

public class ChessBoardTest {

	private ChessBoard board;
	
	@Before
	public void ctor() {
		this.board = new ChessBoard(new Player(Colour.White), new Player(Colour.Black), null);
	}
	
	@Test
	public void hasAllTiles() {
		
		for(int i = 1; i <= 8; i++) {
			for(int j = 1; j <= 8; j++) {
				assertNotNull(board.getBoard().get(new Coordinate(j, i)));				
			}
		}
	}
	
	@Test
	public void placedChessPieces() throws Exception {
		
		//the pawns should always be in the 2. and 7. rows
		for(int i = 1; i <= 8; i++) {
			ChessPiece whitePawns = board.getBoard().get(new Coordinate(i, 2)).getPiece();
			ChessPiece blackPawns = board.getBoard().get(new Coordinate(i, 7)).getPiece();
			
			assertEquals(whitePawns.getColour(), Colour.White);
			assertEquals(blackPawns.getColour(), Colour.Black);
			
			assertEquals(whitePawns.getType(), Type.Pawn);
			assertEquals(blackPawns.getType(), Type.Pawn);
		}
		
		//there should be no chess pieces in the 3. to 6. rows
		for(int i = 1; i <= 8; i++) {
			for(int j = 3; j <= 6; j++) {
				assertNull(board.getBoard().get(new Coordinate(i, j)).getPiece());
			}
		}
		
		//in the 1. and 8. rows there should be exactly one King, one Queen, two Rooks, two Bishops and two Knights
		int kingCounter = 0;
		int queenCounter = 0;
		int bishopCounter = 0;
		int rookCounter = 0;
		int knightCounter = 0;
		
		for(int i = 1; i <= 8; i++) {
			Type type = board.getBoard().get(new Coordinate(i, 1)).getPiece().getType();
			
			switch(type) {
				case Bishop:
					bishopCounter++;
					break;
				case King:
					kingCounter++;
					break;
				case Knight:
					knightCounter++;
					break;
				case Queen:
					queenCounter++;
					break;
				case Rook:
					rookCounter++;
					break;
				default:
					throw new Exception("Unexpected Type in the 1. row!");
			}
		}
		
		assertEquals(kingCounter, 1);
		assertEquals(queenCounter, 1);
		assertEquals(bishopCounter, 2);
		assertEquals(rookCounter, 2);
		assertEquals(knightCounter, 2);
		
		//we reset the counter
		kingCounter = 0;
		queenCounter = 0;
		bishopCounter = 0;
		rookCounter = 0;
		knightCounter = 0;
		
		for(int i = 1; i <= 8; i++) {
			Type type = board.getBoard().get(new Coordinate(i, 8)).getPiece().getType();
			
			switch(type) {
				case Bishop:
					bishopCounter++;
					break;
				case King:
					kingCounter++;
					break;
				case Knight:
					knightCounter++;
					break;
				case Queen:
					queenCounter++;
					break;
				case Rook:
					rookCounter++;
					break;
				default:
					throw new Exception("Unexpected Type in the 8. row!");
			}
		}
		
		assertEquals(kingCounter, 1);
		assertEquals(queenCounter, 1);
		assertEquals(bishopCounter, 2);
		assertEquals(rookCounter, 2);
		assertEquals(knightCounter, 2);
	}

}
