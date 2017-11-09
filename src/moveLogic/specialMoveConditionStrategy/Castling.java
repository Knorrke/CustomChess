package moveLogic.specialMoveConditionStrategy;

import static helper.Helper.X;
import static helper.Helper.Y;
import static helper.Helper.pos;

import java.util.Arrays;

import model.Board;
import model.pieces.Piece;
import model.pieces.Rook;

public class Castling implements SpecialMoveCondition {

	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, int[] newPos) {
		int[] oldPos = piece.getPosition();
		Piece rook = findRook(board, oldPos, newPos);
		return (oldPos[Y] == newPos[Y]) 
				&& rook != null 
				&& !piece.isMoved() 
				&& !rook.isMoved() 
				&& !otherPiecesBetween(board, oldPos, newPos, rook, piece)
				&& !otherPiecesBetween(board, rook.getPosition(), (newPos[X] == 2 ? pos(3, newPos[Y]) : pos(5, newPos[Y])), rook, piece)
				&& !squareAttacked(board, piece, oldPos, newPos);
	}

	private Piece findRook(Board board, int[] oldPos, int[] newPos) {
		int dir = Integer.signum(newPos[X] - oldPos[X]);
		for (int i = 0; oldPos[X]+i*dir >= 0 && oldPos[X]+i*dir<board.size(X); i++) {
			if (board.isPieceOnSquare(pos(oldPos[X]+i*dir, oldPos[Y]))) {
				Piece piece = board.getPieceOfSquare(pos(oldPos[X]+i*dir, oldPos[Y]));
				if (piece.getType().contains(Rook.class)) {
					return piece;
				}
			}
		}
		throw new IllegalArgumentException("No rook found for castling");
	}

	private boolean squareAttacked(Board board, Piece piece, int[] oldPos, int[] newPos) {
		int dir = (int) Math.signum(newPos[X] - oldPos[X]);
		for (int i = 0; i <= Math.abs(oldPos[X] - newPos[X]); i++) {
			if (board.isAttacked(piece.getColor().getOppositColor(), pos(oldPos[X] + i * dir, oldPos[Y])))
				return true;
		}

		return false;
	}

	/**
	 * Returns if there is a piece horizontally between pos1 and pos2 (both
	 * included), except for the specified pieces
	 * 
	 * @param board
	 * @param pos1
	 * @param pos2
	 * @return
	 */
	private boolean otherPiecesBetween(Board board, int[] pos1, int[] pos2, Piece... pieces) {
		int dir = Integer.signum(pos2[X] - pos1[X]);
		
		for (int i = 0; i <= Math.abs(pos2[X]-pos1[X]); i++) {
			if (board.isPieceOnSquare(pos(pos1[X]+i*dir, pos1[Y]))) {
				Piece piece = board.getPieceOfSquare(pos(pos1[X]+i*dir, pos1[Y]));
				if (pieces.length == 0 || !Arrays.stream(pieces).anyMatch(p -> p == piece))
					return true;
			}
		}
		return false;
	}

}
