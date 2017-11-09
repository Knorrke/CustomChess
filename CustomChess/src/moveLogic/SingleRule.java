package moveLogic;

import model.Board;
import model.pieces.Piece;
import moveLogic.movementConditionStrategy.MovementCondition;
import moveLogic.specialMoveConditionStrategy.SpecialMoveCondition;
import moves.Move;
import moves.StandardMove;
import moves.additionalActions.AdditionalActionFactory;

public class SingleRule {
	private MovementCondition moveCondition;
	private SpecialMoveCondition specialMoveCondition;
	private String additionalActions;
	private final Board board;
	private final Piece piece;
	
	public SingleRule(Board board, 
				Piece piece, 
				MovementCondition moveCondition,
				SpecialMoveCondition specialMoveCondition,
				String additionalActions) {
		this.board = board;
		this.piece = piece;
		this.moveCondition = moveCondition;
		this.specialMoveCondition = specialMoveCondition;
		this.additionalActions = additionalActions;
	}
	
	public boolean matches(int[] newPos) {
		return moveCondition.matchesMovementCondition(board, piece, newPos)
				&& specialMoveCondition.isMatchingSpecialCondition(board, piece, newPos);
	}
	
	public Move createMove(int[] newPos) {
		return AdditionalActionFactory.getDecoratedMove(additionalActions, new StandardMove(board.getPieceOfSquare(piece.getPosition()), newPos));
	}
}
