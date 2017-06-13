package chess;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class MoveValidationTest {
	//Simple test to check valid moves if only one Piece is on the board
	@Test
    public void testKingMove() {
        Piece king = new King(Player.White);
        assertEquals(true, king.isValidMove(new Position("e1"), new Position("e2")));
        assertEquals(true, king.isValidMove(new Position("e2"), new Position("d2")));
        assertEquals(true, king.isValidMove(new Position("d2"), new Position("c1"))); // check diagonal
        assertEquals(false, king.isValidMove(new Position("c1"), new Position("c6"))); // move more than one sheet not allowed false
    }
	
	@Test
    public void testKnightMove() {
        Piece knight = new Knight(Player.White);
        assertEquals(true, knight.isValidMove(new Position("b1"), new Position("a3")));
        assertEquals(true, knight.isValidMove(new Position("b1"), new Position("c3")));
        assertEquals(true, knight.isValidMove(new Position("a3"), new Position("c4")));
        assertEquals(true, knight.isValidMove(new Position("c4"), new Position("e3")));
        assertEquals(false, knight.isValidMove(new Position("b1"), new Position("a1"))); //not allowed
    }
	
	@Test
    public void testQueenMove() {
        Piece queen = new Queen(Player.White);
        assertEquals(true, queen.isValidMove(new Position("d1"), new Position("d8"))); //Vertically to the end of the board
        assertEquals(true, queen.isValidMove(new Position("d8"), new Position("a8")));
        assertEquals(true, queen.isValidMove(new Position("a8"), new Position("h1"))); //check Diagonal
        assertEquals(false, queen.isValidMove(new Position("h1"), new Position("f2"))); //check L move
    }
	
	@Test
    public void testRookMove() {
        Piece k = new Rook(Player.White);
        assertEquals(true, k.isValidMove(new Position("h1"), new Position("h2"))); //Vertical one sheet move
        assertEquals(true, k.isValidMove(new Position("h2"), new Position("g2"))); //Horizontal one sheet move
        assertEquals(true, k.isValidMove(new Position("g2"), new Position("a2"))); //Horizontal multiple sheets move
        assertEquals(true, k.isValidMove(new Position("a2"), new Position("a8"))); //Vertical multiple sheets move
        assertEquals(false, k.isValidMove(new Position("a8"), new Position("h1"))); //Diagonal does not allowed
    }
	
	
	@Test
    public void testBishopMove() {
        Piece bishop = new Bishop(Player.White);
        assertEquals(true, bishop.isValidMove(new Position("c1"), new Position("b2"))); //Diagonal one sheet move
        assertEquals(true, bishop.isValidMove(new Position("c1"), new Position("a3"))); //Diagonal multiple sheets
        assertEquals(false, bishop.isValidMove(new Position("a3"), new Position("b3"))); //Horizontal one sheet not allowed
        assertEquals(false, bishop.isValidMove(new Position("a3"), new Position("a4"))); //Vertical one sheet not allowed
    }
	

	@Test
    public void testWhitePawnMove() {
		Pawn k = new Pawn(Player.White);
        assertEquals(true, k.isValidMove(new Position("a2"), new Position("a3"))); //1 sheet forward
        assertEquals(true, k.isValidMove(new Position("a2"), new Position("a4"))); //2 sheet forward
        
        assertEquals(false, k.isValidMove(new Position("a2"), new Position("a1"))); //1 sheet back  should not allow
        assertEquals(true, k.isValidFightMove(new Position("a2"), new Position("b3"))); //Diagonal to kill an enemy forward
        assertEquals(false, k.isValidFightMove(new Position("b3"), new Position("c2"))); //Diagonal to kill an enemy back not allowed
        
        assertEquals(true, k.isValidMove(new Position("a7"), new Position("a6"))); //1 sheet forward another side
        assertEquals(true, k.isValidMove(new Position("a7"), new Position("a5"))); //2 sheet forward another side
    }
	
	
	@Test
    public void testBlackPawnMove() {
		Pawn k = new Pawn(Player.Black);
        assertEquals(true, k.isValidMove(new Position("a7"), new Position("a6"))); //1 sheet forward another side
        assertEquals(true, k.isValidMove(new Position("a7"), new Position("a5"))); //2 sheet forward another side
    }
}
