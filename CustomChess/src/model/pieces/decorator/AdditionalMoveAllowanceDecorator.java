package model.pieces.decorator;

import model.pieces.Piece;

public abstract class AdditionalMoveAllowanceDecorator extends AbstractDecorator{

	public AdditionalMoveAllowanceDecorator(Piece wrappedPiece) {
		super(wrappedPiece);
	}

	@Override
	public final boolean moveCorrect(int[] newPos) {
		return wrappedPiece.moveCorrect(newPos) || super.moveCorrect(newPos);
	}
	
	@Override
	/**
	 * Default implementation. No additional check.
	 * Override this to add a customized move logic
	 */
	public void initializeMoveLogic(){
		setMoveLogic(pos->false);
	}
	
}

