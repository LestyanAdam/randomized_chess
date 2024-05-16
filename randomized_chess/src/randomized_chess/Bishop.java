package randomized_chess;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Bishop extends ChessPiece{
	
	private Icon icon;

	public Bishop(Colour colour, int id){
		super(colour, Type.Bishop, id);
		
		//loading the icons of the chess pieces
		if(colour == Colour.White) {
			InputStream stream = Bishop.class.getResourceAsStream( "/resources/white-bishop.png" );
			try {
				BufferedImage image = ImageIO.read(stream);
				icon = new ImageIcon(image);
				//icon = (Icon) image;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			InputStream stream = Bishop.class.getResourceAsStream( "/resources/black-bishop.png" );
			try {
				BufferedImage image = ImageIO.read(stream);
				icon = new ImageIcon(image);
				//icon = (Icon) image;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean canMove(Tile target, boolean justChecking) {
		
		//checks if the target Tile is valid
		if(target == null || !target.isValid()) {
			return false;
		}
		
		//a piece can't capture a piece that is the same color 
		if(target.getPieceColour() == this.getColour() && !justChecking) {
			return false;
		}
		
		//first we check if it's diagonal from the start Tile
		//if it isn't then the target is invalid
		if(!(Math.abs(this.getCoordinateX() - target.getCoordinateX()) == Math.abs(this.getCoordinateY() - target.getCoordinateY()))) {
			return false;
		}
		
		//we divide the chess board into different areas and check if the target Tile is along a diagonal line from the starting Tile
		//we don't check the same row or column that our piece is in since a bishop can't move horizontally or vertically
		
		//first we check if the target is up and to the right from our starting Tile
		//if it is we go along the diagonal line from the starting Tile
		if(target.getCoordinateX() > this.getCoordinateX() & target.getCoordinateY() > this.getCoordinateY()) {
			
			//we check the diagonal line between our piece and the target
			//since a bishop can't jump over pieces, if there is a chess piece between our piece and the target the bishop can't move there
			for(int i = 1; i < target.getCoordinateX() - this.getCoordinateX(); i++) {
				
				if(this.getChessBoard().board.get(new Coordinate(this.getCoordinateX() + i, this.getCoordinateY() + i)).getPiece() != null) {
					
					if(justChecking && this.getChessBoard().board.get(new Coordinate(this.getCoordinateX() + i, this.getCoordinateY() + i)).getPiece().getType() == Type.King) {
						continue;
					}
					
					return false;
				}
			}
			
			//if there were no pieces between the bishop and the target, the bishop can move to the target
			return true;
			
		}//now we check if the target is down and to the right from the starting Tile
		else if(target.getCoordinateX() > this.getCoordinateX() & target.getCoordinateY() < this.getCoordinateY()) {
			
			//we check the diagonal line between our piece and the target
			//since a bishop can't jump over pieces, if there is a chess piece between our piece and the target the bishop can't move there
			for(int i = 1; i < target.getCoordinateX() - this.getCoordinateX(); i++) {
				
				if(this.getChessBoard().board.get(new Coordinate(this.getCoordinateX() + i, this.getCoordinateY() - i)).getPiece() != null) {
					
					if(justChecking && this.getChessBoard().board.get(new Coordinate(this.getCoordinateX() + i, this.getCoordinateY() - i)).getPiece().getType() == Type.King) {
						continue;
					}
					
					return false;
				}
			}
			
			
			//if there were no pieces between the bishop and the target, the bishop can move to the target
			return true;
			
		}//now we check if the target is up and to the left from the starting Tile
		else if(target.getCoordinateX() < this.getCoordinateX() & target.getCoordinateY() > this.getCoordinateY()) {
			//now we check if the target is  and to the left from the starting Tile
			
			//we check the diagonal line between our piece and the target
			//since a bishop can't jump over pieces, if there is a chess piece between our piece and the target the bishop can't move there
			for(int i = 1; i < this.getCoordinateX() - target.getCoordinateX(); i++) {
				
				if(this.getChessBoard().board.get(new Coordinate(this.getCoordinateX() - i, this.getCoordinateY() + i)).getPiece() != null) {
					
					if(justChecking && this.getChessBoard().board.get(new Coordinate(this.getCoordinateX() - i, this.getCoordinateY() + i)).getPiece().getType() == Type.King) {
						continue;
					}
					
					return false;
				}
			}
			
			//if there were no pieces between the bishop and the target, the bishop can move to the target
			return true;
			
		}//now we check if the target is down and to the left from the starting Tile
		else if(target.getCoordinateX() < this.getCoordinateX() & target.getCoordinateY() < this.getCoordinateY()) {

			//we check the diagonal line between our piece and the target
			//since a bishop can't jump over pieces, if there is a chess piece between our piece and the target the bishop can't move there
			for(int i = 1; i < this.getCoordinateX() - target.getCoordinateX(); i++) {
				
				if(this.getChessBoard().board.get(new Coordinate(this.getCoordinateX() - i, this.getCoordinateY() - i)).getPiece() != null) {
					
					if(justChecking && this.getChessBoard().board.get(new Coordinate(this.getCoordinateX() - i, this.getCoordinateY() - i)).getPiece().getType() == Type.King) {
						continue;
					}
					
					return false;
				}
			}
			
			//if there were no pieces between the bishop and the target, the bishop can move to the target
			return true;
		}
		
		return false;
	}

	@Override
	public Icon getIcon() {
		return icon;
	}

}
