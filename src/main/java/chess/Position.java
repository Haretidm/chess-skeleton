package chess;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Describes a position on the Chess Board
 */
public class Position {
    public static final int MIN_ROW = 1;
    public static final int MAX_ROW = 8;
    public static final char MIN_COLUMN = 'a';
    public static final char MAX_COLUMN = 'h';
    private int row;
    private char column;
    
    private static final List<Character> x_axys = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');
    
    private int x;
    private int y;

    /**
     * Create a new position object
     *
     * @param column The column
     * @param row The row
     */
    public Position(char column, int row) {
        this.row = row;
        this.column = column;
        this.x = x_axys.indexOf(column);
        this.y = row - 1;
    }
    
    /**
     * Create a new Position object by parsing the string
     * @param colrow The column and row to use.  I.e. "a1", "h7", etc.
     */
    public Position(String colrow) {
        this(colrow.toCharArray()[0], Character.digit(colrow.toCharArray()[1], 10));
    }

    public int getRow() {
        return row;
    }

    public char getColumn() {
        return column;
    }
    
    public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean isValidPositionOnTheBoard(){
		if (row >= MIN_ROW && row <= MAX_ROW && getX() >= 0) {
			return true;
		}
		return false;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (column != position.column) return false;
        if (x != position.x) return false;
      
        //noinspection RedundantIfStatement
        if (row != position.row) return false;
        if (y != position.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
       return  Objects.hash(row, column, x, y);
    }

    @Override
    public String toString() {
        return "" + column + row;
    }

}
