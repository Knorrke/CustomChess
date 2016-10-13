package view;

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
			for (int x = 0; x < squares[y].length; x++) {
				System.out.print("|");
				squares[x][y].draw();
			}
			System.out.println("|");
		}
		System.out.println("\n");
	};
}
