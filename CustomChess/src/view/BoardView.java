package view;

import static helper.Helper.console;

import com.diogonunes.jcdp.color.api.Ansi.Attribute;
import com.diogonunes.jcdp.color.api.Ansi.BColor;
import com.diogonunes.jcdp.color.api.Ansi.FColor;

import model.Board;
import model.Square;

public class BoardView implements ViewInterface {
	private Board board;

	public BoardView(Board board) {
		this.board = board;
	}

	public void draw() {
		Square[][] squares = board.getSquares();
		for (int y = squares.length-1; y >=0; y--) {
			console.print((y+1) + " ", Attribute.NONE, FColor.GREEN, BColor.BLACK);
			for (int x = 0; x < squares[y].length; x++) {
				squares[x][y].draw();
				console.print(" ");
			}
			console.println("\n", Attribute.NONE, FColor.WHITE, BColor.WHITE);
		}
		console.println("   a  b  c  d  e  f  g  h ", Attribute.NONE, FColor.GREEN, BColor.BLACK);
		console.println("\n");
		console.clear();
	};
}
