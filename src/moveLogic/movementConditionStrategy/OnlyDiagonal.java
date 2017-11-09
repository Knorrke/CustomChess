package moveLogic.movementConditionStrategy;

import model.Board;
import model.pieces.Piece;

public class OnlyDiagonal implements MovementCondition{

	@Override
	public boolean matchesMovementCondition(Board board, Piece piece, int[] newPos) {
		int[] pos = piece.getPosition();
		return Math.abs(newPos[0]-pos[0]) == Math.abs(newPos[1]-pos[1]);
	}

}
