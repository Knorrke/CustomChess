package model;

import model.pieces.Piece;

public class Square {

	private Piece piece;

	/**
	 * @return the piece
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * @param piece the piece to set
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public boolean hasPiece() {
		return this.piece != null;
	}
	
}
