package model;

import model.pieces.Piece;
import view.SquareView;
import view.ViewInterface;

public class Square implements Drawable{

	private Piece piece;
	private ViewInterface view;

	public Square(SquareColor color) {
		piece = null;
		view = new SquareView(this, color);
	}
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
	
	@Override
	public void draw() {
		view.draw();
	}
	
}
