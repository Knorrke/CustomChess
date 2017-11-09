package moveLogic.movementConditionStrategy;

import static helper.Helper.*;

import java.util.Arrays;

import model.Board;
import model.pieces.Piece;

public class OnlyCorrectSquare implements MovementCondition {

	private final int[] allowedSquare;
	public OnlyCorrectSquare(String rule) {
		allowedSquare = pos(rule);
	}
	
	@Override
	public boolean matchesMovementCondition(Board board, Piece piece, int[] newPos) {
		if (allowedSquare[X] < 0 || allowedSquare[X] >= board.size(X)
		 || allowedSquare[Y] < 0 || allowedSquare[Y] >= board.size(Y)) {
			return false;
		}
		
		return Arrays.equals(newPos, allowedSquare);
	}

}
