package moveLogic.additionalActions;

import static helper.Helper.Y;

import gameController.GameController;
import model.pieces.Piece;
import player.PlayerColor;
import view.PromotedView;

public class PromotionAction implements AdditionalAction {

	private Piece promoted;
	@Override
	public void execute(GameController game, Piece piece, int[] newPos) {
		if ((piece.getColor().equals(PlayerColor.WHITE) && newPos[Y] == game.getBoard().size(Y)-1)
				|| (piece.getColor().equals(PlayerColor.BLACK) && newPos[Y] == 0)){
			if (promoted == null) {				
				PromotedView view = new PromotedView();
				promoted = view.getPromotionPiece(game.getBoard(), piece, newPos);
			}

			game.getBoard().removePiece(piece);
			game.getBoard().addPiece(promoted);
		}
	}
}
