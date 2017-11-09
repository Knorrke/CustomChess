package model.pieces.decorator;

import java.util.Collections;

import model.pieces.Piece;
import moveLogic.specialMoveConditionStrategy.OnlyToNotAttackedSquare;
import moveLogic.specialMoveConditionStrategy.SpecialMoveCondition;

public class Mighty extends AbstractDecorator {

	public Mighty(Piece wrappedPiece) {
		super(wrappedPiece);
	}

	@Override
	public void initializeMoveLogic() {
		setMoveLogic(pos -> {
			SpecialMoveCondition mc = new OnlyToNotAttackedSquare();
			if(mc.isMatchingSpecialCondition(getBoard(), this, pos)) {
				return wrappedPiece.getPossibleMoves(pos);
			} else {
				return Collections.emptyList();
			}
		});	
	}
}
