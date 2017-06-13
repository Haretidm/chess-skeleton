package chess.pieces;

import chess.Player;
import chess.Position;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {
    public Bishop(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }

    @Override
	public boolean isValidMove(Position from, Position to) {
		return coudMoveDiagonally(from, to);
	}
}
