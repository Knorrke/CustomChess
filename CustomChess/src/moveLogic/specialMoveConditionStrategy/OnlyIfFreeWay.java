package moveLogic.specialMoveConditionStrategy;

import model.Board;
import model.pieces.Piece;

public class OnlyIfFreeWay implements SpecialMoveCondition {

	private final static int X=0,Y=1;
	
	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, int[] newPos) {
		int[] currentPos = piece.getPosition();
		int left = Math.min(currentPos[X],newPos[X]); 
		int lower = Math.min(currentPos[Y],newPos[Y]);
		
		//slope of the line through the current and new position
		double deltaY = Math.abs(currentPos[Y]-newPos[Y])+1; // plus one because of zero-based index
		double deltaX = Math.abs(currentPos[X]-newPos[X])+1; //plus one because of zero-based index
		
		//Move in steps of one and check all fields in one slope range
		for (int i=0; i<=Math.abs(currentPos[X]-newPos[X]); i++) {
			int start = (int) Math.floor(i*deltaY/deltaX);
			int bound = (int) Math.ceil((i+1)*deltaY/deltaX);
			
			for(int j=start; j<bound; j++){
				if((left+i == newPos[X] && lower+j == newPos[Y]) ||
						(left+i == currentPos[X] && lower+j == currentPos[Y])) {
					continue;
				}
				if(board.isPieceOnSquare(new int[] {left+i,lower+j})) {
					return false;
				}
			}
		}
		return true;
	}
}
