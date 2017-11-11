package moveLogic.movementConditionStrategy;

import model.Board;
import model.pieces.Piece;

public class OnlyDiagonal implements MovementCondition{
	
	private boolean zeroAllowed;
	public OnlyDiagonal(String[] ruleparts) {
		zeroAllowed = ruleparts[0].equals("n0");
	}
	
	@Override
	public boolean matchesMovementCondition(Board board, Piece piece, int[] newPos) {
		int[] pos = piece.getPosition();
		return Math.abs(newPos[0]-pos[0]) == Math.abs(newPos[1]-pos[1]) && (zeroAllowed || newPos[0] != pos[0]);
	}

}
