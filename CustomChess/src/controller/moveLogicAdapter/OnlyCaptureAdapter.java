package controller.moveLogicAdapter;

import model.Board;
import model.pieces.Piece;

public class OnlyCaptureAdapter implements MoveLogicAdapterInterface {

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, String rulepart, int[] newPos) {
		return board.isPieceOnSquare(piece.getColor().getOppositColor(), newPos);
	}
}
