package moveLogic.specialMoveConditionStrategy;

import model.Board;
import model.pieces.Piece;

public class OnlyIfJumpOverPiece implements SpecialMoveCondition {
	
	private SpecialMoveCondition onlyFreeWayAdapter = SpecialMoveConditionFactory.getBehaviour('F');

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, int[] newPos) {
		return !onlyFreeWayAdapter.isMatchingSpecialCondition(board, piece, newPos);
	}
}
