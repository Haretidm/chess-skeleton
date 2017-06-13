package chess;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chess.pieces.Pawn;
import chess.pieces.Rook;

public class SimpleBlockingCheck {

	private GameState state;

	@Before
	public void setUp() {
		state = new GameState();
	}

	@Test
	public void testBlockinPosition() {
		final Position rookPosition1 = new Position("a1");
		final Position pawnPosition = new Position("a2");
		state.placePiece(new Rook(Player.White), rookPosition1);
		state.placePiece(new Pawn(Player.White), pawnPosition);

		assertTrue("White Rook is blocked by pawn", state.isBlocked(rookPosition1, new Position("a4"), false));

		final Position rookPosition2 = new Position("h1");
		state.placePiece(new Rook(Player.White), rookPosition2);

		assertFalse("White Rook is not blocked by pawn", state.isBlocked(rookPosition2, new Position("h1"), false));
		
		
		//Check from other side
		final Position rookPosition3 = new Position("h8");
		state.placePiece(new Rook(Player.Black), rookPosition2);

		assertFalse("Black Rook is not blocked by pawn", state.isBlocked(rookPosition3, new Position("h1"), false));
		
		final Position rookPosition4 = new Position("a8");
		state.placePiece(new Rook(Player.Black), rookPosition2);
		state.placePiece(new Pawn(Player.Black),  new Position("a7"));

		assertTrue("Balck Rook is not blocked by pawn", state.isBlocked(rookPosition4, new Position("a1"), false));
	}

}
