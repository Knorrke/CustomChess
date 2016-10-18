package moveLogic;

import model.Board;
import model.pieces.Piece;

public class MoveLogic implements MoveLogicInterface {
	private final Board board;
	private final Piece piece;
	private final Rule rule;

	public MoveLogic(Board board, Piece piece, String rule) {
		this.board = board;
		this.piece = piece;
		this.rule = MoveLogicParser.parse(board, piece, rule);
	}

	/**
	 * Checks if the piece of this controller can move to the specified position
	 * 
	 * @param newPos
	 *            Array containing x and y position, where piece should move to
	 * @return true if move is according to the rule, false otherwise
	 */
	public boolean moveCorrect(int[] newPos) {
		if (board.isPieceOfColorOnSquare(piece.getColor(), newPos)) {
			return false;
		}
		return !rule.getMatchingSingleRules(newPos).isEmpty();
	}
}
