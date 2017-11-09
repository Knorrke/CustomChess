package model.pieces.decorator;

import java.util.List;

import model.pieces.Knight;
import model.pieces.Piece;
import moveLogic.MoveLogic;
import moves.Move;

public class KnightRiding extends AbstractDecorator {

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
						List<Move> possibleMoves = wrappedPiece.getPossibleMoves(pos);
						possibleMoves.addAll((new MoveLogic(getBoard(), wrappedPiece, "2,1|1,2")).getPossibleMoves(pos));
						return possibleMoves;
					}
				}
			}
			return wrappedPiece.getPossibleMoves(pos);
		});
	}
	
	private boolean isKnight(Piece piece) {
		return piece.getType().contains(Knight.class);
	}
}
