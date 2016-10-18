package view;

import model.Square;
import model.SquareColor;
import model.pieces.Piece;

public class SquareView implements ViewInterface {

	private Square square;
	private SquareColor color;

	public SquareView(Square square, SquareColor color) {
		this.square = square;
		this.color = color;
	}

	@Override
	public void draw() {
		Piece piece = square.getPiece();
		switch (color) {
		case BLACK:
			System.out.print("=");
			if (piece != null)
				piece.draw();
			else
				System.out.print("=");
			System.out.print("=");
			break;
		case WHITE:
			System.out.print(" ");
			if (piece != null)
				piece.draw();
			else
				System.out.print(" ");
			System.out.print(" ");
			break;
		default:
			break;
		}
	}
}
