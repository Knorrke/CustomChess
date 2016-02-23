package model.pieces.decorator;

import model.pieces.Piece;
import moveLogic.MoveLogic;

public class Mighty extends Decorator{

	public Mighty(Piece wrappedPiece) {
		super(wrappedPiece);
	}

	@Override
	public void initializeMoveLogic() {
		setMoveLogic(new MoveLogic(getBoard(), this,"n,m;!"));
	}

	@Override
	public void initializeView() {
		setView(wrappedPiece::draw);
	}
}
