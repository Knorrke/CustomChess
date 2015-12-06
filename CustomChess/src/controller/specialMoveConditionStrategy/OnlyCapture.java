package controller.specialMoveConditionStrategy;

import model.Board;
import model.pieces.Piece;

public class OnlyCapture implements SpecialMoveCondition {

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, String rulepart, int[] newPos) {
		return board.isPieceOfColorOnSquare(piece.getColor().getOppositColor(), newPos);
	}
}
