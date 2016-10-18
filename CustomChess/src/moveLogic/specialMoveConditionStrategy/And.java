package moveLogic.specialMoveConditionStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Board;
import model.pieces.Piece;

public class And implements SpecialMoveCondition{

	private List<SpecialMoveCondition> conditions;
	
	public And(SpecialMoveCondition... conditions) {
		this.conditions = new ArrayList<SpecialMoveCondition>();
		Collections.addAll(this.conditions, conditions);
	}
	
	public And(List<SpecialMoveCondition> conditions) {
		this.conditions = conditions;
	}
	
	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, int[] newPos) {
		boolean success = true;
		for (SpecialMoveCondition specialMoveCondition : conditions) {
			success = success && specialMoveCondition.isMatchingSpecialCondition(board, piece, newPos);
		}
		return success;
	}
}
