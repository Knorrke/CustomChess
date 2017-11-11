package moves.additionalActions;

import model.Board;
import moves.Move;

public abstract class AdditionalAction extends Move {

	private Move decorated;
	
	public AdditionalAction(Move move) {
		super(move.getPiece(), move.getTo());
		decorated = move;
	}

	@Override
	public Board execute(Board board) {
		super.execute(board);
		beforeDecoratedExecute(board);
		decorated.execute(board);
		afterDecoratedExecute(board);
		return board;
	}


	protected void beforeDecoratedExecute(Board board) {}
	protected void afterDecoratedExecute(Board board) {}

	@Override
	public Board reverse(Board board) {
		if (!isExecuted()) return board;
		super.reverse(board);
		beforeDecoratedReverse(board);
		decorated.reverse(board);
		afterDecoratedReverse(board);
		return board;
	}

	protected void beforeDecoratedReverse(Board board) {}
	protected void afterDecoratedReverse(Board board) {}
}
