package model.pieces.decorator;

import model.pieces.Piece;
import moveLogic.MoveLogic;

public class Mighty extends AdditionalMoveConditionDecorator {

	public Mighty(Piece wrappedPiece) {
		super(wrappedPiece);
	}

	@Override
	public void initializeMoveLogic() {
		setMoveLogic(new MoveLogic(getBoard(), this,"n,m;!"));
	}
}
