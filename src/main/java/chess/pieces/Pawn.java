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
		boolean propperDirection = getProperDirectionForPlayer(from, to);
		final int dX = Math.abs(to.getColumn() - from.getColumn());
		final int dY = (to.getRow() - from.getRow());
		
		if (dX > 0
				|| ((dY < 0 && (getOwner() == Player.White)) 
				|| (dY > 0 && (getOwner() == Player.Black)))) {
			return false;
		}
		
		if (isFirstMove) {
			if (propperDirection) {
				return (dY == 2) || (dY == 1); 
			} else {
				return (dY == -2) || (dY == -1); 
			}
		}
		return propperDirection ? (dY == 1) : (dY == -1);
	}
	
	
	public boolean isValidFightMove(Position from, Position to) {
		final int dX = to.getColumn() - from.getColumn();
		final int dY = to.getRow() - from.getRow();
		
		boolean propperDirection = getProperDirectionForPlayer(from, to);
		
		if (propperDirection) {
			return (dX ==  1) && (dY == 1);
		} else {
			return (dX ==  -1) && (dY == -1);
		}
		
	}
	
	private boolean getProperDirectionForPlayer (Position from, Position to) { //True for White, false for Black
		boolean direction = (getOwner() == Player.White) ? (from.getRow() < to.getRow()) : (to.getRow() > from.getRow());
		return direction;
	}
	
	public void looseFirstLongMoveChanceIfNeeded(){
		if (isFirstMove) {
			isFirstMove = false;
		}
	}
	
}
