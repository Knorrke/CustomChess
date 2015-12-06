package controller.specialMoveConditionStrategy;

import java.util.List;

import model.Board;
import model.Square;
import model.pieces.Piece;

public class OnlyIfFreeWay implements SpecialMoveCondition {

	private final static int X=0,Y=1;
	
	@Override
	public boolean isMatchingSpecialCondition(Board board, Piece piece, String rulepart, int[] newPos) {
		List<Square> squares = getSquaresBetween(board.getSquares(), piece.getPosition(), newPos);
		for(Square square : squares){
			if(square.hasPiece()){
				return false;
			}
		}
		return true;
	}

	private List<Square> getSquaresBetween(Square[][] squares, int[] pos, int[] newPos) {
		//TODO implement logic
//		List<Square> list = new ArrayList<>();
//		if(pos[X] == newPos[X]){//vertical
//			int row = pos[X];
//			for(int col=Math.min(pos[Y],newPos[Y])+1; col<Math.max(pos[Y],newPos[Y]); col++){
//				list.add(squares[row][col]);
//			}
//		}else{ //not vertical
//		}
		return null;
	}
}
