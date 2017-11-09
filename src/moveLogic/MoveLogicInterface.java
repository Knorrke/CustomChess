package moveLogic;

import java.util.List;

import moves.Move;

public interface MoveLogicInterface {
	public List<Move> getPossibleMoves(int[] newPos);
}
