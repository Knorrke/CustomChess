package controller.moveLogicStrategy;

import model.Board;
import model.pieces.Piece;

public class OnlyIfJumpOverPiece implements MoveLogicBehaviour {
	
	private MoveLogicBehaviour onlyFreeWayAdapter = BehaviourFactory.getBehaviour('F');

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, String rulepart, int[] newPos) {
		return !onlyFreeWayAdapter.isMatchingSpecialCondition(board, piece, rulepart, newPos);
	}
}
