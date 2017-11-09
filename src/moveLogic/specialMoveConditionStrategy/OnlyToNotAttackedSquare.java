package moveLogic.specialMoveConditionStrategy;

import model.Board;
import model.pieces.Piece;

public class OnlyToNotAttackedSquare implements SpecialMoveCondition {

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, int[] newPos) {
		board.removePiece(piece);
		boolean attacked = board.isAttacked(piece.getColor().getOppositColor(), newPos);
		board.addPiece(piece);
		return !attacked;
	}

}
