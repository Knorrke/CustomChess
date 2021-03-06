package moveLogic.specialMoveConditionStrategy;

import model.Board;
import model.pieces.Piece;

public class OnlyIfAttacked implements SpecialMoveCondition{

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, int[] newPos) {
		return board.isAttacked(piece.getColor().getOppositColor(), piece.getPosition());
	}

}
