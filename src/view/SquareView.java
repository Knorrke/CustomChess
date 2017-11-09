package view;

import static helper.Helper.*;

import com.diogonunes.jcdp.color.api.Ansi.BColor;

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
		console.setBackgroundColor(color == SquareColor.WHITE ? BColor.WHITE : BColor.BLACK);
		if (piece != null) {
			console.print(" ");
			piece.draw();
		} else {
			console.print("  ");
		}
	}
}
