package controller.moveLogicAdapter;

import model.Board;
import model.pieces.Piece;

public class OnlyJumpOverPieceAdapter implements MoveLogicAdapterInterface {
	
	private MoveLogicAdapterInterface onlyFreeWayAdapter = AdapterFactory.getAdapter('F');

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, String rulepart, int[] newPos) {
		return !onlyFreeWayAdapter.isMatchingSpecialCondition(board, piece, rulepart, newPos);
	}
}
