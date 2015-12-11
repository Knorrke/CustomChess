package controller.specialMoveConditionStrategy;

import model.Board;
import model.pieces.Piece;

public class OnlyIfNeverMoved implements SpecialMoveCondition {

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, int[] newPos) {
		// TODO Auto-generated method stub
		return false;
	}

}
