package controller.moveLogicAdapter;

import model.Board;
import model.pieces.Piece;
import player.PlayerColor;

public class OnlyMoveAdapter implements MoveLogicAdapterInterface {

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, String rulepart, int[] newPos) {
		return !( board.isPieceOnSquare(PlayerColor.BLACK,newPos) 
				|| board.isPieceOnSquare(PlayerColor.BLACK, newPos));
	}

}
