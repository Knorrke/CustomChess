package moves;

import static helper.Helper.console;

import model.Board;
import model.pieces.Piece;

public class StandardMove extends Move {
	
	private boolean pieceMovedBefore;
	private Piece captured;

	public StandardMove(Piece piece, int[] to) {
		super(piece, to);
	}

	@Override
	public Board execute(Board board) {
		pieceMovedBefore = super.piece.isMoved();
		captured = board.getPieceOfSquare(super.to);
		board.setPieceToNewPosition(super.piece, super.to);
		piece.setMoved(true);
		super.execute(board);
		return board;
	}

	@Override
	public Board reverse(Board board) {
		if (isExecuted()) {
			board.setPieceToNewPosition(piece, from);
			if (captured != null){
				board.addPiece(captured);
			}
			piece.setMoved(pieceMovedBefore);
			super.reverse(board);
			return board;
		} else {
			console.errorPrint("Move wasn't executed but tried to reverse");
			return null;
		}
	}

}
