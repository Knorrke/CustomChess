package moveLogic;

import model.Board;
import model.pieces.Piece;
import moveLogic.movementConditionStrategy.MovementCondition;
import moveLogic.movementConditionStrategy.MovementConditionFactory;
import moveLogic.specialMoveConditionStrategy.NoOwnPieceOnSquare;
import moveLogic.specialMoveConditionStrategy.SpecialMoveCondition;
import moveLogic.specialMoveConditionStrategy.SpecialMoveConditionFactory;

public class MoveLogicParser {

	public static Rule parse(Board board, Piece piece, String ruleString) {
		Rule rule = new Rule();
		for (String singleRuleString : ruleString.split("\\|")) {

			String[] ruleParts = singleRuleString.split(";");
			MovementCondition mc = MovementConditionFactory.getBehaviour(ruleParts[0]);
			SpecialMoveCondition smc = ruleParts.length > 1
					? SpecialMoveConditionFactory.getBehaviour(ruleParts[1])
					: new NoOwnPieceOnSquare();
			String actions = ruleParts.length > 2 ? ruleParts[2] : "";
			rule.add(new SingleRule(board, piece, mc, smc, actions));
		}
		return rule;
	}
}
