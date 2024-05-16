package randomized_chess;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class TileButton extends JButton{
	private static final long serialVersionUID = 1L; //I just did this to get rid of the warning, it has no real use for us
	
	private GameWindow window;
	
	private Tile tile;
	
	public TileButton(Color color, GameWindow window, Tile tile) {
		super();
		
		this.tile = tile;
		
		if(tile.getPiece() != null) {
			this.setIcon(tile.getPiece().getIcon());
		}
		
		this.window = window;
		
		this.setBackground(color);
		
		this.setBorder(new LineBorder(Color.red, 3));
		
		this.setBorderPainted(false);
		
		this.addActionListener(new ButtonActionListener(this));

	}
	
	public GameWindow getWindow() {
		return window;
	}

	public Tile getTile() {
		return tile;
	}

	private class ButtonActionListener implements ActionListener{
		
		TileButton button;
		
		public ButtonActionListener(TileButton button) {
			this.button = button;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			Player player = null;
			Player otherPlayer = null;
			
			if(button.window.getPlayerTurn() == Colour.White) {
				
				player = button.window.getBoard().getPlayerWhite();
				otherPlayer = button.window.getBoard().getPlayerBlack();
				
			}else if(button.window.getPlayerTurn() == Colour.Black) {
				
				player = button.window.getBoard().getPlayerBlack();
				otherPlayer = button.window.getBoard().getPlayerWhite();
			}
			
			//we check if there is a button already selected 
			if(button.window.getSelectedButton() != null) {
				
				//we check if the clicked button isn't the already selected button and if the selected Tile's chess piece can move to the clicked button
				//if it can move there, then we move the piece
				//if there was an enemy piece on the clicked Tile then it becomes captured and is removed from the game
				if(!button.isSelected() && button.window.getSelectedButton().tile.getPiece().canMove(button.tile, false)) {
					
					if(button.tile.getPiece() != null) {
						
						//we remove the enemy piece from the target Tile, if there is one
						//if it is a king then the game is over
						if(button.tile.getPiece().getType() == Type.King) {
							
							try {
								button.window.getHandler().addLogToXML("Player " + player.getColor().toString() + " has won the game!");
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							
							//we close the game window and open a game over window
							button.window.setGameOverWindow(new GameOverWindow(player.getColor()));
							button.window.getGameOverWindow().setVisible(true);
							button.window.dispose();
							
							return;
						}

						try {
							button.window.getHandler().addLogToXML(button.tile.getPiece().getId() + " has been captured!");
						} catch (Exception e1) {
							e1.printStackTrace();
						}			
						
						otherPlayer.getChessPieces().remove(new Id(otherPlayer.getColor(), button.tile.getPiece().getType(), button.tile.getPiece().getId().getNumber()));
						
					}
					
					try {
						button.window.getHandler().addLogToXML(button.window.getSelectedButton().tile.getPiece().getId() + " to " + button.tile.getCoordinate().toString());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					//then we move our chess piece to the target Tile
					button.tile.setPiece(button.window.getSelectedButton().tile.getPiece());
					button.window.getSelectedButton().tile.setPiece(null);
					button.tile.getPiece().setTile(button.tile);
					
					//we update the icons
					button.setIcon(button.tile.getPiece().getIcon());
					button.window.getSelectedButton().setIcon(null);
					
					//we deselect the selected button
					button.window.getSelectedButton().setSelected(false);
					button.window.getSelectedButton().setBorderPainted(false);
					button.window.setSelectedButton(null);
					
					//finally we change the player's turn
					button.window.setPlayerTurn(otherPlayer.getColor());
					
					//if as a result of the player's move the enemy's or the player's own king is checkmated, then the game is over	
					//or if the enemy king is the only remaining piece and it can't move anywhere					
					if(player.getKing().getTile().isChecked(player.getColor()) && !player.getKing().canMoveAnywhere()) {
						try {
							button.window.getHandler().addLogToXML("Checkmate!");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						try {
							button.window.getHandler().addLogToXML("Player " + otherPlayer.getColor().toString() + " has won the game!");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						//we close the game window and open a game over window
						button.window.setGameOverWindow(new GameOverWindow(player.getColor()));
						button.window.getGameOverWindow().setVisible(true);
						button.window.dispose();
						
						return;
					}
					
					if(otherPlayer.getKing().getTile().isChecked(otherPlayer.getColor()) && !otherPlayer.getKing().canMoveAnywhere() ||
							otherPlayer.chessPieces.size() == 1 && !otherPlayer.getKing().canMoveAnywhere()) {
						try {
							button.window.getHandler().addLogToXML("Checkmate!");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						try {
							button.window.getHandler().addLogToXML("Player " + player.getColor().toString() + " has won the game!");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						//we close the game window and open a game over window
						button.window.setGameOverWindow(new GameOverWindow(player.getColor()));
						button.window.getGameOverWindow().setVisible(true);
						button.window.dispose();
						
						return;
					}
					
					if(otherPlayer.getKing().getTile().isChecked(otherPlayer.getColor())) {
						try {
							button.window.getHandler().addLogToXML("Check!");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
					return;
				}
			}else {
				
				//if there is no selected button 
				//the player can't select a Tile that has no chess pieces
				//the player can't select a chess piece that isn't it's own colour
				//the player can't select a chess piece that can't move anywhere
				
				if(button.tile.getPiece() != null && button.tile.getPiece().getColour() == player.getColor() && button.tile.getPiece().canMoveAnywhere()) { 
					
					//when the king is checked the player can only select the king
					if(player.getKing().getTile().isChecked(player.getColor()) && button.tile.getPiece().getType() != Type.King) {
						return;
					}
					
					//we set the button to selected
					button.window.setSelectedButton(button);
					button.setSelected(true);
					button.setBorderPainted(true);
				}
			}
		}
	}	
}
