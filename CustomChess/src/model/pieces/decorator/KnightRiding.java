package model.pieces.decorator;

import model.pieces.Knight;
import model.pieces.Piece;
import moveLogic.MoveLogic;

public class KnightRiding extends AdditionalMoveAllowanceDecorator {

	public KnightRiding(Piece wrappedPiece) {
		super(wrappedPiece);
	}

	@Override
	public void initializeMoveLogic() {
		setMoveLogic(pos -> {
			int[] oldpos = wrappedPiece.getPosition();
			for(int i=-1; i<=1; i++) {
				for(int j=-1; j<=1; j++) {
					Piece adjacentPiece = getBoard().getPieceOfSquare(new int[] {oldpos[0]+i, oldpos[1]+j});
					if(adjacentPiece != null && isKnight(adjacentPiece)) {
						return (new MoveLogic(getBoard(), wrappedPiece, "2,1|1,2")).moveCorrect(pos);
					}
				}
			}
			return false;
		});
	}
	
	private boolean isKnight(Piece piece) {
		return piece.getType().contains(Knight.class);
	}
}
