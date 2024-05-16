package randomized_chess;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Pawn extends ChessPiece{
	
	private Icon icon;

	public Pawn(Colour colour, int id) {
		super(colour, Type.Pawn, id);
		
		//loading the icons of the chess pieces
		if(colour == Colour.White) {
			InputStream stream = Bishop.class.getResourceAsStream( "/resources/white-pawn.png" );
			try {
				BufferedImage image = ImageIO.read(stream);
				icon = new ImageIcon(image);
				//icon = (Icon) image;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			InputStream stream = Bishop.class.getResourceAsStream( "/resources/black-pawn.png" );
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
		
		if(this.getColour() == Colour.White) {
			if(target.getPiece() == null && !justChecking){//checks if the target Tile is occupied
				
				//checks if the target is in the same column and two Tiles ahead
				if(target.getCoordinateY() == this.getCoordinateY() + 2 && this.getCoordinateX() == target.getCoordinateX()) {
					
					//a Pawn can only move two Tiles ahead if it hasn't moved since the start and there isn't an enemy piece on the next Tile ahead
					if(this.getCoordinateY() == 2 && target.getChessBoard().board.get(new Coordinate(this.getCoordinateX(), this.getCoordinateY() + 1)).getPiece() == null) {
						return true;
					}
				}else if(target.getCoordinateY() == this.getCoordinateY() + 1 && this.getCoordinateX() == target.getCoordinateX()) {
					return true;
				}
			}else if(target.getCoordinateY() == this.getCoordinateY() + 1){ //checks if target is in the next row
				
				//checks if the target is diagonally ahead
				if(target.getCoordinateX() == this.getCoordinateX() + 1 | target.getCoordinateX() == this.getCoordinateX() - 1) { 
					return true;
				}
			}
			
			return false;
		}else {
			if(target.getPiece() == null && !justChecking){//checks if the target Tile is occupied
				
				//checks if the target is in the same column and two Tiles ahead
				if(target.getCoordinateY() == this.getCoordinateY() - 2 && this.getCoordinateX() == target.getCoordinateX()) {
					
					//a Pawn can only move two Tiles ahead if it hasn't moved since the start and there isn't an enemy piece on the next Tile ahead
					if(this.getCoordinateY() == 7 && target.getChessBoard().board.get(new Coordinate(this.getCoordinateX(), this.getCoordinateY() - 1)).getPiece() == null) {
						return true;
					}
				}else if(target.getCoordinateY() == this.getCoordinateY() - 1 && this.getCoordinateX() == target.getCoordinateX()) {
					return true;
				}
			}else if(target.getCoordinateY() == this.getCoordinateY() - 1){ //checks if target is in the next row
				
				//checks if the target is diagonally ahead
				if(target.getCoordinateX() == this.getCoordinateX() + 1 | target.getCoordinateX() == this.getCoordinateX() - 1) { 
					return true;
				}
			}
			
			return false;
		}
	}

	@Override
	public Icon getIcon() {
		return icon;
	}

}
