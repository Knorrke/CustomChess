package controller.moveLogicAdapter;

import model.Board;
import model.pieces.Piece;

public interface MoveLogicAdapterInterface {
	public boolean isMatchingSpecialCondition(Board board, Piece piece, String rulepart, int[] newPos);
}
