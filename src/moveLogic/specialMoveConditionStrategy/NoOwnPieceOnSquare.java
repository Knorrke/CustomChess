package moveLogic.specialMoveConditionStrategy;

import model.Board;
import model.pieces.Piece;

public class NoOwnPieceOnSquare implements SpecialMoveCondition {

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, int[] newPos) {
		Piece pieceOnSquare = board.getPieceOfSquare(newPos);
		return pieceOnSquare == null || !pieceOnSquare.getColor().equals(piece.getColor());
	}

}
