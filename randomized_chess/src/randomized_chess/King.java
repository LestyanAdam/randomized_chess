package randomized_chess;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class King extends ChessPiece{
	
	private Icon icon;
	
	public King(Colour colour, int id){
		super(colour, Type.King, id);
		
		//loading the icons of the chess pieces
		if(colour == Colour.White) {
			InputStream stream = Bishop.class.getResourceAsStream( "/resources/white-king.png" );
			try {
				BufferedImage image = ImageIO.read(stream);
				icon = new ImageIcon(image);
				//icon = (Icon) image;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			InputStream stream = Bishop.class.getResourceAsStream( "/resources/black-king.png" );
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
	public boolean canMove(Tile target, boolean justChecking){
		
		//checks if the target Tile is valid
		if(target == null || !target.isValid()) { 
			return false;
		}
		
		//a piece can't capture a piece that is the same color 
		if(target.getPieceColour() == this.getColour() && !justChecking) {
			return false;
		}
		
		//a king can't move to a Tile that is being attacked by an enemy piece
		if(!justChecking && target.isChecked(this.getColour())) {
			return false;
		}
		
		//this part of the code checks if the target is around the king
		//if it is then the king is free to move there
		if(target.getCoordinateX() == this.getCoordinateX()) {
			
			if(target.getCoordinateY() == this.getCoordinateY() + 1) {
				return true;
				
			}else if(target.getCoordinateY() == this.getCoordinateY() - 1) {
				return true;
			}
			
		}else if(target.getCoordinateY() == this.getCoordinateY()) {
			
			if(target.getCoordinateX() == this.getCoordinateX() + 1) {
				return true;
				
			}else if(target.getCoordinateX() == this.getCoordinateX() - 1) {
				return true;
			}
			
		}else if(target.getCoordinateX() == this.getCoordinateX() + 1) {
			
			if(target.getCoordinateY() == this.getCoordinateY() + 1) {
				return true;
				
			}else if(target.getCoordinateY() == this.getCoordinateY() - 1) {
				return true;
			}
			
		}else if(target.getCoordinateX() == this.getCoordinateX() - 1) {
			
			if(target.getCoordinateY() == this.getCoordinateY() + 1) {
				return true;
				
			}else if(target.getCoordinateY() == this.getCoordinateY() - 1) {
				return true;
			}
		}
		
		/*
		On castling:
		this version of randomized chess implements the transcendental chess version
		where the pieces on the back rows are fully randomized and thus castling is not permitted
		*/
		
		return false;
	}

	@Override
	public Icon getIcon() {
		return icon;
	}
}
