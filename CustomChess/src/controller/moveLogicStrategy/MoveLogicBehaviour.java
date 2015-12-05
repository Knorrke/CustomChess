package controller.moveLogicStrategy;

import model.Board;
import model.pieces.Piece;

public interface MoveLogicBehaviour {
	public boolean isMatchingSpecialCondition(Board board, Piece piece, String rulepart, int[] newPos);
}
