package moves;

import model.Board;

public abstract class AbstractMoveDecorator extends Move {

	private Move decorated;
	
	public AbstractMoveDecorator(Move move) {
		super(move.getPiece(), move.getTo());
		decorated = move;
	}

	@Override
	public Board execute(Board board) {
		super.execute(board);
		beforeExecute(board);
		decorated.execute(board);
		afterDecoratedExecute(board);
		return board;
	}


	protected void beforeExecute(Board board) {}
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
