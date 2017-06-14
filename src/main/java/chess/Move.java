package chess;

import java.util.Objects;

public class Move {
	private final Position from;
	private final Position to;

	public Move(final Position from, final Position to) {
		this.from = from;
		this.to = to;
	}

	public Position getFrom() {
		return from;
	}

	public Position getTo() {
		return to;
	}

	@Override
	public int hashCode() {
		return Objects.hash(from, to);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Move)) return false;
		Move move = (Move) obj;
	    return from.equals(move.from) && to.equals(move.to);
	}

	@Override
	public String toString() {
		return String.format("	%s %s", from, to);
	}
	
}
