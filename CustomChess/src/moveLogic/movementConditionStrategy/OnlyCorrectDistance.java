package moveLogic.movementConditionStrategy;

import model.Board;
import model.pieces.Piece;
import player.PlayerColor;

public class OnlyCorrectDistance implements MovementCondition {
	private String[] distanceConditions;
	
	public OnlyCorrectDistance(String[] ruleparts) {
		distanceConditions = ruleparts;
	}

	@Override
	public boolean matchesMovementCondition(Board board, Piece piece, int[] newPos) {
    	int[] pos = piece.getPosition();
		for(int i = 0; i< pos.length; i++){
			String distanceCondition = distanceConditions[i];
			//if Piece is black, swap + and - (because black pieces move in the other direction)
			if(piece.getColor()==PlayerColor.BLACK){
				distanceCondition = swapChars(distanceCondition,'+','-');
			}
			
			if(!correctDistance(distanceCondition, newPos[i], pos[i])){
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks for one condition, if the position is in correct Distance
	 * @param distanceCondition
	 * @param newPos
	 * @param pos
	 * @return
	 */
	private boolean correctDistance(String distanceCondition, int newPos, int pos) {
		if(distanceCondition.equals("n")){
			return true;
		}
		
		if(distanceCondition.startsWith("+")){
			if(newPos-pos != Integer.parseInt(distanceCondition.substring(1))){
				return false;
			}
		}else if(distanceCondition.startsWith("-")){
			if(pos-newPos != Integer.parseInt(distanceCondition.substring(1))){
				return false;
			}
		}else{
			if(Math.abs(newPos-pos) != Integer.parseInt(distanceCondition)){
				return false;
			}
		}
		
		return true;
	}
	
	/**
     * Swaps all occurrences of Character a and Character b
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
}
