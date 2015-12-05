package controller.moveLogicStrategy;

import model.Board;
import model.pieces.Piece;

public class OnlyToCapture implements MoveLogicBehaviour {

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, String rulepart, int[] newPos) {
		return board.isPieceOnSquare(piece.getColor().getOppositColor(), newPos);
	}
}
