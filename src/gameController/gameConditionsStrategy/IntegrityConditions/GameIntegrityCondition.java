package gameController.gameConditionsStrategy.IntegrityConditions;

import model.Board;
import moves.Move;

public interface GameIntegrityCondition {
	public boolean isGameIntegrityEnsured(Board board, Move move);
}
