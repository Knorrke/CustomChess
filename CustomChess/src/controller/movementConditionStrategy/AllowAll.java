package controller.movementConditionStrategy;

import model.Board;
import model.pieces.Piece;

public class AllowAll implements MovementCondition{

	@Override
	public boolean matchesMovementCondition(Board board, Piece piece, String rulepart, int[] newPos) {
		return true;
	}
}
