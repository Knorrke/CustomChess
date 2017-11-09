package moves;

import model.Board;
import model.pieces.Piece;

public abstract class Move {
	protected final Piece piece;
	protected final int[] from, to;
	private boolean executed;

	private Move(Piece piece, int[] from, int[] to) {
		this.piece = piece;
		this.from = from;
		this.to = to;
		this.executed = false;
	}
	
	public Move(Piece piece, int[] to) {
		this(piece, piece.getPosition(), to);
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
	
	/**
	 * Sets if move was already executed
	 * @param executed
	 */
	protected void setExecuted(boolean executed) {
		this.executed = executed;
	}
	
	/**
	 * @return executed
	 */
	public boolean isExecuted() {
		return executed;
	}
	
	/**
	 * Override this to add execution logic
	 * @param board
	 * @return
	 */
	public Board execute(Board board) {
		// Override to add logic
		setExecuted(true);
		return board;
	};
	
	/**
	 * Override this to add reverse logic
	 * @param board
	 * @return
	 */
	public Board reverse(Board board) {
		setExecuted(false);
		return board;
	};
}
