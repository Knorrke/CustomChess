package controller.moveLogicStrategy;

import model.Board;
import model.pieces.Piece;

public class OnlyIfAttacked implements MoveLogicBehaviour{

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, String rulepart, int[] newPos) {
		return board.isAttacked(piece.getColor().getOppositColor(), piece.getPosition());
	}

}