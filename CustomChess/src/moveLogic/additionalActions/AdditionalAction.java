package moveLogic.additionalActions;

import gameController.GameController;
import model.pieces.Piece;

public interface AdditionalAction {
	public void execute(GameController game, Piece piece, int[] newPos);
}
