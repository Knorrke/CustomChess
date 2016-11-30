package moves;

import model.pieces.Piece;

public class Move {
	private final Piece piece;
	private final int[] from, to;

	public Move(Piece piece, int[] from, int[] to) {
		this.piece = piece;
		this.from = from;
		this.to = to;
	}

	/**
	 * @return the piece
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * @return the from
	 */
	public int[] getFrom() {
		return from;
	}

	/**
	 * @return the to
	 */
	public int[] getTo() {
		return to;
	}
	
}
