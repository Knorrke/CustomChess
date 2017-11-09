package moves.additionalActions;

import static helper.Helper.*;

import model.Board;
import model.pieces.King;
import model.pieces.Piece;
import moves.Move;

public class CastleAction extends AdditionalAction {

	private boolean executed;
	private Piece rook;
	private int[] rookOldPosition;
	
	public CastleAction(Move decorated) {
		super(decorated);
	}
	
	@Override
	protected void afterDecoratedExecute(Board board) {
		if(!piece.getType().contains(King.class)){
			return;
		}
		
		switch(to[X]) {
		case 2: rookOldPosition = pos(0, to[Y]);
				rook = board.getPieceOfSquare(rookOldPosition);
				board.setPieceToNewPosition(rook, pos(3, to[Y]));
				executed = true;
				break;
		case 6: rookOldPosition = pos(7, to[Y]);
				rook = board.getPieceOfSquare(rookOldPosition);
				board.setPieceToNewPosition(rook, pos(5, to[Y]));
				executed = true;
				break;
		}
	}
	@Override
	protected void beforeDecoratedReverse(Board board) {
		if (executed) {
			board.setPieceToNewPosition(rook, rookOldPosition);
		}
	}
}
