package moveLogic.additionalActions;

import static helper.Helper.X;
import static helper.Helper.Y;
import static helper.Helper.pos;

import gameController.GameController;
import model.Board;
import model.pieces.Pawn;
import model.pieces.Piece;
import player.PlayerColor;

public class EnPassantAction implements AdditionalAction {

	@Override
	public void execute(GameController game, Piece piece, int[] newPos) {
		Board board = game.getBoard();
		PlayerColor player = game.getCurrentPlayer();

		if (!piece.getType().contains(Pawn.class) || !piece.getColor().equals(player)) {
			return;
		}
		Piece opponent = board.getPieceOfSquare(pos(newPos[X], player.equals(PlayerColor.WHITE) ? newPos[Y] - 1 : newPos[Y] + 1));
		if (opponent == null || !opponent.getType().contains(Pawn.class) || opponent.getColor().equals(player)) {
			return;
		}
		
		board.removePiece(opponent);
	}

}
