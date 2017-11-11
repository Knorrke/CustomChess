package moveLogic;

import java.util.List;
import java.util.stream.Collectors;

import model.Board;
import model.pieces.Piece;
import moves.Move;

public class MoveLogic implements MoveLogicInterface {
	private final Rule rule;

	public MoveLogic(Board board, Piece piece, String rule) {
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
		List<SingleRule> matchingSingleRules = rule.getMatchingSingleRules(newPos);
		List<Move> moves = matchingSingleRules.stream()
				.map(singleRule -> singleRule.createMove(newPos))
				.collect(Collectors.toList());
		return moves;
	}
}
