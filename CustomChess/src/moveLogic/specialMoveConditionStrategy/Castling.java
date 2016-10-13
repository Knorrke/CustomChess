package moveLogic.specialMoveConditionStrategy;

import static helper.Helper.X;
import static helper.Helper.Y;
import static helper.Helper.pos;

import model.Board;
import model.pieces.Piece;

public class Castling implements SpecialMoveCondition {

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, int[] newPos) {
		int[] oldPos = piece.getPosition();
		return (oldPos[Y] == newPos[Y])
				  && !piece.isMoved()
				  && !findRook(board, oldPos, newPos).isMoved()
				  && !pieceBetween(board, oldPos, newPos)
				  && !squareAttacked(board, piece, oldPos, newPos);
	}

	private Piece findRook(Board board, int[] oldPos, int[] newPos) {
		if (oldPos[X] > newPos[X]) {
			return board.getPieceOfSquare(pos(0, newPos[Y]));
		} else {
			return board.getPieceOfSquare(pos(board.size(X)-1, newPos[Y]));
		}
	}

	private boolean squareAttacked(Board board, Piece piece, int[] oldPos, int[] newPos) {
		int dir = (int) Math.signum(newPos[X]-oldPos[X]);
		for (int i=0; i <= Math.abs(oldPos[X]-newPos[X]); i++) {
			if(board.isAttacked(piece.getColor().getOppositColor(), pos(oldPos[X]+i*dir,oldPos[Y]))) return true;
		}
		
		return false;
	}

	private boolean pieceBetween(Board board, int[] oldPos, int[] newPos) {
		if (oldPos[X] > newPos[X]) {
			for(int i=1; i<oldPos[X]; i++) {
				if (board.isPieceOnSquare(pos(i,oldPos[Y]))) return true;
			}
		} else {
			for (int i=oldPos[X]+1; i < board.size(X)-1; i++) {
				if (board.isPieceOnSquare(pos(i,oldPos[Y]))) return true;
			}
		}
		return false;
	}

}
