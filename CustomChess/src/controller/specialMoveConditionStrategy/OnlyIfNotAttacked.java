package controller.specialMoveConditionStrategy;

import model.Board;
import model.pieces.Piece;

public class OnlyIfNotAttacked implements SpecialMoveCondition {

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, String rulepart, int[] newPos) {
		return !board.isAttacked(piece.getColor().getOppositColor(), piece.getPosition());
	}

}
