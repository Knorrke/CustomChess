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
	protected void afterDecoratedExecute(Board board) {
		if(!piece.getType().contains(King.class)){
			return;
		}
		Castling logik = new Castling();
		rook = logik.findRook(board, from, to);
		rookOldPosition = rook.getPosition();
		board.setPieceToNewPosition(rook, pos(to[X] == 2 ? 3 : 5, to[Y]));
		executed = true;		
	}
	@Override
	protected void beforeDecoratedReverse(Board board) {
		if (executed) {
			board.setPieceToNewPosition(rook, rookOldPosition);
		}
	}
	

	@Override
	public String toString() {
		return String.format("Castling %s %s->%s",piece.getClass().getSimpleName(),posToString(from), posToString(to));
	}
}
