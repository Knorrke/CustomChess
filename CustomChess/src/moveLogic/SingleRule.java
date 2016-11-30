package moveLogic;

import java.util.List;

import gameController.GameController;
import model.Board;
import model.pieces.Piece;
import moveLogic.additionalActions.AdditionalAction;
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
		boolean correct = moveCondition.matchesMovementCondition(board, piece, newPos)
				&& specialMoveCondition.isMatchingSpecialCondition(board, piece, newPos);
		if(correct && !additionalActions.isEmpty()) {
			board.registerAction(newPos, additionalActions);
		}
		
		return correct;
	}
	
	public List<AdditionalAction> getAdditionalActions() {
		return additionalActions;
	}
	
	public void executeAdditionalActions(GameController game, Piece piece, int[] newPos) {
		for(AdditionalAction action : additionalActions) {
			action.execute(game, piece, newPos);
		}
	}
}
