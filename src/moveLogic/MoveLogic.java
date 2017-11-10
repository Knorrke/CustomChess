package moveLogic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import model.Board;
import model.pieces.Piece;
import moves.Move;

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
	 * and returns a List of possible Moves (for different sideeffects)
	 * 
	 * @param newPos
	 *            Array containing x and y position, where piece should move to
	 * @return List of possible Moves, empty if move not according to the rule
	 */
	public List<Move> getPossibleMoves(int[] newPos) {
		if (!Arrays.equals(newPos, piece.getPosition()) && board.isPieceOfColorOnSquare(piece.getColor(), newPos)) {
			return Collections.emptyList();
		}
		List<SingleRule> matchingSingleRules = rule.getMatchingSingleRules(newPos);
		List<Move> moves = matchingSingleRules.stream()
				.map(singleRule -> singleRule.createMove(newPos))
				.collect(Collectors.toList());
		return moves;
	}
}
