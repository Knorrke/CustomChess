package moveLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import model.Board;
import model.pieces.Piece;
import moveLogic.movementConditionStrategy.MovementCondition;
import moveLogic.movementConditionStrategy.MovementConditionFactory;
import moveLogic.specialMoveConditionStrategy.SpecialMoveCondition;
import moveLogic.specialMoveConditionStrategy.SpecialMoveConditionFactory;

public class MoveLogic implements MoveLogicInterface {
	private final Board board;
	private final Piece piece;
	private final String rule;
	private HashMap<Character, SpecialMoveCondition> specialConditionMap;
	private HashMap<String, MovementCondition> movementConditionMap;
	private ArrayList<MoveLogic> additionalMoveConditions = new ArrayList<>();
	private ArrayList<MoveLogic> additionalMoveAllowance = new ArrayList<>();

	public MoveLogic(Board board, Piece piece, String rule) {
		this.board = board;
		this.piece = piece;
		this.rule = rule;
		specialConditionMap = new HashMap<>();
		movementConditionMap = new HashMap<>();
		addBehavioursAutomatically();
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

		boolean success = false;
		String[] rules = rule.split("\\|");
		//Split total rule at "or" into single segments
		for (int i = 0; i < rules.length; i++) {
			// If the move matches one of the rules then its correct
			success = success || moveAccordingToSingleRule(rules[i], newPos);
		}
		for (MoveLogic ml : additionalMoveConditions) {
			success = success && ml.moveCorrect(newPos);
		}
		for (MoveLogic ml : additionalMoveAllowance) {
			success = success || ml.moveCorrect(newPos);
		}

		return success;
	}

	/**
	 * Checks if the move to specified position matches the rule.
	 * 
	 * @param singleRule
	 *            a single rule of type "x-Difference, y-Difference, special
	 *            conditions"
	 * @param newPos
	 *            position to move to as {x,y} array
	 * @return true if the move matches the specified rule
	 */
	private boolean moveAccordingToSingleRule(String singleRule, int[] newPos) {
		if (containsSpecialConditions(singleRule)) {
			String movementPart = singleRule.substring(0, singleRule.indexOf(';'));
			return allowedMovement(movementPart, newPos) && matchingSpecialConditions(singleRule, newPos);
		} else {
			return allowedMovement(singleRule, newPos);
		}
	}

	/**
	 * Checks, if the movement is allowed, ignoring special conditions
	 * 
	 * @param movementCondition
	 *            rule specifying allowed movement
	 * @param newPos
	 *            position to move to as {x,y} array
	 * @return true, if movement is allowed, false otherwise
	 */
	private boolean allowedMovement(String movementCondition, int[] newPos) {
		MovementCondition mc = movementConditionMap.get(movementCondition);
		return mc == null || mc.matchesMovementCondition(board, piece, newPos);
	}

	/**
	 * Checks if the move matches the special conditions of the rule
	 * 
	 * @param singleRule
	 *            Single rule of type "movementConditions;specialConditions"
	 * @param newPos
	 *            position to move to
	 * @return true, if move matches special conditions, false otherwise
	 */
	private boolean matchingSpecialConditions(String singleRule, int[] newPos) {
		if (containsSpecialConditions(singleRule)) {
			String[] ruleparts = singleRule.split(";");
			for (char condition : ruleparts[1].toCharArray()) {
				// for each special condition
				SpecialMoveCondition smc = specialConditionMap.get(condition);
				if (smc != null && !smc.isMatchingSpecialCondition(board, piece, newPos)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks, if a single rule has some special conditions seperated by ';'
	 * 
	 * @param singleRule
	 * @return true, if rule contains special conditions
	 */
	private boolean containsSpecialConditions(String singleRule) {
		return singleRule.contains(";");
	}

	/**
	 * Adds a movement condition for the specified conditionkey in rules
	 * 
	 * @param conditionKey
	 * @param smc
	 * @return this
	 */
	public MoveLogic addMovementCondition(String conditionKey, MovementCondition mc) {
		movementConditionMap.put(conditionKey, mc);
		return this;
	}

	/**
	 * Adds a special move condition for the specified conditionkey in rules
	 * 
	 * @param conditionKey
	 * @param smc
	 * @return this
	 */
	public MoveLogic addSpecialMoveCondition(char conditionKey, SpecialMoveCondition smc) {
		specialConditionMap.put(conditionKey, smc);
		return this;
	}

	/**
	 * Automatically adds the MoveLogicBehaviours for the special conditions in
	 * the rule.
	 */
	public MoveLogic addBehavioursAutomatically() {
		for (String singleRule : rule.split("\\|")) {
			String[] ruleparts = singleRule.split(";");

			addMovementCondition(ruleparts[0], MovementConditionFactory.getBehaviour(ruleparts[0]));
			if (ruleparts.length > 1) {
				for (char condition : ruleparts[1].toCharArray()) {
					addSpecialMoveCondition(condition, SpecialMoveConditionFactory.getBehaviour(condition));
				}
			}
		}

		return this;
	}

	/**
	 * Add movelogics, that should also be checked on every move and represent
	 * further conditions
	 * 
	 * @param additional
	 *            MoveLogics
	 * @return this
	 */
	public MoveLogic and(MoveLogic... additionals) {
		Collections.addAll(additionalMoveConditions, additionals);
		return this;
	}

	/**
	 * Add MoveLogics, that should also be checked on every move and represent
	 * alternatives
	 * 
	 * @param additionals
	 * @return this
	 */
	public MoveLogic or(MoveLogic... additionals) {
		Collections.addAll(additionalMoveAllowance, additionals);
		return this;
	}
}
