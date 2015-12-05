package controller;

import java.util.Arrays;
import java.util.HashMap;

import controller.moveLogicStrategy.BehaviourFactory;
import controller.moveLogicStrategy.MoveLogicBehaviour;
import model.Board;
import model.pieces.Piece;
import player.PlayerColor;

public class MoveLogic implements MoveLogicInterface {
	
	private final Board board;
	private final Piece piece;
	private final String rule;
	private final static int X = 0, Y=1;
	private HashMap<Character, MoveLogicBehaviour> strategyMap;
	
	
	public MoveLogic(Board board, Piece piece, String rule){
		this.board = board;
		this.piece = piece;
		this.rule = rule;
		strategyMap = new HashMap<>();
	}
	
	/**
	 * Checks if the piece of this controller can move to the specified position
	 * @param newPos Array containing x and y position, where piece should move to
	 * @return true if move is according to the rule, false otherwise
	 */
	public boolean moveCorrect(int[] newPos){
		if(newPos.length!=2){
			throw new IllegalArgumentException("Position not two-dimensional: " + Arrays.toString(newPos));
		}else{
	    	if(board.isPieceOnSquare(piece.getColor(),newPos)) return false; 
	    	
			String[] rules = rule.split("\\|"); //Split total rule at "or" into single segments
	    	for(int i=0; i<rules.length; i++){
	    		if(moveAccordingToSingleRule(rules[i], newPos)){
	    			return true; //If the move matches one of the rules then its correct
	    		}
	    	}
	    	return false; //if no correct rule was found previously then its wrong
		}
    }
	
	/**
     * Checks if the move to specified position matches the rule.
     * @param rule a single rule of type "x-Difference, y-Difference, special conditions"
     * @param newPos position to move to as {x,y} array
     * @return true if the move matches the specified rule
     */
	private boolean moveAccordingToSingleRule(String rule, int[] newPos){
		return allowedMovement(rule,newPos) && matchingSpecialConditions(rule,newPos);
	}
	
    /**
     * Checks, if the movement is allowed, ignoring special conditions
     * @param rule rule specifying allowed movement
     * @param newPos position to move to as {x,y} array
     * @return true, if movement is allowed, false otherwise
     */
	private boolean allowedMovement(String rule, int[] newPos) {
		int[] pos = piece.getPosition();
    	
		//if Piece is black, swap + and - (because black pieces move in the other direction)
    	if(piece.getColor()==PlayerColor.BLACK){
    		rule = swapChars(rule,'+','-');
    	}
    	
    	String[] regelTeil = rule.split(","); //Split at ','
    	if(allowEverywhere(regelTeil)); // piece can move everywhere 
    	else if(regelTeil[0].equals("n") || regelTeil[1].equals("n")){
    		if(allowDiagonal(regelTeil)){ //piece can move diagonal
    			if(Math.abs(newPos[0]-pos[0]) != Math.abs(newPos[1]-pos[1])) return false;
    		}else{
    			int i = regelTeil[0].equals("n") ? 1 : 0;
    			if(!correctDistance(regelTeil[i],newPos[i],pos[i])){
    				return false;
    			}
    		}
    	}else{ //If rule specifies correct distance directly
	    	if(!correctDistance(regelTeil[X],newPos[X],pos[X]) 
	    	 || !correctDistance(regelTeil[Y],newPos[Y],pos[Y])){
	    		return false;
	    	}
	    }
    	
    	return true;
	}

	/**
	 * Checks if the move matches the special conditions of the rule
	 * @param singleRule Single rule of type "x-difference, y-difference, specialconditions"
	 * @param newPos position to move to
	 * @return true, if move matches special conditions, false otherwise
	 */
    private boolean matchingSpecialConditions(String singleRule, int[] newPos) {
    	String[] ruleparts = singleRule.split(",");
    	if(ruleparts.length > 2){ //if there are special conditions
    		for(char condition : ruleparts[2].toCharArray()){ //for each special condition
    			MoveLogicBehaviour mlb = strategyMap.get(condition);
    			if(mlb != null && !mlb.isMatchingSpecialCondition(board, piece, singleRule, newPos)){
    				return false;
        		}
    		}
    	}
    	return true;

//	    		case 'B': //Nur wenn noch nicht bewegt
//	    			if(bewegt) return false;
//	    			break;
//	    			
//	    		case '2': case '3': case '4': //zwei- /drei- /vier-mal ziehen
//	    			break;
//	    		default:
//	    			System.err.println("Unbekannte Zugregel " + regelTeil[3].charAt(0));
//	    			break;
	}

	
	/**
	 * Checks for one rule part, if the position is in correct Distance
	 * @param rulepart
	 * @param newPos
	 * @param pos
	 * @return
	 */
	private boolean correctDistance(String rulepart, int newPos, int pos) {
		if(rulepart.startsWith("+")){
			if(newPos-pos != Integer.parseInt(rulepart.substring(1))){
				return false;
			}
		}else if(rulepart.startsWith("-")){
			if(pos-newPos != Integer.parseInt(rulepart.substring(1))){
				return false;
			}
		}else{
			if(Math.abs(newPos-pos) != Integer.parseInt(rulepart)){
				return false;
			}
		}
		
		return true;
	}

	/**
     * Swaps all Occurences of Character a and Character b
     * @param source
     * @param a
     * @param b
     * @return
     */
	private String swapChars(String source, char a, char b) {
		StringBuilder sb = new StringBuilder();
		for (char c : source.toCharArray()) {
		  if (c == a) sb.append(b);
		  else if (c == b) sb.append(a);
		  else sb.append(c);
		}
		return sb.toString();
	}
	
	/**
	 * Checks if the rule is of form "n,m"
	 * @param ruleparts
	 * @return
	 */
	private boolean allowEverywhere(String[] ruleparts){
		if(ruleparts.length < 2){
			throw new IllegalArgumentException("Wrong rule");
		}
		return ruleparts[0].equals("n") && ruleparts[1].equals("m");
	}
	
	/**
	 * Checks if the rule is of form "n,n"
	 * @param ruleparts
	 * @return
	 */
	private boolean allowDiagonal(String[] ruleparts) {
		if(ruleparts.length < 2){
			throw new IllegalArgumentException("Wrong rule");
		}
		return ruleparts[0].equals(ruleparts[1]);
	}
	
	/**
	 * Adds a MoveLogicBehaviour for the specified condition in rules
	 * @param condition
	 * @param mlb
	 */
	public void addMoveLogicAdapter(char condition, MoveLogicBehaviour mlb){
		strategyMap.put(condition, mlb);
	}

	/**
	 * Automatically adds the MoveLogicBehaviours for the special conditions in the rule.
	 */
	public void addBehavioursAutomatically() {
		for( String rulepart : rule.split("\\|")){
			String[] ruleparts = rulepart.split(",");
			if(ruleparts.length > 2){
				for(char condition : ruleparts[2].toCharArray()){
					strategyMap.put(condition,BehaviourFactory.getBehaviour(condition));
				}
			}
		}
	}
}
