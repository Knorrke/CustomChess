package model.pieces.decorator;

import java.util.List;

import model.pieces.Piece;
import moveLogic.MoveLogic;
import moves.Move;

public class AxeSwinging extends AbstractDecorator {

	public AxeSwinging(Piece wrappedPiece) {
		super(wrappedPiece);
	}
	
	@Override
	public void initializeMoveLogic() {
		setMoveLogic(pos -> {
			List<Move> possibleMoves = wrappedPiece.getPossibleMoves(pos);
			possibleMoves.addAll((new MoveLogic(getBoard(), this,"0,2;FC|2,0;FC")).getPossibleMoves(pos));
			return possibleMoves;
		});
	}
}
