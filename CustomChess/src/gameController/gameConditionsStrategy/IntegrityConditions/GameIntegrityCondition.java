package gameController.gameConditionsStrategy.IntegrityConditions;

import model.Board;
import model.pieces.Piece;

public interface GameIntegrityCondition {
	public boolean isGameIntegrityEnsured(Board board, Piece piece, int[] newPos);
}
