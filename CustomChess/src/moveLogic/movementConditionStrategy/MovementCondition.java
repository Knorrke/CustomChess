package moveLogic.movementConditionStrategy;

import model.Board;
import model.pieces.Piece;

public interface MovementCondition {
	public boolean matchesMovementCondition(Board board, Piece piece, int[] newPos);
}
