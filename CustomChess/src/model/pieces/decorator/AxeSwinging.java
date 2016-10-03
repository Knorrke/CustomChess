package model.pieces.decorator;

import model.pieces.Piece;
import moveLogic.MoveLogic;

public class AxeSwinging extends AdditionalMoveAllowanceDecorator {

	public AxeSwinging(Piece wrappedPiece) {
		super(wrappedPiece);
	}
	
	@Override
	public void initializeMoveLogic() {
		setMoveLogic(new MoveLogic(getBoard(), this,"0,2;FC|2,0;FC"));
	}
}
