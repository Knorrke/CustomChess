package gameController.gameConditionsStrategy.EndConditions;

import gameController.GameController;
import gameController.GameState;

public interface GameEndCondition {
	GameState isEndConditionMet(GameController game);
}
