package moveLogic;

import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.pieces.Piece;
import moveLogic.additionalActions.AdditionalAction;
import moveLogic.additionalActions.AdditionalActionFactory;
import moveLogic.movementConditionStrategy.MovementCondition;
import moveLogic.movementConditionStrategy.MovementConditionFactory;
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
					: (b,p,n)->true;
			List<AdditionalAction> actions = ruleParts.length > 2 
					? AdditionalActionFactory.getActions(ruleParts[2]) 
					: new ArrayList<>();
			rule.add(new SingleRule(board, piece, mc, smc, actions));
		}
		return rule;
	}
}
