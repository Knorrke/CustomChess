package moveLogic.specialMoveConditionStrategy;

import model.Board;
import model.pieces.Piece;
import player.PlayerColor;

public class OnlyMove implements SpecialMoveCondition {

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, int[] newPos) {
		return !( board.isPieceOfColorOnSquare(PlayerColor.BLACK,newPos) 
				|| board.isPieceOfColorOnSquare(PlayerColor.WHITE, newPos));
	}

}
