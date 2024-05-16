package randomized_chess;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Knight extends ChessPiece{

	private Icon icon;
	
	public Knight(Colour colour, int id) {
		super(colour, Type.Knight, id);
		
		//loading the icons of the chess pieces
		if(colour == Colour.White) {
			InputStream stream = Bishop.class.getResourceAsStream( "/resources/white-knight.png" );
			try {
				BufferedImage image = ImageIO.read(stream);
				icon = new ImageIcon(image);
				//icon = (Icon) image;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			InputStream stream = Bishop.class.getResourceAsStream( "/resources/black-knight.png" );
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
		
		//this part of the code checks if the knight can move to the target Tile
		//the knight moves in an L shape
		if(target.getCoordinateX() == this.getCoordinateX() + 1) {
			
			if(target.getCoordinateY() == this.getCoordinateY() + 2) {
				return true;
				
			}else if(target.getCoordinateY() == this.getCoordinateY() - 2) {
				return true;
			}
			
		}else if(target.getCoordinateX() == this.getCoordinateX() + 2) {
			
			if(target.getCoordinateY() == this.getCoordinateY() + 1) {
				return true;
				
			}else if(target.getCoordinateY() == this.getCoordinateY() - 1) {
				return true;
			}
			
		}else if(target.getCoordinateX() == this.getCoordinateX() - 1) {
			
			if(target.getCoordinateY() == this.getCoordinateY() + 2) {
				return true;
				
			}else if(target.getCoordinateY() == this.getCoordinateY() - 2) {
				return true;
			}
			
		}else if(target.getCoordinateX() == this.getCoordinateX() - 2) {
			
			if(target.getCoordinateY() == this.getCoordinateY() + 1) {
				return true;
				
			}else if(target.getCoordinateY() == this.getCoordinateY() - 1) {
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
