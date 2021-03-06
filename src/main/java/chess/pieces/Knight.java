package chess.pieces;

import chess.Player;
import chess.Position;

/**
 * The Knight class
 */
public class Knight extends Piece {
    public Knight(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }

    @Override
	public boolean isValidMove(Position from, Position to) {
    	final int dX = Math.abs(from.getColumn() - to.getColumn());
    	final int dY = Math.abs(from.getRow() - to.getRow());
		return (dX == 1 && dY == 2) || (dY == 1 && dX == 2);
	}
}
