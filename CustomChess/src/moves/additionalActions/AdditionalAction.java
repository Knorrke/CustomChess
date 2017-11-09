package moves.additionalActions;

import model.Board;
import moves.AbstractMoveDecorator;
import moves.Move;

public abstract class AdditionalAction extends AbstractMoveDecorator {
	public AdditionalAction(Move decorated) {
		super(decorated);
	}
	
	@Override
	protected abstract void afterDecoratedExecute(Board board);
	@Override
	protected abstract void beforeDecoratedReverse(Board board);
}
