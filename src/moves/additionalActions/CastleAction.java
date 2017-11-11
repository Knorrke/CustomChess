package moves.additionalActions;

import static helper.Helper.*;

import model.Board;
import model.pieces.King;
import model.pieces.Piece;
import moveLogic.specialMoveConditionStrategy.Castling;
import moves.Move;

public class CastleAction extends AdditionalAction {

	private boolean executed;
	private Piece rook;
	private int[] rookOldPosition;
	
	public CastleAction(Move decorated) {
		super(decorated);
	}
	
	@Override
	protected void beforeDecoratedExecute(Board board) {
		if (!piece.getType().contains(King.class)) {
			return;
		}
		Castling logik = new Castling();
		rook = logik.findRook(board, from, to);
		if (rook == null) {
			return;
		}
		rookOldPosition = rook.getPosition();
		board.removePiece(rook);
	}

	@Override
	protected void afterDecoratedReverse(Board board) {
		if (executed) {			
			rook.setMoved(false);
			rook.setPosition(rookOldPosition);
			board.addPiece(rook);
		}
	}
	
	
	@Override
	protected void afterDecoratedExecute(Board board) {
		if (rook == null) {
			return;
		}
		rook.setPosition(pos(to[X] == 2 ? 3 : 5, to[Y]));
		rook.setMoved(true);
		board.addPiece(rook);
		executed = true;		
	}
	@Override
	protected void beforeDecoratedReverse(Board board) {
		if (executed) {
			board.removePiece(rook);
		}
	}

	@Override
	public String toString() {
		return String.format("Castling %s %s->%s",piece.getClass().getSimpleName(),posToString(from), posToString(to));
	}
}
