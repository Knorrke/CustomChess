package controller.specialMoveConditionStrategy;

import model.Board;
import model.pieces.Piece;

public interface SpecialMoveCondition {
	public boolean isMatchingSpecialCondition(Board board, Piece piece, String rulepart, int[] newPos);
}
