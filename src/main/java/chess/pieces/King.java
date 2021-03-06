package chess.pieces;

import chess.Player;
import chess.Position;

/**
 * The King class
 */
public class King extends Piece {
    public King(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }

	@Override
	public boolean isValidMove(Position from, Position to) {
		final int dX = Math.abs(from.getColumn() - to.getColumn());
		final int dY = Math.abs(from.getRow() - to.getRow());
		return (dX <= 1) && (dY <= 1);
	}
}
