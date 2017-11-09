package moveLogic.specialMoveConditionStrategy;

import java.util.ArrayList;
import java.util.Collections;

import model.Board;
import model.pieces.Piece;

public class Or implements SpecialMoveCondition {

	private ArrayList<SpecialMoveCondition> conditions = new ArrayList<>();
	
	public Or(SpecialMoveCondition... conditions) {
		Collections.addAll(this.conditions, conditions);
	}
	
	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, int[] newPos) {
		boolean success = false;
		for (SpecialMoveCondition specialMoveCondition : conditions) {
			success = success || specialMoveCondition.isMatchingSpecialCondition(board, piece, newPos);
		}
		return success;
	}
}
