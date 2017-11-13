package moves.additionalActions;

import static helper.Helper.*;

import model.Board;
import model.pieces.Piece;
import moves.Move;
import player.PlayerColor;
import view.PromotedView;

public class PromotionAction extends AdditionalAction {

	private boolean executed;
	private Piece promoted;

	public PromotionAction(Move decorated) {
		super(decorated);
	}
	
	@Override
	protected void afterDecoratedExecute(Board board) {
		if ((piece.getColor().equals(PlayerColor.WHITE) && to[Y] == board.size(Y)-1)
				|| (piece.getColor().equals(PlayerColor.BLACK) && to[Y] == 0)) {
			if (promoted == null) {				
				PromotedView view = new PromotedView();
				promoted = view.getPromotionPiece(board, piece, to);
			}

			board.removePiece(piece);
			board.addPiece(promoted);
			executed = true;
		}
	}
	@Override
	protected void beforeDecoratedReverse(Board board) {
		if(executed) {
			board.removePiece(promoted);
			board.addPiece(piece);
		}
	}
	
	@Override
	public String toString() {
		return String.format("Promotion %s %s->%s",piece.getClass().getSimpleName(),posToString(from), posToString(to));
	}
	
	public void setPromoted(Piece promoted) {
		this.promoted = promoted;
	}
}
