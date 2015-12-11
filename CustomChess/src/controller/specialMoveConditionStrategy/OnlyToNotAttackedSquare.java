package controller.specialMoveConditionStrategy;

import model.Board;
import model.pieces.Piece;

public class OnlyToNotAttackedSquare implements SpecialMoveCondition {

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, int[] newPos) {
		return !board.isAttacked(piece.getColor(), newPos);
	}

}
