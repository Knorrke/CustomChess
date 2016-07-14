package gameController.gameConditionsStrategy;

import model.Board;
import model.pieces.Piece;

public interface GameCondition {
	public boolean isGameIntegrityEnsured(Board board, Piece piece, int[] newPos);
}
