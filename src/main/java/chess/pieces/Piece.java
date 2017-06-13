package chess.pieces;

import chess.Player;
import chess.Position;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final Player owner;

    protected Piece(Player owner) {
        this.owner = owner;
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public Player getOwner() {
        return owner;
    }

    protected abstract char getIdentifyingCharacter();
    
    public abstract boolean isValidMove(Position from, Position to);
    
    protected final boolean coudMoveDiagonally(Position from, Position to) {
    	return (Math.abs(to.getColumn() - from.getColumn()) == Math.abs(to.getRow() - from.getRow()));
    }
    
    protected final boolean couldMoveVerticallyAndHorizontally(Position from, Position to) {
    	return ((from.getColumn() == to.getColumn() && from.getRow() != to.getRow()) ||
    			(from.getRow() == to.getRow() && from.getColumn() != to.getRow()));
    }
}
