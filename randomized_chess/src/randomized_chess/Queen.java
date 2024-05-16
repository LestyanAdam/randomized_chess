package randomized_chess;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Queen extends ChessPiece{
	
	private Icon icon;
	
	public Queen(Colour colour, int id) {
		super(colour, Type.Queen, id);
		
		//loading the icons of the chess pieces
		if(colour == Colour.White) {
			InputStream stream = Bishop.class.getResourceAsStream( "/resources/white-queen.png" );
			try {
				BufferedImage image = ImageIO.read(stream);
				icon = new ImageIcon(image);
				//icon = (Icon) image;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			InputStream stream = Bishop.class.getResourceAsStream( "/resources/black-queen.png" );
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
		
		//since the Queen combines the movesets of the Rook and the Bishop
		//we can just check if a rook or a bishop would be able to move to the target Tile
		
		ChessPiece tempRook = new Rook(this.getColour(), 0);
		tempRook.setTile(this.getTile());
		tempRook.setChessBoard(this.getChessBoard());
		if(tempRook.canMove(target, justChecking)) {
			return true;
		}
		
		ChessPiece tempBishop = new Bishop(this.getColour(), 0);
		tempBishop.setTile(this.getTile());
		tempBishop.setChessBoard(this.getChessBoard());
		if(tempBishop.canMove(target, justChecking)) {
			return true;
		}
		
		return false;
	}

	@Override
	public Icon getIcon() {
		return icon;
	}


}
