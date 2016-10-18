package moveLogic;

import java.util.List;

import gameController.GameController;
import model.Board;
import model.pieces.Piece;
import moveLogic.movementConditionStrategy.MovementCondition;
import moveLogic.specialMoveConditionStrategy.SpecialMoveCondition;

public class SingleRule {
	private MovementCondition moveCondition;
	private SpecialMoveCondition specialMoveCondition;
	private List<AdditionalAction> additionalActions;
	private final Board board;
	private final Piece piece;
	
	public SingleRule(Board board, 
				Piece piece, 
				MovementCondition moveCondition,
				SpecialMoveCondition specialMoveCondition,
				List<AdditionalAction> additionalActions) {
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
	
	public List<AdditionalAction> getAdditionalActions() {
		return additionalActions;
	}
	
	public void executeAdditionalActions(GameController game) {
		for(AdditionalAction action : additionalActions) {
			action.execute(game);
		}
	}
}
