package controller.moveLogicAdapter;

import model.Board;
import model.pieces.Piece;

public class OnlyNotAttackedAdapter implements MoveLogicAdapterInterface {

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, String rulepart, int[] newPos) {
		return !board.isAttacked(piece.getColor(), newPos);
	}

}