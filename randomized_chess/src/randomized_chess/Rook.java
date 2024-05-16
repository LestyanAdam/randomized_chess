package randomized_chess;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Rook extends ChessPiece{
	
	private Icon icon;

	public Rook(Colour colour, int id){
		super(colour, Type.Rook, id);
		
		//loading the icons of the chess pieces
		if(colour == Colour.White) {
			InputStream stream = Bishop.class.getResourceAsStream( "/resources/white-rook.png" );
			try {
				BufferedImage image = ImageIO.read(stream);
				icon = new ImageIcon(image);
				//icon = (Icon) image;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			InputStream stream = Bishop.class.getResourceAsStream( "/resources/black-rook.png" );
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
		
		
		if(target.getCoordinateX() == this.getCoordinateX()) { //checks if the target is in the same column
			
			//checks if the target is to the north
			if(target.getCoordinateY() > this.getCoordinateY()) {
				
				//checks if the Tiles between the target and our piece is occupied
				for(int i = 1; i < target.getCoordinateY() - this.getCoordinateY(); i++) {

					if(this.getChessBoard().board.get(new Coordinate(this.getCoordinateX(), this.getCoordinateY() + i)).getPiece() != null) {
						
						//if the canMove method was called in the isChecked method than the current position of the king doesn't matter since it is going to move
						if(justChecking && this.getChessBoard().board.get(new Coordinate(this.getCoordinateX(), this.getCoordinateY() + i)).getPiece().getType() == Type.King) {
							continue;
						}
						
						return false;
					}
				}
		
				//if there are no pieces between the target and our piece
				return true;
				 
			}else if(target.getCoordinateY() < this.getCoordinateY()) { //checks if the target is to the south
				
				//checks if the Tiles between the target and our piece is occupied
				for(int i = 1; i <  this.getCoordinateY() - target.getCoordinateY(); i++) {
					
					if(this.getChessBoard().board.get(new Coordinate(this.getCoordinateX(), this.getCoordinateY() - i)).getPiece() != null) {
						
						//if the canMove method was called in the isChecked method than the current position of the king doesn't matter since it is going to move
						if(justChecking && this.getChessBoard().board.get(new Coordinate(this.getCoordinateX(), this.getCoordinateY() - i)).getPiece().getType() == Type.King) {
							continue;
						}
						
						return false;
					}
				}
				
				//if there are no pieces between the target and our piece
				return true;
			}
		}else if(target.getCoordinateY() == this.getCoordinateY()) { //checks if the target is in the same row
			
			//checks if the target is to the east
			if(target.getCoordinateX() > this.getCoordinateX()) {
				
				//checks if the Tiles between the target and our piece is occupied
				for(int i = 1; i <  target.getCoordinateX() - this.getCoordinateX(); i++) {
					
					if(this.getChessBoard().board.get(new Coordinate(this.getCoordinateX() + i, this.getCoordinateY())).getPiece() != null) {
						
						//if the canMove method was called in the isChecked method than the current position of the king doesn't matter since it is going to move
						if(justChecking && this.getChessBoard().board.get(new Coordinate(this.getCoordinateX() + i, this.getCoordinateY())).getPiece().getType() == Type.King) {
							continue;
							
						}
						return false;
					}
				}
				
				//if there are no pieces between the target and our piece
				return true;
				
			}else if(target.getCoordinateX() < this.getCoordinateX()) { //checks if the target is to the west
				
				//checks if the Tiles between the target and our piece is occupied
				for(int i = 1; i <  this.getCoordinateX() - target.getCoordinateX(); i++) {
				
					if(this.getChessBoard().board.get(new Coordinate(this.getCoordinateX() - i, this.getCoordinateY())).getPiece() != null) {
						
						//if the canMove method was called in the isChecked method than the current position of the king doesn't matter since it is going to move
						if(justChecking && this.getChessBoard().board.get(new Coordinate(this.getCoordinateX() - i, this.getCoordinateY())).getPiece().getType() == Type.King) {
							continue;
						}
						
						return false;
					}
				}
				
				//if there are no pieces between the target and our piece
				return true;
			}
		}
		return false;
	}

	@Override
	public Icon getIcon() {
		return icon;
	}

}
