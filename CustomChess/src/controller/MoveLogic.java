package controller;

import java.util.HashMap;

import controller.movementConditionStrategy.MovementCondition;
import controller.movementConditionStrategy.MovementConditionFactory;
import controller.specialMoveConditionStrategy.SpecialMoveCondition;
import controller.specialMoveConditionStrategy.SpecialMoveConditionFactory;
import model.Board;
import model.pieces.Piece;

public class MoveLogic implements MoveLogicInterface {
	private final Board board;
	private final Piece piece;
	private final String rule;
	private HashMap<Character, SpecialMoveCondition> specialConditionMap;
	private HashMap<String, MovementCondition> movementConditionMap;
	
	
	public MoveLogic(Board board, Piece piece, String rule){
		this.board = board;
		this.piece = piece;
		this.rule = rule;
		specialConditionMap = new HashMap<>();
		movementConditionMap = new HashMap<>();
	}
	
	/**
	 * Checks if the piece of this controller can move to the specified position
	 * @param newPos Array containing x and y position, where piece should move to
	 * @return true if move is according to the rule, false otherwise
	 */
	public boolean moveCorrect(int[] newPos){
		if(board.isPieceOfColorOnSquare(piece.getColor(),newPos)){
			return false; 
		}
	    	
		String[] rules = rule.split("\\|"); //Split total rule at "or" into single segments
    	for(int i=0; i<rules.length; i++){
    		if(moveAccordingToSingleRule(rules[i], newPos)){
    			return true; //If the move matches one of the rules then its correct
    		}
    	}
    	return false; //if no correct rule was found previously then its wrong
    }
	
	/**
     * Checks if the move to specified position matches the rule.
     * @param singleRule a single rule of type "x-Difference, y-Difference, special conditions"
     * @param newPos position to move to as {x,y} array
     * @return true if the move matches the specified rule
     */
	private boolean moveAccordingToSingleRule(String singleRule, int[] newPos){
		if(containsSpecialConditions(singleRule)){
			String movementPart = singleRule.substring(0,singleRule.indexOf(';'));
			return allowedMovement(movementPart,newPos) && matchingSpecialConditions(singleRule,newPos);
		}else{
			return allowedMovement(singleRule, newPos);
		}
	}

	/**
     * Checks, if the movement is allowed, ignoring special conditions
     * @param movementCondition rule specifying allowed movement
     * @param newPos position to move to as {x,y} array
     * @return true, if movement is allowed, false otherwise
     */
	private boolean allowedMovement(String movementCondition, int[] newPos) {
		MovementCondition mc = movementConditionMap.get(movementCondition);
    	return mc == null || mc.matchesMovementCondition(board, piece, movementCondition, newPos);
	}

	/**
	 * Checks if the move matches the special conditions of the rule
	 * @param singleRule Single rule of type "movementConditions;specialConditions"
	 * @param newPos position to move to
	 * @return true, if move matches special conditions, false otherwise
	 */
    private boolean matchingSpecialConditions(String singleRule, int[] newPos) {
    	if(containsSpecialConditions(singleRule)){
    		String[] ruleparts = singleRule.split(";");
    		for(char condition : ruleparts[1].toCharArray()){ //for each special condition
    			SpecialMoveCondition smc = specialConditionMap.get(condition);
    			if(smc != null && !smc.isMatchingSpecialCondition(board, piece, singleRule, newPos)){
    				return false;
        		}
    		}
    	}
    	return true;
    }
	
	/**
	 * Checks, if a single rule has some special conditions seperated by ';'
	 * @param singleRule
	 * @return true, if rule contains special conditions
	 */
    private boolean containsSpecialConditions(String singleRule) {
		return singleRule.contains(";");
	}
	
	/**
	 * Adds a movement condition for the specified conditionkey in rules
	 * @param conditionKey
	 * @param smc
	 */
	public void addMovementCondition(String conditionKey, MovementCondition mc){
		movementConditionMap.put(conditionKey, mc);
	}
	
	/**
	 * Adds a special move condition for the specified conditionkey in rules
	 * @param conditionKey
	 * @param smc
	 */
	public void addSpecialMoveCondition(char conditionKey, SpecialMoveCondition smc){
		specialConditionMap.put(conditionKey, smc);
	}

	/**
	 * Automatically adds the MoveLogicBehaviours for the special conditions in the rule.
	 */
	public void addBehavioursAutomatically() {
		for( String rulepart : rule.split("\\|")){
			String[] ruleparts = rulepart.split(";");
			
			movementConditionMap.put(ruleparts[0],MovementConditionFactory.getBehaviour(ruleparts[0]));
			if(ruleparts.length>1){
				for(char condition : ruleparts[1].toCharArray()){
					specialConditionMap.put(condition,SpecialMoveConditionFactory.getBehaviour(condition));
				}
			}
		}
	}
}
