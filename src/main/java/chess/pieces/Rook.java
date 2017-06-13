package chess.pieces;

import chess.Player;
import chess.Position;

/**
 * The 'Rook' class
 */
public class Rook extends Piece {

    public Rook(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'r';
    }
    
	@Override
	public boolean isValidMove(Position from, Position to) {
		return couldMoveVerticallyAndHorizontally(from, to);
	}
}
