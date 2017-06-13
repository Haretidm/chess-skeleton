package chess.pieces;

import chess.Player;
import chess.Position;

/**
 * The Pawn
 */
public class Pawn extends Piece {
    public Pawn(Player owner) {
        super(owner);
    }
    
    private boolean isFirstMove = true;
    
    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }

	@Override
	public boolean isValidMove(Position from, Position to) {
		boolean propperDirection = (getOwner() == Player.White) ? (from.getY() < to.getY()) : (to.getY() > from.getY());
		final int dY = (to.getY() - from.getY());
		
		if (isFirstMove) {
			if (propperDirection) {
				return (dY == 2) || (dY == 1); 
			} else {
				return (dY == -2) || (dY == -1); 
			}
		}
		return propperDirection ? (dY == -1) : (dY == 1);
	}
	
	
	public boolean isValidFightMove(Position from, Position to) {
		final int dX = Math.abs(to.getX() - from.getX());
		final int dY = Math.abs(to.getY() - from.getY());
		
		return (dX ==  1) && (dY == 1);
	}
	
}
