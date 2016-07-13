package model.pieces.decorator;

import model.pieces.Piece;

public abstract class AdditionalMoveConditionDecorator extends AbstractDecorator{

	public AdditionalMoveConditionDecorator(Piece wrappedPiece) {
		super(wrappedPiece);
	}

	@Override
	public final boolean moveCorrect(int[] newPos) {
		return super.moveCorrect(newPos) && wrappedPiece.moveCorrect(newPos);
	}
	
	@Override
	/**
	 * Default implementation. No additional check.
	 * Override this to add a customized move logic
	 */
	public void initializeMoveLogic(){
		setMoveLogic(pos->true);
	}
	
}
