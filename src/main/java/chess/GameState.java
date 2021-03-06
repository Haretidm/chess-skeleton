package chess;


import chess.pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

    /**
     * The current player
     */
    private Player currentPlayer = Player.White;

    /**
     * A map of board positions to pieces at that position
     */
    private Map<Position, Piece> positionToPieceMap;

    /**
     * Create the game state.
     */
    public GameState() {
        positionToPieceMap = new HashMap<Position, Piece>();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public void setCurrentPlayer(final Player player) {
        this.currentPlayer = player;
    }

    /**
     * Call to initialize the game state into the starting positions
     */
    public void reset() {
        // White Pieces
        placePiece(new Rook(Player.White), new Position("a1"));
        placePiece(new Knight(Player.White), new Position("b1"));
        placePiece(new Bishop(Player.White), new Position("c1"));
        placePiece(new Queen(Player.White), new Position("d1"));
        placePiece(new King(Player.White), new Position("e1"));
        placePiece(new Bishop(Player.White), new Position("f1"));
        placePiece(new Knight(Player.White), new Position("g1"));
        placePiece(new Rook(Player.White), new Position("h1"));
        placePiece(new Pawn(Player.White), new Position("a2"));
        placePiece(new Pawn(Player.White), new Position("b2"));
        placePiece(new Pawn(Player.White), new Position("c2"));
        placePiece(new Pawn(Player.White), new Position("d2"));
        placePiece(new Pawn(Player.White), new Position("e2"));
        placePiece(new Pawn(Player.White), new Position("f2"));
        placePiece(new Pawn(Player.White), new Position("g2"));
        placePiece(new Pawn(Player.White), new Position("h2"));

        // Black Pieces
        placePiece(new Rook(Player.Black), new Position("a8"));
        placePiece(new Knight(Player.Black), new Position("b8"));
        placePiece(new Bishop(Player.Black), new Position("c8"));
        placePiece(new Queen(Player.Black), new Position("d8"));
        placePiece(new King(Player.Black), new Position("e8"));
        placePiece(new Bishop(Player.Black), new Position("f8"));
        placePiece(new Knight(Player.Black), new Position("g8"));
        placePiece(new Rook(Player.Black), new Position("h8"));
        placePiece(new Pawn(Player.Black), new Position("a7"));
        placePiece(new Pawn(Player.Black), new Position("b7"));
        placePiece(new Pawn(Player.Black), new Position("c7"));
        placePiece(new Pawn(Player.Black), new Position("d7"));
        placePiece(new Pawn(Player.Black), new Position("e7"));
        placePiece(new Pawn(Player.Black), new Position("f7"));
        placePiece(new Pawn(Player.Black), new Position("g7"));
        placePiece(new Pawn(Player.Black), new Position("h7"));
    }

    /**
     * Get the piece at the position specified by the String
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return getPieceAt(position);
    }

    /**
     * Get the piece at a given position on the board
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(Position position) {
        return positionToPieceMap.get(position);
    }

    /**
     * Method to place a piece at a given position
     * @param piece The piece to place
     * @param position The position
     */
    void placePiece(Piece piece, Position position) {
        positionToPieceMap.put(position, piece);
    }
    
    
    /**
     * Get the piece that you want to remove
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece removePiece(Position position) {
        return positionToPieceMap.remove(position);
    }
    
    /**
     * Get the all possible moves for player, by scanning board for every Piece 
     * @param player (White or Black)
     * @return The piece at that position, or null if it does not exist.
     */
	public Set<Move> getMovesForPlayer(Player player) {
		final Set<Move> moveList = new HashSet<Move>();
		for (Map.Entry<Position, Piece> entry : positionToPieceMap.entrySet()) {
			final Position positionFrom = entry.getKey();
			final Piece piece = entry.getValue();
			if (player == piece.getOwner()) {
				for (char column = Position.MIN_COLUMN; column <= Position.MAX_COLUMN; column++) {
					for (int row = Position.MIN_ROW; row <= Position.MAX_ROW; row++) {
						final Position positionTo = new Position(column, row);
						if (!positionFrom.equals(positionTo)) {
							final Piece possiblePieceOnPosition = getPieceAt(positionTo);
							if (possiblePieceOnPosition == null || possiblePieceOnPosition.getOwner() != player) { //can move to free position 
																												   //or position where enemy is placed
								if (piece instanceof Pawn) {
									Pawn pawn = (Pawn) piece;
									pawn.isValidFightMove(positionFrom, positionTo);
									moveList.add(new Move(positionFrom, positionTo));
								}
								final boolean isKnight = (piece instanceof Knight); // knight can jump over sheets
								if (piece.isValidMove(positionFrom, positionTo) && !isBlocked(positionFrom, positionTo, isKnight)) {
									moveList.add(new Move(positionFrom, positionTo));
								}
							}
						}						
					}
				}
			}
		}
		
		return moveList;
	}
	

	
	public boolean isBlocked(Position positionFrom, Position positionTo, boolean isKnight) {
		if (!isKnight) {
			final char xFrom = positionFrom.getColumn();
			final int yFrom = positionFrom.getRow();
			final char xTo = positionTo.getColumn();
			final int yTo = positionTo.getRow();
			
			int maxSheetTo = Math.max(Math.abs(xTo - xFrom), Math.abs(yTo - yFrom));
			
			int dX = (xFrom < xTo) ? 1 : ((xFrom == xTo) ? 0 : -1);
			int dY = (yFrom < yTo) ? 1 : ((yFrom == yTo) ? 0 : -1);
			
			for (int i = 1; i < maxSheetTo; i++) {
				char icrementalX = (char) (xFrom +  (i * dX));
				int icrementalY = yFrom + i * dY;
				if (getPieceAt(new Position(icrementalX, icrementalY)) != null) {
					return true;
				}
				
			}
		}
		return false;
	}

	
	public boolean isKingAlive() {  //Currently as template for checkMate when king is not alive
		final List<Piece> pieces =	new ArrayList<Piece>(positionToPieceMap.values());
    	for (Piece piece : pieces) {
    		if (piece instanceof King && piece.getOwner() == getCurrentPlayer()) {
    			return true;
    		}
		}
    	return false;
	}
    
}
