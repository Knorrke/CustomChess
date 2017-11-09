package moves.additionalActions;

import static helper.Helper.*;

import model.Board;
import model.pieces.Pawn;
import model.pieces.Piece;
import moves.Move;
import player.PlayerColor;

public class EnPassantAction extends AdditionalAction {

	private boolean executed;
	private Piece opponent;
	
	public EnPassantAction(Move decorated) {
		super(decorated);
	}

	@Override
	protected void afterDecoratedExecute(Board board) {
		PlayerColor player = piece.getColor();

		if (!piece.getType().contains(Pawn.class)) {
			return;
		}
		int[] opponentPosition = pos(to[X], player.equals(PlayerColor.WHITE) ? to[Y] - 1 : to[Y] + 1);
		opponent = board.getPieceOfSquare(opponentPosition);
		if (opponent == null || !opponent.getType().contains(Pawn.class) || opponent.getColor().equals(player)) {
			return;
		}
		
		executed = true;
		board.removePiece(opponent);
	}

	@Override
	protected void beforeDecoratedReverse(Board board) {
		if (executed) {			
			board.addPiece(opponent);
		}
	}

}
